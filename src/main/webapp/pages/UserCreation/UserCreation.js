Application.$controller("UserCreationPageController", ["$scope", "$timeout", function($scope, $timeout) {
    "use strict";
    $scope.newUser;

    $scope.onPageReady = function() {
        //Current Date for subscriptions
        $scope.toDay = Date.parse(new Date().toDateString());
    };

    //unchecked municipalities
    var selectedMunicipalites = [];

    // For verifying password match
    var proceedSubmission = false;

    function passwordCheck() {
        //Store the password field objects into variables ...
        var pass1 = document.getElementById('textPwd');
        var pass2 = document.getElementById('textRePwd');
        //Set the colorsfor use
        var matchColor = "#fff";
        var missmatchColor = "#ff66";
        //Compare the values in the password field and the confirmation field
        //
        if (pass1.value == pass2.value) {
            pass2.style.backgroundColor = matchColor;
            proceedSubmission = true;
        } else {
            pass2.style.backgroundColor = missmatchColor;
            proceedSubmission = false;
        }
    }


    $scope.checkboxsetMunicipalitesChange = function($event, $isolateScope, newVal, oldVal) {
        selectedMunicipalites = newVal;
    };

    $scope.CreateUseronSuccess = function(variable, data) {
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
        // For Registering User with Role as USER for first municiality
        $scope.Variables.NewUserRole.setInput({
            "roleName": "User",
            "description": "User",
            "users": data,
            "userId": data.id,
            "municipalities": selectedMunicipalites[0],
            "municipalityId": selectedMunicipalites[0].id
        });
        $scope.Variables.NewUserRole.insertRecord();

        // For updating the password
        $scope.Variables.UpdatePwdAndCF.setInput({
            "cf": $scope.Widgets.radiosetCF.datavalue,
            "password": $scope.Widgets.textPwd.datavalue,
            "newUser": data.id,
        });
        $scope.Variables.UpdatePwdAndCF.update();
        $scope.Variables.NewUserToLogin.navigate();

    };



    $scope.wizard1Done = function($isolateScope, steps) {
        // check for password match
        if (proceedSubmission == true) {
            $scope.Widgets.liveform2.save();
        } else {
            $scope.Variables.PasswordMissMatch.notify();
        }
    };


    $scope.textRePwdKeyup = function($event, $isolateScope) {
        passwordCheck();
    };


    $scope.textPwdKeyup = function($event, $isolateScope) {
        passwordCheck();
    };


    $scope.GetMunicipalitiesonSuccess = function(variable, data) {
        if (data.length == 0) {
            $scope.Widgets.labelNoMunicipalities.show = true;
            $scope.Widgets.compositeSelectMunicipalites.show = false;
        } else {
            $scope.Widgets.labelNoMunicipalities.show = false;
            $scope.Widgets.compositeSelectMunicipalites.show = true;
        }
    };

}]);

Application.$controller("liveform2Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);