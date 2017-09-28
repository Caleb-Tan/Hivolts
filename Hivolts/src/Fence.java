
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Caleb Tan on 9/26/2017.
 */

public class Fence extends JPanel{
    //BufferedImage fence = ImageIO.read(new File("../resources/fence.jpg"));
    Image fence;
    Fence() {
        ImageIcon fenceIcon = new ImageIcon("../resources/fence.jpg");
        fence = fenceIcon.getImage();
    }
    public void paint(Graphics g){

        g.drawImage(fence, 0,0,this);
    }
}
