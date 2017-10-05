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

    /* paints the fence perimeter. this constructor is for the object that
    only paints the perimeter*/
    Fence () {
        try {
            fence = ImageIO.read(new File("C:/Users/engtechp7/Desktop/Hivolts/Hivolts/src/res/fence.png"));
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    /* assigns the x and y values to the fence and runs the paintFence method to paint
    * the randomly generated fence. this constructor is for the internal fences,
    * because it calls the paint fence method which paints an individual fence*/
    Fence (Graphics g, int x, int y) {
        try {
            fence = ImageIO.read(new File("C:/Users/engtechp7/Desktop/Hivolts/Hivolts/src/res/fence.png"));
        } catch (IOException e){
            e.printStackTrace();
        }

        this.x = x;
        this.y = y;

        paintFence(g);
    }


    private void paintFence(Graphics g) {
        g.drawImage(fence, x, y, null);
    }

    public void paintFencePerimeter(Graphics g) {

        // first for loop creates vertical fences
        for (int i=0; i<=11; i++) {
            g.drawImage(fence, 0, getGridCoords().get(i), null);    // paints fences on left
            g.drawImage(fence, 660,getGridCoords().get(i), null);   // paints fences on right
        }
        // second for loop creates horizontal fences
        for (int i=1; i<=10; i++) {
            g.drawImage(fence, getGridCoords().get(i), 0, null);  // paints top
            g.drawImage(fence, getGridCoords().get(i), 660, null);  // paints bottom

        }
    }
}
