﻿
/**************************************************
LEADTOOLS (C) 1991-2016 LEAD Technologies, Inc. ALL RIGHTS RESERVED.
This software is protected by United States and International copyright laws.
Any copying, duplication, deployment, redistribution, modification or other
disposition hereof is STRICTLY PROHIBITED without an express written license
granted by LEAD Technologies, Inc.. This notice may not be removed or otherwise
altered under any circumstances.
Portions of this product are licensed under US patent 5,327,254 and foreign
counterparts.
For more information, contact LEAD Technologies, Inc. at 704-332-5532 or visit
https://www.leadtools.com
**************************************************/
// Leadtools.Documents.js
// Version:19.0.0.2
var lt;
(function(c) {
    (function(d) {
        (function(h) {
            (function(b) {
                b[b.unknown = 0] = "unknown";
                b[b.ean13 = 1] = "ean13";
                b[b.ean8 = 2] = "ean8";
                b[b.upca = 3] = "upca";
                b[b.upce = 4] = "upce";
                b[b.code3Of9 = 5] = "code3Of9";
                b[b.code128 = 6] = "code128";
                b[b.codeInterleaved2Of5 = 7] = "codeInterleaved2Of5";
                b[b.codabar = 8] = "codabar";
                b[b.uccean128 = 9] = "uccean128";
                b[b.code93 = 10] = "code93";
                b[b.eanext5 = 11] = "eanext5";
                b[b.eanext2 = 12] = "eanext2";
                b[b.msi = 13] = "msi";
                b[b.code11 = 14] = "code11";
                b[b.codeStandard2Of5 = 15] = "codeStandard2Of5";
                b[b.gs1Databar = 16] = "gs1Databar";
                b[b.gs1DatabarLimited =
                    17] = "gs1DatabarLimited";
                b[b.gs1DatabarExpanded = 18] = "gs1DatabarExpanded";
                b[b.patchCode = 19] = "patchCode";
                b[b.postNet = 20] = "postNet";
                b[b.planet = 21] = "planet";
                b[b.australianPost4State = 22] = "australianPost4State";
                b[b.royalMail4State = 23] = "royalMail4State";
                b[b.usps4State = 24] = "usps4State";
                b[b.gs1DatabarStacked = 25] = "gs1DatabarStacked";
                b[b.gs1DatabarExpandedStacked = 26] =
                    "gs1DatabarExpandedStacked";
                b[b.pdf417 = 27] = "pdf417";
                b[b.microPDF417 = 28] = "microPDF417";
                b[b.datamatrix = 29] = "datamatrix";
                b[b.qr = 30] = "qr";
                b[b.aztec =
                    31] = "aztec";
                b[b.maxi = 32] = "maxi";
                b[b.microQR = 33] = "microQR"
            })(h.BarcodeSymbology || (h.BarcodeSymbology = {}));
            var a = h.BarcodeSymbology,
                g = function() {
                    function b() {
                        this._symbology = a.unknown;
                        this._bounds = c.LeadRectD.empty;
                        this._rotationAngle = 0;
                        this._data = [];
                        this._value = ""
                    }
                    b.fromJson = function(a) {
                        if (null == a) return null;
                        var f = new b;
                        return !f.copyFromJson(a) ? null : f
                    };
                    b.prototype.copyFromJson = function(b) {
                        if (null == b || !d.Internal.PropertyBag.copyMatchingProperties(
                                b, this, ["data"])) return !1;
                        b = b.data;
                        null != b && 0 < b.length &&
                            (this._data = c.LTHelper.base64Decode(b));
                        return !0
                    };
                    Object.defineProperty(b.prototype, "symbology", {
                        get: function() {
                            return this._symbology
                        },
                        set: function(b) {
                            this._symbology = b
                        },
                        enumerable: !0,
                        configurable: !0
                    });
                    Object.defineProperty(b.prototype, "bounds", {
                        get: function() {
                            return this._bounds
                        },
                        set: function(b) {
                            this._bounds = b
                        },
                        enumerable: !0,
                        configurable: !0
                    });
                    Object.defineProperty(b.prototype, "rotationAngle", {
                        get: function() {
                            return this._rotationAngle
                        },
                        set: function(b) {
                            this._rotationAngle = b
                        },
                        enumerable: !0,
                        configurable: !0
                    });
                    Object.defineProperty(b.prototype, "data", {
                        get: function() {
                            return this._data
                        },
                        set: function(b) {
                            this._data = b
                        },
                        enumerable: !0,
                        configurable: !0
                    });
                    Object.defineProperty(b.prototype, "value", {
                        get: function() {
                            return this._value
                        },
                        set: function(b) {
                            this._value = b
                        },
                        enumerable: !0,
                        configurable: !0
                    });
                    return b
                }();
            h.BarcodeData = g
        })(d.Barcode || (d.Barcode = {}))
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(c) {
        (function(b) {
            b[b.auto = 0] = "auto";
            b[b.disabled = 1] = "disabled";
            b[b.always = 2] = "always"
        })(c.DocumentConverterSvgImagesRecognitionMode || (c.DocumentConverterSvgImagesRecognitionMode = {}));
        var h = c.DocumentConverterSvgImagesRecognitionMode;
        (function(b) {
            b[b.none = 0] = "none";
            b[b.skip = 1] = "skip";
            b[b.skipIgnoreAnnotations = 2] = "skipIgnoreAnnotations"
        })(c.DocumentConverterEmptyPageMode || (c.DocumentConverterEmptyPageMode = {}));
        var a = c.DocumentConverterEmptyPageMode;
        (function(b) {
            b[b.abort = 0] = "abort";
            b[b.resume = 1] = "resume"
        })(c.DocumentConverterJobErrorMode || (c.DocumentConverterJobErrorMode = {}));
        var g = c.DocumentConverterJobErrorMode;
        (function(b) {
            b[b.none = 0] = "none";
            b[b.external = 1] = "external";
            b[b.overlay = 2] = "overlay";
            b[b.embed = 3] = "embed"
        })(c.DocumentConverterAnnotationsMode || (c.DocumentConverterAnnotationsMode = {}));
        var b = c.DocumentConverterAnnotationsMode,
            e = function() {
                return function() {
                    this.jobErrorMode = g.resume;
                    this.pageNumberingTemplate =
                        "##name##_Page(##page##).##extension##";
                    this.enableSvgConversion = !0;
                    this.svgImagesRecognitionMode = h.auto;
                    this.emptyPageMode = a.none;
                    this.preprocessorInvert = this.preprocessorOrient = this.preprocessorDeskew = !
                        1;
                    this.inputDocumentLastPageNumber = this.inputDocumentFirstPageNumber =
                        0;
                    this.documentFormat = c.Writers.DocumentFormat.user;
                    this.rasterImageFormat = c.RasterImageFormat.unknown;
                    this.rasterImageBitsPerPixel = 0;
                    this.jobName = this.documentOptions = null;
                    this.annotationsMode = b.none;
                    this.annotations = this.documentName = null
                }
            }();
        c.DocumentConverterJobData = e
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(c) {
        var h = function() {
            function a(a) {
                this._document = a
            }
            Object.defineProperty(a.prototype, "document", {
                get: function() {
                    return this._document
                },
                enumerable: !0,
                configurable: !0
            });
            return a
        }();
        c.DocumentBarcodes = h
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    c = c.Documents || (c.Documents = {});
    c = c.RasterImageFormat || (c.RasterImageFormat = {});
    c[c.unknown = 0] = "unknown";
    c[c.tifJpeg422 = 24] = "tifJpeg422";
    c[c.ccittGroup4 = 29] = "ccittGroup4";
    c[c.rasPdfJpeg = 150] = "rasPdfJpeg";
    c[c.rasPdfG4 = 149] = "rasPdfG4"
})(lt || (lt = {}));
(function(c) {
    (function(c) {
        (function(c) {
            var a = function() {
                function a(b) {
                    this._format = b
                }
                Object.defineProperty(a.prototype, "format", {
                    get: function() {
                        return this._format
                    },
                    enumerable: !0,
                    configurable: !0
                });
                return a
            }();
            c.DocumentOptions = a
        })(c.Writers || (c.Writers = {}))
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
var __extends = this && this.__extends || function(c, d) {
    function h() {
        this.constructor = c
    }
    for (var a in d) d.hasOwnProperty(a) && (c[a] = d[a]);
    c.prototype = null === d ? Object.create(d) : (h.prototype = d.prototype,
        new h)
};
(function(c) {
    (function(c) {
        (function(c) {
            var a = function(a) {
                function b() {
                    a.call(this, c.DocumentFormat.rtf);
                    this.textMode = c.DocumentTextMode.auto
                }
                __extends(b, a);
                return b
            }(c.DocumentOptions);
            c.RtfDocumentOptions = a
        })(c.Writers || (c.Writers = {}))
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(c) {
        (function(c) {
            (function(b) {
                b[b.ansi = 0] = "ansi";
                b[b.unicode = 1] = "unicode";
                b[b.unicodeBigEndian = 2] = "unicodeBigEndian";
                b[b.utf8 = 3] = "utf8"
            })(c.TextDocumentType || (c.TextDocumentType = {}));
            var a = c.TextDocumentType,
                g = function(b) {
                    function g() {
                        b.call(this, c.DocumentFormat.text);
                        this.documentType = a.ansi;
                        this.formatted = this.addPageBreak = this.addPageNumber = !1
                    }
                    __extends(g, b);
                    return g
                }(c.DocumentOptions);
            c.TextDocumentOptions = g
        })(c.Writers || (c.Writers = {}))
    })(c.Documents || (c.Documents = {}))
})(lt ||
    (lt = {}));
(function(c) {
    (function(c) {
        (function(c) {
            var a = function(a) {
                function b() {
                    a.call(this, c.DocumentFormat.svg)
                }
                __extends(b, a);
                return b
            }(c.DocumentOptions);
            c.SvgDocumentOptions = a;
            a = function(a) {
                function b() {
                    a.call(this, c.DocumentFormat.emf)
                }
                __extends(b, a);
                return b
            }(c.DocumentOptions);
            c.EmfDocumentOptions = a
        })(c.Writers || (c.Writers = {}))
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(c) {
        (function(c) {
            var a = function(a) {
                function b() {
                    a.call(this, c.DocumentFormat.xls)
                }
                __extends(b, a);
                return b
            }(c.DocumentOptions);
            c.XlsDocumentOptions = a
        })(c.Writers || (c.Writers = {}))
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(c) {
        (function(c) {
            (function(b) {
                b[b.pdf = 0] = "pdf";
                b[b.pdfA = 1] = "pdfA";
                b[b.pdf12 = 2] = "pdf12";
                b[b.pdf13 = 3] = "pdf13";
                b[b.pdf15 = 4] = "pdf15";
                b[b.pdf16 = 5] = "pdf16"
            })(c.PdfDocumentType || (c.PdfDocumentType = {}));
            var a = c.PdfDocumentType;
            (function(b) {
                b[b.rc40Bit = 0] = "rc40Bit";
                b[b.rc128Bit = 1] = "rc128Bit"
            })(c.PdfDocumentEncryptionMode || (c.PdfDocumentEncryptionMode = {}));
            var g = c.PdfDocumentEncryptionMode;
            (function(b) {
                b[b.pageOnly = 0] = "pageOnly";
                b[b.fullScreen = 3] = "fullScreen";
                b[b.bookmarksAndPage = 1] = "bookmarksAndPage";
                b[b.thumbnailAndPage = 2] = "thumbnailAndPage";
                b[b.layerAndPage = 4] = "layerAndPage";
                b[b.attachmentsAndPage = 5] = "attachmentsAndPage"
            })(c.PdfDocumentPageModeType || (c.PdfDocumentPageModeType = {}));
            var b = c.PdfDocumentPageModeType;
            (function(b) {
                b[b.singlePageDisplay = 0] = "singlePageDisplay";
                b[b.oneColumnDisplay = 1] = "oneColumnDisplay";
                b[b.twoColumnLeftDisplay = 2] = "twoColumnLeftDisplay";
                b[b.twoColumnRightDisplay = 3] = "twoColumnRightDisplay";
                b[b.twoPageLeft = 4] = "twoPageLeft";
                b[b.twoPageRight = 5] = "twoPageRight"
            })(c.PdfDocumentPageLayoutType ||
                (c.PdfDocumentPageLayoutType = {}));
            var e = c.PdfDocumentPageLayoutType;
            (function(b) {
                b[b.defaultType = 0] = "defaultType";
                b[b.fitWidth = 1] = "fitWidth";
                b[b.fitHeight = 2] = "fitHeight";
                b[b.fitWidthBounds = 3] = "fitWidthBounds";
                b[b.fitHeightBounds = 4] = "fitHeightBounds";
                b[b.fitBounds = 5] = "fitBounds"
            })(c.PdfDocumentPageFitType || (c.PdfDocumentPageFitType = {}));
            var f = c.PdfDocumentPageFitType,
                i = function(i) {
                    function d() {
                        i.call(this, c.DocumentFormat.pdf);
                        this.documentType = a.pdf;
                        this.fontEmbedMode = c.DocumentFontEmbedMode.auto;
                        this.linearized = this.imageOverText = !1;
                        this.author = this.keywords = this.subject = this.title =
                            null;
                        this.isProtected = !1;
                        this.ownerPassword = this.userPassword = null;
                        this.encryptionMode = g.rc128Bit;
                        this.annotationsEnabled = this.editEnabled = this.copyEnabled =
                            this.highQualityPrintEnabled = this.printEnabled = !0;
                        this.assemblyEnabled = !1;
                        this.oneBitImageCompression = c.OneBitImageCompressionType.jbig2;
                        this.coloredImageCompression = c.ColoredImageCompressionType.flateJpeg;
                        this.qualityFactor = 80;
                        this.imageOverTextSize = c.DocumentImageOverTextSize.original;
                        this.imageOverTextMode = c.DocumentImageOverTextMode.strict;
                        this.initialPageNumber = 1;
                        this.pageModeType = b.pageOnly;
                        this.pageLayoutType = e.singlePageDisplay;
                        this.pageFitType = f.defaultType;
                        this.zoomPercent = this.yCoordinate = this.xCoordinate = 0;
                        this.displayDocTitle = this.centerWindow = this.fitWindow =
                            this.hideWindowUI = this.hideMenubar = this.hideToolbar = !
                            1
                    }
                    __extends(d, i);
                    return d
                }(c.DocumentOptions);
            c.PdfDocumentOptions = i
        })(c.Writers || (c.Writers = {}))
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(c) {
        (function(c) {
            var a = function(a) {
                function b() {
                    a.call(this, c.DocumentFormat.xps)
                }
                __extends(b, a);
                return b
            }(c.DocumentOptions);
            c.XpsDocumentOptions = a
        })(c.Writers || (c.Writers = {}))
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(c) {
        (function(c) {
            var a = function(a) {
                function b() {
                    a.call(this, c.DocumentFormat.pub)
                }
                __extends(b, a);
                return b
            }(c.DocumentOptions);
            c.PubDocumentOptions = a
        })(c.Writers || (c.Writers = {}))
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(c) {
        (function(c) {
            var a = function(a) {
                function b() {
                    a.call(this, c.DocumentFormat.mob)
                }
                __extends(b, a);
                return b
            }(c.DocumentOptions);
            c.MobDocumentOptions = a
        })(c.Writers || (c.Writers = {}))
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(c) {
        (function(c) {
            var a = function(a) {
                function b() {
                    a.call(this, c.DocumentFormat.ltd)
                }
                __extends(b, a);
                return b
            }(c.DocumentOptions);
            c.LtdDocumentOptions = a
        })(c.Writers || (c.Writers = {}))
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(c) {
        (function(c) {
            var a = function(a) {
                function b() {
                    a.call(this, c.DocumentFormat.html);
                    this.fontEmbedMode = c.DocumentFontEmbedMode.none;
                    this.useBackgroundColor = !1;
                    this.backgroundColor = "white"
                }
                __extends(b, a);
                return b
            }(c.DocumentOptions);
            c.HtmlDocumentOptions = a
        })(c.Writers || (c.Writers = {}))
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(c) {
        (function(c) {
            var a = function(a) {
                function b() {
                    a.call(this, c.DocumentFormat.docx);
                    this.textMode = c.DocumentTextMode.auto
                }
                __extends(b, a);
                return b
            }(c.DocumentOptions);
            c.DocxDocumentOptions = a
        })(c.Writers || (c.Writers = {}))
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(c) {
        (function(c) {
            var a = function(a) {
                function b() {
                    a.call(this, c.DocumentFormat.doc);
                    this.textMode = c.DocumentTextMode.auto
                }
                __extends(b, a);
                return b
            }(c.DocumentOptions);
            c.DocDocumentOptions = a
        })(c.Writers || (c.Writers = {}))
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(c) {
        (function(c) {
            (function(b) {
                b[b.mm10 = 0] = "mm10";
                b[b.inch1200 = 1] = "inch1200";
                b[b.dpi = 2] = "dpi";
                b[b.pixel = 3] = "pixel"
            })(c.AltoXmlMeasurementUnit || (c.AltoXmlMeasurementUnit = {}));
            var a = c.AltoXmlMeasurementUnit,
                g = function(b) {
                    function g() {
                        b.call(this, c.DocumentFormat.altoXml);
                        this.measurementUnit = a.mm10;
                        this.applicationDescription = this.softwareVersion = this.softwareName =
                            this.softwareCreator = this.processingStepSettings = this.processingStepDescription =
                            this.processingAgency = this.processingDateTime =
                            this.fileName = null;
                        this.firstPhysicalPageNumber = 1;
                        this.formatted = !1;
                        this.indentation = "\t"
                    }
                    __extends(g, b);
                    return g
                }(c.DocumentOptions);
            c.AltoXmlDocumentOptions = g
        })(c.Writers || (c.Writers = {}))
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(d) {
        var h = function() {
            function a(a) {
                this._document = a
            }
            Object.defineProperty(a.prototype, "document", {
                get: function() {
                    return this._document
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "annotationsUri", {
                get: function() {
                    return this._document.values.annotationsUri
                },
                enumerable: !0,
                configurable: !0
            });
            a.prototype.getAnnotations = function(g) {
                var b = [d.DocumentFactory.serviceUri, a._controllerName,
                        "GetAnnotations"
                    ].join("/"),
                    g = {
                        documentId: this._document.documentId,
                        pageNumber: 0,
                        createEmpty: g,
                        userData: this._document.serviceUserData
                    },
                    e = $.Deferred(),
                    b = {
                        url: b,
                        type: "POST",
                        contentType: "application/json",
                        data: JSON.stringify(g)
                    };
                if (!d.DocumentFactory.doPrepareAjax(this, a._className,
                        "GetAnnotations", b)) return e.reject(null, "Canceled",
                    "Canceled").promise();
                $.ajax(b).done(function(b) {
                    var a = b.annotations,
                        g = null;
                    null != a && (g = (new c.Annotations.Core.AnnCodecs).loadAll(
                        a));
                    e.resolve(g, "undefined" === typeof b.userData ? null : b
                        .userData)
                }).fail(function(b, a, c) {
                    d.DocumentFactory.log(c);
                    e.reject(b,
                        a, c)
                });
                return e.promise()
            };
            a.prototype.setAnnotations = function(g) {
                var b = [d.DocumentFactory.serviceUri, a._controllerName,
                        "SetAnnotations"
                    ].join("/"),
                    e = null;
                null != g && (e = (new c.Annotations.Core.AnnCodecs).saveAll(g,
                    c.Annotations.Core.AnnFormat.annotations));
                var g = {
                        documentId: this._document.documentId,
                        pageNumber: 0,
                        annotations: e,
                        userData: this._document.serviceUserData
                    },
                    f = $.Deferred(),
                    b = {
                        url: b,
                        type: "POST",
                        contentType: "application/json",
                        data: JSON.stringify(g)
                    };
                if (!d.DocumentFactory.doPrepareAjax(this, a._className,
                        "SetAnnotations", b)) return f.reject(null, "Canceled",
                    "Canceled").promise();
                $.ajax(b).done(function(b) {
                    f.resolve("undefined" === typeof b.userData ? null : b.userData)
                }).fail(function(b, a, c) {
                    d.DocumentFactory.log(c);
                    f.reject(b, a, c)
                });
                return f.promise()
            };
            a._className = "DocumentAnnotations";
            a._controllerName = "Page";
            return a
        }();
        d.DocumentAnnotations = h
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(c) {
        (function(c) {
            (function(a) {
                a[a.user = -1] = "user";
                a[a.ltd = 0] = "ltd";
                a[a.pdf = 1] = "pdf";
                a[a.doc = 2] = "doc";
                a[a.rtf = 3] = "rtf";
                a[a.html = 4] = "html";
                a[a.text = 5] = "text";
                a[a.emf = 6] = "emf";
                a[a.xps = 7] = "xps";
                a[a.docx = 8] = "docx";
                a[a.xls = 9] = "xls";
                a[a.pub = 10] = "pub";
                a[a.mob = 11] = "mob";
                a[a.svg = 12] = "svg";
                a[a.altoXml = 13] = "altoXml"
            })(c.DocumentFormat || (c.DocumentFormat = {}));
            (function(a) {
                a[a.none = 0] = "none";
                a[a.auto = 1] = "auto";
                a[a.force = 2] = "force";
                a[a.all = 3] = "all"
            })(c.DocumentFontEmbedMode || (c.DocumentFontEmbedMode = {}));
            (function(a) {
                a[a.flate = 0] = "flate";
                a[a.faxG31D = 1] = "faxG31D";
                a[a.faxG32D = 2] = "faxG32D";
                a[a.faxG4 = 3] = "faxG4";
                a[a.lzw = 4] = "lzw";
                a[a.jbig2 = 5] = "jbig2"
            })(c.OneBitImageCompressionType || (c.OneBitImageCompressionType = {}));
            (function(a) {
                a[a.flateJpeg = 0] = "flateJpeg";
                a[a.lzwJpeg = 1] = "lzwJpeg";
                a[a.flate = 2] = "flate";
                a[a.lzw = 3] = "lzw";
                a[a.jpeg = 4] = "jpeg";
                a[a.flateJpx = 5] = "flateJpx";
                a[a.lzwJpx = 6] = "lzwJpx";
                a[a.jpx = 7] = "jpx"
            })(c.ColoredImageCompressionType || (c.ColoredImageCompressionType = {}));
            (function(a) {
                a[a.original =
                    0] = "original";
                a[a.half = 1] = "half";
                a[a.quarter = 2] = "quarter"
            })(c.DocumentImageOverTextSize || (c.DocumentImageOverTextSize = {}));
            (function(a) {
                a[a.none = 0] = "none";
                a[a.strict = 1] = "strict";
                a[a.relaxed = 2] = "relaxed"
            })(c.DocumentImageOverTextMode || (c.DocumentImageOverTextMode = {}));
            (function(a) {
                a[a.auto = 0] = "auto";
                a[a.nonFramed = 1] = "nonFramed";
                a[a.framed = 2] = "framed"
            })(c.DocumentTextMode || (c.DocumentTextMode = {}))
        })(c.Writers || (c.Writers = {}))
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(c) {
        var h = function() {
            function a() {
                Array.apply(this, arguments);
                return []
            }
            a.prototype.pop = function() {
                return 0
            };
            a.prototype.push = function() {
                return 0
            };
            return a
        }();
        c.DocumentPagesArrayWrapper = h;
        h.prototype = [];
        h = function(a) {
            function c(b) {
                a.call(this);
                this._document = b
            }
            __extends(c, a);
            Object.defineProperty(c.prototype, "document", {
                get: function() {
                    return this._document
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(c.prototype, "defaultPageSize", {
                get: function() {
                    return this._document.values.defaultPageSize
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(c.prototype, "defaultResolution", {
                get: function() {
                    return this._document.values.defaultResolution
                },
                enumerable: !0,
                configurable: !0
            });
            return c
        }(h);
        c.DocumentPages = h
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(d) {
        (function(d) {
            var a = function() {
                function a() {
                    this._size = null;
                    this._originalPageNumber = this._pageNumber = this._resolution =
                        0;
                    this._hasEmbeddedAnnotations = this._isAnnotationsModified =
                        this._isDeleted = !1;
                    this._size = c.LeadSizeD.empty;
                    this._originalPageNumber = this._pageNumber = this._resolution =
                        0;
                    this._hasEmbeddedAnnotations = this._isAnnotationsModified =
                        this._isDeleted = !1
                }
                a.fromJson = function(b) {
                    if (null == b) return null;
                    var c = new a;
                    return !c.copyFromJson(b) ? null : c
                };
                a.prototype.copyFromJson =
                    function(b) {
                        return null == b || !d.PropertyBag.copyMatchingProperties(b,
                            this, ["pageNumber"]) ? !1 : !0
                    };
                Object.defineProperty(a.prototype, "size", {
                    get: function() {
                        return this._size.clone()
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "resolution", {
                    get: function() {
                        return this._resolution
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "pageNumber", {
                    get: function() {
                        return this._pageNumber
                    },
                    set: function(b) {
                        this._pageNumber = b
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype,
                    "originalPageNumber", {
                        get: function() {
                            return this._originalPageNumber
                        },
                        enumerable: !0,
                        configurable: !0
                    });
                Object.defineProperty(a.prototype, "isDeleted", {
                    get: function() {
                        return this._isDeleted
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "isAnnotationsModified", {
                    get: function() {
                        return this._isAnnotationsModified
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "hasEmbeddedAnnotations", {
                    get: function() {
                        return this._hasEmbeddedAnnotations
                    },
                    enumerable: !0,
                    configurable: !0
                });
                return a
            }();
            d.DocumentPageValues = a
        })(d.Internal || (d.Internal = {}))
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(c) {
        var h = function() {
            function a() {
                this._bookmarks = [];
                this._pageLinks = []
            }
            a.prototype.DocumentParseStructureData = function() {};
            Object.defineProperty(a.prototype, "bookmarks", {
                get: function() {
                    return this._bookmarks
                },
                set: function(a) {
                    this._bookmarks = a
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "pageLinks", {
                get: function() {
                    return this._pageLinks
                },
                set: function(a) {
                    this._pageLinks = a
                },
                enumerable: !0,
                configurable: !0
            });
            return a
        }();
        c.DocumentParseStructureData = h;
        h = function() {
            function a(a) {
                this._document =
                    null;
                this._bookmarks = [];
                if (null == a) throw Error("Document cannot be null");
                this._document = a
            }
            Object.defineProperty(a.prototype, "document", {
                get: function() {
                    return this._document
                },
                enumerable: !0,
                configurable: !0
            });
            a.prototype.copyFromJson = function(a, b) {
                var e = this;
                if (!a && !b) return !1;
                a && 0 < a.length && (this._bookmarks = [], a.forEach(function(
                    b) {
                    var a = new c.DocumentBookmark;
                    a.copyFromJson(b);
                    e._bookmarks.push(a)
                }));
                b && 0 < b.length && b.forEach(function(b, a) {
                    var c = e._document.pages[a];
                    if (!c || !c.copyLinksFromJson(b)) return false
                });
                return this._document.values.isStructureParsed = !0
            };
            Object.defineProperty(a.prototype, "bookmarks", {
                get: function() {
                    return this._bookmarks
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "parseBookmarks", {
                get: function() {
                    return this._document.values.parseBookmarks
                },
                set: function(a) {
                    this._document.values.parseBookmarks = a
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "parsePageLinks", {
                get: function() {
                    return this._document.values.parsePageLinks
                },
                set: function(a) {
                    this._document.values.parsePageLinks =
                        a
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "isParsed", {
                get: function() {
                    return this._document.values.isStructureParsed
                },
                enumerable: !0,
                configurable: !0
            });
            a.prototype.parse = function() {
                var g = this,
                    b = $.Deferred();
                if (this.isParsed) return b.resolve(this._document), b.promise();
                var e = this._document,
                    e = {
                        url: [c.DocumentFactory.serviceUri, a._controllerName,
                            "ParseStructure"
                        ].join("/"),
                        type: "POST",
                        contentType: "application/json",
                        data: JSON.stringify({
                            documentId: e.documentId,
                            parseBookmarks: this.parseBookmarks,
                            parsePageLinks: this.parsePageLinks,
                            userData: this._document.serviceUserData
                        })
                    };
                if (!c.DocumentFactory.doPrepareAjax(this, a._className,
                        "Parse", e)) return b.reject(null, "Canceled", "Canceled").promise();
                $.ajax(e).done(function(a) {
                    if (!a.hasOwnProperty("bookmarks") || !a.hasOwnProperty(
                            "pageLinks")) return b.reject(null, null,
                        "Invalid object"), b.promise();
                    g.copyFromJson(a.bookmarks, a.pageLinks) ? b.resolve(g._document,
                        "undefined" === typeof a.userData ? null : a.userData
                    ) : b.reject(null, null, "Invalid object")
                }).fail(function(a,
                    e, g) {
                    c.DocumentFactory.log(g);
                    b.reject(a, e, g)
                });
                return b.promise()
            };
            a._className = "DocumentStructure";
            a._controllerName = "Structure";
            return a
        }();
        c.DocumentStructure = h
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(c) {
        (function(a) {
            a[a.auto = 0] = "auto";
            a[a.svgOnly = 1] = "svgOnly";
            a[a.ocrOnly = 2] = "ocrOnly"
        })(c.DocumentTextExtractionMode || (c.DocumentTextExtractionMode = {}));
        var h = function() {
            function a(a) {
                this._document = a
            }
            Object.defineProperty(a.prototype, "document", {
                get: function() {
                    return this._document
                },
                enumerable: !0,
                configurable: !0
            });
            a.fromJson = function(c, b) {
                if (null == c) return null;
                var e = new a(b);
                return !e.copyFromJson(c) ? null : e
            };
            a.prototype.copyFromJson = function(a) {
                return null == a || !c.Internal.PropertyBag.copyMatchingProperties(
                        a,
                        this, ["document", "linkPatterns", "defLinkPatterns"]) ? !1 :
                    !0
            };
            Object.defineProperty(a.prototype, "textExtractionMode", {
                get: function() {
                    return this._document.values.textExtractionMode
                },
                set: function(a) {
                    this._document.values.textExtractionMode = a
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "autoParseLinks", {
                get: function() {
                    return this._document.values.autoParseLinks
                },
                set: function(a) {
                    this._document.values.autoParseLinks = a
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a, "linkPatterns", {
                get: function() {
                    return a._linkPatterns
                },
                enumerable: !0,
                configurable: !0
            });
            a.resetLinkPatterns = function() {
                a._linkPatterns = [];
                a._linkPatterns.push(RegExp(a._defaultLinkPatterns[0], a._defaultLinkFlags[
                    0]));
                a._linkPatterns.push(RegExp(a._defaultLinkPatterns[1], a._defaultLinkFlags[
                    1]))
            };
            a._defaultLinkPatterns = [
                "((?:[a-z][\\w-]+:(?:/{1,3}|[a-z0-9%])|www\\d{0,3}[.]|ftp\\d{0,3}[.]|mailto\\d{0,3}[@]|[a-z0-9.\\-]+[.][a-z]{2,4}/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]{};:'\".,<>?\u00ab\u00bb\u201c\u201d\u2018\u2019]))",
                "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"
            ];
            a._defaultLinkFlags = ["gi", "g"];
            a._linkPatterns = [RegExp(a._defaultLinkPatterns[0], a._defaultLinkFlags[
                0]), RegExp(a._defaultLinkPatterns[1], a._defaultLinkFlags[1])];
            return a
        }();
        c.DocumentText = h
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(d) {
        (function(h) {
            var a = function() {
                function a() {
                    this._annotationsUri = "";
                    this._defaultBitsPerPixel = 0;
                    this._defaultPageSize = c.LeadSizeD.empty;
                    this._defaultResolution = 0;
                    this._documentId = "";
                    this._name = null;
                    this._documentType = "";
                    this._fileLength = 0;
                    this._isStructureSupported = this._isStructureParsed = this._isReadOnly =
                        this._isEncrypted = this._isDownloaded = this._isDecrypted = !
                        1;
                    this._password = this._mimeType = "";
                    this._maximumImagePixelSize = 0;
                    this._thumbnailPixelSize = c.LeadSizeD.empty;
                    this._unembedSvgImages = !1;
                    this._uri = "";
                    this._isResolutionsSupported = this._isSvgViewingPreferred =
                        this._isSvgSupported = !1;
                    this._textExtractionMode = d.DocumentTextExtractionMode.auto;
                    this._parsePageLinks = this._parseBookmarks = this._autoParseLinks = !
                        0
                }
                a.fromJson = function(b) {
                    if (null == b) return null;
                    var c = new a;
                    return !c.copyFromJson(b) ? null : c
                };
                a.prototype.copyFromJson = function(b) {
                    return null == b || !h.PropertyBag.copyMatchingProperties(b,
                        this, null) ? !1 : !0
                };
                Object.defineProperty(a.prototype, "annotationsUri", {
                    get: function() {
                        return this._annotationsUri
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "defaultBitsPerPixel", {
                    get: function() {
                        return this._defaultBitsPerPixel
                    },
                    set: function(b) {
                        this._defaultBitsPerPixel = b
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "defaultPageSize", {
                    get: function() {
                        return this._defaultPageSize
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "defaultResolution", {
                    get: function() {
                        return this._defaultResolution
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype,
                    "documentId", {
                        get: function() {
                            return this._documentId
                        },
                        enumerable: !0,
                        configurable: !0
                    });
                Object.defineProperty(a.prototype, "name", {
                    get: function() {
                        return this._name
                    },
                    set: function(b) {
                        this._name = b
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "documentType", {
                    get: function() {
                        return this._documentType
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "fileLength", {
                    get: function() {
                        return this._fileLength
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype,
                    "isDecrypted", {
                        get: function() {
                            return this._isDecrypted
                        },
                        enumerable: !0,
                        configurable: !0
                    });
                Object.defineProperty(a.prototype, "isDownloaded", {
                    get: function() {
                        return this._isDownloaded
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "isEncrypted", {
                    get: function() {
                        return this._isEncrypted
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "isReadOnly", {
                    get: function() {
                        return this._isReadOnly
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "isStructureParsed", {
                    get: function() {
                        return this._isStructureParsed
                    },
                    set: function(b) {
                        this._isStructureParsed = b
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "isStructureSupported", {
                    get: function() {
                        return this._isStructureSupported
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "mimeType", {
                    get: function() {
                        return this._mimeType
                    },
                    set: function(b) {
                        this._mimeType = b
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "password", {
                    get: function() {
                        return this._password
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "maximumImagePixelSize", {
                    get: function() {
                        return this._maximumImagePixelSize
                    },
                    set: function(b) {
                        this._maximumImagePixelSize = b
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "thumbnailPixelSize", {
                    get: function() {
                        return this._thumbnailPixelSize
                    },
                    set: function(b) {
                        this._thumbnailPixelSize = b
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "unembedSvgImages", {
                    get: function() {
                        return this._unembedSvgImages
                    },
                    set: function(b) {
                        this._unembedSvgImages =
                            b
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "uri", {
                    get: function() {
                        return this._uri
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "isSvgSupported", {
                    get: function() {
                        return this._isSvgSupported
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "isSvgViewingPreferred", {
                    get: function() {
                        return this._isSvgViewingPreferred
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "isResolutionsSupported", {
                    get: function() {
                        return this._isResolutionsSupported
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "textExtractionMode", {
                    get: function() {
                        return this._textExtractionMode
                    },
                    set: function(b) {
                        this._textExtractionMode = b
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "autoParseLinks", {
                    get: function() {
                        return this._autoParseLinks
                    },
                    set: function(b) {
                        this._autoParseLinks = b
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "parseBookmarks", {
                    get: function() {
                        return this._parseBookmarks
                    },
                    set: function(b) {
                        this._parseBookmarks =
                            b
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "parsePageLinks", {
                    get: function() {
                        return this._parsePageLinks
                    },
                    set: function(b) {
                        this._parsePageLinks = b
                    },
                    enumerable: !0,
                    configurable: !0
                });
                return a
            }();
            h.DocumentValues = a
        })(d.Internal || (d.Internal = {}))
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(d) {
        var h = function() {
            function a(a) {
                this._document = a
            }
            Object.defineProperty(a.prototype, "document", {
                get: function() {
                    return this._document
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "isSvgSupported", {
                get: function() {
                    return this._document.values.isSvgSupported
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "isSvgViewingPreferred", {
                get: function() {
                    return this._document.values.isSvgViewingPreferred
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype,
                "isResolutionsSupported", {
                    get: function() {
                        return this._document.values.isResolutionsSupported
                    },
                    enumerable: !0,
                    configurable: !0
                });
            Object.defineProperty(a.prototype, "defaultBitsPerPixel", {
                get: function() {
                    return this._document.defaultBitsPerPixel
                },
                set: function(a) {
                    switch (a) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 12:
                        case 16:
                        case 24:
                        case 32:
                        case 48:
                        case 64:
                            break;
                        default:
                            throw Error("Invalid bits per pixel value");
                    }
                    this._document.defaultBitsPerPixel = a
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype,
                "maximumImagePixelSize", {
                    get: function() {
                        return this._document.values.maximumImagePixelSize
                    },
                    set: function(a) {
                        if (0 > a) throw Error(
                            "Must have a value equal to or greater than 0");
                        this._document.values.maximumImagePixelSize = a
                    },
                    enumerable: !0,
                    configurable: !0
                });
            Object.defineProperty(a.prototype, "thumbnailPixelSize", {
                get: function() {
                    return this._document.values.thumbnailPixelSize
                },
                set: function(a) {
                    if (a.isEmpty || 0 > a.width || 0 > a.height) throw Error(
                        "Must have width and height values greater than 0");
                    this._document.values.thumbnailPixelSize =
                        a
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "unembedSvgImages", {
                get: function() {
                    return this._document.values.unembedSvgImages
                },
                set: function(a) {
                    this._document.values.unembedSvgImages = a
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a, "elementAjaxMethod", {
                get: function() {
                    return a._elementAjaxMethod
                },
                set: function(c) {
                    a._elementAjaxMethod = c.toUpperCase()
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a, "elementUrlMode", {
                get: function() {
                    return a._elementUrlMode
                },
                set: function(c) {
                    a._elementUrlMode =
                        c
                },
                enumerable: !0,
                configurable: !0
            });
            a.getImageScale = function(a, b) {
                if (0 == b || a.isEmpty || 0 == a.width || 0 == a.height) return
                1;
                var c = 1;
                if (a.width > b || a.height > b) c = a.width > a.height ? a.width /
                    b : a.height / b;
                return c
            };
            a.scaleImageSize = function(a, b) {
                return 1 == b ? a.clone() : c.LeadSizeD.create(a.width * b +
                    0.5 | 0, a.height * b + 0.5 | 0)
            };
            a.prototype.getThumbnailsGridUrlParams = function(a, b, c) {
                var f = this._document.images.thumbnailPixelSize;
                return {
                    documentId: this._document.documentId,
                    firstPageNumber: a,
                    lastPageNumber: b,
                    maximumGridWidth: c,
                    width: f.width,
                    height: f.height,
                    userData: this._document.serviceUserData
                }
            };
            a.prototype.getThumbnailsGrid = function(a, b, c) {
                return this.getThumbnailsGridUrl(a, b, c)
            };
            a.prototype.getThumbnailsGridUrl = function(c, b, e) {
                return d.Internal.ImageRequestHelper.createFullUrl(d.Internal.ImageRequestHelper
                    .createBaseUrl(a._controllerName, a._getThumbnailsGridActionName),
                    this.getThumbnailsGridUrlParams(c, b, e))
            };
            a.prototype.getThumbnailsGridElement = function(c, b, e, f) {
                var i = $.Deferred(),
                    j = d.Internal.ImageRequestHelper.createBaseUrl(a._controllerName,
                        a._getThumbnailsGridActionName),
                    c = this.getThumbnailsGridUrlParams(c, b, e);
                return d.Internal.ImageRequestHelper.doGenericElementLoad(i, f,
                    j, c, a._className, a._getThumbnailsGridMethodName)
            };
            a._className = "DocumentImages";
            a._controllerName = "Images";
            a._elementAjaxMethod = "GET";
            a._elementUrlMode = c.ImageLoaderUrlMode.imageUrl;
            a._getThumbnailsGridActionName = "GetThumbnailsGrid";
            a._getThumbnailsGridMethodName = "GetThumbnailsGrid";
            return a
        }();
        d.DocumentImages = h
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(d) {
        var h = function() {
            function a() {
                this._pageFitType = d.DocumentPageFitType.none;
                this._pageNumber = 0;
                this._position = c.LeadPointD.empty;
                this._zoomPercent = 0
            }
            a.fromJson = function(a) {
                if (null == a) return null;
                var b = new d.DocumentLink;
                return !b.copyFromJson(a) ? null : b
            };
            a.prototype.copyFromJson = function(a) {
                return null == a || !d.Internal.PropertyBag.copyMatchingProperties(
                    a, this, null) ? !1 : !0
            };
            Object.defineProperty(a.prototype, "pageFitType", {
                get: function() {
                    return this._pageFitType
                },
                set: function(a) {
                    this._pageFitType =
                        a
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "pageNumber", {
                get: function() {
                    return this._pageNumber
                },
                set: function(a) {
                    this._pageNumber = a
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "position", {
                get: function() {
                    return this._position.clone()
                },
                set: function(a) {
                    this._position = a.clone()
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "zoomPercent", {
                get: function() {
                    return this._zoomPercent
                },
                set: function(a) {
                    this._zoomPercent = a
                },
                enumerable: !0,
                configurable: !0
            });
            a.prototype.clone = function() {
                var c = new a;
                c.pageFitType = this.pageFitType;
                c.pageNumber = this.pageNumber;
                c.position = this.position;
                c.zoomPercent = this.zoomPercent;
                return c
            };
            return a
        }();
        d.DocumentLinkTarget = h
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(d) {
        (function(a) {
            a[a.value = 0] = "value";
            a[a.targetPage = 1] = "targetPage"
        })(d.DocumentLinkType || (d.DocumentLinkType = {}));
        var h = d.DocumentLinkType,
            a = function() {
                function a() {
                    this._bounds = c.LeadRectD.empty;
                    this._value = "";
                    this._linkType = h.value;
                    this._target = new d.DocumentLinkTarget
                }
                a.fromJson = function(b) {
                    if (null == b) return null;
                    var c = new a;
                    return !c.copyFromJson(b) ? null : c
                };
                a.prototype.copyFromJson = function(a) {
                    return null == a || !d.Internal.PropertyBag.copyMatchingProperties(
                            a, this, null) ? !1 :
                        !0
                };
                Object.defineProperty(a.prototype, "bounds", {
                    get: function() {
                        return this._bounds
                    },
                    set: function(a) {
                        this._bounds = a.clone()
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "linkType", {
                    get: function() {
                        return this._linkType
                    },
                    set: function(a) {
                        this._linkType = a
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "value", {
                    get: function() {
                        return this._value
                    },
                    set: function(a) {
                        this._value = a
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "target", {
                    get: function() {
                        return this._target
                    },
                    set: function(a) {
                        this._target.copyFromJson(a)
                    },
                    enumerable: !0,
                    configurable: !0
                });
                return a
            }();
        d.DocumentLink = a
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(d) {
        (function(a) {
            a[a.none = 0] = "none";
            a[a.allowPolylineText = 1] = "allowPolylineText";
            a[a.dropImages = 2] = "dropImages";
            a[a.dropShapes = 4] = "dropShapes";
            a[a.dropText = 8] = "dropText";
            a[a.forConversion = 16] = "forConversion";
            a[a.ignoreXmlParsingErrors = 32] = "ignoreXmlParsingErrors"
        })(d.DocumentGetSvgOptions || (d.DocumentGetSvgOptions = {}));
        var h = function() {
            function a(a) {
                this._values = new d.Internal.DocumentPageValues;
                this._document = null;
                this._links = [];
                if (null == a) throw Error("Document cannot be null");
                this._document = a
            }
            a.fromJson = function(c, b, e) {
                if (null == c) return null;
                b = new a(b);
                return !b.copyFromJson(c, e) ? null : b
            };
            a.prototype.copyFromJson = function(a, b) {
                if (null == a || !a.hasOwnProperty("values") || !this._values.copyFromJson(
                        a.values)) return !1;
                this._values.pageNumber = b;
                return !0
            };
            Object.defineProperty(a.prototype, "document", {
                get: function() {
                    return this._document
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "size", {
                get: function() {
                    return this.values.size.clone()
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "resolution", {
                get: function() {
                    return this.values.resolution
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "pageNumber", {
                get: function() {
                    return this.values.pageNumber
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "imageScale", {
                get: function() {
                    return null == this._document ? 1 : d.DocumentImages.getImageScale(
                        this._document.sizeToPixels(this.values.size), this._document
                        .images.maximumImagePixelSize)
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype,
                "originalPageNumber", {
                    get: function() {
                        return this.values.originalPageNumber
                    },
                    enumerable: !0,
                    configurable: !0
                });
            Object.defineProperty(a.prototype, "isDeleted", {
                get: function() {
                    return this.values.isDeleted
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "isAnnotationsModified", {
                get: function() {
                    return this.values.isAnnotationsModified
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "hasEmbeddedAnnotations", {
                get: function() {
                    return this.values.hasEmbeddedAnnotations
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "values", {
                get: function() {
                    return this._values
                },
                enumerable: !0,
                configurable: !0
            });
            a.prototype.parseAndMergeValueLinks = function(a) {
                null == a || 0 == a.characters.length || this.mergeLinks(a.parseLinks(
                    d.DocumentText.linkPatterns))
            };
            a.prototype.removeSmallerDuplicate = function(a, b) {
                for (var c = a.length - 1; 0 <= c; c--) b.bounds.containsRect(a[
                    c].bounds) && a.splice(c, 1)
            };
            a.prototype.mergeLinks = function(a) {
                if (!(null == a || 0 == a.length)) {
                    for (var b = 0; b < a.length; b++) this.removeSmallerDuplicate(
                        this._links,
                        a[b]);
                    this._links = this._links.concat(a)
                }
            };
            a.prototype.copyLinksFromJson = function(a) {
                var b = [];
                if (null == a) return !0;
                for (var c = 0; c < a.length; c++) {
                    var f = d.DocumentLink.fromJson(a[c]);
                    b.push(f)
                }
                this.mergeLinks(b);
                return !0
            };
            a.prototype.getLinks = function() {
                return this._links
            };
            a.prototype.getText = function(c) {
                var b = this,
                    e = [d.DocumentFactory.serviceUri, a._controllerName,
                        "GetText"
                    ].join("/"),
                    c = {
                        documentId: this._document.documentId,
                        pageNumber: this.pageNumber,
                        clip: c,
                        textExtractionMode: this._document.text.textExtractionMode,
                        userData: this._document.serviceUserData
                    },
                    f = $.Deferred(),
                    e = {
                        url: e,
                        type: "POST",
                        contentType: "application/json",
                        data: JSON.stringify(c)
                    };
                if (!d.DocumentFactory.doPrepareAjax(this, a._className,
                        "GetText", e)) return f.reject(null, "Canceled", "Canceled")
                    .promise();
                $.ajax(e).done(function(a) {
                    var c = d.DocumentPageText.fromJson(a.pageText);
                    b._document.text.autoParseLinks && b.parseAndMergeValueLinks(
                        c);
                    f.resolve(c, "undefined" === typeof a.userData ? null : a
                        .userData)
                }).fail(function(a, b, c) {
                    d.DocumentFactory.log(c);
                    f.reject(a,
                        b, c)
                });
                return f.promise()
            };
            a.prototype.getAnnotations = function(g) {
                var b = [d.DocumentFactory.serviceUri, a._controllerName,
                        "GetAnnotations"
                    ].join("/"),
                    g = {
                        documentId: this._document.documentId,
                        pageNumber: this.pageNumber,
                        createEmpty: g,
                        userData: this._document.serviceUserData
                    },
                    e = $.Deferred(),
                    b = {
                        url: b,
                        type: "POST",
                        contentType: "application/json",
                        data: JSON.stringify(g)
                    };
                if (!d.DocumentFactory.doPrepareAjax(this, a._className,
                        "GetAnnotations", b)) return e.reject(null, "Canceled",
                    "Canceled").promise();
                $.ajax(b).done(function(a) {
                    var b =
                        a.annotations,
                        j = null;
                    null != b && (j = (new c.Annotations.Core.AnnCodecs).load(
                        b, 0));
                    e.resolve(j, "undefined" === typeof a.userData ? null : a
                        .userData)
                }).fail(function(a, b, c) {
                    d.DocumentFactory.log(c);
                    e.reject(a, b, c)
                });
                return e.promise()
            };
            a.prototype.setAnnotations = function(g) {
                var b = [d.DocumentFactory.serviceUri, a._controllerName,
                        "SetAnnotations"
                    ].join("/"),
                    e = null;
                null != g && (e = (new c.Annotations.Core.AnnCodecs).save(g, c.Annotations
                    .Core.AnnFormat.annotations, null, this.pageNumber));
                var g = {
                        documentId: this._document.documentId,
                        pageNumber: this.pageNumber,
                        annotations: e,
                        userData: this._document.serviceUserData
                    },
                    f = $.Deferred(),
                    b = {
                        url: b,
                        type: "POST",
                        contentType: "application/json",
                        data: JSON.stringify(g)
                    };
                if (!d.DocumentFactory.doPrepareAjax(this, a._className,
                        "SetAnnotations", b)) return f.reject(null, "Canceled",
                    "Canceled").promise();
                $.ajax(b).done(function(a) {
                    f.resolve("undefined" === typeof a.userData ? null : a.userData)
                }).fail(function(a, b, c) {
                    d.DocumentFactory.log(c);
                    f.reject(a, b, c)
                });
                return f.promise()
            };
            a.prototype.getThumbnailImageUrlParams =
                function() {
                    var a = this._document.images.thumbnailPixelSize;
                    return {
                        documentId: this._document.documentId,
                        pageNumber: this.pageNumber,
                        width: a.width,
                        height: a.height,
                        userData: this._document.serviceUserData
                    }
                };
            a.prototype.getThumbnailImage = function() {
                return this.getThumbnailImageUrl()
            };
            a.prototype.getThumbnailImageUrl = function() {
                return d.Internal.ImageRequestHelper.createFullUrl(d.Internal.ImageRequestHelper
                    .createBaseUrl(a._controllerName, a._getThumbnailImageActionName),
                    this.getThumbnailImageUrlParams())
            };
            a.prototype.getThumbnailImageElement = function(c) {
                var b = $.Deferred(),
                    e = d.Internal.ImageRequestHelper.createBaseUrl(a._controllerName,
                        a._getThumbnailImageActionName),
                    f = this.getThumbnailImageUrlParams();
                return d.Internal.ImageRequestHelper.doGenericElementLoad(b, c,
                    e, f, a._className, a._getThumbnailImageMethodName)
            };
            a.prototype.getImageUrlParams = function(a, b) {
                var c = {
                    documentId: this._document.documentId,
                    pageNumber: this.pageNumber,
                    userData: this._document.serviceUserData
                }; - 1 !== a && -1 !== b && (c.width = a, c.height =
                    b);
                return c
            };
            a.prototype.getImage = function() {
                return this.getImageUrl()
            };
            a.prototype.getImageUrl = function() {
                return d.Internal.ImageRequestHelper.createFullUrl(d.Internal.ImageRequestHelper
                    .createBaseUrl(a._controllerName, a._getImageActionName),
                    this.getImageUrlParams(-1, -1))
            };
            a.prototype.getImageResized = function(a, b) {
                return this.getImageResizedUrl(a, b)
            };
            a.prototype.getImageResizedUrl = function(c, b) {
                return d.Internal.ImageRequestHelper.createFullUrl(d.Internal.ImageRequestHelper
                    .createBaseUrl(a._controllerName,
                        a._getImageActionName), this.getImageUrlParams(c, b))
            };
            a.prototype.getImageElement = function(c) {
                return this.internalGetImageElement(a._getImageMethodName, -1, -
                    1, c)
            };
            a.prototype.getImageResizedElement = function(c, b, e) {
                return this.internalGetImageElement(a._getImageResizedMethodName,
                    c, b, e)
            };
            a.prototype.internalGetImageElement = function(c, b, e, f) {
                var i = $.Deferred(),
                    j = d.Internal.ImageRequestHelper.createBaseUrl(a._controllerName,
                        a._getImageActionName),
                    b = this.getImageUrlParams(b, e);
                return d.Internal.ImageRequestHelper.doGenericElementLoad(i,
                    f, j, b, a._className, c)
            };
            a.prototype.getSvgBackImageUrlParams = function(a) {
                return {
                    documentId: this._document.documentId,
                    pageNumber: this.pageNumber,
                    backColor: a,
                    userData: this._document.serviceUserData
                }
            };
            a.prototype.getSvgBackImage = function(a) {
                return this.getSvgBackImageUrl(a)
            };
            a.prototype.getSvgBackImageUrl = function(c) {
                return d.Internal.ImageRequestHelper.createFullUrl(d.Internal.ImageRequestHelper
                    .createBaseUrl(a._controllerName, a._getSvgBackImageActionName),
                    this.getSvgBackImageUrlParams(c))
            };
            a.prototype.getSvgBackImageElement =
                function(c, b) {
                    var e = $.Deferred(),
                        f = d.Internal.ImageRequestHelper.createBaseUrl(a._controllerName,
                            a._getSvgBackImageActionName),
                        i = this.getSvgBackImageUrlParams(c);
                    return d.Internal.ImageRequestHelper.doGenericElementLoad(e, b,
                        f, i, a._className, a._getSvgBackImageMethodName)
                };
            a.prototype.getSvgUrlParams = function(a) {
                return {
                    documentId: this._document.documentId,
                    pageNumber: this.pageNumber,
                    options: a,
                    unembedImages: this._document.images.unembedSvgImages,
                    userData: this._document.serviceUserData
                }
            };
            a.prototype.getSvg =
                function(a) {
                    return this.getSvgUrl(a)
                };
            a.prototype.getSvgUrl = function(c) {
                return d.Internal.ImageRequestHelper.createFullUrl(d.Internal.ImageRequestHelper
                    .createBaseUrl(a._controllerName, a._getSvgActionName),
                    this.getSvgUrlParams(c))
            };
            a.prototype.getSvgElement = function(c, b) {
                var e = $.Deferred(),
                    f = d.Internal.ImageRequestHelper.createBaseUrl(a._controllerName,
                        a._getSvgActionName),
                    i = this.getSvgUrlParams(c);
                return d.Internal.ImageRequestHelper.doGenericElementLoad(e, b,
                    f, i, a._className, a._getSvgMethodName)
            };
            a.prototype.readBarcodes = function(c, b, e) {
                var f = [d.DocumentFactory.serviceUri, a._controllerName,
                        "ReadBarcodes"
                    ].join("/"),
                    c = {
                        documentId: this._document.documentId,
                        pageNumber: this.pageNumber,
                        bounds: c,
                        maximumBarcodes: b,
                        symbologies: e,
                        userData: this._document.serviceUserData
                    },
                    i = $.Deferred(),
                    f = {
                        url: f,
                        type: "POST",
                        contentType: "application/json",
                        data: JSON.stringify(c)
                    };
                if (!d.DocumentFactory.doPrepareAjax(this, a._className,
                        "ReadBarcodes", f)) return i.reject(null, "Canceled",
                    "Canceled").promise();
                $.ajax(f).done(function(a) {
                    var b =
                        a.barcodes.map(function(a) {
                            return d.Barcode.BarcodeData.fromJson(a)
                        });
                    i.resolve(b, "undefined" === typeof a.userData ? null : a
                        .userData)
                }).fail(function(a, b, c) {
                    d.DocumentFactory.log(c);
                    i.reject(a, b, c)
                });
                return i.promise()
            };
            a._className = "DocumentPage";
            a._controllerName = "Page";
            a._getThumbnailImageActionName = "GetThumbnail";
            a._getThumbnailImageMethodName = "GetThumbnailImage";
            a._getImageActionName = "GetImage";
            a._getImageMethodName = "GetImage";
            a._getImageResizedActionName = "GetImage";
            a._getImageResizedMethodName =
                "GetImageResized";
            a._getSvgBackImageActionName = "GetSvgBackImage";
            a._getSvgBackImageMethodName = "GetSvgBackImage";
            a._getSvgActionName = "GetSvg";
            a._getSvgMethodName = "GetSvg";
            return a
        }();
        d.DocumentPage = h
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(d) {
        (function(a) {
            a[a.normal = 0] = "normal";
            a[a.bold = 1] = "bold";
            a[a.italic = 2] = "italic";
            a[a.underline = 4] = "underline"
        })(d.DocumentFontStyles || (d.DocumentFontStyles = {}));
        (function(a) {
            a[a.none = 0] = "none";
            a[a.fit = 1] = "fit";
            a[a.fitAlways = 2] = "fitAlways";
            a[a.fitWidth = 3] = "fitWidth";
            a[a.fitHeight = 4] = "fitHeight"
        })(d.DocumentPageFitType || (d.DocumentPageFitType = {}));
        var h = function() {
            function a() {
                this._children = []
            }
            a.fromJson = function(c) {
                if (null == c) return null;
                var b = new a;
                return !b.copyFromJson(c) ?
                    null : b
            };
            a.prototype.copyFromJson = function(g) {
                if (null == g || !g.hasOwnProperty("fontStyles") || !g.hasOwnProperty(
                        "children") || !g.hasOwnProperty("target") || !g.hasOwnProperty(
                        "title")) return !1;
                this.fontStyles = g.fontStyles;
                this.title = g.title;
                var b = new d.DocumentLinkTarget,
                    e = g.target;
                if (!e.hasOwnProperty("pageFitType") || !e.hasOwnProperty(
                        "pageNumber") || !e.hasOwnProperty("position") || !e.hasOwnProperty(
                        "zoomPercent")) return !1;
                b.pageFitType = e.pageFitType;
                b.pageNumber = e.pageNumber;
                b.position = c.LeadPointD.fromJSON(e.position);
                b.zoomPercent = e.zoomPercent;
                this.target = b;
                for (b = 0; b < g.children.length; b++) this.children.push(a.fromJson(
                    g.children[b]));
                return !0
            };
            Object.defineProperty(a.prototype, "fontStyles", {
                get: function() {
                    return this._fontStyles
                },
                set: function(a) {
                    this._fontStyles = a
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "children", {
                get: function() {
                    return this._children
                },
                set: function(a) {
                    this._children = a
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "target", {
                get: function() {
                    return this._target
                },
                set: function(a) {
                    this._target = a.clone()
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "title", {
                get: function() {
                    return this._title
                },
                set: function(a) {
                    this._title = a
                },
                enumerable: !0,
                configurable: !0
            });
            return a
        }();
        d.DocumentBookmark = h
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
if (lt.LTHelper.browser == lt.LTBrowser.internetExplorer && 9 >= lt.LTHelper.version)
    try {
        $.support.cors = !0
    } catch (e$$8) {
        console.log(
            "IE9 does not support AJAX without a special jQuery flag. Please ensure jQuery is properly added to the page."
        )
    }
    (function(c) {
        (function(c) {
            var h = function() {
                return function() {
                    this.resolution = 0;
                    this.annotationsUri = this.password = this.name = null;
                    this.loadEmbeddedAnnotations = !0;
                    this.maximumImagePixelSize = 0
                }
            }();
            c.LoadDocumentOptions = h
        })(c.Documents || (c.Documents = {}))
    })(lt || (lt = {}));
(function(c) {
    (function(d) {
        var h = function() {
            function a() {
                this._bounds = c.LeadRectD.empty;
                this._isRightToLeft = this._isEndOfLine = this._isEndOfWord = !1
            }
            Object.defineProperty(a.prototype, "code", {
                get: function() {
                    return this._code
                },
                set: function(a) {
                    this._code = a
                },
                enumerable: !0,
                configurable: !0
            });
            a.prototype.codeAsString = function() {
                return String.fromCharCode(this.code)
            };
            Object.defineProperty(a.prototype, "bounds", {
                get: function() {
                    return this._bounds.clone()
                },
                set: function(a) {
                    this._bounds = a.clone()
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "isEndOfWord", {
                get: function() {
                    return this._isEndOfWord
                },
                set: function(a) {
                    this._isEndOfWord = a
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "isEndOfLine", {
                get: function() {
                    return this._isEndOfLine
                },
                set: function(a) {
                    this._isEndOfLine = a
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "isRightToLeft", {
                get: function() {
                    return this._isRightToLeft
                },
                set: function(a) {
                    this._isRightToLeft = a
                },
                enumerable: !0,
                configurable: !0
            });
            return a
        }();
        d.DocumentCharacter = h;
        var a = function() {
            function a() {
                this._value = null;
                this._bounds = c.LeadRectD.empty
            }
            Object.defineProperty(a.prototype, "value", {
                get: function() {
                    return this._value
                },
                set: function(a) {
                    this._value = a
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "bounds", {
                get: function() {
                    return this._bounds
                },
                set: function(a) {
                    this._bounds = a
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "firstCharacterIndex", {
                get: function() {
                    return this._firstCharacterIndex
                },
                set: function(a) {
                    this._firstCharacterIndex = a
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "lastCharacterIndex", {
                get: function() {
                    return this._lastCharacterIndex
                },
                set: function(a) {
                    this._lastCharacterIndex = a
                },
                enumerable: !0,
                configurable: !0
            });
            return a
        }();
        d.DocumentWord = a;
        var g = function() {
            function b(a) {
                this._textMap = this._text = this._words = this._characters =
                    null;
                this._characters = a
            }
            b.fromJson = function(a) {
                if (null == a || !a.hasOwnProperty("characters")) return null;
                var f = new b(null);
                f._characters = [];
                for (var i = 0; i < a.characters.length; i++) {
                    var j = new h;
                    j.isEndOfWord = a.characters[i].isEndOfWord;
                    j.isEndOfLine = a.characters[i].isEndOfLine;
                    j.isRightToLeft = a.characters[i].isRightToLeft;
                    j.code = a.characters[i].code;
                    j.bounds = c.LeadRectD.fromJSON(a.characters[i].bounds);
                    f._characters.push(j)
                }
                return f
            };
            Object.defineProperty(b.prototype, "characters", {
                get: function() {
                    return this._characters
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "words", {
                get: function() {
                    return this._words
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "text", {
                get: function() {
                    return this._text
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "textMap", {
                get: function() {
                    return this._textMap
                },
                enumerable: !0,
                configurable: !0
            });
            b.prototype.clearBuildData = function() {
                this._textMap = this._text = this._words = null
            };
            b.prototype.buildWords = function() {
                for (var e = 0, f = this._characters.length, i = [], j = !1, d = -
                        1; e < f;) {
                    for (var k = c.LeadRectD.empty, g = e, n = !1, l = !0, m =
                            new h; l && e < f;) m = this._characters[e], l = m.bounds
                        .clone(),
                        k = k.isEmpty ? l.clone() : c.LeadRectD.unionRects(k, l), l =
                        e < f && !m.isEndOfWord && !m.isEndOfLine, e++, m.isRightToLeft &&
                        (n = !0);
                    for (var l = "", q = g; q < e; q++) l += this._characters[q].code;
                    q = new a;
                    q.value = l;
                    q.bounds = k.clone();
                    q.firstCharacterIndex = g;
                    q.lastCharacterIndex = e - 1;
                    n && (q.value = b.reverse(q.value));
                    j && n ? (d = -1 == d ? i.length - 1 : d, i.splice(d, 0, q)) :
                        (-1 != d && (this.reverseCharacterPositions(i[d].lastCharacterIndex,
                            i[i.length - 1].lastCharacterIndex), d = -1), i.push(q));
                    j = n && !m.isEndOfLine
                }
                this._words = i
            };
            b.reverse = function(a) {
                return a.split("").reverse().join("")
            };
            b.prototype.reverseCharacterPositions = function(a, b) {
                var c = this._characters[a],
                    j = this._characters[a];
                j.isEndOfWord = this._characters[b].isEndOfWord;
                j.isEndOfLine = this._characters[b].isEndOfLine;
                this._characters[a] = j;
                j = this._characters[b];
                j.isEndOfWord = c.isEndOfWord;
                j.isEndOfLine = c.isEndOfLine;
                this._characters[b] = j
            };
            b.prototype.buildText = function() {
                this.doBuildText(!1)
            };
            b.prototype.buildTextWithMap = function() {
                this.doBuildText(!0)
            };
            b.prototype.doBuildText = function(a) {
                var b = !1;
                null === this._words && null !==
                    this._characters && (this.buildWords(), b = !0);
                if (null === this._words || 0 === this._words.length) this._textMap =
                    null, this._text = "", b && (this._words = null);
                else {
                    this._textMap = a ? [] : null;
                    for (var c = "", j = this._words.length, d = 0; d < j;) {
                        var k = this._words[d];
                        if (null != k.value && "" !== k.value && (c += k.value, a))
                            for (var g = k.firstCharacterIndex; g <= k.lastCharacterIndex; g++)
                                this._textMap.push(g);
                        g = !1; - 1 !== k.lastCharacterIndex && (g = this._characters[
                            k.lastCharacterIndex].isEndOfLine);
                        k = d === j - 1;
                        g ? (k || (c += "\n"), a && this._textMap.push(-2)) :
                            k || (c += " ", a && this._textMap.push(-1));
                        d++
                    }
                    b && (this._words = null);
                    this._text = c
                }
            };
            b.prototype.removeSmallerDuplicate = function(a, b) {
                for (var c = a.length - 1; 0 <= c; c--) b.bounds.containsRect(a[
                    c].bounds) && a.splice(c, 1)
            };
            b.prototype.parseLinks = function(a) {
                var b = [];
                if (null == a || 0 === a.length) return b;
                null == this._textMap && this.buildTextWithMap();
                var c = this.text;
                if (!c || 0 === c.length) return b;
                var j = this.textMap;
                if (!j || 0 === j.length) return b;
                for (var p = 0; p < a.length; p++)
                    for (var k = null, g = a[p]; null !== (k = g.exec(c));) {
                        var h =
                            k.index,
                            l = k.index + k[0].length - 1,
                            m = this.characters,
                            q = j.length;
                        if (!(0 > h || h >= q || 0 > l || l >= q)) {
                            h = j[h];
                            for (q = j[l]; - 1 === h && h < q;) h++;
                            if (!(h > q)) {
                                l = m[h].bounds;
                                for (h += 1; h <= q; h++) l.union(m[q].bounds);
                                m = new d.DocumentLink;
                                m.linkType = d.DocumentLinkType.value;
                                m.bounds = l;
                                m.value = k[0];
                                this.removeSmallerDuplicate(b, m);
                                b.push(m)
                            }
                        }
                    }
                return b
            };
            b.prototype.clipText = function(a) {
                if (a.isEmpty) return b.fromJson(this);
                var c = this._characters;
                if (null == c) return null;
                var i = [],
                    d = c.length,
                    p = new h;
                p.isEndOfWord = !1;
                p.isEndOfLine = !1;
                for (var k = -1, g = 0; g < d; g++) {
                    var n = c[g],
                        l = !1,
                        m = n.code;
                    9 == m || 160 == m || 5760 == m || 6158 == m || 8192 <= m &&
                        8202 >= m || 8232 == m || 8233 == m || 8239 == m || 8287 ==
                        m || 12288 == m ? l = !0 : a.isEmpty || (l = !a.intersectsWith(
                            n.bounds));
                    if (l) {
                        if (n.isEndOfWord && (p.isEndOfWord = !0), n.isEndOfLine) p
                            .isEndOfLine = !0
                    } else {
                        if (p.isEndOfWord || p.isEndOfLine) - 1 != k && (l = i[k],
                                p.isEndOfWord && (l.isEndOfWord = !0), p.isEndOfLine &&
                                (l.isEndOfLine = !0), i[k] = l), p.isEndOfWord = !1, p.isEndOfLine = !
                            1;
                        i.push(n);
                        k++
                    }
                }
                return new b(i)
            };
            return b
        }();
        d.DocumentPageText = g
    })(c.Documents ||
        (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(d) {
        var h = function() {
            function a() {
                this._length = this._mimeType = this._url = null
            }
            a.fromJson = function(c) {
                if (null == c) return null;
                var f = new a;
                return !f.copyFromJson(c) ? null : f
            };
            a.prototype.copyFromJson = function(a) {
                return null == a ? !1 : d.Internal.PropertyBag.copyMatchingProperties(
                    a, this, null)
            };
            Object.defineProperty(a.prototype, "url", {
                get: function() {
                    return this._url
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "mimeType", {
                get: function() {
                    return this._mimeType
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "length", {
                get: function() {
                    return this._length
                },
                enumerable: !0,
                configurable: !0
            });
            return a
        }();
        d.ConvertItem = h;
        var a = function() {
            function a() {
                this._archive = new h;
                this._document = new h;
                this._annotations = new h
            }
            a.fromJson = function(c) {
                if (null == c) return null;
                var f = new a;
                return !f.copyFromJson(c) ? null : f
            };
            a.prototype.copyFromJson = function(a) {
                return null == a ? !1 : d.Internal.PropertyBag.copyMatchingProperties(
                    a, this, null)
            };
            Object.defineProperty(a.prototype, "archive", {
                get: function() {
                    return this._archive
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "document", {
                get: function() {
                    return this._document
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "annotations", {
                get: function() {
                    return this._annotations
                },
                enumerable: !0,
                configurable: !0
            });
            return a
        }();
        d.DocumentConvertResult = a;
        var g = function() {
            function b() {
                this._structure = this._barcodes = this._images = this._annotations =
                    this._pages = null;
                this._values = new d.Internal.DocumentValues;
                this._text =
                    new d.DocumentText(this);
                this._serviceUserData = null;
                this._pages = new d.DocumentPages(this);
                this._annotations = new d.DocumentAnnotations(this);
                this._images = new d.DocumentImages(this);
                this._barcodes = new d.DocumentBarcodes(this);
                this._structure = null
            }
            b.fromJson = function(a) {
                if (null == a) return null;
                var c = new b;
                return !c.copyFromJson(a) ? null : c
            };
            b.prototype.copyFromJson = function(a) {
                var b = this;
                if (null == a || !a.hasOwnProperty("values") || !a.hasOwnProperty(
                        "pages") || !this._values.copyFromJson(a.values)) return !1;
                for (var c =
                        0; c < a.pages.length; c++) this._pages.push(d.DocumentPage
                    .fromJson(a.pages[c], this, c + 1));
                this._values.isStructureSupported ? (this._structure = new d.DocumentStructure(
                    this), this._values.isStructureParsed && a.hasOwnProperty(
                    "structure") && (c = a.structure, this._structure.copyFromJson(
                    c.bookmarks, c.pageLinks))) : this._structure = null;
                if (a.hasOwnProperty("metadata")) {
                    var j = a.metadata;
                    this._metadata = {};
                    Object.keys(j).forEach(function(a) {
                        b._metadata[a] = j[a]
                    })
                }
                return a.hasOwnProperty("text") && !this._text.copyFromJson(a.text) ?
                    !1 : !0
            };
            Object.defineProperty(b.prototype, "documentId", {
                get: function() {
                    return this.values.documentId
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "name", {
                get: function() {
                    return this.values.name
                },
                set: function(a) {
                    this.values.name = a
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "uri", {
                get: function() {
                    return this.values.uri
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "isDownloaded", {
                get: function() {
                    return this.values.isDownloaded
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "isReadOnly", {
                get: function() {
                    return this.values.isReadOnly
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "mimeType", {
                get: function() {
                    return this.values.mimeType
                },
                set: function(a) {
                    this.values.mimeType = a
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "pages", {
                get: function() {
                    return this._pages
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "isEncrypted", {
                get: function() {
                    return this.values.isEncrypted
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "isDecrypted", {
                get: function() {
                    return this.values.isDecrypted
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "isStructureSupported", {
                get: function() {
                    return this.values.isStructureSupported
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "annotations", {
                get: function() {
                    return this._annotations
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "structure", {
                get: function() {
                    return this._structure
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "images", {
                get: function() {
                    return this._images
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "barcodes", {
                get: function() {
                    return this._barcodes
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "metadata", {
                get: function() {
                    return this._metadata
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "text", {
                get: function() {
                    return this._text
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype,
                "defaultResolution", {
                    get: function() {
                        return this.values.defaultResolution
                    },
                    enumerable: !0,
                    configurable: !0
                });
            Object.defineProperty(b, "unitsPerInch", {
                get: function() {
                    return 720
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "values", {
                get: function() {
                    return this._values
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "annotationsUri", {
                get: function() {
                    return this.values.annotationsUri
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "defaultBitsPerPixel", {
                get: function() {
                    return this.values.defaultBitsPerPixel
                },
                set: function(a) {
                    this.values.defaultBitsPerPixel = a
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "defaultPageSize", {
                get: function() {
                    return this.values.defaultPageSize
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "documentType", {
                get: function() {
                    return this.values.documentType
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "password", {
                get: function() {
                    return this.values.password
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "serviceUserData", {
                get: function() {
                    return this._serviceUserData
                },
                set: function(a) {
                    this._serviceUserData = a
                },
                enumerable: !0,
                configurable: !0
            });
            b.prototype.validate = function() {
                return this.mimeType ? {
                    valid: !0,
                    error: ""
                } : {
                    valid: !1,
                    error: "Unrecognized image file format."
                }
            };
            b.prototype.decrypt = function(a) {
                var c = this,
                    i = [d.DocumentFactory.serviceUri, b._controllerName,
                        "Decrypt"
                    ].join("/"),
                    a = {
                        documentId: this.documentId,
                        password: a,
                        userData: this.serviceUserData
                    },
                    j =
                    $.Deferred(),
                    i = {
                        url: i,
                        type: "POST",
                        contentType: "application/json",
                        data: JSON.stringify(a)
                    };
                if (!d.DocumentFactory.doPrepareAjax(this, b._className,
                        "Decrypt", i)) return j.reject(null, "Canceled", "Canceled")
                    .promise();
                $.ajax(i).done(function(a) {
                    var b = a.document;
                    c.copyFromJson(b) ? j.resolve(b, "undefined" === typeof a
                        .userData ? null : a.userData) : j.reject(null, null,
                        "Invalid document object")
                }).fail(function(a, b, c) {
                    d.DocumentFactory.log(c);
                    j.reject(a, b, c)
                });
                return j.promise()
            };
            b.prototype.convert = function(c) {
                var f = [d.DocumentFactory.serviceUri, b._controllerName,
                        "Convert"
                    ].join("/"),
                    c = {
                        documentId: this.documentId,
                        jobData: c,
                        userData: this.serviceUserData
                    },
                    i = $.Deferred(),
                    f = {
                        url: f,
                        type: "POST",
                        contentType: "application/json",
                        data: JSON.stringify(c)
                    };
                if (!d.DocumentFactory.doPrepareAjax(this, b._className,
                        "Convert", f)) return i.reject(null, "Canceled", "Canceled")
                    .promise();
                $.ajax(f).done(function(b) {
                    var c = a.fromJson(b);
                    i.resolve(c, "undefined" === typeof b.userData ? null : b
                        .userData)
                }).fail(function(a, b, c) {
                    d.DocumentFactory.log(c);
                    i.reject(a, b, c)
                });
                return i.promise()
            };
            b.documentToPixels = function(a, f) {
                c.LeadDoubleTools.isZero(a) && (a = 96);
                return Math.floor(f / b.unitsPerInch * a + 0.5)
            };
            b.pixelsToDocument = function(a, f) {
                c.LeadDoubleTools.isZero(a) && (a = 96);
                return f * b.unitsPerInch / a
            };
            b.prototype.pointToPixels = function(a) {
                if (a.isEmpty) return c.LeadPointD.empty;
                var f = this.defaultResolution;
                return c.LeadPointD.create(b.documentToPixels(f, a.x), b.documentToPixels(
                    f, a.y))
            };
            b.prototype.pointToDocument = function(a) {
                var f = this.defaultResolution;
                return c.LeadPointD.create(b.pixelsToDocument(f, a.x), b.pixelsToDocument(
                    f, a.y))
            };
            b.prototype.sizeToPixels = function(a) {
                if (a.isEmpty) return c.LeadSizeD.empty;
                var f = this.defaultResolution;
                return c.LeadSizeD.create(b.documentToPixels(f, a.width), b.documentToPixels(
                    f, a.height))
            };
            b.prototype.sizeToDocument = function(a) {
                var f = this.defaultResolution;
                return c.LeadSizeD.create(b.pixelsToDocument(f, a.width), b.pixelsToDocument(
                    f, a.height))
            };
            b.prototype.rectToPixels = function(a) {
                if (a.isEmpty) return c.LeadRectD.empty;
                var f = this.defaultResolution;
                return c.LeadRectD.fromLTRB(b.documentToPixels(f, a.left), b.documentToPixels(
                    f, a.top), b.documentToPixels(f, a.right), b.documentToPixels(
                    f, a.bottom))
            };
            b.prototype.rectToDocument = function(a) {
                var f = this.defaultResolution;
                return c.LeadRectD.fromLTRB(b.pixelsToDocument(f, a.left), b.pixelsToDocument(
                    f, a.top), b.pixelsToDocument(f, a.right), b.pixelsToDocument(
                    f, a.bottom))
            };
            b._className = "Document";
            b._controllerName = "Document";
            return b
        }();
        d.Document = g
    })(c.Documents || (c.Documents = {}))
})(lt ||
    (lt = {}));
(function(c) {
    (function(d) {
        (function(h) {
            var a = function() {
                function a() {}
                a.createFullUrl = function(a, c) {
                    var f = a + "?",
                        i = Object.keys(c);
                    i.forEach(function(a, b) {
                        f += a + "=" + c[a];
                        b != i.length - 1 && (f += "&")
                    });
                    return f
                };
                a.createBaseUrl = function(a, d) {
                    return c.Documents.DocumentFactory.serviceUri + "/" + a +
                        "/" + d
                };
                a.doGenericElementLoad = function(a, e, f, i, j, p) {
                    var g = this,
                        o = !!e,
                        n = o ? e.urlMode : d.DocumentImages.elementUrlMode,
                        l = h.ImageRequestHelper.createAjaxSettings(n, f, o ? e.ajaxOptions
                            .method : d.DocumentImages.elementAjaxMethod, i);
                    o || (e = new c.ImageLoader, e.urlMode = n);
                    e.preRun.add(function(c, n) {
                        var o = false;
                        l && !d.DocumentFactory.doPrepareAjax(g, j, p, l) &&
                            (o = true);
                        l ? h.ImageRequestHelper.setAjaxSettings(e, l) : e.url =
                            h.ImageRequestHelper.createFullUrl(f, i);
                        h.ImageRequestHelper.setImageLoaderCallbacks(e, a);
                        if (o) {
                            a.reject(null, "Canceled", "Canceled").promise();
                            n.cancel = true
                        }
                    });
                    o || e.run();
                    return a.promise()
                };
                a.createAjaxSettings = function(a, e, f, i) {
                    if (a === c.ImageLoaderUrlMode.imageUrl) return null;
                    f || (f = d.DocumentImages.elementAjaxMethod);
                    a = {
                        url: e,
                        type: f,
                        method: f
                    };
                    "post" === f.toLowerCase() ? (a.contentType =
                        "application/json; charset=utf-8", a.data = JSON.stringify(
                            i)) : a.data = i;
                    return a
                };
                a.setAjaxSettings = function(a, c) {
                    var f = a.ajaxOptions,
                        i = c.headers;
                    i && Object.keys(i).forEach(function(a) {
                        f.headers[a] = i[a]
                    });
                    f.method = c.method || c.type;
                    if (f.method)
                        if ("get" === f.method.toLowerCase()) a.url = h.ImageRequestHelper
                            .createFullUrl(c.url, c.data);
                        else if ("post" === f.method.toLowerCase()) {
                        if (!i || !i["content-type"] && !i.contentType) f.headers[
                                "content-type"] =
                            c.contentType;
                        a.url = c.url;
                        f.postData = c.data
                    }
                };
                a.setImageLoaderCallbacks = function(a, c) {
                    var f = function(a) {
                            c.resolve(a.element)
                        },
                        i = function(a) {
                            d.DocumentFactory.log(a.error.message);
                            c.reject(null, "error", a.error)
                        },
                        j;
                    j = function() {
                        a.done.remove(f);
                        a.fail.remove(i);
                        a.always.remove(j)
                    };
                    a.done.add(f);
                    a.fail.add(i);
                    a.always.add(j)
                };
                return a
            }();
            h.ImageRequestHelper = a;
            a = function() {
                function a() {}
                a.copyMatchingProperties = function(a, e, f) {
                    Object.getOwnPropertyNames(e).forEach(function(i) {
                        if (1 < i.length && "_" === i.charAt(0) &&
                            "_" !== i.charAt(1)) {
                            var j = i.substr(1);
                            if (!(null != f && -1 != f.indexOf(j))) {
                                if (!a.hasOwnProperty(j)) return d.DocumentFactory
                                    .log("Missing required property: " + j), !1;
                                var g = e[i],
                                    j = a[j];
                                e[i] = g instanceof c.LeadPointD ? c.LeadPointD.fromJSON(
                                        j) : g instanceof c.LeadSizeD ? c.LeadSizeD.fromJSON(
                                        j) : g instanceof c.LeadRectD ? c.LeadRectD.fromJSON(
                                        j) : g instanceof c.Documents.ConvertItem ? c
                                    .Documents.ConvertItem.fromJson(j) : j
                            }
                        }
                    });
                    return !0
                };
                return a
            }();
            h.PropertyBag = a;
            a = function() {
                function a() {}
                a.slice = function(a, c, f) {
                    void 0 ===
                        c && (c = 0);
                    void 0 === f && (f = a.byteLength);
                    c = Math.floor(c);
                    f = Math.floor(f);
                    0 > c && (c += a.byteLength);
                    0 > f && (f += a.byteLength);
                    c = Math.min(Math.max(0, c), a.byteLength);
                    f = Math.min(Math.max(0, f), a.byteLength);
                    if (0 >= f - c) return new ArrayBuffer(0);
                    var i = new ArrayBuffer(f - c),
                        d = new Uint8Array(i),
                        a = new Uint8Array(a, c, f - c);
                    d.set(a);
                    return i
                };
                return a
            }();
            h.ArrayBufferSlicer = a
        })(d.Internal || (d.Internal = {}))
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    (function(d) {
        (function(a) {
            a[a.created = 0] = "created";
            a[a.uploading = 1] = "uploading";
            a[a.finished = 2] = "finished";
            a[a.error = 3] = "error";
            a[a.aborted = 4] = "aborted"
        })(d.DocumentUploadProgressState || (d.DocumentUploadProgressState = {}));
        var h = d.DocumentUploadProgressState,
            a = function() {
                function a(c, b, f) {
                    this._state = c;
                    this._progress = b;
                    this._userData = f
                }
                Object.defineProperty(a.prototype, "state", {
                    get: function() {
                        return this._state
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "progress", {
                    get: function() {
                        return this._progress
                    },
                    enumerable: !0,
                    configurable: !0
                });
                Object.defineProperty(a.prototype, "userData", {
                    get: function() {
                        return this._userData
                    },
                    enumerable: !0,
                    configurable: !0
                });
                return a
            }();
        d.DocumentUploadProgress = a;
        var g = function() {
            function a(c, b, f, d, e, g) {
                this._message = c;
                this._time = b;
                this._isLicenseChecked = f;
                this._isLicenseExpired = d;
                this._isCacheAccessible = e;
                this._kernelType = g
            }
            Object.defineProperty(a.prototype, "message", {
                get: function() {
                    return this._message
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "time", {
                get: function() {
                    return this._time
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "isLicenseChecked", {
                get: function() {
                    return this._isLicenseChecked
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "isLicenseExpired", {
                get: function() {
                    return this._isLicenseExpired
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "isCacheAccessible", {
                get: function() {
                    return this._isCacheAccessible
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "kernelType", {
                get: function() {
                    return this._kernelType
                },
                enumerable: !0,
                configurable: !0
            });
            return a
        }();
        d.ServiceStatus = g;
        var b = function() {
            function b() {}
            b.verifyService = function() {
                var a = [b.serviceUri, "Test/Ping"].join("/"),
                    c = {
                        userData: b._serviceUserData
                    },
                    d = $.Deferred(),
                    a = {
                        url: a,
                        type: "POST",
                        contentType: "application/json",
                        data: JSON.stringify(c)
                    };
                if (!b.doPrepareAjax(this, b._className, "VerifyService", a))
                    return d.reject(null, "Canceled", "Canceled").promise();
                $.ajax(a).done(function(a) {
                    var b =
                        new g(a.message, new Date(a.time), a.isLicenseChecked,
                            a.isLicenseExpired, a.isCacheAccessible, a.kernelType
                        );
                    d.resolve(b, "undefined" === typeof a.userData ? null : a
                        .userData)
                }).fail(function(a, c, i) {
                    b.log(i);
                    d.reject(a, c, i)
                });
                return d.promise()
            };
            b.checkLicense = function() {
                var a = [b.serviceUri, "Test/CheckLicense"].join("/"),
                    c = {
                        userData: b._serviceUserData
                    },
                    d = $.Deferred(),
                    a = {
                        url: a,
                        type: "POST",
                        contentType: "application/json",
                        data: JSON.stringify(c)
                    };
                if (!b.doPrepareAjax(this, b._className, "CheckLicense", a))
                    return d.reject(null,
                        "Canceled", "Canceled").promise();
                $.ajax(a).done(function(a) {
                    d.resolve(a.isExpired, "undefined" === typeof a.userData ?
                        null : a.userData)
                }).fail(function(a, c, i) {
                    b.log(i);
                    d.reject(a, c, i)
                });
                return d.promise()
            };
            Object.defineProperty(b, "serviceHost", {
                get: function() {
                    return b._serviceHost
                },
                set: function(a) {
                    b._serviceHost = a;
                    b._serviceUri = b.build()
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b, "servicePath", {
                get: function() {
                    return b._servicePath
                },
                set: function(a) {
                    b._servicePath = a;
                    b._serviceUri = b.build()
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b, "serviceApiPath", {
                get: function() {
                    return b._serviceApiPath
                },
                set: function(a) {
                    b._serviceApiPath = a;
                    b._serviceUri = b.build()
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b, "serviceUri", {
                get: function() {
                    null == b._serviceUri && (b._serviceUri = b.build());
                    return b._serviceUri
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b, "serviceUserData", {
                get: function() {
                    return b._serviceUserData
                },
                set: function(a) {
                    b._serviceUserData = a
                },
                enumerable: !0,
                configurable: !0
            });
            b.build = function() {
                var a = null,
                    c = window.location,
                    a = b._serviceHost,
                    d = b._servicePath,
                    e = b._serviceApiPath,
                    a = null == a ? c.protocol + "//" + c.host : a;
                "/" === a.charAt(a.length - 1) && (a = a.substring(0, a.length -
                    1));
                null == d ? (c = c.pathname, -1 < c.indexOf(".") && (d = c.lastIndexOf(
                        "/"), -1 < d && (c = c.substring(0, d))), a += b.clean(c)) :
                    a += b.clean(d);
                null != e && 0 < e.length && (a += b.clean(e));
                return a
            };
            b.clean = function(a) {
                var b = a.length;
                "/" !== a.charAt(0) && (a = "/" + a, b += 1);
                "/" === a.charAt(b - 1) && (a = a.substring(0, b - 1));
                return a
            };
            Object.defineProperty(b,
                "logErrors", {
                    get: function() {
                        return b._logErrors
                    },
                    set: function(a) {
                        b._logErrors = a
                    },
                    enumerable: !0,
                    configurable: !0
                });
            b.log = function(a) {
                b.logErrors && console.log(a)
            };
            b.isUploadDocumentUri = function(a) {
                if (null == a) return !1;
                a = a.toLowerCase();
                return 0 == a.indexOf("http://leadcache") || 0 == a.indexOf(
                    "https://leadcache")
            };
            b.loadFromCache = function(a) {
                var c = [b.serviceUri, b._controllerName, "LoadFromCache"].join(
                        "/"),
                    a = {
                        documentId: a,
                        userData: b.serviceUserData
                    },
                    e = $.Deferred(),
                    c = {
                        url: c,
                        type: "POST",
                        contentType: "application/json",
                        data: JSON.stringify(a)
                    };
                if (!b.doPrepareAjax(this, b._className, "LoadFromCache", c))
                    return e.reject(null, "Canceled", "Canceled").promise();
                $.ajax(c).done(function(a) {
                    var b = d.Document.fromJson(a.document);
                    e.resolve(b, "undefined" === typeof a.userData ? null : a
                        .userData)
                }).fail(function(a, c, d) {
                    b.log(d);
                    e.reject(a, c, d)
                });
                return e.promise()
            };
            b.loadFromUri = function(a, c) {
                var e = [b.serviceUri, b._controllerName, "LoadFromUri"].join(
                    "/");
                null == c && (c = new d.LoadDocumentOptions);
                var g = {
                        uri: a,
                        resolution: c.resolution,
                        options: c,
                        userData: b.serviceUserData
                    },
                    h = $.Deferred(),
                    e = {
                        url: e,
                        type: "POST",
                        contentType: "application/json",
                        data: JSON.stringify(g)
                    };
                if (!b.doPrepareAjax(this, b._className, "LoadFromUri", e))
                    return h.reject(null, "Canceled", "Canceled").promise();
                $.ajax(e).done(function(a) {
                    var b = d.Document.fromJson(a.document);
                    h.resolve(b, "undefined" === typeof a.userData ? null : a
                        .userData)
                }).fail(function(a, c, d) {
                    b.log(d);
                    h.reject(a, c, d)
                });
                return h.promise()
            };
            b.uploadFile = function(d) {
                var e = $.Deferred();
                if (!c.LTHelper.supportsFileReader) return console.error ?
                    console.error(
                        "The FileReader API is not supported on this browser - all file manipulation is unavailable."
                    ) : console.log && console.log(
                        "The FileReader API is not supported on this browser - all file manipulation is unavailable."
                    ), e.reject(null, "browsererror",
                        "The FileReader API is not supported on this browser - all file manipulation is unavailable."
                    ), e.promise({
                        abort: function() {}
                    });
                var g = null,
                    k = !1,
                    o = !1;
                b.beginUpload().done(function(c) {
                    e.notify(new a(h.created, 0, null));
                    g = c;
                    c = new FileReader;
                    c.onloadend =
                        function(c) {
                            if (2 === c.target.readyState) {
                                var l = c.target.result,
                                    n = 0,
                                    r = function(c) {
                                        n += 1048576;
                                        if (!k) {
                                            var m = 100 * (n / d.size),
                                                m = Math.max(0, Math.min(m, 100));
                                            n < d.size ? (e.notify(new a(h.uploading, m,
                                                    c)), b.uploadDocument(g, l, n, 1048576)
                                                .done(r).fail(s)) : (o = !0, e.notify(new a(
                                                h.finished, m, c)), e.resolve(g, c))
                                        }
                                    },
                                    s = function(a, c, d) {
                                        k || (b.log(d), e.reject(a, c, d))
                                    };
                                b.uploadDocument(g, l, n, 1048576).done(r).fail(s)
                            }
                        };
                    var l = d.slice(0, d.size);
                    c.readAsArrayBuffer(l)
                }).fail(function(a, c, d) {
                    b.log(d);
                    e.reject(a, c, d)
                });
                return e.promise({
                    abort: function() {
                        k = !0;
                        o ? b.log("Cannot abort after upload has completed") :
                            (b.abortUploadDocument(g), e.notify(new a(h.aborted,
                                0, null)), e.reject(null, null, null))
                    }
                })
            };
            b.beginUpload = function() {
                var a = $.Deferred(),
                    c = {
                        url: [b.serviceUri, b._controllerName, "BeginUpload"].join(
                            "/"),
                        type: "POST",
                        contentType: "application/json",
                        data: JSON.stringify({
                            userData: b._serviceUserData
                        })
                    };
                if (!b.doPrepareAjax(this, b._className, "BeginUpload", c))
                    return a.reject(null, "Canceled", "Canceled").promise();
                $.ajax(c).done(function(b) {
                    a.resolve(b.uploadUri,
                        "undefined" === typeof b.userData ? null : b.userData
                    )
                }).fail(function(c, d, e) {
                    b.log(e);
                    a.reject(c, d, e)
                });
                return a.promise()
            };
            b.uploadDocument = function(a, e, g, h) {
                var o = $.Deferred();
                if (!c.LTHelper.supportsFileReader) return console.error ?
                    console.error(
                        "The FileReader API is not supported on this browser - all file manipulation is unavailable."
                    ) : console.log && console.log(
                        "The FileReader API is not supported on this browser - all file manipulation is unavailable."
                    ), o.reject(null, "browsererror",
                        "The FileReader API is not supported on this browser - all file manipulation is unavailable."
                    ).promise();
                var n = null,
                    n = ArrayBuffer.prototype.slice ? e.slice(g, g + h) : d.Internal
                    .ArrayBufferSlicer.slice(e, g, g + h),
                    e = c.LTHelper.base64EncodeFromArrayBuffer(n),
                    a = {
                        url: [b.serviceUri, b._controllerName, "UploadDocument"].join(
                            "/"),
                        type: "POST",
                        contentType: "application/json",
                        data: JSON.stringify({
                            uri: a,
                            data: e,
                            userData: b._serviceUserData
                        })
                    };
                if (!b.doPrepareAjax(this, b._className, "UploadDocument", a))
                    return o.reject(null, "Canceled", "Canceled").promise();
                $.ajax(a).done(function(a) {
                    o.resolve("undefined" === typeof a.userData ? null :
                        a.userData)
                }).fail(function(a, c, d) {
                    b.log(d);
                    o.reject(a, c, d)
                });
                return o.promise()
            };
            b.abortUploadDocument = function(a) {
                var c = [b.serviceUri, b._controllerName, "AbortUploadDocument"]
                    .join("/"),
                    a = {
                        uri: a,
                        userData: b._serviceUserData
                    },
                    d = $.Deferred(),
                    c = {
                        url: c,
                        type: "POST",
                        contentType: "application/json",
                        data: JSON.stringify(a)
                    };
                if (!b.doPrepareAjax(this, b._className, "AbortUploadDocument",
                        c)) return d.reject(null, "Canceled", "Canceled").promise();
                $.ajax(c).done(function(a) {
                    d.resolve("undefined" === typeof a.userData ?
                        null : a.userData)
                });
                return d.promise()
            };
            b.downloadDocument = function(a, d, e, g) {
                var h = [b.serviceUri, b._controllerName, "DownloadDocument"].join(
                        "/"),
                    a = {
                        documentId: a,
                        uri: d,
                        position: e,
                        dataSize: g,
                        userData: b._serviceUserData
                    },
                    n = $.Deferred(),
                    h = {
                        url: h,
                        type: "GET",
                        contentType: "application/json",
                        data: a,
                        headers: {
                            "cache-control": "no-cache"
                        }
                    };
                if (!b.doPrepareAjax(this, b._className, "DownloadDocument", h))
                    return n.reject(null, "Canceled", "Canceled").promise();
                $.ajax(h).fail(function(a, c, d) {
                    b.log(d);
                    n.reject(a, c, d)
                }).done(function(a) {
                    var b =
                        c.LTHelper.base64DecodeToByteArray(a.data);
                    n.resolve(b, "undefined" === typeof a.userData ? null : a
                        .userData)
                });
                return n.promise()
            };
            b.deleteFromCache = function(a) {
                var c = [b.serviceUri, b._controllerName, "Delete"].join("/"),
                    a = {
                        documentId: a,
                        userData: b._serviceUserData
                    },
                    d = $.Deferred(),
                    c = {
                        url: c,
                        type: "POST",
                        contentType: "application/json",
                        data: JSON.stringify(a)
                    };
                if (!b.doPrepareAjax(this, b._className, "DeleteFromCache", c))
                    return d.reject(null, "Canceled", "Canceled").promise();
                $.ajax(c).fail(function(a, c, e) {
                    b.log(e);
                    d.reject(a, c, e)
                }).done(function(a) {
                    d.resolve("undefined" === typeof a.userData ? null : a.userData)
                });
                return d.promise()
            };
            Object.defineProperty(b, "prepareAjax", {
                get: function() {
                    return b._prepareAjax
                },
                enumerable: !0,
                configurable: !0
            });
            b.doPrepareAjax = function(a, c, d, g) {
                c = new e(c, d, g);
                b._prepareAjax.invoke(a, c);
                return !c.cancel
            };
            b._className = "DocumentFactory";
            b._controllerName = "Factory";
            b._serviceHost = null;
            b._servicePath = null;
            b._serviceApiPath = null;
            b._serviceUri = null;
            b._serviceUserData = null;
            b._logErrors = !0;
            b._prepareAjax =
                c.LeadEvent.create(b, "prepareAjax");
            return b
        }();
        d.DocumentFactory = b;
        var e = function(a) {
            function b(c, d, e) {
                a.call(this);
                this._sourceClass = c[0].toUpperCase() + c.substr(1, c.length - 1);
                this._sourceMethod = d[0].toUpperCase() + d.substr(1, d.length -
                    1);
                this._settings = e;
                this._cancel = !1
            }
            __extends(b, a);
            Object.defineProperty(b.prototype, "sourceClass", {
                get: function() {
                    return this._sourceClass
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "sourceMethod", {
                get: function() {
                    return this._sourceMethod
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "settings", {
                get: function() {
                    return this._settings
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(b.prototype, "cancel", {
                get: function() {
                    return this._cancel
                },
                set: function(a) {
                    this._cancel = a
                },
                enumerable: !0,
                configurable: !0
            });
            return b
        }(c.LeadEventArgs);
        d.PrepareAjaxEventArgs = e;
        b = function(a) {
            function b() {
                a.apply(this, arguments)
            }
            __extends(b, a);
            b.prototype.add = function(b) {
                return a.prototype.add.call(this, b)
            };
            b.prototype.remove = function(b) {
                a.prototype.remove.call(this,
                    b)
            };
            return b
        }(c.LeadEvent);
        d.PrepareAjaxEventType = b
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
eval("ltDocumentsString = String;");
(function(c) {
    (function(c) {
        var h = function() {
            function a(a, b, c, d, h, j, p, k, o) {
                this._message = a;
                this._detail = b;
                this._code = c;
                this._link = d;
                this._exceptionType = h;
                this._methodName = j;
                this._jqXHR = p;
                this._statusText = k;
                this._errorThrown = o;
                this._isAbortError = !p && "abort" === k && !o;
                this._isParseError = "parsererror" == k && !!o;
                this._isCancelError = !p && "Canceled" === k && "Canceled" === o;
                this._isBrowserError = "browsererror" == k && !!o
            }
            Object.defineProperty(a.prototype, "message", {
                get: function() {
                    return this._message
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "detail", {
                get: function() {
                    return this._detail
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "code", {
                get: function() {
                    return this._code
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "link", {
                get: function() {
                    return this._link
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "exceptionType", {
                get: function() {
                    return this._exceptionType
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "methodName", {
                get: function() {
                    return this._methodName
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "jqXHR", {
                get: function() {
                    return this._jqXHR
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "statusText", {
                get: function() {
                    return this._statusText
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "errorThrown", {
                get: function() {
                    return this._errorThrown
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "isAbortError", {
                get: function() {
                    return this._isAbortError
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "isParseError", {
                get: function() {
                    return this._isParseError
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "isCancelError", {
                get: function() {
                    return this._isCancelError
                },
                enumerable: !0,
                configurable: !0
            });
            Object.defineProperty(a.prototype, "isBrowserError", {
                get: function() {
                    return this._isBrowserError
                },
                enumerable: !0,
                configurable: !0
            });
            a.parseError = function(g, b, e) {
                if (!g && !b && !e) return new a(null, null, null, null, null,
                    null, null,
                    "abort", null);
                if (!g && "Canceled" === b && "Canceled" === e || !g &&
                    "browsererror" === b) return new a(null, null, null, null,
                    null, null, null, b, e);
                var f = null;
                try {
                    f = JSON.parse(g.responseText)
                } catch (h) {
                    return c.DocumentFactory.log(
                        "Could not parse JSON from Error"), new a(null, null,
                        null, null, null, null, g, b || "parsererror", e ? e :
                        "The response error object could not be parsed from JSON."
                    )
                }
                return new a(f.message, f.detail, f.code, f.link, f.exceptionType,
                    f.methodName, g, b, e)
            };
            return a
        }();
        c.ServiceError = h
    })(c.Documents || (c.Documents = {}))
})(lt || (lt = {}));
(function(c) {
    c = c.Documents || (c.Documents = {});
    c.Service || (c.Service = {})
})(lt || (lt = {}));
(function(c) {
    c = c.Documents || (c.Documents = {});
    c.Service || (c.Service = {})
})(lt || (lt = {}));
(function(c) {
    c = c.Documents || (c.Documents = {});
    c.Service || (c.Service = {})
})(lt || (lt = {}));
(function(c) {
    c = c.Documents || (c.Documents = {});
    c.Service || (c.Service = {})
})(lt || (lt = {}));
(function(c) {
    c = c.Documents || (c.Documents = {});
    c.Service || (c.Service = {})
})(lt || (lt = {}));
(function(c) {
    c = c.Documents || (c.Documents = {});
    c.Service || (c.Service = {})
})(lt || (lt = {}));
(function(c) {
    c = c.Documents || (c.Documents = {});
    c.Service || (c.Service = {})
})(lt || (lt = {}));
(function(c) {
    c = c.Documents || (c.Documents = {});
    c.Service || (c.Service = {})
})(lt || (lt = {}));
(function(c) {
    c = c.Documents || (c.Documents = {});
    c.Service || (c.Service = {})
})(lt || (lt = {}));