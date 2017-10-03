import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Mho extends Cell {
    private BufferedImage mho;
    int x, y;
    Mho () {
        try {
            mho = ImageIO.read(new File("C:/Users/engtechp7/Desktop/Hivolts/Hivolts/res/mho.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    /* coordinates for the mho object */
    Mho (Graphics g, int x, int y){

        paintMho(g, x, y);
    }

    public void paintMho(Graphics g, int x, int y) {
        g.drawImage(mho, x, y, null);
    }

}
