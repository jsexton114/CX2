Application.$controller("UserCreationPageController", ["$scope", "$timeout", function($scope, $timeout) {
    "use strict";
    $scope.newUser;

    $scope.onPageReady = function() {
        //Current Date for subscriptions
        $scope.toDay = Date.parse(new Date().toDateString());
    };


    var selectedMunicipalites = [];
    $scope.checkboxsetMunicipalitesChange = function($event, $isolateScope, newVal, oldVal) {
        selectedMunicipalites = newVal;
    };

    $scope.CreateUseronSuccess = function(variable, data) {
        debugger
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


}]);

Application.$controller("liveform2Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);