import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Mho extends Cell {
    private BufferedImage mho;
    Mho () {
        try {
            mho = ImageIO.read(new File("C:/Users/engtechp7/Desktop/Hivolts/Hivolts/res/mho.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    protected BufferedImage getMho() {
        return mho;
    }
}
