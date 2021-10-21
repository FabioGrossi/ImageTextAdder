package com.fabiogrossi.imagetextadderapi;

import com.fabiogrossi.imagetextadderapi.data.BrokenText;
import com.fabiogrossi.imagetextadderapi.utilities.TextBreakingUtils;

import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class ImageTextAdder {

    private ImageTextAdder() {}

    public static BufferedImage drawTextInSquare(BufferedImage image, String text, Font font, int rectangleX, int rectangleY, int rectangleWidth, int rectangleHeight) {
        Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        Rectangle rectangle = new Rectangle(rectangleX, rectangleY, rectangleWidth, rectangleHeight);
        BrokenText brokenText = TextBreakingUtils.breakText(text, font, graphics2D.getFontRenderContext(), rectangleWidth);

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

    public static BufferedImage drawTextInCenteredSquareWithOffset(BufferedImage image, String text, Font font, int rectangleWidth, int rectangleHeight, int offsetX, int offsetY) {
        Graphics2D graphics2D = (Graphics2D) image.getGraphics();

        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        int rectanglePositionX = (imageWidth / 2) - (rectangleWidth / 2) + offsetX;
        int rectanglePositionY = (imageHeight / 2) - (rectangleHeight / 2) + offsetY;

        Rectangle rectangle = new Rectangle(rectanglePositionX, rectanglePositionY, rectangleWidth, rectangleHeight);
        BrokenText brokenText = TextBreakingUtils.breakText(text, font, graphics2D.getFontRenderContext(), rectangleWidth);

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

    public static BufferedImage drawTextInCenteredSquare(BufferedImage image, String text, Font font, int rectangleWidth, int rectangleHeight) {
        Graphics2D graphics2D = (Graphics2D) image.getGraphics();

        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        int rectanglePositionX = (imageWidth / 2) - (rectangleWidth / 2);
        int rectanglePositionY = (imageHeight / 2) - (rectangleHeight / 2);

        Rectangle rectangle = new Rectangle(rectanglePositionX, rectanglePositionY, rectangleWidth, rectangleHeight);
        BrokenText brokenText = TextBreakingUtils.breakText(text, font, graphics2D.getFontRenderContext(), rectangleWidth);

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

}
