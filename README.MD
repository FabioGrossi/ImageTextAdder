# Image Text Adder API

A simple API to add text into them


# Note

This API is WIP

# How to use

Using this API is simple as it seems

>BufferedImage bufferedImage = ImageIO.read(new File("black.png"));  
>Font font = new Font(Font.DIALOG_INPUT, Font.PLAIN, 15);  
>String text = "Per le suddette proprietà, il buco nero non è osservabile direttamente";  
>BufferedImage editedImage = ImageTextAdder.drawTextInCenteredSquare(bufferedImage, text, font, 400, 200);  
>ImageIO.write(editedImage, "png", new File("out.png"));  