var HTML5Demos;
(function (HTML5Demos) {
    var DocumentViewerDemo;
    (function (DocumentViewerDemo) {
        // Contains the interactive part
        var InteractivePart = (function () {
            function InteractivePart(main) {
                // Reference to the DocumentViewerDemoApp
                this._mainApp = null;
                // Reference to main imageviewer's useElements mode
                this._useElements = false;
                // Interactive menu items
                this.headerToolbar_InteractiveMenu = {
                    interactiveMenuItem: "#interactiveMenuItem",
                    panZoomModeMenuItem: "#panZoomMode",
                    panModeMenuItem: "#panMode",
                    zoomModeMenuItem: "#zoomMode",
                    zoomToModeMenuItem: "#zoomToMode",
                    magnifyGlassModeMenuItem: "#magnifyGlassMode",
                    rubberBandInteractiveModeMenuItem: "#rubberBandInteractiveMode",
                    selectTextModeMenuItem: "#selectTextMode",
                    autoPanMenuItem: "#autoPan",
                    inertiaScrollMenuItem: "#inertiaScroll"
                };
                // Shortcuts
                this.shortcuts = {
                    panZoomModeBtn: "#panZoomMode_shortcut",
                    panModeBtn: "#panMode_shortcut",
                    zoomModeBtn: "#zoomMode_shortcut",
                    zoomToModeBtn: "#zoomToMode_shortcut",
                    magnifyGlassModeBtn: "#magnifyGlassMode_shortcut",
                    rubberBandInteractiveModeBtn: "#rubberBandInteractiveMode_shortcut",
                    selectTextModeBtn: "#selectTextMode_shortcut",
                };
                this._mainApp = main;
                this.initInteractiveUI();
            }
            InteractivePart.prototype.initInteractiveUI = function () {
                $(this.headerToolbar_InteractiveMenu.rubberBandInteractiveModeMenuItem).bind("click", $.proxy(this.rubberBandInteractiveModeMenuItem_Click, this));
                $(this.shortcuts.rubberBandInteractiveModeBtn).bind("click", $.proxy(this.rubberBandInteractiveModeBtn_Click, this));
                $(this.headerToolbar_InteractiveMenu.interactiveMenuItem).bind("click", $.proxy(this.interactiveMenuItem_Click, this));
                $(this.headerToolbar_InteractiveMenu.inertiaScrollMenuItem).bind("click", $.proxy(this.inertiaScrollMenuItem_Click, this));
            };
            InteractivePart.prototype.bindElements = function () {
                var currentIndex;
                // Interactive menu
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.headerToolbar_InteractiveMenu.interactiveMenuItem);
                this._mainApp.commandsBinder.elements[currentIndex].updateEnabled = false;
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.interactivePanZoom;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.headerToolbar_InteractiveMenu.panZoomModeMenuItem);
                this._mainApp.commandsBinder.elements[currentIndex].updateChecked = true;
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.interactivePan;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.headerToolbar_InteractiveMenu.panModeMenuItem);
                this._mainApp.commandsBinder.elements[currentIndex].updateChecked = true;
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.interactiveZoom;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.headerToolbar_InteractiveMenu.zoomModeMenuItem);
                this._mainApp.commandsBinder.elements[currentIndex].updateChecked = true;
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.interactiveZoomTo;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.headerToolbar_InteractiveMenu.zoomToModeMenuItem);
                this._mainApp.commandsBinder.elements[currentIndex].updateChecked = true;
                if (!this._mainApp.useElements) {
                    currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                    this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.interactiveMagnifyGlass;
                    this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.headerToolbar_InteractiveMenu.magnifyGlassModeMenuItem);
                    this._mainApp.commandsBinder.elements[currentIndex].updateChecked = true;
                }
                else {
                    $(this.headerToolbar_InteractiveMenu.magnifyGlassModeMenuItem).remove();
                }
                if (this._mainApp.demoMode == DocumentViewerDemo.DemoMode.OCR) {
                    currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                    this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.interactiveRubberBand;
                    this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.headerToolbar_InteractiveMenu.rubberBandInteractiveModeMenuItem);
                    this._mainApp.commandsBinder.elements[currentIndex].updateChecked = true;
                    this._mainApp.commandsBinder.elements[currentIndex].autoRun = false;
                }
                if (this._mainApp.demoMode != DocumentViewerDemo.DemoMode.Barcode) {
                    currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                    this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.interactiveSelectText;
                    this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.headerToolbar_InteractiveMenu.selectTextModeMenuItem);
                    this._mainApp.commandsBinder.elements[currentIndex].updateChecked = true;
                }
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.interactiveAutoPan;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.headerToolbar_InteractiveMenu.autoPanMenuItem);
                this._mainApp.commandsBinder.elements[currentIndex].updateChecked = true;
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.headerToolbar_InteractiveMenu.inertiaScrollMenuItem);
                // Shortcuts
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.interactivePanZoom;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.shortcuts.panZoomModeBtn);
                this._mainApp.commandsBinder.elements[currentIndex].updateChecked = true;
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.interactivePan;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.shortcuts.panModeBtn);
                this._mainApp.commandsBinder.elements[currentIndex].updateChecked = true;
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.interactiveZoom;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.shortcuts.zoomModeBtn);
                this._mainApp.commandsBinder.elements[currentIndex].updateChecked = true;
                currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.interactiveZoomTo;
                this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.shortcuts.zoomToModeBtn);
                this._mainApp.commandsBinder.elements[currentIndex].updateChecked = true;
                if (!this._mainApp.useElements) {
                    // Remove the magnifyGlass option in Elements Mode
                    currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                    this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.interactiveMagnifyGlass;
                    this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.shortcuts.magnifyGlassModeBtn);
                    this._mainApp.commandsBinder.elements[currentIndex].updateChecked = true;
                }
                else {
                    $(this.shortcuts.magnifyGlassModeBtn).remove();
                }
                if (this._mainApp.demoMode == DocumentViewerDemo.DemoMode.OCR || this._mainApp.demoMode == DocumentViewerDemo.DemoMode.Barcode) {
                    currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                    this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.interactiveRubberBand;
                    this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.shortcuts.rubberBandInteractiveModeBtn);
                    this._mainApp.commandsBinder.elements[currentIndex].updateChecked = true;
                }
                if (this._mainApp.demoMode != DocumentViewerDemo.DemoMode.Barcode) {
                    currentIndex = this._mainApp.commandsBinder.elements.push(new DocumentViewerDemo.CommandBinderElement()) - 1;
                    this._mainApp.commandsBinder.elements[currentIndex].commandName = lt.Documents.UI.DocumentViewerCommands.interactiveSelectText;
                    this._mainApp.commandsBinder.elements[currentIndex].userInterfaceElement = $(this.shortcuts.selectTextModeBtn);
                    this._mainApp.commandsBinder.elements[currentIndex].updateChecked = true;
                }
                // Set up the rubberband options, only once
                this.setRubberBandInteractiveMode();
            };
            InteractivePart.prototype.rubberBandInteractiveModeMenuItem_Click = function (e) {
                this._mainApp.documentViewer.commands.run(lt.Documents.UI.DocumentViewerCommands.interactiveRubberBand, null);
            };
            InteractivePart.prototype.rubberBandInteractiveModeBtn_Click = function (e) {
                this._mainApp.documentViewer.commands.run(lt.Documents.UI.DocumentViewerCommands.interactiveRubberBand, null);
            };
            InteractivePart.prototype.setRubberBandInteractiveMode = function () {
                var _this = this;
                var rubberbandMode = this._mainApp.documentViewer.view.imageViewer.interactiveModes.findById(lt.Controls.ImageViewerInteractiveMode.rubberBandModeId);
                rubberbandMode.rubberBandCompleted.add(function (sender, e) { return _this.rubberBandCompleted(sender, e); });
                rubberbandMode.autoItemMode = lt.Controls.ImageViewerAutoItemMode.autoSet;
            };
            InteractivePart.prototype.rubberBandCompleted = function (sender, e) {
                if (e.interactiveEventArgs.nativeEvent.type == "mousewheel")
                    return;
                var imageViewer = this._mainApp.documentViewer.view.imageViewer;
                var rubberBand = sender;
                var item = rubberBand.item;
                var pt1 = imageViewer.convertPoint(item, lt.Controls.ImageViewerCoordinateType.control, lt.Controls.ImageViewerCoordinateType.image, e.points[0]);
                var pt2 = imageViewer.convertPoint(item, lt.Controls.ImageViewerCoordinateType.control, lt.Controls.ImageViewerCoordinateType.image, e.points[1]);
                var searchArea = lt.LeadRectD.fromLTRB(pt1.x, pt1.y, pt2.x, pt2.y);
                if (searchArea.width > 3 && searchArea.height > 3) {
                    // If > 3, it's not a click. Call readBarcodes.
                    var pageIndex = imageViewer.items.indexOf(item);
                    var document = this._mainApp.documentViewer.document;
                    searchArea = document.rectToDocument(searchArea);
                    var page = document.pages[pageIndex];
                    if (this._mainApp.demoMode == DocumentViewerDemo.DemoMode.OCR)
                        this._mainApp.recognize(page, searchArea);
                    else if (this._mainApp.demoMode == DocumentViewerDemo.DemoMode.Barcode)
                        this._mainApp.readBarcodes(page, searchArea);
                }
                else if (this._mainApp.demoMode == DocumentViewerDemo.DemoMode.Barcode) {
                    // If < 3, consider it a click, and show the barcode if it's in the current barcode list.
                    var pageIndex = imageViewer.items.indexOf(item);
                    var document = this._mainApp.documentViewer.document;
                    searchArea = document.rectToDocument(searchArea);
                    this._mainApp.checkBarcodeData(pageIndex, searchArea);
                }
            };
            InteractivePart.prototype.interactiveMenuItem_Click = function (e) {
                HTML5Demos.Utils.DemoHelper.checked($(this.headerToolbar_InteractiveMenu.inertiaScrollMenuItem).find(".icon"), this._mainApp.preferencesPart.enableInertiaScroll);
            };
            InteractivePart.prototype.inertiaScrollMenuItem_Click = function (e) {
                this._mainApp.toggleInertiaScroll(false);
                HTML5Demos.Utils.DemoHelper.checked($(this.headerToolbar_InteractiveMenu.inertiaScrollMenuItem).find(".icon"), this._mainApp.preferencesPart.enableInertiaScroll);
            };
            return InteractivePart;
        })();
        DocumentViewerDemo.InteractivePart = InteractivePart;
    })(DocumentViewerDemo = HTML5Demos.DocumentViewerDemo || (HTML5Demos.DocumentViewerDemo = {}));
})(HTML5Demos || (HTML5Demos = {}));
