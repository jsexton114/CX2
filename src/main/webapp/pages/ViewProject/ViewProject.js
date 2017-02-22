Application.$controller("ViewProjectPageController", ["$scope", function($scope) {
            "use strict";

            /* perform any action on widgets/variables within this block */
            var crum;
            $scope.onPageReady = function() {
                $scope.today = moment().valueOf();
                //var temp = $scope.Variables.BreadCrumb.dataSet;
                $scope.Widgets.gridProjectMembers.addMember = true;
            };


            $scope.CurrentProjectonSuccess = function(variable, data) {
                $scope.Widgets.htmlProjectDescription.content = decodeURIComponent(escape(window.atob(data[0].projectDescription)));

                if ($scope.Variables.loggedInUser.dataSet.id == data[0].usersByCreatedBy.id) {
                    $scope.Widgets.gridProjectMembers.addMember = false;
                }
                var temp = $scope.Variables.loggedInUser.dataSet.roles;
                //Checking if user is muniadmin or cxadmin
                for (let i = 0; i < temp.length; i++) {
                    if ((temp[i] == "MunicipalityAdmin") || (temp[i] == "CXAdmin")) {
                        $scope.Widgets.gridProjectMembers.addMember = false;
                    }
                }
            };


            $scope.UpdateProjectDescriptiononSuccess = function(variable, data) {
                $scope.today = moment().valueOf();
                $scope.Variables.SetModifiedDateForProject.setInput({
                    'DateModified': $scope.today
                });
                $scope.Variables.SetModifiedDateForProject.update();
            };


            $scope.UpdateProjectDetailsonSuccess = function(variable, data) {
                $scope.today = moment().valueOf();
                $scope.Variables.SetModifiedDateForProject.setInput({
                    'DateModified': $scope.today
                });
                $scope.Variables.SetModifiedDateForProject.update();
            };


            $scope.GetMessageIdForCurrentPostonSuccess = function(variable, data) {
                //Send Mails
                var people = $scope.Variables.PeopleList.dataSet;
                var m = data.content[0];
                $scope.messageMailingList = '';
                // Insert people as Tagged People For RecentMessage
                for (var i = 0; i < people.length; i++) {
                    $scope.Variables.InsertTaggedPeople.setInput({
                        "taggedPersonId": people[i].id,
                        "users": people[i],
                        "formMessages": m,
                        "formMessageId": data.content[0].id,
                    });
                    $scope.Variables.InsertTaggedPeople.insertRecord();
                };


                $scope.buttonAddMessageClick = function($event, $isolateScope) {
                    // Posting Message
                    $scope.Variables.PostFormMessage.setInput({
                        'PostedAt': moment().valueOf(),
                    });
                    $scope.Variables.PostFormMessage.update();
                };

                $scope.PostFormMessageonSuccess = function(variable, data) {
                    $scope.Widgets.textAddMessage.datavalue = undefined;
                    let people = $scope.Variables.PeopleList.dataSet;
                    if (people.length === 0) {
                        // DO nothing
                    } else {
                        $scope.Variables.GetMessageIdForCurrentPost.setInput({
                            'PostedAt': variable.dataBinding.PostedAt,
                            'form': $scope.pageParams.FormGUID
                        });
                        $scope.Variables.GetMessageIdForCurrentPost.update();

                    }
                };

            }]);

        Application.$controller("gridProjectMembersController", ["$scope",
            function($scope) {
                "use strict";
                $scope.ctrlScope = $scope;
            }
        ]);

        Application.$controller("dialogAddMemberController", ["$scope",
            function($scope) {
                "use strict";
                $scope.ctrlScope = $scope;
                $scope.today = moment().valueOf();
            }
        ]);



        Application.$controller("dialogAddGISRecordController", ["$scope",
            function($scope) {
                "use strict";
                $scope.ctrlScope = $scope;
                $scope.today = moment().valueOf();

                $scope.buttonAddByAddressClick = function($event, $isolateScope) {
                    $scope.Variables.AddGIStoProjects.setInput({
                        'GISRecordId': $scope.Widgets.searchAddress.datavalue.id
                    });
                    $scope.Variables.AddGIStoProjects.update();
                };


                $scope.buttonAddBySubdivisionClick = function($event, $isolateScope) {
                    $scope.Variables.AddGIStoProjects.setInput({
                        'GISRecordId': $scope.Widgets.searchSubdivision.datavalue.id
                    });
                    $scope.Variables.AddGIStoProjects.update();
                };


                $scope.buttonAddByParcelClick = function($event, $isolateScope) {
                    $scope.Variables.AddGIStoProjects.setInput({
                        'GISRecordId': $scope.Widgets.searchParcel.datavalue.id
                    });
                    $scope.Variables.AddGIStoProjects.update();
                };

            }
        ]);

        Application.$controller("dialogParcelController", ["$scope",
            function($scope) {
                "use strict";
                $scope.ctrlScope = $scope;
            }
        ]);

        Application.$controller("gridProjectFormsController", ["$scope",
            function($scope) {
                "use strict";
                $scope.ctrlScope = $scope;
            }
        ]);

        Application.$controller("dialogAddFormsToProjectController", ["$scope",
            function($scope) {
                "use strict";
                $scope.ctrlScope = $scope;
            }
        ]);

        Application.$controller("gridLocationController", ["$scope",
            function($scope) {
                "use strict";
                $scope.ctrlScope = $scope;
            }
        ]);

        Application.$controller("gridTasksController", ["$scope",
            function($scope) {
                "use strict";
                $scope.ctrlScope = $scope;
            }
        ]);

        Application.$controller("dialogAddtaskController", ["$scope",
            function($scope) {
                "use strict";
                $scope.ctrlScope = $scope;
            }
        ]);

        Application.$controller("dialogUpdateTaskController", ["$scope",
            function($scope) {
                "use strict";
                $scope.ctrlScope = $scope;
            }
        ]);


        Application.$controller("dialogDeleteGISRecordConfController", ["$scope",
            function($scope) {
                "use strict";
                $scope.ctrlScope = $scope;
            }
        ]);

        Application.$controller("dialogEditProjectDescriptionController", ["$scope",
            function($scope) {
                "use strict";
                $scope.ctrlScope = $scope;

                $scope.buttonUpdateProjectDescriptionClick = function($event, $isolateScope) {
                    var encodedDescription = btoa(unescape(encodeURIComponent($scope.Widgets.richtexteditorUpdateDescription.datavalue)));
                    $scope.Variables.UpdateProjectDescription.setInput({
                        'ProjectDescription': encodedDescription
                    });
                    $scope.Variables.UpdateProjectDescription.update();
                };

            }
        ]);

        Application.$controller("dialogTagPeopleController", ["$scope",
            function($scope) {
                "use strict";
                $scope.ctrlScope = $scope;
            }
        ]);

        Application.$controller("dialogShowTaggedPeopleController", ["$scope",
            function($scope) {
                "use strict";
                $scope.ctrlScope = $scope;
            }
        ]);