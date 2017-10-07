import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener {
	Game(){
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        generateElements();   // calls method to generate elements
    }


    private Cell cell = new Cell();                             // grid coordinates
    private Player player;                                  // declares player object to use later
    private ArrayList<Fence> fences = new ArrayList<>();   // contains all the fences
    private ArrayList<Mho> mhos = new ArrayList<>();         // contains all the mhos

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(720,720);
    }

    /* paints every single element */
    public void paint(Graphics g){
        super.paint(g);
        Color green = new Color(10,130,0);
        g.setColor(green);
        g.fillRect(0,0,720,720); // fills background rectangle to be green

        player.paintPlayer(g);                                  // paints the player
        for (Fence fence : fences) fence.paintFence(g); // paints the fences
        for (Mho mho : mhos) mho.paintMho(g);           // paints the mhos in the array

        checkCollision();
    }


    /* generateElements() does 2 things:
    *   1) generates outer perimeter fences
    *   2) randomly generates internal fences, mho starting position, and player's starting position
    */
    private void generateElements() {
        ArrayList<Integer> coords = cell.getGridCoords();                   // get the grid coords
        ArrayList<ArrayList<Integer>> shuffledCoords = new ArrayList<>();   // makes the arraylist that contains arraylists to represent each cell coord

        for (int i=0; i<=11; i++) {                                         // creates the external fence objects
            fences.add(new Fence(0, coords.get(i)));
            fences.add(new Fence(660, coords.get(i)));
        }
        for (int i=1; i<=10; i++){
            fences.add(new Fence(coords.get(i), 0));
            fences.add(new Fence(coords.get(i), 660));
        }

        coords.remove(0);	// removes 70 and 770 so that only inside cells are considered 
        						// when randomly generating internal fences and mhos
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
            	// first 12 of the shuffled coords are mhos
                mhos.add(new Mho(shuffledCoords.get(i).get(0), shuffledCoords.get(i).get(1)));
            } else if (i <= 31){
            	// next 20 of the shuffled coords are fences
                fences.add(new Fence(shuffledCoords.get(i).get(0), shuffledCoords.get(i).get(1)));
            } else {
            	// 33rd coordinate is player
                player = new Player(shuffledCoords.get(32).get(0), shuffledCoords.get(32).get(1));
            }
        }

    }

    private void checkCollision(){
        for (Fence fence : fences){
           if (fence.x == player.x && fence.y == player.y){
                System.out.println("game over");
           }
        }
        for (Mho mho : mhos){
            if (mho.x == player.x && mho.y == player.y){
                System.out.println("game over");
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}


    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key){
            case KeyEvent.VK_W: {
                player.move("up");
                break;
            }
            case KeyEvent.VK_S: {
                player.move("down");
                break;
            }
            case KeyEvent.VK_A: {
                player.move("left");
                break;
            }
            case KeyEvent.VK_D: {
                player.move("right");
                break;
            }
            case KeyEvent.VK_Q: {
                player.move("upLeft");
                break;
            }
            case KeyEvent.VK_E: {
                player.move("upRight");
                break;
            }
            case KeyEvent.VK_Z: {
                player.move("downLeft");
                break;
            }
            case KeyEvent.VK_C: {
                player.move("downRight");
                break;
            }
        }
        repaint();
    }

}
