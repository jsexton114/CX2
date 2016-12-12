Application.$controller("NewFormPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {

    };


    // For navigation to selected form page
    $scope.buttonCreateClick = function($event, $isolateScope) {
        var pageName = $scope.Widgets.selectForm.datavalue.PageName;
        $scope.Variables[pageName].dataSet.FormId = $scope.Widgets.selectForm.datavalue.ID;
        $scope.Variables[pageName].dataSet.MunicipalityId = $scope.Widgets.selectMunicipality.datavalue.ID;
        $scope.Variables[pageName].navigate();

    };

}]);