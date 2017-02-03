Application.$controller("ForgotPasswordPageController", ["$scope", "wmToaster", function($scope, wmToaster) {
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
    };





    $scope.getUserIDonSuccess = function(variable, data) {
        if (data.content.length === 0) {
            // if user not found in database
            wmToaster.show('error', 'ERROR', 'That email address does not exist in our system.', 6000);
            $scope.Widgets.signup.show = true;
        } else {
            $scope.Variables.sendResetPasswordbyUserID.setInput('userID', data.content[0].ID);
            $scope.Variables.sendResetPasswordbyUserID.update({}, function(data) {
                // success Toaster
                wmToaster.show('success', 'SUCCESS', 'Please check your email for password reset instructions.', 6000);
            });

        }
    };

}]);