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
            wmToaster.show('error', 'ERROR', 'The passwords do not match. Please try again', 5000);
            $isolateScope.reset();
            return false;
        }
    };





    $scope.resetPasswordonSuccess = function(variable, data) {
        if (data == 0) {
            wmToaster.show('error', 'ERROR', 'Your password reset link has expired. Please generate a new link or contact Support at 614-737-3743 for further assistance', 6000);
            return false;
        } else {
            $scope.Variables.deleteToken.setInput('token', $scope.token);
            $scope.Variables.deleteToken.update({}, function(data) {
                $scope.Variables.NewUserToLogin.navigate();
            });

        }
    };

}]);