import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Mho extends Cell{
    private BufferedImage mho;
    int x, y;

    /* coordinates for the mho object and creating the image */
    Mho (int x, int y){
        try {
            mho = ImageIO.read(new File("res/mho.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        this.x = x;
        this.y = y;
    }

    public void paintMho(Graphics g) {
        g.drawImage(mho, x, y, null);
    }

}
