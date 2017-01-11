Application.$controller("MyProfilePageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        //Current Date for subscriptions
        $scope.toDay = Date.parse(new Date().toDateString());

    };


    // For verifying password match
    var proceedSubmission = false;

    function passwordCheck() {
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




    $scope.buttonUpdateNewClick = function($event, $isolateScope) {
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


    $scope.GetUserSubscriptionsonSuccess = function(variable, data) {
        for (let i = 0; i < data.length; i++) {
            var temp = $scope.Variables.MunicpalitiesList.dataSet;
            temp.push(data[i].municipalities);
        }

    };


    $scope.buttonAddMunicipalityClick = function($event, $isolateScope) {
        var temp = $scope.Widgets.search2.datavalue;
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

    };


    $scope.buttonRemoveClick = function($event, $isolateScope, item, currentItemWidgets) {
        // Removing the deleted municipalities
        _.remove($scope.Variables.MunicpalitiesList.dataSet, {
            id: item.id
        });
    };


    $scope.buttonUpdateSubscriptionsClick = function($event, $isolateScope) {
        var temp = $scope.Variables.MunicpalitiesList.dataSet;
        if (temp.length < 0) {
            $scope.Variables.PleaseSelectMunicipality.notify();
        } else
            $scope.Variables.DeleteSubscriptions.update();
    };


    $scope.DeleteSubscriptionsonSuccess = function(variable, data) {
        var temp = $scope.Variables.MunicpalitiesList.dataSet;
        for (var i = 0; i < temp.length; i++) {
            // For Registering User  for subscribed municialities        
            $scope.Variables.InsertSubscriptions.setInput({
                "DateSubscribed": $scope.toDay,
                "UserId": $scope.Variables.CurrentUserDetails.dataSet.id,
                "MunicipalityId": temp[i].id
            });
            $scope.Variables.InsertSubscriptions.update();
        }
    };

}]);


Application.$controller("dialog1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function(e) {
                    $scope.Widgets.picture2.picturesource = e.target.result;
                    $scope.$root.$safeApply($scope);
                };

                reader.readAsDataURL(input.files[0]);
            }
        }
        $scope.dialog1Opened = function($event, $isolateScope) {
            $('[name="dialog1"]').on('change', '.app-blob-upload', function() {
                readURL(this);
            })
        };

    }
]);

Application.$controller("liveformUpdateController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;


        $scope.photoClick = function($event, $isolateScope) {};



    }
]);

Application.$controller("pagedialogNewFormController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("grid1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("liveform2Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);



Application.$controller("usersDialogController", ["$scope",
	function($scope) {
		"use strict";
		$scope.ctrlScope = $scope;
	}
]);

Application.$controller("liveform4Controller", ["$scope",
	function($scope) {
		"use strict";
		$scope.ctrlScope = $scope;
	}
]);

Application.$controller("grid2Controller", ["$scope",
	function($scope) {
		"use strict";
		$scope.ctrlScope = $scope;
	}
]);

Application.$controller("liveform3Controller", ["$scope",
	function($scope) {
		"use strict";
		$scope.ctrlScope = $scope;
	}
]);