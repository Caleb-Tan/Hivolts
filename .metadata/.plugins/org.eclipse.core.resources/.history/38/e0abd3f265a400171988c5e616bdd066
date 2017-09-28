
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Caleb Tan on 9/26/2017.
 */

public class Fence extends JPanel {

    BufferedImage fence;

    Fence() {

        try {
            fence = ImageIO.read(new File("C:/Users/engtechp7/Desktop/fence.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        ImageIcon fenceIcon = new ImageIcon("../resources/fence.jpg");
//        fence = fenceIcon.getImage();
    }

    @Override
    public void paint(Graphics g){

        super.paint(g);

        g.drawImage(fence, 0,0, null);
    }
}
