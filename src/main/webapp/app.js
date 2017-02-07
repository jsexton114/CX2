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

});

Application.directive('datetimePicker', ['uibDateParser', function(uibDateParser) {
    return {
        restrict: 'E',
        replace: true,
        template: '<p class="input-group app-date" ng-style="{\'max-width\': (!time ? \'13em\' : \'21em\')}">' + '  <input type="text" class="form-control" name="{{name}}" uib-datepicker-popup="{{format}}" datepicker-popup-template-url="{{time===true ? \'uib/template/datepicker/datetimepickerPopup.html\' : \'uib/template/datepicker/datepickerPopup.html\'}}" ng-model="dateObj" open-on-focus="false" is-open="dateTimePopup.opened" close-on-date-selection="false" />' + '  <span class="input-group-btn">' + '    <button type="button" class="btn btn-default btn-date" ng-click="openDateTimePicker()"><i class="app-icon wi wi-calendar"></i><i ng-if="!!time" class="app-icon wi wi-clock"></i></button>' + '  </span>' + '</p>',
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

Application.directive('ngFileModel', function() {
    return {
        restrict: 'A',
        scope: {
            ngFileModel: '='
        },
        link: function(scope, elem, attrs) {
            elem.bind('change', function() {
                var fileToAdd = elem[0].files[0];
                scope.ngFileModel = {
                    Filename: fileToAdd.name,
                    Mimetype: fileToAdd.type,
                    Contents: angular.copy(fileToAdd)
                };
            });
        }
    };
});

Application.run(["$templateCache", function($templateCache) {
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
}]);

Application.run(["$templateCache", function($templateCache) {
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
}]);