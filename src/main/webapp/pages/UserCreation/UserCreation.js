Application.$controller("UserCreationPageController", ["$scope", function($scope) {
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
        // Saving newUser Object for Further trancastions(Subscribing & Roles)
        $scope.newUser = data;
        $scope.newUserId = data.id;

    };


    $scope.wizard1Done = function($isolateScope, steps) {
        for (var i = 0; i < selectedMunicipalites.length; i++) {
            // For Registering User  for subscribed municialities        
            $scope.Variables.RegisterSubscriptions.setInput({
                "dateSubscribed": $scope.toDay,
                "users": $scope.newUser,
                "userId": $scope.newUser.id,
                "municipalities": selectedMunicipalites[i],
                "municipalityId": selectedMunicipalites[i].id
            });
            $scope.Variables.RegisterSubscriptions.insertRecord();

            // For Registering User with Role as USER for subscribed municialities
            $scope.Variables.NewUserRole.setInput({
                "roleName": "User",
                "description": "User",
                "users": $scope.newUser,
                "userId": $scope.newUser.id,
                "municipalities": selectedMunicipalites[i],
                "municipalityId": selectedMunicipalites[i].id
            });
            $scope.Variables.NewUserRole.insertRecord();


        }
        $scope.Variables.NewUserToLogin.navigate();

    };

}]);

Application.$controller("liveform2Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);