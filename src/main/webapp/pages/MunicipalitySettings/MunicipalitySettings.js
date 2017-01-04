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

Application.$controller("liveformUpdateLogoController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);