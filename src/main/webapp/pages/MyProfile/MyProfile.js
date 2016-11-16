Application.$controller("MyProfilePageController", ["$scope", function($scope) {
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


    // For verifying password match
    var proceedSubmission = false;

    function passwordCheck() {
        debugger
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
    }



    $scope.buttonUpdateClick = function($event, $isolateScope) {
        // check for password match
        if (proceedSubmission == true) {
            $scope.Variables.UpdateNewPassword.update();
        } else {
            $scope.Variables.PasswordMissMatch.notify();
        }
    };






    $scope.textRePwdKeyup = function($event, $isolateScope) {
        passwordCheck();
    };


    $scope.textPwdKeyup = function($event, $isolateScope) {
        passwordCheck();
    };


    $scope.Cx2UsersDataonSuccess = function(variable, data) {
        $scope.Widgets.picture1.picturesource = "services/cx2/Users/" + data.id + "/content/photo?ts=" + moment().valueOf();
    };

}]);


Application.$controller("dialog1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveformUpdateController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);