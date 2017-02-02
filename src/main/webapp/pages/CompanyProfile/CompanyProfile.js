Application.$controller("CompanyProfilePageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {

        //current date
        $scope.Variables.Today.dataSet.dataValue = Date.parse(new Date().toDateString());
        $scope.today = moment().valueOf();
        // if ($scope.pageParams.companyID != null) {
        //     $scope.Variables.CurrentVendorObj.setInput('id', $scope.pageParams.companyID);
        //     $scope.Variables.CurrentVendorObj.update();
        // } else {
        //     $scope.Variables.CurrentVendorObj.setInput('id', $scope.Widgets.selectSelectCompany.datavalue.ID);
        //     $scope.Variables.CurrentVendorObj.update();
        // }

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
        $data.lastUpdated = $scope.Variables.Today.dataSet.dataValue;
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