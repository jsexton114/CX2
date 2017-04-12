Application.$controller("ViewInspectionPageController", ["$scope", function($scope) {
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

    $scope.buttonUpdateOutcomeClick = function($event, $isolateScope) {
        $scope.Variables.svSetInspectionOutcome.update();
    };

    $scope.svFieldDataonSuccess = function(variable, data) {};

    $scope.liveformFeesBeforeservicecall = function($event, $operation, $data) {
        $data.inspectionGuid = $scope.Variables.lvInspection.dataSet.data[0].inspectionGuid;
    };

    $scope.liveformFeesSuccess = function($event, $operation, $data) {
        // var comments = null;

        // if ($operation === 'insert') {
        //     comments = ('Added a ' + $filter('toCurrency')($data.amount, '$', 2) + ' ' + $data.feeType + ' fee.');
        // } else if ($operation === 'update') {
        //     comments = ('Comments', ('Updated the ' + $data.feeType + ' fee [' + $filter('toCurrency')($data.amount, '$', 2) + '].'));
        // } else if ($operation === 'delete') {
        //     comments = ('Removed the ' + $data.feeType + ' fee [' + $filter('toCurrency')($data.amount, '$', 2) + '].');
        // }

        // $scope.Variables.svRecordHistory.setInput('Comments', comments);
        // $scope.Variables.svRecordHistory.update();
    };


    $scope.lvDocumentsonSuccess = function(variable, data) {

    };


    $scope.svSetInspectionOutcomeonSuccess = function(variable, data) {
        $scope.Widgets.textareaNotes.reset();
    };

}]);

var liveformFeesScope = {};

Application.$controller("gridFeesController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.addNewRowAction = function($event) {
            liveformFeesScope.isNewFee = true;
        };

        $scope.updaterowAction = function($event, $rowData) {
            liveformFeesScope.isNewFee = false;
        };

        $scope.itemInCart = function(feeId) {
            let cartItems = $scope.Variables.svCartItemIds.dataSet.content;

            if (!cartItems) {
                return true; // By default, disable add to cart for anything if we don't have the cart data yet.
            }

            return !!_.find(cartItems, {
                feeId: feeId
            });
        };

        $scope.customRowAction = function($event, $rowData) {
            // Add to Cart Functionality
            $scope.Variables.svInsertIntoCart.setInput({
                'FeeId': $rowData.id
            });
            $scope.Variables.svInsertIntoCart.update();
        };
    }
]);

Application.$controller("dialogUploadDocumentController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
        $scope.docsToUpload = [];

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

Application.$controller("liveformFeesController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        liveformFeesScope = $scope;
    }
]);

Application.$controller("gridLocationController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);

Application.$controller("dialogParcelController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
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

Application.$controller("gridInspectionHistoryController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);