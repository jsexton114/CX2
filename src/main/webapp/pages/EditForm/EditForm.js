Application.$controller("EditFormPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {

    };

    // Setting existing categories to CategoriesList
    $scope.FormCategoriesonSuccess = function(variable, data) {
        for (let i = 0; i < data.length; i++) {
            var temp = $scope.Variables.CategoriesList.dataSet;
            temp.push(data[i].formCategories);
        }
    };


    $scope.buttonRemoveClick = function($event, $isolateScope, item, currentItemWidgets) {
        // Removing the deleted municipalities
        _.remove($scope.Variables.CategoriesList.dataSet, {
            id: item.id
        });
    };


    $scope.buttonUpdateClick = function($event, $isolateScope) {
        var temp = $scope.Variables.CategoriesList.dataSet;
        if (temp.length < 0) {
            $scope.Variables.PleaseSelectCategory.notify();
        } else
            $scope.Variables.DeleteCategoryMapping.update();
    };


    $scope.buttonAddClick = function($event, $isolateScope) {
        var temp = $scope.Widgets.searchCategory.datavalue;
        var data = $scope.Variables.CategoriesList.dataSet;
        // checking for any Categories in CategoriesList variable, if not add from search 
        if (data.length == 0) {
            data.push(temp);
        } else {
            // checking if adding value already exist in CategoriesList variable 
            var exist = 0;
            for (let i = 0; i < data.length; i++) {
                if (temp.id == data[i].id)
                    exist = 1;
            }
            // If already added then notify user else push to CategoriesList variable
            if (exist == 1)
                $scope.Variables.CategoryExist.notify();
            else
                data.push(temp);

        }
    };


    $scope.DeleteCategoryMappingonSuccess = function(variable, data) {
        var temp = $scope.Variables.CategoriesList.dataSet;
        for (var i = 0; i < temp.length; i++) {
            // For setting Form Categories        
            $scope.Variables.InsertCategoryMapping.setInput({
                "FormTypeId": $scope.pageParams.FormTypeId,
                "FormCategoryId": temp[i].id,

            });
            $scope.Variables.InsertCategoryMapping.update();
        }
    };

}]);


Application.$controller("liveformUpdateFormTypeController", ["$scope",
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
    }
]);