Application.$controller("ResetPasswordPageController", ["$scope", "wmToaster", function($scope, wmToaster) {
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




    $scope.form1Beforesubmit = function($event, $isolateScope, $data) {
        if ($isolateScope.dataoutput.newPassword === $isolateScope.dataoutput.retypePassword) {
            $scope.Variables.resetPassword.setInput('token', $scope.pageParams.token);
            $isolateScope.dataoutput.token = $scope.pageParams.token;
            return true;
        } else {
            wmToaster.show('info', 'INFO', 'Password Mismatch Please Re Enter Password ', 5000);
            $isolateScope.reset();
            return false;
        }
    };




    $scope.button3Click = function($event, $isolateScope) {
        $isolateScope.reset();
    };


    $scope.resetPasswordonSuccess = function(variable, data) {
        if (data == 0) {
            wmToaster.show('info', 'INFO', 'Unable to update Password Please try Again or Contact us ', 5000);
            return false;
        } else {
            $scope.Variables.deleteToken.setInput('token', $scope.token);
            $scope.Variables.deleteToken.update({}, function(data) {
                $scope.Variables.NewUserToLogin.navigate();
            });

        }
    };

}]);