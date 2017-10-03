import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by engtechp7 on 9/28/2017.
 */
public class Fence extends Cell {
    BufferedImage fence;
    int x, y;
    Fence () {
        try {
            fence = ImageIO.read(new File("./res/fence.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    Fence (int x, int y) {

    }

    protected void paintFencePerimeter(Graphics g) {
        // first for loop creates vertical fences
        for (int i=0; i<=11; i++) {
            g.drawImage(fence, 5, getGridCoords()[0][i], null);    // paints ones on left
            g.drawImage(fence,775, getGridCoords()[0][i], null);   // paints ones on right
        }
        // second for loop creates horizontal fences
        for (int i=1; i<=10; i++) {
            g.drawImage(fence, getGridCoords()[0][i]+5, 0, null);  // paints top
            g.drawImage(fence, getGridCoords()[0][i]+5, 775, null);  // paints bottom

        }
    }
}
