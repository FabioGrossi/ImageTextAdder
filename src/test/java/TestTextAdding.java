import com.fabiogrossi.imagetextadderapi.ImageTextAdder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestTextAdding {

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();
        BufferedImage bufferedImage = ImageIO.read(new File("black.png"));
        long loadedTime = System.currentTimeMillis();
        System.out.printf("Image Loaded (%dms)%n", loadedTime - startTime);
        Font font = new Font(Font.DIALOG_INPUT, Font.PLAIN, 15);
        String text = "Per le suddette proprietà, il buco nero non è osservabile direttamente. La sua presenza si rivela solo indirettamente mediante i suoi effetti sullo spazio circostante: le interazioni gravitazionali con altri corpi celesti e le loro emissioni (vedi lente gravitazionale), le irradiazioni principalmente elettromagnetiche della materia catturata dal suo campo di forza. Nel corso dei decenni successivi alla pubblicazione della Relatività Generale, base teorica della loro esistenza, vennero raccolte numerose osservazioni interpretabili, pur non sempre univocamente, come prove della presenza di buchi neri, specialmente in alcune galassie attive e sistemi stellari di binarie X.[5] L'esistenza di tali oggetti è oggi definitivamente dimostrata e via via ne vengono individuati di nuovi con massa molto variabile, da valori di circa 5 fino a miliardi di masse solari.";
        //String text = "Per le suddette proprietà, il buco nero non è osservabile direttamente";

        BufferedImage editedImage = ImageTextAdder.drawTextInCenteredSquare(bufferedImage, text, font, 400, 200);
        long processedTime = System.currentTimeMillis();
        System.out.printf("Image Processed (%dms)%n", processedTime - loadedTime);
        ImageIO.write(editedImage, "png", new File("out.png"));
        System.out.printf("End (Total %dms)%n", System.currentTimeMillis() - startTime);
    }

}
