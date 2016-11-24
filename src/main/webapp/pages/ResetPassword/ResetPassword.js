Application.$controller("ResetPasswordPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        /*
         * variables can be accessed through '$scope.Variables' property here
         * e.g. to get dataSet in a staticVariable named 'loggedInUser' use following script
         * $scope.Variables.loggedInUser.getData()
         *
         * widgets can be accessed through '$scope.Widgets' property here
         * e.g. to get value of text widget named 'username' use following script
         * '$scope.Widgets.username.datavalue'
         */
        $scope.token = $scope.pageParams.token;
        if ($scope.pageParams.token) {
            $scope.Variables.ValidateToken.setInput('token', $scope.pageParams.token);
            $scope.Variables.ValidateToken.update({}, function(data) {

            });
        } else {

        }
    };


    $scope.resetPasswordonSuccess = function(variable, data) {
        $scope.Variables.deleteToken.setInput('token', $scope.pageParams.token);
        $scope.Variables.deleteToken.update({}, function(data) {
            $scope.Variables.NewUserToLogin.navigate();
        });

    };

}]);