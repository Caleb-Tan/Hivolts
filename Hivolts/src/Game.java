import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Game extends JPanel {
    // declare all the objects
    Cell cell = new Cell();
    Fence fence = new Fence();
    Mho mho = new Mho();

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(720,720);
    }

    public void paint(Graphics g){
        super.paint(g);
        Color green = new Color(10,130,0);
        g.setColor(green);
        g.fillRect(0,0,720,720);
        cell.paintGrid(g);
        fence.paintFencePerimeter(g);
        generateMhos(g);
    }

    private void generateMhos(Graphics g){
        Random random = new Random();       // get random object
        Image[] mhoArray = new Image[12];   // array to store the mhos
        int[] gridCoords = cell.getGridCoords();  // pick random grid coordinates

        for (int i=0; i <= 11; i++){
            int x = random.nextInt(10)+1;
            int y = random.nextInt(10)+1;
            mhoArray[i] = mho.getMho();
            g.drawImage(mhoArray[i],gridCoords[x],gridCoords[y],null);
        }
    }
}
