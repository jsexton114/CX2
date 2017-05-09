Application.$controller("_editLettersPageController", ["$scope", function($scope) {
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

}]);

Application.$controller("gridLettersController", ["$scope", "$timeout",
    function($scope, $timeout) {
        "use strict";
        $scope.ctrlScope = $scope;

        function updateAndOpenDialog() {
            $scope.Variables.svGetLetterTemplate.update();
            // $scope.Widgets.dialogLetterTemplate.open();
        }

        $scope.addNewRowAction = function($event) {
            $scope.Variables.svGetLetterTemplate.setInput('letterTemplateId', undefined);
            updateAndOpenDialog();
        };

        $scope.updaterowAction = function($event, $rowData) {
            $timeout(function() {
                $scope.Variables.svGetLetterTemplate.setInput('letterTemplateId', $scope.Widgets.gridLetters.selecteditem.id);
                updateAndOpenDialog();
            });
        };

    }
]);

Application.$directive("tokenField", [function() {
    return {
        restrict: 'C',
        link: function(scope, elem, attrs) {
            elem.on('focus', function() {
                $('.token-field').removeClass('target-token-field');

                elem.addClass('target-token-field');
            });
        }
    };
}]);

Application.$controller("dialogLetterTemplateController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;

        $scope.Math = window.Math;

        $scope.insertField = function(field) {
            insertAtCursor($('textarea.target-token-field')[0], field);
        };

        function insertAtCursor(myField, myValue) { // http://stackoverflow.com/a/41426040
            if (document.selection) { // IE support
                sel = document.selection.createRange();
                sel.text = myValue;
            } else if (window.navigator.userAgent.indexOf("Edge") > -1) { // Microsoft Edge
                let startPos = myField.selectionStart;
                let endPos = myField.selectionEnd;

                myField.value = myField.value.substring(0, startPos) + myValue + myField.value.substring(endPos, myField.value.length);

                var pos = startPos + myValue.length;
                myField.setSelectionRange(pos, pos);
            } else if (myField.selectionStart || myField.selectionStart == '0') { // MOZILLA and others
                let startPos = myField.selectionStart;
                let endPos = myField.selectionEnd;
                myField.value = myField.value.substring(0, startPos) + myValue + myField.value.substring(endPos, myField.value.length);
            } else {
                myField.value += myValue;
            }

            myField.focus();
        }
    }
]);

Application.$controller("liveformLettersController", ["$scope",
    function($scope) {
        "use strict";
        $scope.ctrlScope = $scope;
    }
]);