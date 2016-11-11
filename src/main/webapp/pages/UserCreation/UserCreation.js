Application.$controller("UserCreationPageController", ["$scope", "$timeout", function($scope, $timeout) {
    "use strict";
    $scope.newUser;

    $scope.onPageReady = function() {
        //Current Date for subscriptions
        $scope.toDay = Date.parse(new Date().toDateString());
    };
    // For verifying password match
    var proceedSubmission = false;

    //unchecked municipalities
    var selectedMunicipalites = [];

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
            // For Registering User with Role as USER for subscribed municialities
            $scope.Variables.NewUserRole.setInput({
                "roleName": "User",
                "description": "User",
                "users": data,
                "userId": data.id,
                "municipalities": selectedMunicipalites[i],
                "municipalityId": selectedMunicipalites[i].id
            });
            $scope.Variables.NewUserRole.insertRecord();
        }
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
        //Store the password field objects into variables ...
        var pass1 = document.getElementById('textPwd');
        var pass2 = document.getElementById('textRePwd');
        //Set the colorsfor use
        var matchColor = "#fff";
        var missmatchColor = "#ff6666";
        //Compare the values in the password field and the confirmation field
        //
        if (pass1.value == pass2.value) {
            pass2.style.backgroundColor = matchColor;
            proceedSubmission = true;
        } else {
            pass2.style.backgroundColor = missmatchColor;
            proceedSubmission = false;
        }


    };

}]);

Application.$controller("liveform2Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);