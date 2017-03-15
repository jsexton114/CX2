var HTML5Demos;
(function (HTML5Demos) {
    var DocumentViewerDemo;
    (function (DocumentViewerDemo) {
        var Converter;
        (function (Converter) {
            var Dialogs;
            (function (Dialogs) {
                var SaveToDlg = (function () {
                    function SaveToDlg() {
                        // Create shortcuts for the dialog UI elements
                        this.dialogUI = {
                            dialog: "#saveToDialog",
                            localSaveBtn: "#localSave",
                            saveToOneDriveBtn: "#saveToOneDrive",
                            saveToSharePointBtn: "#saveToSharePoint",
                            saveToGoogleDriveBtn: "#saveToGoogleDrive",
                            googleSaveContainerDiv: "#googleSaveContainer",
                            fileNameTextInput: "#fileName",
                            fileSavingImage: "#fileSaving",
                            closeBtn: "#closeSaveToDialog",
                        };
                        this.Init();
                    }
                    Object.defineProperty(SaveToDlg.prototype, "sharePointHelper", {
                        set: function (value) {
                            this._sharePointHelper = value;
                        },
                        enumerable: true,
                        configurable: true
                    });
                    SaveToDlg.prototype.Init = function () {
                        $(this.dialogUI.localSaveBtn).bind("click", $.proxy(this.localSaveBtn_Click, this));
                        $(this.dialogUI.saveToOneDriveBtn).bind("click", $.proxy(this.saveToOneDriveBtn_Click, this));
                        $(this.dialogUI.saveToSharePointBtn).bind("click", $.proxy(this.saveToSharePointBtn_Click, this));
                        // if IE9 or below, Google Drive is not supported.
                        if (lt.LTHelper.browser === lt.LTBrowser.internetExplorer && lt.LTHelper.version <= 9)
                            $(this.dialogUI.saveToGoogleDriveBtn).prop("disabled", true);
                        else
                            $(this.dialogUI.saveToGoogleDriveBtn).bind("click", $.proxy(this.saveToGoogleDriveBtn_Click, this));
                        $(this.dialogUI.fileNameTextInput).bind("change", $.proxy(this.fileNameTextInput_Changed, this));
                        $(this.dialogUI.closeBtn).bind("click", $.proxy(this.closeBtn_Click, this));
                    };
                    SaveToDlg.prototype.InitDriveHelpers = function () {
                        var _this = this;
                        // OneDrive
                        this._oneDriveHelper = new HTML5Demos.DriveHelper.LTOneDrive.OneDriveHelper();
                        this._oneDriveHelper.saveDone = function (error) { return _this.saveDone(error); };
                        // SharePoint
                        this._sharePointHelper.saveDone = function (error) { return _this.saveDone(error); };
                        // GoogleDrive
                        // if IE9 or below, Google Drive is not supported.
                        if (!(lt.LTHelper.browser === lt.LTBrowser.internetExplorer && lt.LTHelper.version <= 9)) {
                            this._googleDriveHelper = new HTML5Demos.DriveHelper.LTGoogleDrive.GoogleDriveHelper();
                        }
                    };
                    SaveToDlg.prototype.show = function (convertItems) {
                        if (!convertItems || convertItems.length == 0)
                            return;
                        var convertItem = convertItems[0];
                        this._remaining = convertItems.slice(1, convertItems.length);
                        var lengthRemaining = this._remaining.length;
                        if (lengthRemaining)
                            $(this.dialogUI.closeBtn).text("Next (" + lengthRemaining + ")");
                        else
                            $(this.dialogUI.closeBtn).text("Close");
                        var name = convertItem.url.substring(convertItem.url.lastIndexOf("/") + 1);
                        $(this.dialogUI.fileNameTextInput).val(name);
                        this._convertItem = convertItem;
                        this._fileOriginalName = name;
                        if (this._googleDriveHelper)
                            this._googleDriveHelper.save(this._convertItem.url, name);
                        $(this.dialogUI.dialog).modal();
                    };
                    SaveToDlg.prototype.localSaveBtn_Click = function (e) {
                        var url = this._convertItem.url;
                        var win = null;
                        if (lt.LTHelper.browser === lt.LTBrowser.internetExplorer) {
                            win = window.open("");
                            win.navigate(url);
                        }
                        else {
                            win = window.open(url);
                        }
                        if (win == null || typeof (win) == 'undefined') {
                            alert('A Popup Blocker may have blocked opening a new window.\nIf this is the case, disable the Popup Blocker for this page and try again.');
                        }
                    };
                    SaveToDlg.prototype.closeBtn_Click = function () {
                        if (!this._remaining || this._remaining.length == 0)
                            $(this.dialogUI.dialog).modal("hide");
                        else
                            this.show(this._remaining);
                    };
                    SaveToDlg.prototype.saveToOneDriveBtn_Click = function (e) {
                        $(this.dialogUI.fileSavingImage).css("display", "block");
                        $(this.dialogUI.googleSaveContainerDiv).css("display", "none");
                        var fileName = $(this.dialogUI.fileNameTextInput).val();
                        if (fileName == null || fileName == "") {
                            fileName = this._fileOriginalName;
                        }
                        this._oneDriveHelper.save(this._convertItem.url, fileName);
                    };
                    SaveToDlg.prototype.saveToSharePointBtn_Click = function (e) {
                        $(this.dialogUI.fileSavingImage).css("display", "block");
                        $(this.dialogUI.googleSaveContainerDiv).css("display", "none");
                        var fileName = $(this.dialogUI.fileNameTextInput).val();
                        if (fileName == null || fileName == "") {
                            fileName = this._fileOriginalName;
                        }
                        this._sharePointHelper.save(this._convertItem.url, fileName);
                    };
                    SaveToDlg.prototype.saveToGoogleDriveBtn_Click = function (e) {
                        var fileName = $(this.dialogUI.fileNameTextInput).val();
                        if (fileName == null || fileName == "") {
                            fileName = this._fileOriginalName;
                        }
                        this._googleDriveHelper.save(this._convertItem.url, fileName);
                        $(this.dialogUI.googleSaveContainerDiv).css("display", "block");
                    };
                    // Save done handler 
                    SaveToDlg.prototype.saveDone = function (error) {
                        $(this.dialogUI.fileSavingImage).css("display", "none");
                        if (error) {
                            window.alert(error);
                        }
                    };
                    SaveToDlg.prototype.fileNameTextInput_Changed = function (e) {
                        var fileName = $(this.dialogUI.fileNameTextInput).val();
                        if (fileName == null || fileName == "") {
                            fileName = this._fileOriginalName;
                        }
                        this._googleDriveHelper.save(this._convertItem.url, fileName);
                    };
                    return SaveToDlg;
                })();
                Dialogs.SaveToDlg = SaveToDlg;
            })(Dialogs = Converter.Dialogs || (Converter.Dialogs = {}));
        })(Converter = DocumentViewerDemo.Converter || (DocumentViewerDemo.Converter = {}));
    })(DocumentViewerDemo = HTML5Demos.DocumentViewerDemo || (HTML5Demos.DocumentViewerDemo = {}));
})(HTML5Demos || (HTML5Demos = {}));
