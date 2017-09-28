import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Grid extends JPanel {
    BufferedImage fence;

    Grid () {
        try {
            fence = ImageIO.read(new File("./res/fence.jpg"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000,1000);
    }

    public void paint(Graphics g){

        super.paint(g);

        Color green = new Color(10,130,0);
        g.setColor(green);
        g.fillRect(0,0,960,960);

        paintFence(g);
    }

    public void paintFence(Graphics g) {
        g.drawImage(fence,0,0,null);
    }


}
