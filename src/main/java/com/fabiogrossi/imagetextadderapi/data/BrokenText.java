package com.fabiogrossi.imagetextadderapi.data;

import lombok.Getter;
import lombok.Setter;

import java.awt.font.TextLayout;
import java.util.List;

public class BrokenText {

    @Getter
    private final String fullText;

    @Getter
    @Setter
    private float textHeight;

    @Getter
    private final List<TextLayout> lines;

    public BrokenText(List<TextLayout> lines, String fullText, float textHeight) {
        this.fullText = fullText;
        this.textHeight = textHeight;
        this.lines = lines;
    }
}
