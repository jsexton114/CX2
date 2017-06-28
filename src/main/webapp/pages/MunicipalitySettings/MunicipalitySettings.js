Application.$controller("MunicipalitySettingsPageController", ["$scope", function($scope) {
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

    $scope.UpdateLogoMunicipalitiesonSuccess = function(variable, data) {
        $scope.Widgets.picture1.picturesource = "services/cx2/Municipalities/" + data.id + "/content/logo?" + moment().valueOf();
    };
}]);


Application.$controller("dialog1Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.newMunicipalityLogo = {};
        var newLogo;

        $scope.$watch('newMunicipalityLogo.Contents', function(newVal, oldVal) {
            if (!!newVal && !!newVal) {
                var reader = new FileReader();

                reader.onload = function(e) {
                    newLogo = e.target.result;
                    $scope.Widgets.picture2.picturesource = newLogo;
                    $scope.$root.$safeApply($scope);
                };

                reader.readAsDataURL(newVal);
            }
        });

        $scope.buttonSaveClick = function($event, $isolateScope) {
            $scope.Variables.svUpdateLogo.setInput('photo', [$scope.newMunicipalityLogo.Contents]);
            $scope.Variables.svUpdateLogo.update();
            $scope.Widgets.picture1.picturesource = newLogo;
        };
    }
]);


Application.$controller("dialog2Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.newAdditionalImage = {};
        var newImage;

        $scope.$watch('newAdditionalImage.Contents', function(newVal, oldVal) {
            if (!!newVal && !!newVal) {
                var reader = new FileReader();

                reader.onload = function(e) {
                    newImage = e.target.result;
                    $scope.Widgets.picture3.picturesource = newImage;
                    $scope.$root.$safeApply($scope);
                };

                reader.readAsDataURL(newVal);
            }
        });

        $scope.buttonSaveClick = function($event, $isolateScope) {
            $scope.Variables.svAddImage.setInput('photo', [$scope.newAdditionalImage.Contents]);
            $scope.Variables.svAddImage.update();
        };
    }
]);