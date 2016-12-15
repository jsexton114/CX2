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

        $scope.loginVariableonSuccess = function(variable, data) {
            $scope.Variables.userDetails.setFilter('email', $("input[name='usernametext'").val());
            $scope.Variables.userDetails.update({}, function(data) {
                if (data[0].banned == true) {
                    $scope.Variables.logoutVariable.invoke({}, function(data) {
                        wmToaster.show('error', 'ERROR', 'Your account has been banned. If you have any questions, please contact support at 614-737-3743.', 5000);

                    });
                }
            })
        };

    }
]);

Application.$controller("loginFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);