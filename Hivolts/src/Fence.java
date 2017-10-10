import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by engtechp7 on 9/28/2017.
 */
public class Fence extends Element {
    BufferedImage fence;
    int x, y;

    /* assigns the x and y values to the fence when it is first created */
    Fence (int x, int y) {
        try {
            fence = ImageIO.read(new File(ImagePath.fencePath));
        } catch (IOException e){
            e.printStackTrace();
        }

        this.x = x;
        this.y = y;
    }


    public void paintFence(Graphics g) {
        g.drawImage(fence, x, y, null);
    }
}
