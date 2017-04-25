Application.requires.push('vcRecaptcha');
Application.requires.push('ui.grid');
Application.requires.push('ui.grid.pagination');

Application.config(['vcRecaptchaServiceProvider', '$routeProvider', function(vcRecaptchaServiceProvider, $routeProvider) {
    vcRecaptchaServiceProvider.setSiteKey("6LcNeBUUAAAAAE7ACQkzLMRlZ4kHI9-ebthTlQ61");

    $routeProvider.when("/editDocument/:docId", {
        templateUrl: "resources/leadTools/index.html"
    });
}]);

Application.run(['$rootScope', 'Utils', '$window', function($rootScope, Utils, $window) {
    "use strict";
    /* perform any action on the variables within this block(on-page-load) */
    $rootScope.onAppVariablesReady = function() {

        /*
         * variables can be accessed through '$rootScope.Variables' property heret
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

    $rootScope.svCartItemIdsonSuccess = function(variable, data) {
        // For count badge in top nav
        $rootScope.Variables.stvCartCount.dataSet.dataValue = data.content.length;
    };
    $rootScope.StandardUserMunicipalitesonSuccess = function(variable, data) {
        // For count badge in left nav
        $rootScope.Variables.NoOfMunicipalitiesForUser.dataSet.dataValue = data.totalElements;
    };

    // Gets value of a cookie by its name
    function getCookie(name) {
        var val = document.cookie.match('(^|;)\\s*' + name + '\\s*=\\s*([^;]+)');
        return val ? val.pop() : '';
    }

    var _simulateFileDownload = Utils.simulateFileDownload;
    Utils.simulateFileDownload = function(params) {
        // add csrf cookie to the query params for download requests
        params.url += '&_csrf=' + getCookie('wm_xsrf_token');
        _simulateFileDownload(params);
    };
}]);

Application.factory('_', ['$window', function($window) {
    "use strict";
    return $window._;
}]);

Application.factory('stickyItems', function() {
    "use strict";
    return {
        byName: {}
    };
});

Application.directive('sticky', ['$timeout', 'stickyItems', function($timeout, stickyItems) {
    "use strict";
    return {
        restrict: 'A',
        link: function(scope, elem, attrs) {
            $timeout(function() {
                var wasSticky = false;
                var $window = $(window);
                var elWidth = elem.outerWidth();
                var elHeight = elem.outerHeight();
                var navTop = elem.offset().top;
                var targetTop = 0;

                var stickyBottomOf = attrs.stickyBottomOf ? stickyItems.byName[attrs.stickyBottomOf] : null;
                var stickyBottomElem = !!stickyBottomOf ? stickyBottomOf.elem : null;

                var thisStickyItem = {
                    elem: elem,
                    isSticky: false
                };

                stickyItems.byName[attrs.sticky] = thisStickyItem;

                $window.on('scroll', function() {
                    updateStickyness();
                });
                $window.on('resize', function() {
                    updateStickyness(true);
                });

                function updateStickyness(isResize) {
                    thisStickyItem.isSticky = $window.scrollTop() > navTop || (!!stickyBottomOf && stickyBottomOf.isSticky);

                    if (thisStickyItem.isSticky === wasSticky && !isResize) {
                        return;
                    }

                    elWidth = elem.parent().width();
                    elHeight = elem.parent().height();

                    if (!!stickyBottomElem) {
                        targetTop = stickyBottomElem.outerHeight();
                    }

                    if (thisStickyItem.isSticky) {
                        elem.css('width', elWidth + 'px');
                        elem.css('top', targetTop + 'px');

                        if (!!attrs.pushDownElement) {
                            $(attrs.pushDownElement).css('top', elHeight + 'px');
                        }
                    } else {
                        elem.css('width', '');
                        elem.css('top', '');
                        $(attrs.pushDownElement).css('top', '');
                    }

                    elem.toggleClass('stuckTop', thisStickyItem.isSticky);

                    wasSticky = thisStickyItem.isSticky;
                }
            });
        }
    };
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

Application.factory('variableImageData', [function() {
    "use strict";
    return {
        profilePhotoUpdated: null
    };
}]);

Application.filter('toLocalDateString', [function() {
    var dateFormat = 'MM-DD-YYYY z';
    var dateTimeFormat = 'MM-DD-YYYY hh:mm a z';

    return function(dateObject, noTime, timezone) {
        return moment.utc(dateObject).local().format(noTime ? dateFormat : dateTimeFormat);
    };
}]);

Application.directive('profilePhoto', ['variableImageData', '$timeout', function(variableImageData, $timeout) {
    "use strict";
    return {
        restrict: 'C',
        link: function(scope, elem, attrs) {
            if (!!variableImageData.profilePhotoUpdated) {
                $timeout(function() {
                    elem.attr('src', ("services/cx2/Users/" + scope.Variables.loggedInUser.dataSet.id + "/content/photo?ts=" + variableImageData.profilePhotoUpdated));
                }, 0);
            }

            elem.on('error', function(e) {
                elem.attr("src", 'resources/images/imagelists/fa-user-170.png');
            });

            scope.$on('profilePhotoUpdated', function(newImg) {
                variableImageData.profilePhotoUpdated = moment().valueOf();
                elem.attr("src", ("services/cx2/Users/" + scope.Variables.loggedInUser.dataSet.id + "/content/photo?ts=" + variableImageData.profilePhotoUpdated));
            });
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

Application.directive('cxCalculatedField', [function() {
    "use strict";
    return {
        restrict: 'E',
        replace: true,
        scope: {
            formField: '=',
            formData: '='
        },
        template: '<div><input class="form-control" type="text" disabled="disabled" ng-value="calculateField()" /></div>',
        link: function(scope, elem, attrs, dynamicFormFieldsCtrl) {
            scope.calculateField = calculateField;

            var compiledCalculation = null;

            function calculateField() {
                if (compiledCalculation === null) {
                    compileCalculation();
                }

                var calculatedValue = scope.$eval(compiledCalculation);
                scope.formData[scope.formField.fieldName] = calculatedValue;

                return calculatedValue;
            }

            function compileCalculation() {
                compiledCalculation = "";

                var rawCalculation = scope.formField.defaultValue;
                var calcRegex = /\[([a-zA-Z0-9]+)\]/gm;

                var match = calcRegex.exec(rawCalculation);

                compiledCalculation = angular.copy(rawCalculation);
                while (match !== null) {
                    var toReplace = match[0];
                    var fieldName = match[1];

                    compiledCalculation = compiledCalculation.replace(toReplace, 'formData[\'' + fieldName + '\']');

                    match = calcRegex.exec(rawCalculation);
                }
            }
        }
    };
}]);

Application.directive('datetimePicker', ['uibDateParser', function(uibDateParser) {
    "use strict";
    return {
        restrict: 'E',
        replace: true,
        template: '<p class="input-group app-date" ng-style="{\'max-width\': (!time ? \'13em\' : \'21em\')}">' + '  <input type="text" class="form-control" name="{{name}}" uib-datepicker-popup="{{format}}" datepicker-popup-template-url="{{time===true ? \'uib/template/datepicker/datetimepickerPopup.html\' : \'uib/template/datepicker/datepickerPopup.html\'}}" ng-model="dateObj" is-open="dateTimePopup.opened" close-on-date-selection="time === false" />' + '  <span class="input-group-btn">' + '    <button type="button" class="btn btn-default btn-date" ng-click="openDateTimePicker()"><i class="app-icon wi wi-calendar"></i> <i ng-if="!!time" class="app-icon wi wi-clock"></i></button>' + '  </span>' + '</p>',
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

            scope.closeDateTimePicker = function() {
                scope.dateTimePopup.opened = false;
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
            formData: '=',
            columnCount: '@'
        },
        replace: true,
        templateUrl: 'dynamicFormFields.html',
        link: function(scope, elem, attrs) {
            var columnNumber = attrs.columnCount ? parseInt(attrs.columnCount) : 2;
            scope.columnCount = columnNumber;

            var unwatchFieldList = scope.$watch('formFieldList', function(newValue, oldValue) {
                if (!newValue) {
                    return;
                }

                var adjustedFieldList = [];
                adjustedFieldList.push([]);
                var currentColumnCount = 0;

                scope.formFieldList.forEach(function(formField, index) {
                    var needsSingleLine = (!formField.formFieldTypes.sqlType && formField.formFieldTypes.label !== 'Calculated');

                    if (needsSingleLine && adjustedFieldList[adjustedFieldList.length - 1].length > 0) {
                        currentColumnCount = 0;
                        adjustedFieldList.push([]);
                    }

                    adjustedFieldList[adjustedFieldList.length - 1].push(formField);
                    currentColumnCount++;

                    if (currentColumnCount === columnNumber || needsSingleLine) {
                        currentColumnCount = 0;
                        adjustedFieldList.push([]);
                    }
                });

                unwatchFieldList();
                scope.adjustedFieldList = adjustedFieldList;
            });
        }
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
        "<wm-gridrow ng-repeat=\"formFieldGroup in adjustedFieldList\" name=\"fieldGridrow{{$index}}\">\n" +
        "    <div ng-repeat=\"formField in formFieldGroup\" class=\"app-grid-column dynamic-field-column col-sm-{{12 / (!formField.formFieldTypes.sqlType && formField.formFieldTypes.label !== 'Calculated' ? formFieldGroup.length : columnCount)}}\" name=\"fieldGridcolumn{{$index}}\" ng-class=\"{'panel-primary': formField.formFieldTypes.label == 'Header'}\">\n" +
        "        <hr ng-if=\"formField.formFieldTypes.label == 'Horizontal Line'\">\n" +
        "        <p ng-if=\"formField.formFieldTypes.label == 'Instruction Text'\" ng-bind-html=\"formField.defaultValue\"></p>\n" +
        "        <h3 class=\"panel-heading\" ng-if=\"formField.formFieldTypes.label == 'Header'\" ng-bind-html=\"formField.defaultValue\"></h3>\n\n" +
        "        <wm-composite ng-if=\"!!formField.formFieldTypes.sqlType || formField.formFieldTypes.label === 'Calculated'\" name=\"fieldComposite{{$index}}\">\n" +
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
        "                <cx-calculated-field ng-if=\"formField.formFieldTypes.label === 'Calculated'\" form-field=\"formField\" form-data=\"formData\"></cx-calculated-field>\n" +
        "            </wm-container>\n" +
        "        </wm-composite>\n" +
        "    </div>\n" +
        "</wm-gridrow>\n</div>");
}]);