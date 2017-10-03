import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Game extends JPanel {
    // declare all the objects
    Cell cell = new Cell();
    Fence fence = new Fence();
    ArrayList<Fence> fences = new ArrayList<Fence>();
    ArrayList<Mho> mhos = new ArrayList<Mho>();

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
        generateMhos(g, 11);
    }


    private void generateMhos(Graphics g, int max) {
        Random rand = new Random();
        int[][] coords = cell.getGridCoords();


        for (int i = 0; i <= 11; i++) {
            int x = rand.nextInt((max) + 1);
            int y = rand.nextInt((max) + 1);
            mhos.add(new Mho(g, x * 70, coords[x][y]));
        }
    }
}
