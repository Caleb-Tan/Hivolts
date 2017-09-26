import java.awt.*;
import javax.swing.JPanel;

public class Grid extends JPanel {
    Grid(){
        // sets preferred size of window
        setPreferredSize(new Dimension(1000,1000));
    };

    public void paint(Graphics g){
        Color grey = new Color(211,211,211);
        g.fillRect(0,0,960,960);
    }
}
