import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener {

    private Cell cell = new Cell();                             // grid coordinates
    private Fence fencePerimeter = new Fence();                 // object used solely to paint the perimeter of the fence
    private Player player;                                  // declares player object to use later
    private ArrayList<Fence> fences = new ArrayList<Fence>();   // contains all the fences
    private ArrayList<Mho> mhos = new ArrayList<Mho>();         // contains all the mhos

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(720,720);
    }


    public void paint(Graphics g){
        super.paint(g);
        Color green = new Color(10,130,0);
        g.setColor(green);
        g.fillRect(0,0,720,720);       // fills background rectangle to be green
        fencePerimeter.paintFencePerimeter(g);             // paints fence perimeter (see method in Fence.java)
        generateElements(g);                               // calls method to generate elements
    }

    /* method that randomly generates the initial positions, */
    private void generateElements(Graphics g) {
        ArrayList<Integer> coords = cell.getGridCoords();                   // get the grid coords
        ArrayList<ArrayList<Integer>> shuffledCoords = new ArrayList<>();   // makes the arraylist that contains arraylists to represent each cell coord
        coords.remove(0);       // removes 70 and 770 so that only inside cells are considered
        coords.remove(10);

        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                ArrayList<Integer> x = new ArrayList<>();
                x.add(coords.get(i));
                x.add(coords.get(j));
                shuffledCoords.add(x);
            }
        }

        Collections.shuffle(shuffledCoords);     // shuffles the collection with each cell coord in it
        for (int i = 0; i <= 32; i++) {
            if (i <= 11) {
                mhos.add(new Mho(g, shuffledCoords.get(i).get(0), shuffledCoords.get(i).get(1)));   // first 12 of the shuffled coords are mhos
            } else if (i <= 31){
                fences.add(new Fence(g, shuffledCoords.get(i).get(0), shuffledCoords.get(i).get(1)));   // next 20 of the shuffled coords are fences
            } else {
                player = new Player(g, shuffledCoords.get(32).get(0), shuffledCoords.get(32).get(1));           // 33rd coordinate is player
            }
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W){
        } else if (e.getKeyCode() == KeyEvent.VK_S) {

        } else if (e.getKeyCode() == KeyEvent.VK_A) {

        } else if (e.getKeyCode() == KeyEvent.VK_D) {

        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    void up(Graphics g){
        player.up(g);
    }
}
