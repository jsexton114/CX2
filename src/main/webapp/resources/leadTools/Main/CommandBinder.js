var HTML5Demos;
(function (HTML5Demos) {
    var DocumentViewerDemo;
    (function (DocumentViewerDemo) {
        // Binds document viewer command to UI elements
        var CommandBinderElement = (function () {
            function CommandBinderElement() {
                this.updateEnabled = true;
                this.hasDocumentVisible = true;
                this.autoRun = true;
            }
            CommandBinderElement.prototype.runCommand = function (documentViewer) {
                if (this.commandName != null) {
                    documentViewer.commands.run(this.commandName, this.getValue != null ? this.getValue() : null);
                }
                else if (this.commandNames != null) {
                    for (var i = 0; i < this.commandNames.length; i++)
                        documentViewer.commands.run(this.commandNames[i], this.getValue != null ? this.getValue() : null);
                }
            };
            CommandBinderElement.prototype.canRunCommand = function (documentViewer) {
                if (this.commandName != null)
                    return documentViewer.commands.canRun(this.commandName, this.getValue != null ? this.getValue() : null);
                for (var i = 0; i < this.commandNames.length; i++) {
                    if (documentViewer.commands.canRun(this.commandNames[i], this.getValue != null ? this.getValue() : null))
                        return true;
                }
                return false;
            };
            Object.defineProperty(CommandBinderElement.prototype, "hasAnyCommand", {
                get: function () {
                    return (this.commandName != null || this.commandNames != null);
                },
                enumerable: true,
                configurable: true
            });
            return CommandBinderElement;
        })();
        DocumentViewerDemo.CommandBinderElement = CommandBinderElement;
        var CommandsBinder = (function () {
            function CommandsBinder(documentViewer) {
                this._elements = new Array();
                this._postRuns = new Array();
                this._documentViewer = documentViewer;
            }
            Object.defineProperty(CommandsBinder.prototype, "elements", {
                get: function () {
                    return this._elements;
                },
                enumerable: true,
                configurable: true
            });
            Object.defineProperty(CommandsBinder.prototype, "postRuns", {
                get: function () {
                    return this._postRuns;
                },
                enumerable: true,
                configurable: true
            });
            CommandsBinder.prototype.bindActions = function (bind) {
                for (var i = 0; i < this._elements.length; i++) {
                    if (bind) {
                        if (this._elements[i].autoRun && this._elements[i].hasAnyCommand) {
                            this._elements[i].userInterfaceElement.data("data", this.elements[i]);
                            this._elements[i].userInterfaceElement.bind("click", $.proxy(this.itemClick, this));
                        }
                        else {
                            this._elements[i].userInterfaceElement.removeData(this.elements[i].commandName);
                            this._elements[i].userInterfaceElement.unbind("click", $.proxy(this.itemClick, this));
                        }
                    }
                }
            };
            CommandsBinder.prototype.itemClick = function (e) {
                var element = $(e.currentTarget).data("data");
                element.runCommand(this._documentViewer);
            };
            CommandsBinder.prototype.run = function () {
                var hasDocument = this._documentViewer.hasDocument;
                for (var i = 0; i < this._elements.length; i++) {
                    var userInterfaceElement = this._elements[i].userInterfaceElement;
                    var canRun = false;
                    if (this._elements[i].canRun != null) {
                        canRun = this._elements[i].canRun(this._documentViewer, this.elements[i].canRunValue);
                    }
                    else if (this._elements[i].hasDocumentVisible) {
                        canRun = hasDocument;
                        if (userInterfaceElement.is(":visible") != canRun)
                            canRun ? userInterfaceElement.show() : userInterfaceElement.hide();
                    }
                    if (canRun && this._elements[i].hasAnyCommand)
                        canRun = this._elements[i].canRunCommand(this._documentViewer);
                    var updateCheckedState = this.elements[i].updateChecked;
                    var command = null;
                    if (this.elements[i].commandName != null) {
                        // This might be a state command, check
                        command = this._documentViewer.commands.getCommand(this._elements[i].commandName);
                    }
                    if (!updateCheckedState)
                        updateCheckedState = (command != null && command.hasState);
                    if (!updateCheckedState) {
                        if (this._elements[i].updateEnabled && (!userInterfaceElement.is(":disabled") != canRun))
                            userInterfaceElement.prop("disabled", !canRun);
                    }
                    else {
                        if (hasDocument) {
                            userInterfaceElement.prop("disabled", false);
                            var checkedState = false;
                            if (command != null && command.hasState)
                                checkedState = command.state;
                            else
                                checkedState = !canRun;
                            if (userInterfaceElement.hasClass("menuItem")) {
                                HTML5Demos.Utils.DemoHelper.checked(userInterfaceElement.find(".icon"), checkedState);
                            }
                            else {
                                HTML5Demos.Utils.DemoHelper.checked(userInterfaceElement, checkedState);
                            }
                        }
                        else {
                            if (userInterfaceElement.hasClass("menuItem")) {
                                HTML5Demos.Utils.DemoHelper.checked(userInterfaceElement.find(".icon"), false);
                            }
                            else {
                                HTML5Demos.Utils.DemoHelper.checked(userInterfaceElement, false);
                            }
                            userInterfaceElement.prop("disabled", true);
                        }
                    }
                    if (this._elements[i].updateVisible && userInterfaceElement.is(":visible") != canRun)
                        canRun ? userInterfaceElement.show() : userInterfaceElement.hide();
                }
                for (var i = 0; i < this._postRuns.length; i++)
                    this._postRuns[i]();
            };
            return CommandsBinder;
        })();
        DocumentViewerDemo.CommandsBinder = CommandsBinder;
    })(DocumentViewerDemo = HTML5Demos.DocumentViewerDemo || (HTML5Demos.DocumentViewerDemo = {}));
})(HTML5Demos || (HTML5Demos = {}));
