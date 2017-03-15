var HTML5Demos;
(function (HTML5Demos) {
    var DocumentViewerDemo;
    (function (DocumentViewerDemo) {
        // Contains the view part
        var ViewPart = (function () {
            function ViewPart(main) {
                // Reference to the DocumentViewerDemoApp
                this._mainApp = null;
                // View menu
                this.headerToolbar_ViewMenu = {
                    viewMenuItem: "#viewMenuItem",
                    rotateCounterClockwiseMenuItem: "#rotateCounterClockwise",
                    rotateClockwiseMenuItem: "#rotateClockwise",
                    zoomOutMenuItem: "#zoomOut",
                    zoomInMenuItem: "#zoomIn",
                    actualSizeMenuItem: "#actualSize",
                    fitMenuItem: "#fit",
                    fitWidthMenuItem: "#fitWidth",
                    asImageMenuItem: "#viewAsImage",
                    asSvgMenuItem: "#viewAsSVG",
                };
                // Shortcuts
                this.shortcuts = {
                    zoomOutBtn: "#zoomOut_shortcut",
                    zoomInBtn: "#zoomIn_shortcut",
                    zoomValuesSelectElement: {
                        SelectElement: "#zoomValues",
                        currentZoomValueOption: "#currentZoomValue"
                    },
                    actualSizeBtn: "#actualSize_shortcut",
                    fitBtn: "#fit_shortcut",
                    fitWidthBtn: "#fitWidth_shortcut",
                };
                this.mobileVersionViewControls = {
                    showViewControlsBtn: "#showViewControls",
                    closeViewControlsBtn: "#closeViewControls",
                    viewControls: "#viewControlsContainer"
                };
                this._mainApp = main;
                this.initViewUI();
            }
            ViewPart.prototype.initViewUI = function () {
                // View menu items
                $(this.headerToolbar_ViewMenu.viewMenuItem).bind("click", $.proxy(this.viewMenuItem_Click, this));
                $(this.headerToolbar_ViewMenu.asSvgMenuItem).bind("click", $.proxy(this.asSvgMenuItem_Click, this));
                $(this.headerToolbar_ViewMenu.asImageMenuItem).bind("click", $.proxy(this.asImageMenuItem_Click, this));
                // Only for mobile version
                if (DocumentViewerDemo.DocumentViewerDemoApp.isMobileVersion) {
                    $(this.mobileVersionViewControls.viewControls).css("bottom", -$(this.mobileVersionViewControls.viewControls).height() - 10);
                    $(this.mobileVersionViewControls.viewControls).css("display", "block");
                    $(this.mobileVersionViewControls.showViewControlsBtn).bind("click", $.proxy(this.showViewControlsBtn_Click, this));
                    $(this.mobileVersionViewControls.closeViewControlsBtn).bind("click", $.proxy(this.closeViewControlsBtn_Click, this));
                }
            };
            ViewPart.prototype.bindElements = function () {
                var currentIndex;
                // View menu
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.headerToolbar_ViewMenu.viewMenuItem);
                this._mainApp.commandsBinder.elements[currentIndex].updateEnabled = false;
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.viewRotateCounterClockwise;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.headerToolbar_ViewMenu.rotateCounterClockwiseMenuItem);
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.viewRotateClockwise;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.headerToolbar_ViewMenu.rotateClockwiseMenuItem);
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.viewZoomOut;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.headerToolbar_ViewMenu.zoomOutMenuItem);
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.viewZoomIn;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.headerToolbar_ViewMenu.zoomInMenuItem);
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.viewActualSize;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.headerToolbar_ViewMenu.actualSizeMenuItem);
                this._mainApp.commandsBinder.elements[currentIndex].updateChecked = true;
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.viewFitPage;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.headerToolbar_ViewMenu.fitMenuItem);
                this._mainApp.commandsBinder.elements[currentIndex].updateChecked = true;
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.viewFitWidth;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.headerToolbar_ViewMenu.fitWidthMenuItem);
                this._mainApp.commandsBinder.elements[currentIndex].updateChecked = true;
                // Shortcuts
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.viewZoomOut;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.shortcuts.zoomOutBtn);
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.shortcuts.zoomValuesSelectElement.SelectElement);
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.viewZoomIn;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.shortcuts.zoomInBtn);
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.viewActualSize;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.shortcuts.actualSizeBtn);
                this._mainApp.commandsBinder.elements[currentIndex].updateChecked = true;
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.viewFitPage;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.shortcuts.fitBtn);
                this._mainApp.commandsBinder.elements[currentIndex].updateChecked = true;
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.viewFitWidth;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.shortcuts.fitWidthBtn);
                this._mainApp.commandsBinder.elements[currentIndex].updateChecked = true;
                this.bindZoom();
            };
            ViewPart.prototype.bindZoom = function () {
                var _this = this;
                this._mainApp.documentViewer.view.imageViewer.transformChanged.add(function () { return _this.updateZoomValueFromView(); });
                $(this.shortcuts.zoomValuesSelectElement.SelectElement).change(function (e) {
                    if (!_this._mainApp.documentViewer.hasDocument)
                        return;
                    // Parse the new zoom value
                    var text = $(_this.shortcuts.zoomValuesSelectElement.SelectElement).val();
                    var imageViewer = _this._mainApp.documentViewer.view.imageViewer;
                    switch (text) {
                        case "Actual Size":
                            imageViewer.zoom(lt.Controls.ControlSizeMode.actualSize, 1, imageViewer.defaultZoomOrigin);
                            break;
                        case "Fit Page":
                            imageViewer.zoom(lt.Controls.ControlSizeMode.fitAlways, 1, imageViewer.defaultZoomOrigin);
                            break;
                        case "Fit Width":
                            imageViewer.zoom(lt.Controls.ControlSizeMode.fitWidth, 1, imageViewer.defaultZoomOrigin);
                            break;
                        case "Fit Height":
                            imageViewer.zoom(lt.Controls.ControlSizeMode.fitHeight, 1, imageViewer.defaultZoomOrigin);
                            break;
                        default:
                            if (text != null && text != "") {
                                var percentage = parseFloat(text.substring(0, text.length - 1));
                                imageViewer.zoom(lt.Controls.ControlSizeMode.none, percentage / 100.0, imageViewer.defaultZoomOrigin);
                            }
                            break;
                    }
                });
            };
            ViewPart.prototype.updateZoomValueFromView = function () {
                if (this._mainApp.documentViewer.hasDocument) {
                    var percentage = this._mainApp.documentViewer.view.imageViewer.scaleFactor * 100.0;
                    $(this.shortcuts.zoomValuesSelectElement.currentZoomValueOption).text((percentage.toFixed(1)).toString() + "%");
                }
                else {
                    $(this.shortcuts.zoomValuesSelectElement.currentZoomValueOption).text("");
                }
                // Select the currentZoomValueOption 
                $(this.shortcuts.zoomValuesSelectElement.SelectElement).prop("selectedIndex", 0);
            };
            ViewPart.prototype.viewMenuItem_Click = function (e) {
                this.updateViewMenu();
            };
            ViewPart.prototype.updateViewMenu = function () {
                if (this._mainApp.documentViewer.hasDocument) {
                    // These elements are not bound to commandsBinder , so we need enable them
                    $(this.headerToolbar_ViewMenu.asSvgMenuItem).prop("disabled", false);
                    $(this.headerToolbar_ViewMenu.asImageMenuItem).prop("disabled", false);
                    if (this._mainApp.documentViewer.document.images.isSvgSupported) {
                        $(this.headerToolbar_ViewMenu.asSvgMenuItem).prop("disabled", false);
                        var asSvgMenuItemChecked = !this._mainApp.documentViewer.commands.canRun(lt.Documents.UI.DocumentViewerCommands.viewItemType, lt.Documents.UI.DocumentViewerItemType.svg);
                        var AsImageMenuItemChecked = !this._mainApp.documentViewer.commands.canRun(lt.Documents.UI.DocumentViewerCommands.viewItemType, lt.Documents.UI.DocumentViewerItemType.image);
                        HTML5Demos.Utils.DemoHelper.checked($(this.headerToolbar_ViewMenu.asSvgMenuItem).find(".icon"), asSvgMenuItemChecked);
                        HTML5Demos.Utils.DemoHelper.checked($(this.headerToolbar_ViewMenu.asImageMenuItem).find(".icon"), AsImageMenuItemChecked);
                    }
                    else {
                        $(this.headerToolbar_ViewMenu.asSvgMenuItem).prop("disabled", true);
                        HTML5Demos.Utils.DemoHelper.checked($(this.headerToolbar_ViewMenu.asSvgMenuItem).find(".icon"), false);
                        HTML5Demos.Utils.DemoHelper.checked($(this.headerToolbar_ViewMenu.asImageMenuItem).find(".icon"), true);
                    }
                }
                else {
                    // These elements are not bound to commandsBinder , so we need to disabled and uncheck them if there no loaded document
                    HTML5Demos.Utils.DemoHelper.checked($(this.headerToolbar_ViewMenu.asSvgMenuItem).find(".icon"), false);
                    $(this.headerToolbar_ViewMenu.asSvgMenuItem).prop("disabled", true);
                    HTML5Demos.Utils.DemoHelper.checked($(this.headerToolbar_ViewMenu.asImageMenuItem).find(".icon"), false);
                    $(this.headerToolbar_ViewMenu.asImageMenuItem).prop("disabled", true);
                }
            };
            ViewPart.prototype.setViewMode = function (isSvg) {
                this._mainApp.setInterpolationMode(this._mainApp.documentViewer.document, isSvg);
                this._mainApp.documentViewer.commands.run(lt.Documents.UI.DocumentViewerCommands.viewItemType, isSvg ? lt.Documents.UI.DocumentViewerItemType.svg : lt.Documents.UI.DocumentViewerItemType.image);
                // Check as svg button
                HTML5Demos.Utils.DemoHelper.checked($(this.headerToolbar_ViewMenu.asSvgMenuItem).find(".icon"), isSvg);
                // Uncheck as image button
                HTML5Demos.Utils.DemoHelper.checked($(this.headerToolbar_ViewMenu.asImageMenuItem).find(".icon"), !isSvg);
            };
            ViewPart.prototype.asSvgMenuItem_Click = function (e) {
                this.setViewMode(true);
            };
            ViewPart.prototype.asImageMenuItem_Click = function (e) {
                this.setViewMode(false);
            };
            ViewPart.prototype.showViewControlsBtn_Click = function (e) {
                $(this._mainApp.mobileVersionControlsContainers).removeClass('visiblePanel');
                $(this.mobileVersionViewControls.viewControls).addClass('visiblePanel');
            };
            ViewPart.prototype.closeViewControlsBtn_Click = function (e) {
                $(this.mobileVersionViewControls.viewControls).removeClass('visiblePanel');
            };
            return ViewPart;
        })();
        DocumentViewerDemo.ViewPart = ViewPart;
    })(DocumentViewerDemo = HTML5Demos.DocumentViewerDemo || (HTML5Demos.DocumentViewerDemo = {}));
})(HTML5Demos || (HTML5Demos = {}));
