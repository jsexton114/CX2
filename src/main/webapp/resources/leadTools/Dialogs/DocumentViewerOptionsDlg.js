var HTML5Demos;
(function (HTML5Demos) {
    var Dialogs;
    (function (Dialogs) {
        var DocumentViewerOptionsDlg = (function () {
            function DocumentViewerOptionsDlg() {
                // Create shortcuts for the dialog UI elements 
                this.dialogUI = {
                    dialog: "#DocumentViewerOptionsDialog",
                    hookPrepareAjax: "#hookPrepareAjax",
                    useAjaxImageLoading: "#useAjaxImageLoading",
                    viewOptions: {
                        numberOfWorkersTextInput: "#viewNumberOfWorkers",
                        lazyLoadCheckbox: "#viewLazyLoad"
                    },
                    thumbnailsOptions: {
                        numberOfWorkersTextInput: "#thumbnailsNumberOfWorkers",
                        lazyLoadCheckbox: "#thumbnailsLazyLoad",
                        useGridsCheckbox: "#useGrids",
                        pixelSizeTextInput: "#pixelSize",
                    },
                    OkBtn: "#DocumentViewerOptionsDlg_OK",
                };
                this.Init();
            }
            Object.defineProperty(DocumentViewerOptionsDlg.prototype, "documentViewer", {
                set: function (value) {
                    this._documentViewer = value;
                    // Initialize the inputs values from the viewer
                    this.initInputsValues();
                },
                enumerable: true,
                configurable: true
            });
            Object.defineProperty(DocumentViewerOptionsDlg.prototype, "hookPrepareAjax", {
                get: function () {
                    return this._hookPrepareAjax;
                },
                set: function (value) {
                    this._hookPrepareAjax = value;
                },
                enumerable: true,
                configurable: true
            });
            Object.defineProperty(DocumentViewerOptionsDlg.prototype, "ok", {
                get: function () {
                    return this._ok;
                },
                set: function (value) {
                    this._ok = value;
                },
                enumerable: true,
                configurable: true
            });
            DocumentViewerOptionsDlg.prototype.Init = function () {
                $(this.dialogUI.viewOptions.numberOfWorkersTextInput).bind("change", $.proxy(this.viewNumberOfWorkersTextInput_Changed, this));
                $(this.dialogUI.thumbnailsOptions.numberOfWorkersTextInput).bind("change", $.proxy(this.thumbnailsNumberOfWorkersTextInput_Changed, this));
                $(this.dialogUI.thumbnailsOptions.pixelSizeTextInput).bind("change", $.proxy(this.pixelSizeTextInput_Changed, this));
                $(this.dialogUI.thumbnailsOptions.useGridsCheckbox).bind("change", $.proxy(this.useGridsCheckbox_CheckedChanged, this));
                $(this.dialogUI.OkBtn).bind("click", $.proxy(this.OkBtn_Click, this));
            };
            DocumentViewerOptionsDlg.prototype.initInputsValues = function () {
                // Document viewer
                $(this.dialogUI.hookPrepareAjax).prop('checked', this.hookPrepareAjax);
                $(this.dialogUI.useAjaxImageLoading).prop('checked', this._documentViewer.useAjaxImageLoading);
                // View
                $(this.dialogUI.viewOptions.numberOfWorkersTextInput).val(this._documentViewer.view.workerCount.toString());
                $(this.dialogUI.viewOptions.lazyLoadCheckbox).prop('checked', this._documentViewer.view.lazyLoad);
                // Thumbnails
                if (this._documentViewer.thumbnails == null) {
                    // Disabled and clear all thumbnails options
                    $(this.dialogUI.thumbnailsOptions.numberOfWorkersTextInput).prop("disabled", true);
                    $(this.dialogUI.thumbnailsOptions.numberOfWorkersTextInput).val("");
                    $(this.dialogUI.thumbnailsOptions.lazyLoadCheckbox).prop("disabled", true);
                    $(this.dialogUI.thumbnailsOptions.lazyLoadCheckbox).prop('checked', false);
                    $(this.dialogUI.thumbnailsOptions.useGridsCheckbox).prop("disabled", true);
                    $(this.dialogUI.thumbnailsOptions.useGridsCheckbox).prop('checked', false);
                    $(this.dialogUI.thumbnailsOptions.pixelSizeTextInput).prop("disabled", true);
                    $(this.dialogUI.thumbnailsOptions.pixelSizeTextInput).val("");
                }
                else {
                    // Enable all thumbnails options , and set the values from the viewer
                    $(this.dialogUI.thumbnailsOptions.numberOfWorkersTextInput).prop("disabled", false);
                    $(this.dialogUI.thumbnailsOptions.numberOfWorkersTextInput).val(this._documentViewer.thumbnails.workerCount.toString());
                    $(this.dialogUI.thumbnailsOptions.lazyLoadCheckbox).prop("disabled", false);
                    $(this.dialogUI.thumbnailsOptions.lazyLoadCheckbox).prop('checked', this._documentViewer.thumbnails.lazyLoad);
                    $(this.dialogUI.thumbnailsOptions.useGridsCheckbox).prop("disabled", false);
                    $(this.dialogUI.thumbnailsOptions.useGridsCheckbox).prop('checked', this._documentViewer.thumbnails.useGrids);
                    $(this.dialogUI.thumbnailsOptions.pixelSizeTextInput).prop("disabled", !this._documentViewer.thumbnails.useGrids);
                    $(this.dialogUI.thumbnailsOptions.pixelSizeTextInput).val(this._documentViewer.thumbnails.gridPixelSize.toString());
                }
            };
            DocumentViewerOptionsDlg.prototype.show = function () {
                $(this.dialogUI.dialog).modal();
            };
            DocumentViewerOptionsDlg.prototype.viewNumberOfWorkersTextInput_Changed = function (e) {
                // Check for valid inputs from the user(integer number : must be 1 and up)
                var value = parseInt($(e.currentTarget).val());
                if (!(value && value >= 1)) {
                    $(e.currentTarget).val(this._documentViewer.view.workerCount.toString());
                }
            };
            DocumentViewerOptionsDlg.prototype.thumbnailsNumberOfWorkersTextInput_Changed = function (e) {
                // Check for valid inputs from the user(integer number : must be 1 and up)
                var value = parseInt($(e.currentTarget).val());
                if (!(value && value >= 1)) {
                    $(e.currentTarget).val(this._documentViewer.thumbnails.workerCount.toString());
                }
            };
            DocumentViewerOptionsDlg.prototype.pixelSizeTextInput_Changed = function (e) {
                // Check for valid inputs from the user(integer number : must be 1 and up)
                var value = parseInt($(e.currentTarget).val());
                if (!(value && value >= 1 && value <= 4000)) {
                    $(e.currentTarget).val(this._documentViewer.thumbnails.gridPixelSize.toString());
                }
            };
            DocumentViewerOptionsDlg.prototype.useGridsCheckbox_CheckedChanged = function (e) {
                $(this.dialogUI.thumbnailsOptions.pixelSizeTextInput).prop("disabled", !($(e.currentTarget).is(':checked')));
            };
            DocumentViewerOptionsDlg.prototype.OkBtn_Click = function (e) {
                $(this.dialogUI.dialog).modal("hide");
                this.hookPrepareAjax = $(this.dialogUI.hookPrepareAjax).is(':checked');
                this._documentViewer.useAjaxImageLoading = $(this.dialogUI.useAjaxImageLoading).is(':checked');
                this._documentViewer.view.workerCount = parseInt($(this.dialogUI.viewOptions.numberOfWorkersTextInput).val());
                this._documentViewer.view.lazyLoad = $(this.dialogUI.viewOptions.lazyLoadCheckbox).is(':checked');
                if (this._documentViewer.thumbnails != null) {
                    this._documentViewer.thumbnails.workerCount = parseInt($(this.dialogUI.thumbnailsOptions.numberOfWorkersTextInput).val());
                    this._documentViewer.thumbnails.lazyLoad = $(this.dialogUI.thumbnailsOptions.lazyLoadCheckbox).is(':checked');
                    this._documentViewer.thumbnails.useGrids = $(this.dialogUI.thumbnailsOptions.useGridsCheckbox).is(':checked');
                    this._documentViewer.thumbnails.gridPixelSize = parseInt($(this.dialogUI.thumbnailsOptions.pixelSizeTextInput).val());
                }
                if (this._ok != null) {
                    this._ok();
                }
            };
            return DocumentViewerOptionsDlg;
        })();
        Dialogs.DocumentViewerOptionsDlg = DocumentViewerOptionsDlg;
    })(Dialogs = HTML5Demos.Dialogs || (HTML5Demos.Dialogs = {}));
})(HTML5Demos || (HTML5Demos = {}));
