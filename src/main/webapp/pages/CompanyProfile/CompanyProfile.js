Application.$controller("CompanyProfilePageController", ["$scope", "_", function($scope, _) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {

        //current date
        $scope._ = _;
        $scope.Variables.BreadCrum.dataSet[0].link += ('?companyId=' + $scope.pageParams.companyID);

        // $('[name="liveform1"]').on('change', '.app-blob-upload', function() {
        //     debugger;
        //     readURL(this);
        // })

        // function readURL(input) {
        //     if (input.files && input.files[0]) {
        //         var pdffile_url = URL.createObjectURL(input.files[0]);
        //         debugger;


        //     }

        // }
    };

    $scope.CurrentVendorObjonBeforeDatasetReady = function(variable, data) {
        _.forEach(data, function(row) {
            row.coi = row.coi === null ? null : '';

        });
    };

    $scope.liveform1Beforeservicecall = function($event, $operation, $data) {
        $data.lastUpdated = new Date().getTime();
    };

}]);


Application.$controller("liveform1Controller", ["$scope",
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
        $scope.today = moment().valueOf();
        //$scope.minExpiration;

        $scope.issuedDateChange = function($event, $isolateScope, newVal, oldVal) {
            // $scope.Variables.minExpiration.dataSet.dataValue;
            // var selectedDate = moment(newVal);
            // var currentDate = moment();
            // if (selectedDate > currentDate) {
            //     $scope.Variables.minExpiration.dataSet.dataValue = selectedDate;

            // } else if (selectedDate < currentDate) {
            //     $scope.Variables.minExpiration.dataSet.dataValue = currentDate;

            // } else {
            //     $scope.Variables.minExpiration.dataSet.dataValue = currentDate;

            // }
        };

    }
]);