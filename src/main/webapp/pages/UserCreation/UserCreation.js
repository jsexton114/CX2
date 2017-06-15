Application.$controller("UserCreationPageController", ["$scope", "$timeout", "pwordValidator", "vcRecaptchaService", function($scope, $timeout, pwordValidator, vcRecaptchaService) {
    "use strict";
    $scope.newUser;

    $scope.onPageReady = function() {
        //Current Date 
        $scope.Variables.Today.dataSet.dataValue = Date.parse(new Date().toDateString());

        $('[name="liveform2"]').on('change', '.app-blob-upload', function() {
            readURL(this);
        });

        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function(e) {
                    $scope.Widgets.picture1.picturesource = e.target.result;
                    $scope.$root.$safeApply($scope);
                };
                reader.readAsDataURL(input.files[0]);
            }
        }

        $('body').on('click', 'a.eulaLink', function($event) {
            $event.preventDefault();
            $scope.Widgets.dialogEULA.open();
        });
    };

    //unchecked municipalities
    var selectedMunicipalites = [];

    $scope.CreateUseronSuccess = function(variable, data) {
        //Setting user preference
        $scope.Variables.InsertUserPreference.setInput({
            'UserId': data.id,
            'PreferenceId': 1
        });
        $scope.Variables.InsertUserPreference.update();

        // Sending welcome mail
        $scope.Variables.welcomeEmail.setInput('username', data.firstName);
        $scope.Variables.welcomeEmail.setInput('recipient', data.email);
        $scope.Variables.welcomeEmail.update({}, function(data) {});
        // For Registering User with Role as USER for first municiality
        $scope.Variables.NewUserRole.setInput({
            "roleName": "User",
            "description": "User",
            "users": data,
            "userId": data.id,
            "municipalities": selectedMunicipalites[0],
            "municipalityId": selectedMunicipalites[0].id
        });

        $scope.Variables.NewUserRole.insertRecord({}, function(data) {

        });

        // For updating the password 
        $scope.Variables.UpdatePwdAndCF.setInput({
            "cf": "Immediately",
            // "cf": $scope.Widgets.radiosetCF.datavalue,
            "password": $scope.Widgets.textPwd.datavalue,
            "newUser": data.id,
        });
        $scope.Variables.UpdatePwdAndCF.update();
        //Looping for selected no of municipalities
        for (var i = 0; i < selectedMunicipalites.length; i++) {
            // For Registering User  for subscribed municialities        
            $scope.Variables.RegisterSubscriptions.setInput({
                "dateSubscribed": moment().valueOf(),
                "users": data,
                "userId": data.id,
                "municipalities": selectedMunicipalites[i],
                "municipalityId": selectedMunicipalites[i].id
            });
            $scope.Variables.RegisterSubscriptions.insertRecord();
        }
        if ($scope.Widgets.radiosetContractor.datavalue == "Yes") {
            if ($scope.Widgets.checkboxIsCompanyListed.datavalue) {

            } else { //
            }
        } else {
            $scope.Variables.NewUserToLogin.navigate();
            window.location.reload();
        }
    };

    $scope.wizard1Done = function($isolateScope, steps) {
        let pwordValidation = pwordValidator.validate($scope.Widgets.textPwd.datavalue, $scope.Widgets.textRePwd.datavalue);

        if (pwordValidation === -2) {
            $scope.Variables.PasswordRequirements.notify();
            vcRecaptchaService.reload();
        } else if (pwordValidation === true && (vcRecaptchaService.getResponse() !== '')) { // check for password match and captcha
            $scope.Widgets.liveform2.save();
        } else if ((vcRecaptchaService.getResponse() !== '') && pwordValidation !== true) {
            $scope.Variables.PasswordMissMatch.notify();
            vcRecaptchaService.reload();
        } else if ((vcRecaptchaService.getResponse() === '') && pwordValidation === true) {
            $scope.Variables.Capcha.notify();
            vcRecaptchaService.reload();
        } else {
            $scope.Variables.PasswordsAndCaptcha.notify();
            vcRecaptchaService.reload();
        }
    };

    $scope.buttonRemoveClick = function($event, $isolateScope, item, currentItemWidgets) {
        // Removing the deleted municipalities
        _.remove($scope.Variables.MunicpalitiesList.dataSet, {
            id: item.id
        });
        // Setting for adding to subscriptions
        selectedMunicipalites = $scope.Variables.MunicpalitiesList.dataSet;
    };

    $scope.liveform2Success = function($event, $operation, $data) {
        $scope.Variables.NewUserToLogin.navigate();
    };

    var pwdKeypressTimeout = null;

    $scope.textPwdChange = function($event, $isolateScope, newVal, oldVal) {
        let pwordValidation = pwordValidator.validate(newVal, newVal);

        $scope.pwordRequirementsMet = (pwordValidation !== -2);

        let rePwdValue = $scope.Widgets.textRePwd.datavalue;
        if (!!rePwdValue) {
            $scope.textRePwdChange($event, $isolateScope, rePwdValue);
        }
    };


    $scope.textRePwdChange = function($event, $isolateScope, newVal, oldVal) {
        let pwordValidation = pwordValidator.validate($scope.Widgets.textPwd.datavalue, newVal);

        $scope.passwordsDontMatch = (pwordValidation === -3);
    };

    $scope.textSearchMunicipalitiesSelect = function($event, $isolateScope, selectedValue) {
        if (!!$scope.Widgets.textSearchMunicipalities.datavalue) {
            var temp = $scope.Widgets.textSearchMunicipalities.datavalue;
            var data = $scope.Variables.MunicpalitiesList.dataSet;
            // checking for any municipalities in MunicpalitiesList variable, if not add from search 
            if (!data.length) {
                data.push(temp);
            } else {
                // checking if adding value already exist in MunicpalitiesList variable 
                var exist = 0;
                for (let i = 0; i < data.length; i++) {
                    if (temp.id == data[i].id)
                        exist = 1;
                }
                // If already added then notify user else push to MunicpalitiesList variable
                if (exist == 1)
                    $scope.Variables.MunicipalityExist.notify();
                else
                    data.push(temp);

            }
            // Setting for adding to subscriptions
            selectedMunicipalites = $scope.Variables.MunicpalitiesList.dataSet;
        } else {
            $scope.Variables.NoMuncipalitiesAdded.notify();
        }
        $scope.Widgets.textSearchMunicipalities.reset();
    };


    $scope.radiosetContractorChange = function($event, $isolateScope, newVal, oldVal) {
        if (newVal == "Yes") {
            $scope.Widgets.checkboxIsCompanyListed.datavalue = false;
            $scope.Widgets.searchCompany.datavalue = undefined;
        } else { //do nothing
        }
    };

}]);

Application.$controller("liveform2Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogEULAController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.button3Click = function($event, $isolateScope) {
            $scope.Widgets.checkboxAcceptEULA.datavalue = true;
        };

    }
]);

Application.$controller("liveformVendorController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveformVendorApplicationController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);