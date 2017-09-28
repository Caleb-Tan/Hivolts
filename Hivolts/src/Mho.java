import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Mho extends JPanel {
	int xcor, ycor;
	Image fence;
    Mho(int x, int y) {
        ImageIcon fenceIcon = new ImageIcon("../res/fence.jpg");
        fence = fenceIcon.getImage();
        xcor = x;
        ycor = y;
    }
    public void paint(Graphics g) {
        g.drawImage(fence, 0, 0, this);
    }
}
