package com.civicxpress.letters;

import static com.civicxpress.letters.PdfDocument.*;

public class LetterElementSettings {
    private final static int DEFAULT_FONT_SIZE = 14;
    private final static int DEFAULT_LINE_HEIGHT = 16;
    private final static TextJustification DEFAULT_TEXT_JUSTIFICATION = TextJustification.Left;
    private final static boolean DEFAULT_IS_BOLD = false;
    private int fontSize = DEFAULT_FONT_SIZE;
    private int lineHeight = DEFAULT_LINE_HEIGHT; // TODO: can this be calculated?
    private TextJustification justification = DEFAULT_TEXT_JUSTIFICATION;
    private boolean isBold = DEFAULT_IS_BOLD;

    public LetterElementSettings() {

    }

    public LetterElementSettings(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getLineHeight() {
        return lineHeight;
    }

    public void setLineHeight(int lineHeight) {
        this.lineHeight = lineHeight;
    }

    public TextJustification getJustification() {
        return justification;
    }

    public void setJustification(TextJustification justification) {
        this.justification = justification;
    }

    public boolean getIsBold() {
        return this.isBold;
    }

    public void setIsBold(boolean isBold) {
        this.isBold = isBold;
    }
}
