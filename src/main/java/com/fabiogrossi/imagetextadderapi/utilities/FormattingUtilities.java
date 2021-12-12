package com.fabiogrossi.imagetextadderapi.utilities;

import com.fabiogrossi.imagetextadderapi.data.BrokenText;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

public class FormattingUtilities {

    private FormattingUtilities() {} //Private constructor to hide implicit public one

    public static BrokenText breakText(String text, Font font, Color color, FontRenderContext fontRenderContext, float width) {
        AttributedString attributedString = new AttributedString(text);
        AttributedCharacterIterator characterIterator = attributedString.getIterator();

        attributedString.addAttribute(TextAttribute.FONT, font, 0, characterIterator.getEndIndex());
        attributedString.addAttribute(TextAttribute.FOREGROUND, color, 0, characterIterator.getEndIndex());

        LineBreakMeasurer measurer = new LineBreakMeasurer(characterIterator, fontRenderContext);

        List<TextLayout> lines = new ArrayList<>();
        while (measurer.getPosition() < text.length()) {
            lines.add(measurer.nextLayout(width));
        }

        float textHeight = 0;

        for (TextLayout line : lines) {
            textHeight += line.getAscent() + line.getDescent() + line.getLeading();
        }

        return new BrokenText(lines, text, textHeight);
    }

    public static float getAdaptFontSize(String text, Font initialFont, Color color, FontRenderContext fontRenderContext, int maxHeight, int maxWidth) throws FontFormatException {
        Font font = initialFont;
        float fontSize = font.getSize();

        int height;
        do {
            height = 0;
            fontSize -= 1F;
            font = font.deriveFont(fontSize);

            for (TextLayout line : breakText(text, font, color, fontRenderContext, maxWidth).getLines()) {
                height += line.getAscent() + line.getDescent() + line.getLeading();
            }

            if (fontSize < 0) {
                throw new FontFormatException("Result font size is negative");
            }

        } while (height > maxHeight);
        return fontSize;
    }

}
