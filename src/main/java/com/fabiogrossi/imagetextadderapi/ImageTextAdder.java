package com.fabiogrossi.imagetextadderapi;

import com.fabiogrossi.imagetextadderapi.data.BrokenText;
import com.fabiogrossi.imagetextadderapi.utilities.FormattingUtilities;
import lombok.SneakyThrows;

import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class ImageTextAdder {

    private ImageTextAdder() {}

    public static BufferedImage drawTextInSquare(BufferedImage image, String text, Font font, Color color, int rectangleX, int rectangleY, int rectangleWidth, int rectangleHeight) {
        Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        Rectangle rectangle = new Rectangle(rectangleX, rectangleY, rectangleWidth, rectangleHeight);
        BrokenText brokenText = FormattingUtilities.breakText(text, font, color, graphics2D.getFontRenderContext(), rectangleWidth);

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Draw rectangle bounds on image -- DEBUG ONLY
        //graphics2D.draw3DRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height, true);

        float y = (float) (rectangle.getY() + (rectangleHeight - brokenText.getTextHeight()) / 2);

        for (TextLayout line : brokenText.getLines()) {
            Rectangle2D bounds = line.getBounds();
            float x = (float) (rectangle.getX() + (rectangleWidth - bounds.getWidth()) / 2);

            line.draw(graphics2D, x, y + line.getAscent());

            y += line.getAscent() + line.getDescent() + line.getLeading();
        }
        graphics2D.dispose();
        return image;
    }

    public static BufferedImage drawTextInCenteredSquareWithOffset(BufferedImage image, String text, Font font, Color color, int rectangleWidth, int rectangleHeight, int offsetX, int offsetY) {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        int rectanglePositionX = (imageWidth / 2) - (rectangleWidth / 2) + offsetX;
        int rectanglePositionY = (imageHeight / 2) - (rectangleHeight / 2) + offsetY;

        return drawTextInSquare(image, text, font, color, rectanglePositionX, rectanglePositionY, rectangleWidth, rectangleHeight);
    }

    public static BufferedImage drawTextInCenteredSquare(BufferedImage image, String text, Font font, Color color, int rectangleWidth, int rectangleHeight) {
        return drawTextInCenteredSquareWithOffset(image, text, font, color, rectangleWidth, rectangleHeight, 0, 0);
    }

    public static float getAdaptFontSize(BufferedImage image, String text, Font initialFont, Color color, int maxHeight, int maxWidth) throws FontFormatException {
        Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        return FormattingUtilities.getAdaptFontSize(text, initialFont, color, graphics2D.getFontRenderContext(), maxHeight, maxWidth);
    }

}
