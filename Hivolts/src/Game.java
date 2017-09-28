import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Game extends JPanel {
    BufferedImage fence;
    
    Game () {
        try {
            fence = ImageIO.read(new File("./res/fence.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(785,785);
    }

    public void paint(Graphics g){

        super.paint(g);

        Color green = new Color(10,130,0);
        g.setColor(green);
        g.fillRect(0,0,785,785);

        paintFence(g);
    }

    private void paintFence(Graphics g) {
    		for (int x=5; x<780; x+=65) {
    			g.drawImage(fence, 0, x,null);
    		}
    }


}
