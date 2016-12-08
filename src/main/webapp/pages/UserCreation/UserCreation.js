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
        if (pass1.value == pass2.value) {
            proceedSubmission = true;
        } else {
            proceedSubmission = false;
        }
    }


    $scope.CreateUseronSuccess = function(variable, data) {
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



    $scope.wizard1Done = function($isolateScope, steps) {
        // check for password match
        if (proceedSubmission == true) {
            //$scope.Widgets.Google_reCAPTCHA1.tokenresponse
            $scope.Widgets.liveform2.save();
            proceedSubmission = false;
            $scope.Variables.NewUserToLogin.navigate();
        } else {
            $scope.Widgets.
            $scope.Variables.PasswordMissMatch.notify();
        }
    };


    $scope.textRePwdKeyup = function($event, $isolateScope) {
        passwordCheck();
    };


    $scope.textPwdKeyup = function($event, $isolateScope) {
        passwordCheck();
    };




    $scope.ButtonAddMuncipalitiesClick = function($event, $isolateScope) {
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
    };




    $scope.buttonRemoveClick = function($event, $isolateScope, item, currentItemWidgets) {
        // Removing the deleted municipalities
        _.remove($scope.Variables.MunicpalitiesList.dataSet, {
            id: item.id
        });
        // Setting for adding to subscriptions
        selectedMunicipalites = $scope.Variables.MunicpalitiesList.dataSet;
    };

}]);

Application.$controller("liveform2Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);