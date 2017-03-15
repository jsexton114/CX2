var HTML5Demos;
(function (HTML5Demos) {
    var Dialogs;
    (function (Dialogs) {
        var DocumentPropertiesDlg = (function () {
            function DocumentPropertiesDlg() {
                // Create shortcuts for the dialog UI elements
                this.dialogUI = {
                    dialog: "#documentPropertiesDialog",
                    documentInfoTableBodyElement: "#documentInfo",
                    metadataTableBodyElement: "#metadata"
                };
            }
            DocumentPropertiesDlg.prototype.show = function (document) {
                this.setup(document);
                $(this.dialogUI.dialog).modal();
            };
            DocumentPropertiesDlg.prototype.setup = function (document) {
                // Ensure that any open panels are closed before showing the dialog
                $('#dialogPanelsContainer .in').collapse('hide');
                var documentInfo = new Array();
                documentInfo["Document ID"] = document.documentId;
                documentInfo["URL"] = document.uri;
                documentInfo["MIME Type"] = document.mimeType;
                documentInfo["Encrypted"] = document.isDecrypted ? "Yes" : "No";
                if (document.annotations.annotationsUri != null) {
                    documentInfo["Annotations URL"] = document.annotations.annotationsUri;
                }
                documentInfo["Pages"] = document.pages.length.toString();
                if (document.pages.length > 0) {
                    var page = document.pages[0];
                    var pageSize = page.size;
                    var sizeInchdes = lt.LeadSizeD.create(pageSize.width / lt.Documents.Document.unitsPerInch, pageSize.height / lt.Documents.Document.unitsPerInch);
                    var sizePixels = document.sizeToPixels(pageSize);
                    documentInfo["Page size"] = sizeInchdes.width.toString() + " x " + sizeInchdes.height.toString() + " in (" + sizePixels.width.toString() + " x " + sizePixels.height.toString() + " px)";
                }
                this.generateDocumentInfoTable(documentInfo);
                this.generateMetadataTable(document.metadata);
                $(this.dialogUI.dialog).modal();
            };
            DocumentPropertiesDlg.prototype.generateDocumentInfoTable = function (documentInfo) {
                var table = "";
                for (var key in documentInfo) {
                    if (documentInfo.hasOwnProperty(key)) {
                        table += "<tr>";
                        table += "<td>" + key + "</td>";
                        table += "<td>" + documentInfo[key] + "</td>";
                        table += "</tr>";
                    }
                }
                $(this.dialogUI.documentInfoTableBodyElement).html(table);
            };
            DocumentPropertiesDlg.prototype.generateMetadataTable = function (metadata) {
                var table = "";
                for (var key in metadata) {
                    if (metadata.hasOwnProperty(key)) {
                        table += "<tr>";
                        table += "<td>" + key + "</td>";
                        table += "<td>" + metadata[key] + "</td>";
                        table += "</tr>";
                    }
                }
                $(this.dialogUI.metadataTableBodyElement).html(table);
            };
            return DocumentPropertiesDlg;
        })();
        Dialogs.DocumentPropertiesDlg = DocumentPropertiesDlg;
    })(Dialogs = HTML5Demos.Dialogs || (HTML5Demos.Dialogs = {}));
})(HTML5Demos || (HTML5Demos = {}));
