import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Caleb Tan on 9/26/2017.
 */

public class Fence extends JPanel{
    BufferedImage fence = null;
    File f = null;

    public void paint(Graphics g){
        f = new File("./fence.jpg");
        try {
            fence = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println("Can't find image.");
        }
        g.drawImage(fence, 0, 0, null);
    }
}
