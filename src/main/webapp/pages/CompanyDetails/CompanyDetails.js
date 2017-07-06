Application.$controller("CompanyDetailsPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {

    };
    $scope.CurrentVendorObjonBeforeDatasetReady = function(variable, data) {
        _.forEach(data, function(row) {
            row.coi = row.coi === null ? null : '';

        });
    };

    $scope.liveform1Beforeservicecall = function($event, $operation, $data) {
        $data.lastUpdated = new Date().getTime();
    };


    $scope.tabpaneDocumentsSelect = function($event, $isolateScope) {

        if ($scope.pageParams.municipalityId != undefined) {
            $scope.Variables.lvDocuments.setFilter({
                'municipalityId': $scope.pageParams.municipalityId
            });
        } else {
            $scope.Variables.lvDocuments.setFilter({
                'municipalityId': undefined
            });
        }
        $scope.Variables.lvDocuments.setFilter({
            'vendorId': $scope.Variables.CurrentVendorObj.dataSet.data[0].id
        });
        $scope.Variables.lvDocuments.update();
    };


    $scope.svIsCurrentVendorAdminonSuccess = function(variable, data) {
        $scope.Variables.stvIsCurrentVendorAdmin.dataSet.dataValue = (data.isCurrentVendorAdmin == 1) ? true : false;
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

Application.$controller("gridDocumentsController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.updaterowAction = function($event, $rowData) {
            window.open('resources/leadTools/index.html?docId=' + $rowData.id);
        };
    }
]);

Application.$controller("dialogUploadDocumentController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.dialogUploadDocumentClose = function($event, $isolateScope) {
            $scope.docsToUpload = [];
        };

        $scope.buttonUploadFileClick = function($event, $isolateScope) {
            var filesContents = [];

            $scope.docsToUpload.forEach(function(doc, index) {
                filesContents.push(doc.Contents);
            });
            $scope.Variables.svUploadDocument.setInput('files', filesContents);
            $scope.Variables.svUploadDocument.update();
            $scope.docsToUpload = [];
            $scope.Widgets.dialogUploadDocument.close();
        };

    }
]);

Application.$controller("confirmdialogDeleteDocumentController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("grid3Controller", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("gridVendorUsersController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogUserProfileController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);