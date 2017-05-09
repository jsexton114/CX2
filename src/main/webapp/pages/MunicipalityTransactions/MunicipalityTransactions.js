Application.$controller("MunicipalityTransactionsPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        $scope.Widgets.datetimeEnd.timestamp = moment().valueOf();
        $scope.Widgets.datetimeStart.timestamp = moment.utc(new Date(2016, 6, 1)).valueOf();
    };

    $scope.selectTermsChange = function($event, $isolateScope, newVal, oldVal) {
        switch (newVal) {
            case "All Time":
                $scope.Widgets.datetimeEnd.timestamp = moment().valueOf();
                $scope.Widgets.datetimeStart.timestamp = moment.utc(new Date(2016, 6, 1)).valueOf();
                break;
            case "This Year":
                $scope.Widgets.datetimeEnd.timestamp = moment().valueOf();
                $scope.Widgets.datetimeStart.timestamp = moment().subtract(1, 'years').valueOf();
                break;
            case "This Half":
                $scope.Widgets.datetimeEnd.timestamp = moment().valueOf();
                $scope.Widgets.datetimeStart.timestamp = moment().subtract(6, 'months').valueOf();
                break;
            case "This Quarter":
                $scope.Widgets.datetimeEnd.timestamp = moment().valueOf();
                $scope.Widgets.datetimeStart.timestamp = moment().subtract(4, 'months').valueOf();
                break;
            case "This Month":
                $scope.Widgets.datetimeEnd.timestamp = moment().valueOf();
                $scope.Widgets.datetimeStart.timestamp = moment().subtract(1, 'months').valueOf();
                break;
            case "This Week":
                $scope.Widgets.datetimeEnd.timestamp = moment().valueOf();
                $scope.Widgets.datetimeStart.timestamp = moment().subtract(1, 'weeks').valueOf();
                break;
            case "Today":
                $scope.Widgets.datetimeEnd.timestamp = moment().valueOf();
                $scope.Widgets.datetimeStart.timestamp = moment().startOf('day').valueOf();
                break;
        }
    };

    $scope.getStartDateTime = function() {
        return !!$scope.Widgets.datetimeStart.datavalue ? moment($scope.Widgets.datetimeStart.datavalue).startOf('day').valueOf() : undefined;
    };

    $scope.getEndDateTime = function() {
        return !!$scope.Widgets.datetimeEnd.datavalue ? moment($scope.Widgets.datetimeEnd.datavalue).endOf('day').valueOf() : undefined;
    };

    $scope.btnViewReportClick = function($event, $isolateScope) {
        var municipality = $scope.Widgets.selectMunicipality.datavalue.MunicipalityName;
        var startDate = $scope.getFormattedDateForUrl($scope.Widgets.datetimeStart.datavalue);
        var endDate = $scope.getFormattedDateForUrl($scope.Widgets.datetimeEnd.datavalue);
        var viewReportUrl = 'http://67.52.205.86:8888/jinfonet/runReport.jsp?jrs.cmd=jrs.web_vw&jrs.report=%2fUSERFOLDERPATH%2fadmin%2fPaidUnpaidFeesByMunicipality.cls&jrs.catalog=%2fUSERFOLDERPATH%2fadmin%2fcx2Fallfield.cat&jrs.result_type=2&jrs.param$MunicipalityInput=' + municipality + '&jrs.param$StartDateInput=' + startDate + '&jrs.param$EndDateInput=' + endDate + '&jrs.auth_uid=admin&jrs.auth_pwd=admin';
        console.log('municipality: ' + municipality);
        console.log('viewReportUrl: ' + viewReportUrl);
        window.open(viewReportUrl);
    };

    $scope.getFormattedDateForUrl = function(dateString) {
        var d = new Date(dateString)
        var formattedDate = d.toLocaleDateString('en-US');
        return formattedDate;
    }

}]);

Application.$controller("gridTransactionsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogFeeDetailsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridFeeDetailsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridTotalsByPaymentTypeController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);