import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Mho {
    private BufferedImage mho;
    int x, y;

    /* coordinates for the mho object and creating the image */
    Mho (Graphics g, int x, int y){
        try {
            mho = ImageIO.read(new File("C:/Users/engtechp7/Desktop/Hivolts/Hivolts/src/res/mho.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        this.x = x;
        this.y = y;

        paintMho(g);
    }

    private void paintMho(Graphics g) {
        g.drawImage(mho, x, y, null);
    }

}
