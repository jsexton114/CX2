Application.$controller("LoginPageController", ["$scope", "wmToaster",
    function($scope, wmToaster) {
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
    }

]);

Application.$controller("loginFormController", ["$scope", "wmToaster",
    function($scope, wmToaster) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.loginFormSubmit = function($event, $isolateScope) {
            $scope.Variables.bannedUser.setInput('emailid', $("input[name='usernametext'").val());
            $scope.Variables.bannedUser.update({}, function(data) {

                if (data.content[0].Banned === true) {
                    wmToaster.show('error', 'ERROR', 'Your account has been banned. If you have any questions, please contact support at 614-737-3743.', 5000);
                } else {
                    $scope.Variables.loginVariable.invoke();
                }

            })

        };

    }
]);