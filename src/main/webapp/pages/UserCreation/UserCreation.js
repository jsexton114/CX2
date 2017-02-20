Application.$controller("UserCreationPageController", ["$scope", "$timeout", function($scope, $timeout) {
    "use strict";
    $scope.newUser;

    $scope.onPageReady = function() {
        //Current Date for subscriptions
        $scope.toDay = moment().valueOf();
        console.log($scope.toDay);
        $('[name="liveform2"]').on('change', '.app-blob-upload', function() {
            readURL(this);
        })

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
            "cf": $scope.Widgets.radiosetCF.datavalue,
            "password": $scope.Widgets.textPwd.datavalue,
            "newUser": data.id,
        });
        $scope.Variables.UpdatePwdAndCF.update();
        //Looping for selected no of municipalities
        for (var i = 0; i < selectedMunicipalites.length; i++) {
            // For Registering User  for subscribed municialities        
            $scope.Variables.RegisterSubscriptions.setInput({
                "dateSubscribed": $scope.toDay,
                "users": data,
                "userId": data.id,
                "municipalities": selectedMunicipalites[i],
                "municipalityId": selectedMunicipalites[i].id
            });
            $scope.Variables.RegisterSubscriptions.insertRecord();
        }
        $scope.Variables.NewUserToLogin.navigate();

    };

    // For verifying password match
    function passwordCheck() {
        if ($scope.Widgets.textPwd.datavalue === $scope.Widgets.textRePwd.datavalue && $scope.Widgets.textPwd.datavalue != undefined && $scope.Widgets.textRePwd.datavalue != undefined) {
            return true;
        } else {
            return false;
        }

    }

    $scope.wizard1Done = function($isolateScope, steps) {
        let format = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}/
        let password = $scope.Widgets.textPwd.datavalue;
        if (!(format.test(password))) {
            $scope.Variables.PasswordRequirements.notify();
            grecaptcha.reset();
        } else {
            // check for password match and captcha
            if (passwordCheck() && (grecaptcha.getResponse() != '')) {
                $scope.Widgets.liveform2.save();
            } else if ((grecaptcha.getResponse() != '') && (passwordCheck() == false)) {
                $scope.Variables.PasswordMissMatch.notify();
                grecaptcha.reset();
            } else if ((grecaptcha.getResponse() == '') && (passwordCheck() == true)) {
                $scope.Variables.Capcha.notify();
                grecaptcha.reset();
            } else {
                $scope.Variables.PasswordsAndCaptcha.notify();
                grecaptcha.reset();
            }
        }
    };

    $scope.ButtonAddMuncipalitiesClick = function($event, $isolateScope) {
        if ($scope.Widgets.textSearchMunicipalities.datavalue != undefined) {
            var temp = $scope.Widgets.textSearchMunicipalities.datavalue;
            var data = $scope.Variables.MunicpalitiesList.dataSet;
            // checking for any municipalities in MunicpalitiesList variable, if not add from search 
            if (data.length == 0) {
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

}]);

Application.$controller("liveform2Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);