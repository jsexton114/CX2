var HTML5Demos;
(function (HTML5Demos) {
    var Dialogs;
    (function (Dialogs) {
        //Annotations Load Options 
        var AnnotationsLoadOption;
        (function (AnnotationsLoadOption) {
            AnnotationsLoadOption[AnnotationsLoadOption["none"] = 0] = "none";
            AnnotationsLoadOption[AnnotationsLoadOption["embedded"] = 1] = "embedded";
            AnnotationsLoadOption[AnnotationsLoadOption["external"] = 2] = "external";
        })(AnnotationsLoadOption || (AnnotationsLoadOption = {}));
        // Custom event args for the UploadDocumentDlg load event
        var UploadDocumentEventArgs = (function () {
            function UploadDocumentEventArgs() {
                this.loadEmbeddedAnnotations = false;
            }
            return UploadDocumentEventArgs;
        })();
        Dialogs.UploadDocumentEventArgs = UploadDocumentEventArgs;
        // Custom event args for the OpenDocumentFromUrlDlg load event
        var OpenDocumentFromUrlEventArgs = (function () {
            function OpenDocumentFromUrlEventArgs() {
                this.fileUrl = "";
                this.annotationsUrl = "";
                this.loadEmbeddedAnnotations = false;
            }
            return OpenDocumentFromUrlEventArgs;
        })();
        Dialogs.OpenDocumentFromUrlEventArgs = OpenDocumentFromUrlEventArgs;
        // Custom event args for the OpenDocumentFromUrlDlg load event
        var OpenFromDocumentStorageEventArgs = (function () {
            function OpenFromDocumentStorageEventArgs() {
                this.loadEmbeddedAnnotations = false;
            }
            return OpenFromDocumentStorageEventArgs;
        })();
        Dialogs.OpenFromDocumentStorageEventArgs = OpenFromDocumentStorageEventArgs;
        var UploadDocumentDlg = (function () {
            function UploadDocumentDlg() {
                this._uploadEventArgs = null;
                // Create shortcuts for the dialog UI elements 
                this.dialogUI = {
                    dialog: "#uploadDocumentDialog",
                    documentFileInput: "#documentFile",
                    annotationsLoadOptionsRadioBtnsGroup: "input[name=uploadDocumentDialog_annotationsLoadOptions]",
                    loadExternalAnnotationsControlsDiv: "#loadExternalAnnotationsControls",
                    annotationsFileInput: "#annotationsFile",
                    OkBtn: "#uploadDocumentDialog_OK"
                };
                // Create the arguments
                this._uploadEventArgs = new UploadDocumentEventArgs();
                this.Init();
            }
            UploadDocumentDlg.prototype.Init = function () {
                // Reset the dialog input elements, to avoid cached data
                $(this.dialogUI.documentFileInput).val("");
                $(this.dialogUI.annotationsFileInput).val("");
                $(this.dialogUI.annotationsLoadOptionsRadioBtnsGroup).first().click();
                $(this.dialogUI.annotationsLoadOptionsRadioBtnsGroup).bind("click", $.proxy(this.annotationsLoadOptionsRadioBtnsGroup_BtnClicked, this));
                $(this.dialogUI.OkBtn).bind("click", $.proxy(this.OkBtn_Click, this));
            };
            UploadDocumentDlg.prototype.show = function () {
                $(this.dialogUI.dialog).modal();
            };
            UploadDocumentDlg.prototype.OkBtn_Click = function (e) {
                // Get the file object
                var documentFile = $(this.dialogUI.documentFileInput)[0].files[0];
                if (documentFile == null) {
                    window.alert("Must choose a document file first");
                    return;
                }
                else {
                    this._uploadEventArgs.documenFile = documentFile;
                }
                var selectedAnnotationsLoadOption = $(this.dialogUI.annotationsLoadOptionsRadioBtnsGroup).filter(':checked').val();
                if (selectedAnnotationsLoadOption == AnnotationsLoadOption.none) {
                    this._uploadEventArgs.loadEmbeddedAnnotations = false;
                    this._uploadEventArgs.annotationFile = null;
                }
                else if (selectedAnnotationsLoadOption == AnnotationsLoadOption.embedded) {
                    this._uploadEventArgs.loadEmbeddedAnnotations = true;
                    this._uploadEventArgs.annotationFile = null;
                }
                else if (selectedAnnotationsLoadOption == AnnotationsLoadOption.external) {
                    this._uploadEventArgs.loadEmbeddedAnnotations = false;
                    var annotationsFile = $(this.dialogUI.annotationsFileInput)[0].files[0];
                    if (annotationsFile == null) {
                        window.alert("Must choose an annotations file first");
                        return;
                    }
                    else {
                        this._uploadEventArgs.annotationFile = annotationsFile;
                    }
                }
                $(this.dialogUI.dialog).modal("hide");
                if (this._OkClick != null)
                    // fire the OK click event and pass the upload args
                    this._OkClick(this._uploadEventArgs);
            };
            UploadDocumentDlg.prototype.annotationsLoadOptionsRadioBtnsGroup_BtnClicked = function (e) {
                var selectedAnnotationsLoadOption = $(e.currentTarget).val();
                // If load external annotations , enable annotations file input
                $(this.dialogUI.annotationsFileInput).prop("disabled", !(selectedAnnotationsLoadOption == AnnotationsLoadOption.external));
            };
            Object.defineProperty(UploadDocumentDlg.prototype, "OkClick", {
                // Events mutators
                set: function (value) {
                    this._OkClick = value;
                },
                enumerable: true,
                configurable: true
            });
            return UploadDocumentDlg;
        })();
        Dialogs.UploadDocumentDlg = UploadDocumentDlg;
        var OpenDocumentFromUrlDlg = (function () {
            function OpenDocumentFromUrlDlg() {
                this._openFromUrlEventArgs = null;
                // Create shortcuts for the dialog UI elements 
                this.dialogUI = {
                    dialog: "#openDocumentFromUrlDialog",
                    fileSelectElement: "#fileSelect",
                    urlDiv: "#urlDiv",
                    fileUrlTextInput: "#fileUrl",
                    annotationsLoadOptionsRadioBtnsGroup: "input[name=openDocumentFromUrlDialog_annotationsLoadOptions]",
                    annotationsUrlTextInput: "#annotationsUrl",
                    loadBtn: "#openDocumentFromUrlDialog_load"
                };
                this._demoSamples = ["Leadtools.pdf", "OCR1-4.tif", "Combined.pdf"];
                // Create the arguments
                this._openFromUrlEventArgs = new OpenDocumentFromUrlEventArgs();
                this.Init();
            }
            OpenDocumentFromUrlDlg.prototype.Init = function () {
                // Reset the dialog input elements , , to avoid cached data
                $(this.dialogUI.fileSelectElement).prop("selectedIndex", 0);
                $(this.dialogUI.annotationsLoadOptionsRadioBtnsGroup).first().click();
                $(this.dialogUI.fileSelectElement).bind("change", $.proxy(this.fileSelectElement_SelectedIndexChanged, this));
                $(this.dialogUI.annotationsLoadOptionsRadioBtnsGroup).bind("click", $.proxy(this.annotationsLoadOptionsRadioBtnsGroup_BtnClicked, this));
                $(this.dialogUI.loadBtn).bind("click", $.proxy(this.loadBtn_Click, this));
            };
            OpenDocumentFromUrlDlg.prototype.show = function () {
                $(this.dialogUI.dialog).modal();
            };
            OpenDocumentFromUrlDlg.prototype.fileSelectElement_SelectedIndexChanged = function (e) {
                var showUrlDiv = ($(this.dialogUI.fileSelectElement).val() == "Enter URL");
                showUrlDiv ? $(this.dialogUI.urlDiv).css("display", "block") : $(this.dialogUI.urlDiv).css("display", "none");
            };
            OpenDocumentFromUrlDlg.prototype.loadBtn_Click = function (e) {
                var selectedIndex = $(this.dialogUI.fileSelectElement).find(":selected").index();
                if ($(this.dialogUI.fileSelectElement).val() == "Enter URL") {
                    var documentUrl = $(this.dialogUI.fileUrlTextInput).val();
                    if (documentUrl == null || documentUrl == "") {
                        window.alert("Must enter a document URL first");
                        return;
                    }
                    else {
                        this._openFromUrlEventArgs.fileUrl = $(this.dialogUI.fileUrlTextInput).val();
                    }
                }
                else {
                    var sample = this._demoSamples[selectedIndex];
                    var newDocumentUrl = 'Samples/' + sample;
                    var serviceBase = lt.Documents.DocumentFactory.serviceUri;
                    var serviceApiPath = lt.Documents.DocumentFactory.serviceApiPath;
                    if (serviceApiPath) {
                        var serviceApiPathIndex = serviceBase.indexOf(serviceApiPath);
                        if (serviceApiPathIndex !== -1) {
                            serviceBase = serviceBase.substring(0, serviceApiPathIndex);
                        }
                    }
                    if (serviceBase.charAt(serviceBase.length - 1) !== "/")
                        serviceBase += "/";
                    newDocumentUrl = serviceBase + newDocumentUrl;
                    this._openFromUrlEventArgs.fileUrl = newDocumentUrl;
                }
                var selectedAnnotationsLoadOption = $(this.dialogUI.annotationsLoadOptionsRadioBtnsGroup).filter(':checked').val();
                if (selectedAnnotationsLoadOption == AnnotationsLoadOption.none) {
                    this._openFromUrlEventArgs.loadEmbeddedAnnotations = false;
                    this._openFromUrlEventArgs.annotationsUrl = null;
                }
                else if (selectedAnnotationsLoadOption == AnnotationsLoadOption.embedded) {
                    this._openFromUrlEventArgs.loadEmbeddedAnnotations = true;
                    this._openFromUrlEventArgs.annotationsUrl = null;
                }
                else if (selectedAnnotationsLoadOption == AnnotationsLoadOption.external) {
                    this._openFromUrlEventArgs.loadEmbeddedAnnotations = false;
                    var annotationsUrl = $(this.dialogUI.annotationsUrlTextInput).val();
                    if (annotationsUrl == null || annotationsUrl == "") {
                        window.alert("Must enter an external annotations URL first");
                        return;
                    }
                    else {
                        this._openFromUrlEventArgs.annotationsUrl = $(this.dialogUI.annotationsUrlTextInput).val();
                    }
                }
                $(this.dialogUI.dialog).modal("hide");
                if (this._loadClick != null)
                    // fire the loadClick event , and pass the load args
                    this._loadClick(this._openFromUrlEventArgs);
            };
            OpenDocumentFromUrlDlg.prototype.annotationsLoadOptionsRadioBtnsGroup_BtnClicked = function (e) {
                var selectedAnnotationsLoadOption = $(e.currentTarget).val();
                // If load external annotations , enable annotations url text input
                $(this.dialogUI.annotationsUrlTextInput).prop("disabled", !(selectedAnnotationsLoadOption == AnnotationsLoadOption.external));
            };
            Object.defineProperty(OpenDocumentFromUrlDlg.prototype, "loadClick", {
                // Events mutators
                set: function (value) {
                    this._loadClick = value;
                },
                enumerable: true,
                configurable: true
            });
            return OpenDocumentFromUrlDlg;
        })();
        Dialogs.OpenDocumentFromUrlDlg = OpenDocumentFromUrlDlg;
        var OpenFromDocumentStorageDlg = (function () {
            function OpenFromDocumentStorageDlg() {
                this._openFromDocumentStorageEventArgs = null;
                // Create shortcuts for the dialog UI elements 
                this.dialogUI = {
                    dialog: "#openFromDocumentStorageDialog",
                    openFromDocumentStorageInformation: "#openFromDocumentStorageInformation",
                    openDocumentFromOneDriveBtn: "#openDocumentFromOneDrive",
                    openDocumentFromSharePointBtn: "#openDocumentFromSharePoint",
                    openDocumentFromGoogleDriveBtn: "#openDocumentFromGoogleDrive",
                    documentFileNameTextInput: "#documentFileName",
                    documentLoadingImage: "#documentLoading",
                    annotationsLoadOptionsRadioBtnsGroup: "input[name=openFromDocumentStorageDialog_annotationsLoadOptions]",
                    openAnnotationsFromOneDriveBtn: "#openAnnotationsFromOneDrive",
                    openAnnotationsFromSharePointBtn: "#openAnnotationsFromSharePoint",
                    openAnnotationsFromGoogleDriveBtn: "#openAnnotationsFromGoogleDrive",
                    annotationsFileNameTextInput: "#annotationsFileName",
                    annotationsLoadingImage: "#annotationsLoading",
                    OkBtn: "#openFromDocumentStorageDialog_OK"
                };
                // Create the arguments
                this._openFromDocumentStorageEventArgs = new OpenFromDocumentStorageEventArgs();
                this.Init();
            }
            Object.defineProperty(OpenFromDocumentStorageDlg.prototype, "sharePointHelper", {
                set: function (value) {
                    this._sharePointHelper = value;
                },
                enumerable: true,
                configurable: true
            });
            OpenFromDocumentStorageDlg.prototype.InitDriveHelpers = function () {
                var _this = this;
                // OneDrive
                this._oneDriveHelper = new HTML5Demos.DriveHelper.LTOneDrive.OneDriveHelper();
                this._oneDriveHelper.openDone = function (file) { return _this.openDone(file); };
                // SharePoint
                this._sharePointHelper.openDone = function (file) { return _this.openDone(file); };
                // GoogleDrive
                // if IE9, Google Drive (which is not supported) will throw an error. So don't create it.
                if (!(lt.LTHelper.browser === lt.LTBrowser.internetExplorer && lt.LTHelper.version <= 9)) {
                    this._googleDriveHelper = new HTML5Demos.DriveHelper.LTGoogleDrive.GoogleDriveHelper();
                    this._googleDriveHelper.openDone = function (file) { return _this.openDone(file); };
                }
            };
            OpenFromDocumentStorageDlg.prototype.Init = function () {
                // Reset the dialog input elements, to avoid cached data
                $(this.dialogUI.annotationsLoadOptionsRadioBtnsGroup).first().click();
                // Right now Google Drive and Microsoft OneDrive will get blocked on Microsoft Edge, so we disable them.
                if (lt.LTHelper.browser == lt.LTBrowser.edge) {
                    $(this.dialogUI.openFromDocumentStorageInformation).text("Open from Microsoft OneDrive or Google Drive is not currently supported by Microsoft Edge.");
                    $(this.dialogUI.openDocumentFromOneDriveBtn).prop("disabled", true);
                    $(this.dialogUI.openDocumentFromGoogleDriveBtn).prop("disabled", true);
                    $(this.dialogUI.openAnnotationsFromOneDriveBtn).prop("disabled", true);
                    $(this.dialogUI.openAnnotationsFromGoogleDriveBtn).prop("disabled", true);
                }
                else {
                    $(this.dialogUI.openDocumentFromOneDriveBtn).bind("click", $.proxy(this.openDocumentFromOneDriveBtn_Clicked, this));
                    $(this.dialogUI.openDocumentFromGoogleDriveBtn).bind("click", $.proxy(this.openDocumentFromGoogleDriveBtn_Clicked, this));
                    $(this.dialogUI.openAnnotationsFromOneDriveBtn).bind("click", $.proxy(this.openAnnotationsFromOneDriveBtn_Clicked, this));
                    $(this.dialogUI.openAnnotationsFromGoogleDriveBtn).bind("click", $.proxy(this.openAnnotationsFromGoogleDriveBtn_Clicked, this));
                }
                $(this.dialogUI.openDocumentFromSharePointBtn).bind("click", $.proxy(this.openDocumentFromSharePointBtn_Clicked, this));
                $(this.dialogUI.openAnnotationsFromSharePointBtn).bind("click", $.proxy(this.openAnnotationsFromSharePointBtn_Clicked, this));
                $(this.dialogUI.annotationsLoadOptionsRadioBtnsGroup).bind("click", $.proxy(this.annotationsLoadOptionsRadioBtnsGroup_BtnClicked, this));
                $(this.dialogUI.OkBtn).bind("click", $.proxy(this.OkBtn_Click, this));
            };
            OpenFromDocumentStorageDlg.prototype.show = function () {
                $(this.dialogUI.dialog).modal();
            };
            OpenFromDocumentStorageDlg.prototype.openDocumentFromOneDriveBtn_Clicked = function (e) {
                this._isAnnotationsFile = false;
                this._oneDriveHelper.open();
                $(this.dialogUI.documentLoadingImage).css("display", "block");
            };
            OpenFromDocumentStorageDlg.prototype.openDocumentFromSharePointBtn_Clicked = function (e) {
                this._isAnnotationsFile = false;
                this._sharePointHelper.open();
                $(this.dialogUI.documentLoadingImage).css("display", "block");
            };
            OpenFromDocumentStorageDlg.prototype.openDocumentFromGoogleDriveBtn_Clicked = function (e) {
                this._isAnnotationsFile = false;
                this._googleDriveHelper.open();
                $(this.dialogUI.documentLoadingImage).css("display", "block");
            };
            OpenFromDocumentStorageDlg.prototype.openAnnotationsFromOneDriveBtn_Clicked = function (e) {
                this._isAnnotationsFile = true;
                this._oneDriveHelper.open();
                $(this.dialogUI.annotationsLoadingImage).css("display", "block");
            };
            OpenFromDocumentStorageDlg.prototype.openAnnotationsFromSharePointBtn_Clicked = function (e) {
                this._isAnnotationsFile = true;
                this._sharePointHelper.open();
                $(this.dialogUI.annotationsLoadingImage).css("display", "block");
            };
            OpenFromDocumentStorageDlg.prototype.openAnnotationsFromGoogleDriveBtn_Clicked = function (e) {
                this._isAnnotationsFile = true;
                this._googleDriveHelper.open();
                $(this.dialogUI.annotationsLoadingImage).css("display", "block");
            };
            // Open done handler 
            OpenFromDocumentStorageDlg.prototype.openDone = function (file) {
                if (!this._isAnnotationsFile) {
                    // Open document file
                    $(this.dialogUI.documentLoadingImage).css("display", "none");
                    if (file) {
                        $(this.dialogUI.documentFileNameTextInput).css("visibility", "visible");
                        $(this.dialogUI.documentFileNameTextInput).val(file.name);
                        this._openFromDocumentStorageEventArgs.documentFile = file;
                    }
                }
                else {
                    // Open annotations file
                    $(this.dialogUI.annotationsLoadingImage).css("display", "none");
                    if (file) {
                        $(this.dialogUI.annotationsFileNameTextInput).css("visibility", "visible");
                        $(this.dialogUI.annotationsFileNameTextInput).val(file.name);
                        this._openFromDocumentStorageEventArgs.annotationsFile = file;
                    }
                }
            };
            OpenFromDocumentStorageDlg.prototype.annotationsLoadOptionsRadioBtnsGroup_BtnClicked = function (e) {
                var selectedAnnotationsLoadOption = $(e.currentTarget).val();
                // If load external annotations , enable annotations url text input
                $(this.dialogUI.openAnnotationsFromSharePointBtn).prop("disabled", !(selectedAnnotationsLoadOption == AnnotationsLoadOption.external));
                // They must be disabled on Microsoft Edge
                if (lt.LTHelper.browser != lt.LTBrowser.edge) {
                    $(this.dialogUI.openAnnotationsFromOneDriveBtn).prop("disabled", !(selectedAnnotationsLoadOption == AnnotationsLoadOption.external));
                    $(this.dialogUI.openAnnotationsFromGoogleDriveBtn).prop("disabled", !(selectedAnnotationsLoadOption == AnnotationsLoadOption.external));
                }
            };
            OpenFromDocumentStorageDlg.prototype.OkBtn_Click = function (e) {
                if (this._openFromDocumentStorageEventArgs.documentFile == null) {
                    window.alert("Must choose a document file first");
                    return;
                }
                var selectedAnnotationsLoadOption = $(this.dialogUI.annotationsLoadOptionsRadioBtnsGroup).filter(':checked').val();
                if (selectedAnnotationsLoadOption == AnnotationsLoadOption.none) {
                    this._openFromDocumentStorageEventArgs.loadEmbeddedAnnotations = false;
                    this._openFromDocumentStorageEventArgs.annotationsFile = null;
                }
                else if (selectedAnnotationsLoadOption == AnnotationsLoadOption.embedded) {
                    this._openFromDocumentStorageEventArgs.loadEmbeddedAnnotations = true;
                    this._openFromDocumentStorageEventArgs.annotationsFile = null;
                }
                else if (selectedAnnotationsLoadOption == AnnotationsLoadOption.external) {
                    this._openFromDocumentStorageEventArgs.loadEmbeddedAnnotations = false;
                    var annotationsFile = this._openFromDocumentStorageEventArgs.annotationsFile;
                    if (annotationsFile == null) {
                        window.alert("Must choose an annotations file first");
                        return;
                    }
                }
                $(this.dialogUI.dialog).modal("hide");
                if (this._OkClick != null)
                    // fire the OkClick event , and pass the open args
                    this._OkClick(this._openFromDocumentStorageEventArgs);
            };
            Object.defineProperty(OpenFromDocumentStorageDlg.prototype, "OkClick", {
                // Events mutators
                set: function (value) {
                    this._OkClick = value;
                },
                enumerable: true,
                configurable: true
            });
            return OpenFromDocumentStorageDlg;
        })();
        Dialogs.OpenFromDocumentStorageDlg = OpenFromDocumentStorageDlg;
    })(Dialogs = HTML5Demos.Dialogs || (HTML5Demos.Dialogs = {}));
})(HTML5Demos || (HTML5Demos = {}));
