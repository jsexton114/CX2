Application.requires.push('vcRecaptcha');

Application.config(['vcRecaptchaServiceProvider', function(vcRecaptchaServiceProvider) {
    vcRecaptchaServiceProvider.setSiteKey("6LcNeBUUAAAAAE7ACQkzLMRlZ4kHI9-ebthTlQ61");
}]);

Application.run(function($rootScope) {
    "use strict";
    /* perform any action on the variables within this block(on-page-load) */
    $rootScope.onAppVariablesReady = function() {
        /*
         * variables can be accessed through '$rootScope.Variables' property here
         * e.g. $rootScope.Variables.staticVariable1.getData()
         */

    };

    /* perform any action on session timeout here, e.g clearing some data, etc */
    $rootScope.onSessionTimeout = function() {
        /*
         * NOTE:
         * On re-login after session timeout:
         * if the same user logs in(through login dialog), app will retain its state
         * if a different user logs in, app will be reloaded and user is redirected to respective landing page configured in Security.
         */
    };

    $rootScope.StandardUserMunicipalitesonSuccess = function(variable, data) {
        // For count badge in left nav
        $rootScope.Variables.NoOfMunicipalitiesForUser.dataSet.dataValue = data.totalElements;
    };

});

Application.factory('_', ['$window', function($window) {
    "use strict";
    return $window._;
}]);

Application.factory('pwordValidator', [function() {
    "use strict";
    return {
        validate: function(pword, rePword) {
            if (!pword && !rePword) {
                return -1;
            } else if (pword === rePword) {
                let format = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}/;
                if (!(format.test(pword))) {
                    return -2;
                } else {
                    return true;
                }
            } else {
                return -3;
            }
        }
    };
}]);

Application.directive('cxCheckboxSet', [function() {
    "use strict";
    return {
        restrict: 'E',
        replace: true,
        template: '<div><div ng-repeat="item in choiceList" class="app-checkbox checkbox" style="margin-top: 0" role="input" name="fieldValue{{formField.fieldName+item}}">' + '    <label ng-class="{unchecked: !items[item]}" style="padding-left: 0" role="button">' + '        <input type="checkbox" ng-model="items[item]" ng-change="changeChoice(items[item], item)"><span class="caption ng-binding" ng-bind-html="item"></span>' + '    </label>' + '</div>' + '<input type="hidden" name="{{fieldName}}" ng-value="model" /></div>',
        scope: {
            choices: '=',
            model: '=',
            fieldName: '='
        },
        link: function(scope, elem, attrs) {
            scope.items = {};
            scope.choiceList = scope.choices.split(',');
            scope.chosenItems = [];

            scope.choiceList.forEach(function(choiceItem, index) {
                scope.items[choiceItem] = !!scope.model && scope.model.indexOf(choiceItem) > -1;
                if (scope.items[choiceItem] === true) {
                    scope.chosenItems.push(choiceItem);
                }
            });

            scope.changeChoice = function(value, choice) {
                if (value === true) {
                    scope.chosenItems.push(choice);
                } else {
                    scope.chosenItems.splice(scope.chosenItems.indexOf(choice), 1);
                }

                scope.model = scope.chosenItems.join(',');
            };
        }
    };
}]);

Application.directive('datetimePicker', ['uibDateParser', function(uibDateParser) {
    "use strict";
    return {
        restrict: 'E',
        replace: true,
        template: '<p class="input-group app-date" ng-style="{\'max-width\': (!time ? \'13em\' : \'21em\')}">' + '  <input type="text" class="form-control" name="{{name}}" uib-datepicker-popup="{{format}}" datepicker-popup-template-url="{{time===true ? \'uib/template/datepicker/datetimepickerPopup.html\' : \'uib/template/datepicker/datepickerPopup.html\'}}" ng-model="dateObj" open-on-focus="false" is-open="dateTimePopup.opened" close-on-date-selection="false" />' + '  <span class="input-group-btn">' + '    <button type="button" class="btn btn-default btn-date" ng-click="openDateTimePicker()"><i class="app-icon wi wi-calendar"></i> <i ng-if="!!time" class="app-icon wi wi-clock"></i></button>' + '  </span>' + '</p>',
        scope: {
            date: '=',
            format: '&',
            time: '='
        },
        link: function(scope, elem, attrs) {
            scope.dateTimePopup = {
                opened: false
            };

            scope.name = scope.$root.$eval(attrs.name);

            scope.$watch('date', function(newValue, oldValue) {
                if (newValue !== null) {
                    if (typeof scope.date === 'number') {
                        scope.dateObj = new Date(scope.date);
                    } else {
                        scope.dateObj = uibDateParser.parse(scope.date, "yyyy-MM-dd");
                    }
                }
            });

            scope.$watch('dateObj', function(newValue, oldValue) {
                if (!newValue) {
                    scope.date = null;
                } else {
                    scope.date = newValue.getTime();
                }
            });

            scope.openDateTimePicker = function() {
                scope.dateTimePopup.opened = true;
            };

            scope.time = (attrs.time === 'true');

            scope.format = attrs.format || 'MM-dd-yyyy';

            if (scope.time === true) {
                scope.format += ' hh:mm a';
            }
        }
    };
}]);

Application.directive('dynamicFormFields', function() {
    "use strict";
    return {
        restrict: 'E',
        scope: {
            formFieldList: '=',
            formData: '='
        },
        replace: true,
        templateUrl: 'dynamicFormFields.html'
    };
});

Application.directive('ngFileModel', function() {
    "use strict";
    return {
        restrict: 'A',
        scope: {
            ngFileModel: '='
        },
        link: function(scope, elem, attrs) {
            function getFileModel(name, type, fileToAdd) {
                return {
                    Filename: name,
                    Mimetype: type,
                    Contents: fileToAdd
                };
            }

            elem.bind('change', function() {
                scope.ngFileModel = scope.ngFileModel || (!!attrs.multiple ? [] : {});

                if (!!attrs.multiple) {
                    scope.ngFileModel = [];
                    for (let i = 0; i < elem[0].files.length; i++) {
                        let fileToAdd = elem[0].files[i];
                        scope.ngFileModel.push(getFileModel(
                            fileToAdd.name,
                            fileToAdd.type,
                            fileToAdd
                        ));
                    }
                } else {
                    let fileToAdd = elem[0].files[0];
                    scope.ngFileModel = getFileModel(
                        fileToAdd.name,
                        fileToAdd.type,
                        fileToAdd
                    );
                }

                scope.$apply();
            });
        }
    };

});

Application.run(["$templateCache", function($templateCache) {
    "use strict";
    $templateCache.put("uib/template/datepicker/datetimepickerPopup.html",
        "<ul role=\"presentation\" class=\"uib-datepicker-popup dropdown-menu uib-position-measure\" dropdown-nested ng-if=\"isOpen\" ng-keydown=\"keydown($event)\" ng-click=\"$event.stopPropagation()\">\n" +
        "  <li ng-transclude></li>\n" +
        "  <div uib-timepicker ng-model=\"$parent.$parent.dateObj\" hour-step=\"1\" minute-step=\"1\" show-meridian=\"true\"></div>\n" +
        "  <li ng-if=\"showButtonBar\" class=\"uib-button-bar\">\n" +
        "    <span class=\"btn-group pull-left\">\n" +
        "      <button type=\"button\" class=\"btn btn-sm btn-info uib-datepicker-current\" ng-click=\"select('today', $event)\" ng-disabled=\"isDisabled('today')\">{{ getText('current') }}</button>\n" +
        "      <button type=\"button\" class=\"btn btn-sm btn-secondary uib-clear\" ng-click=\"select(null, $event)\">{{ getText('clear') }}</button>\n" +
        "    </span>\n" +
        "    <button type=\"button\" class=\"btn btn-sm btn-primary pull-right uib-close\" ng-click=\"close($event)\">{{ getText('close') }}</button>\n" +
        "  </li>\n" +
        "</ul>\n" +
        "");

    $templateCache.put("uib/template/datepicker/datepickerPopup.html",
        "<ul role=\"presentation\" class=\"uib-datepicker-popup dropdown-menu uib-position-measure\" dropdown-nested ng-if=\"isOpen\" ng-keydown=\"keydown($event)\" ng-click=\"$event.stopPropagation()\">\n" +
        "  <li ng-transclude></li>\n" +
        "  <li ng-if=\"showButtonBar\" class=\"uib-button-bar\">\n" +
        "    <span class=\"btn-group pull-left\">\n" +
        "      <button type=\"button\" class=\"btn btn-sm btn-info uib-datepicker-current\" ng-click=\"select('today', $event)\" ng-disabled=\"isDisabled('today')\">{{ getText('current') }}</button>\n" +
        "      <button type=\"button\" class=\"btn btn-sm btn-secondary uib-clear\" ng-click=\"select(null, $event)\">{{ getText('clear') }}</button>\n" +
        "    </span>\n" +
        "    <button type=\"button\" class=\"btn btn-sm btn-primary pull-right uib-close\" ng-click=\"close($event)\">{{ getText('close') }}</button>\n" +
        "  </li>\n" +
        "</ul>\n" +
        "");

    $templateCache.put("dynamicFormFields.html",
        "<div class=\"app-grid-layout clearfix\" name=\"layoutgridDynamicFields\">\n" +
        "<wm-gridrow ng-repeat=\"formField in formFieldList\" name=\"fieldGridrow{{$index}}\">\n" +
        "    <div class=\"app-grid-column col-sm-12\" name=\"fieldGridcolumn{{$index}}\" ng-class=\"{'panel-primary': formField.formFieldTypes.label == 'Header'}\">\n" +
        "        <hr ng-if=\"formField.formFieldTypes.label == 'Horizontal Line'\">\n" +
        "        <p ng-if=\"formField.formFieldTypes.label == 'Instruction Text'\" ng-bind-html=\"formField.defaultValue\"></p>\n" +
        "        <h3 class=\"panel-heading\" ng-if=\"formField.formFieldTypes.label == 'Header'\" ng-bind-html=\"formField.defaultValue\"></h3>\n\n" +
        "        <wm-composite ng-if=\"!!formField.formFieldTypes.sqlType\" name=\"fieldComposite{{$index}}\">\n" +
        "            <label class=\"col-md-12 control-label app-label\" name=\"fieldLabel{{$index}}\" style=\"margin-top: 6px\" ng-class=\"{required: formField.required}\"> {{formField.label}} <i ng-if=\"!!formField.helpText &amp;&amp; formField.helpText != ''\" class=\"wi wi-help fa-lg\" title=\"{{formField.helpText}}\"></i>\n" +
        "            </label>\n\n" +
        "            <wm-container class=\"col-md-12\" name=\"container3\">\n" +
        "                <input ng-if=\"formField.formFieldTypes.label == 'Text'\" class=\"form-control app-textbox\" apply-styles=\"\" role=\"input\" focus-target=\"\" title=\"\" ng-model=\"formData[formField.fieldName]\" pattern=\".*\" accesskey=\"\" ng-model-options=\"{ updateOn:'blur change', debounce: 0 ,allowInvalid: false}\"" +
        "                name=\"fieldValue{{formField.fieldName}}\" placeholder=\"Please enter {{formField.label}}\" type=\"text\" ng-required=\"formField.required\">\n" +
        "                <input class=\"form-control app-textbox\" apply-styles=\"\" role=\"input\" focus-target=\"\" title=\"\" ng-model=\"formData[formField.fieldName]\" pattern=\".*\" accesskey=\"\" ng-model-options=\"{ updateOn:'blur change', debounce: 0 ,allowInvalid: false}\" ng-if=\"formField.formFieldTypes.label == 'Number'\" name=\"fieldValue{{formField.fieldName}}\" placeholder=\"Please enter {{formField.label}}\" type=\"number\" ng-required=\"formField.required\">\n" +
        "                <span ng-if=\"formField.formFieldTypes.label == 'Currency'\" class=\"clearfix\"><input class=\"form-control app-textbox\" apply-styles=\"\" role=\"input\" focus-target=\"\" title=\"\" ng-model=\"formData[formField.fieldName]\" pattern=\".*\" accesskey=\"\" ng-model-options=\"{ updateOn:'blur change', debounce: 0 ,allowInvalid: false}\" name=\"fieldValue{{formField.fieldName}}\" placeholder=\"Please enter {{formField.label}}\" step=\"0.01\" type=\"number\" ng-required=\"formField.required\"></span>\n" +
        "                <textarea class=\"form-control app-textarea\" apply-styles=\"\" role=\"input\" focus-target=\"\" ng-model=\"formData[formField.fieldName]\" title=\"\" accesskey=\"\" ng-model-options=\"{ updateOn:'blur change', debounce: 0}\" ng-if=\"formField.formFieldTypes.label == 'Long Text'\" name=\"fieldValue{{formField.fieldName}}\" placeholder=\"Please enter {{formField.label}}\" ng-required=\"formField.required\"></textarea>\n" +
        "                <datetime-picker ng-if=\"formField.formFieldTypes.label == 'Date'\" placeholder=\"Please enter {{formField.label}}\" date=\"formData[formField.fieldName]\" name=\"{{formField.fieldName}}\" time=\"false\" ng-required=\"formField.required\"></datetime-picker>\n" +
        "                <datetime-picker ng-if=\"formField.formFieldTypes.label == 'Date+Time'\" placeholder=\"Please enter {{formField.label}}\" date=\"formData[formField.fieldName]\" name=\"{{formField.fieldName}}\" time=\"true\" ng-required=\"formField.required\"></datetime-picker>\n" +
        "                <div ng-if=\"formField.formFieldTypes.label == 'Boolean'\" class=\"app-checkbox checkbox\" style=\"margin-top: 0\" role=\"input\" name=\"fieldValue{{formField.fieldName}}\">\n" +
        "                    <label ng-class=\"{unchecked: !formData[formField.fieldName]}\" style=\"padding-left: 0\" role=\"button\">\n" +
        "                        <input type=\"checkbox\" ng-model=\"formData[formField.fieldName]\"><span class=\"caption ng-binding\" ng-bind-html=\"\"></span>\n" +
        "                    </label>\n" +
        "                </div>\n" +
        "                <select ng-if=\"formField.formFieldTypes.label == 'Select'\" style=\"margin-bottom: 6px\" ng-options=\"item for item in (formField.possibleValues.split(','))\" ng-model=\"formData[formField.fieldName]\" ng-required=\"formField.required\"></select>\n" +
        "                <cx-checkbox-set ng-if=\"formField.formFieldTypes.label == 'Multi-Select'\" choices=\"formField.possibleValues\" model=\"formData[formField.fieldName]\" field-name=\"formField.fieldName\"></cx-checkbox-set>\n" +
        "            </wm-container>\n" +
        "        </wm-composite>\n" +
        "    </div>\n" +
        "</wm-gridrow>\n</div>");
}]);