Application.$controller("FormsPageController", ["$scope", "$timeout", "$location", "$filter", function($scope, $timeout, $location, $filter) {
    "use strict";

    var currentBreadCrumb = null;
    var openClosedFormBreadCrumb = {};

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        var breadCrumbs = $scope.Variables.BreadCrumb.dataSet;
        currentBreadCrumb = breadCrumbs[breadCrumbs.length - 1];
        currentBreadCrumb.link += $scope.pageParams.FormGUID;
        openClosedFormBreadCrumb = breadCrumbs[1];
    };

    function setFormStatusProgressValue(newStatusId) {
        var statusListData = $scope.Variables.FormStatus.dataSet.data;
        var currentStatusId = newStatusId || $scope.Variables.CurrentForm.dataSet.data[0].formStatusId;
        $scope.currentStatusId = currentStatusId;

        if (!!statusListData && !!currentStatusId) {
            if (currentStatus.considerClosed === true) {
                openClosedFormBreadCrumb.label = 'Closed Forms';
                openClosedFormBreadCrumb.link = '#/UserClosedForms';
            } else {
                openClosedFormBreadCrumb.label = 'Open Forms';
                openClosedFormBreadCrumb.link = '#/UserOpenForms';
            }
        }
    }

    $scope.lvFormTypeonSuccess = function(variable, data) {
        currentBreadCrumb.label = data[0].formType;
    };
}]);