import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener {
    private Player player;                                  // declares player object to use later
    static ArrayList<Fence> fences = new ArrayList<>();    // contains all the fences
    static ArrayList<Mho> mhos = new ArrayList<>();        // contains all the mhos

    Game() {
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        startGame();
    }

    public void startGame() {
        System.out.println("Game Restarted");
        fences.clear();
        mhos.clear();
        generateElements();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1080, 720);
    }

    //Paints everything
    public void paint(Graphics g) {
        super.paint(g);
        paintBackground(g);


        for (Fence fence : fences) fence.paintFence(g); // paints the fences
        for (Mho mho : mhos) mho.paintMho(g);           // paints the mhos
        player.paintPlayer(g);                          // paints the player
        gameOver(g);       // calls game over method (see java doc)
    }

    /* paints background and scoreboard */
    private void paintBackground (Graphics g) {
        Color SNOWWHITE = new Color(255, 250, 250);     // snow white color

        g.setColor(SNOWWHITE);
        g.fillRect(0, 0, 720, 720); // fills background rectangle to be snow white colored
        g.setColor(Color.darkGray);
        g.fillRect(720, 0, 360, 720);       // paints dark grey scoreboard on right

        g.setColor(SNOWWHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));  // paints titles on right scoreboard
        g.drawString("Winter Hivolts", 770, 320);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
        g.drawString("By Caleb, Brion and Julius", 745,355);
    }

    /* generateElements() does 2 things:
    *   1) generates outer perimeter fences
    *   2) randomly generates internal fences, 
    *   mho starting position, and player's starting position
    */
    private void generateElements() {
        ArrayList<Integer> coords = Element.getGridCoords();     // get the grid coords
        // makes the arraylist that contains arraylists to represent each cell coord
        ArrayList<ArrayList<Integer>> shuffledCoords = new ArrayList<>();

        for (int i = 0; i <= 11; i++) {                        // creates the external fence objects
            fences.add(new Fence(0, coords.get(i)));
            fences.add(new Fence(660, coords.get(i)));
        }
        for (int i = 1; i <= 10; i++) {
            fences.add(new Fence(coords.get(i), 0));
            fences.add(new Fence(coords.get(i), 660));
        }

        coords.remove(0);    // removes 60 and 660 so that only inside cells are considered
        coords.remove(10); // when randomly generating internal fences and mhos

        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                ArrayList<Integer> x = new ArrayList<>();
                x.add(coords.get(i));
                x.add(coords.get(j));
                shuffledCoords.add(x);
            }
        }
        int cx, cy;
        // shuffles the collection with each cell coord in it
        Collections.shuffle(shuffledCoords);
        for (int i = 0; i <= 32; i++) {
            cx = shuffledCoords.get(i).get(0);
            cy = shuffledCoords.get(i).get(1);
            System.out.print(cx / 60 + " " + cy / 60 + "\t");
            if (i <= 11) {
                // first 12 of the shuffled coords are mhos
                mhos.add(new Mho(cx, cy));
            } else if (i <= 31) {
                // next 20 of the shuffled coords are fences
                fences.add(new Fence(cx, cy));
            } else {
                // 33rd coordinate is player
                player = new Player(cx, cy);
            }
            if (i % 8 == 0) System.out.println();
        }
    }

    public static boolean isEmpty(int x, int y) {
        boolean empty = true;
        for (Fence fence : fences) {
            if (fence.x == x && fence.y == y) {
                empty = false;
            }
        }
        for (Mho mho : mhos) {
            if (mho.x == x && mho.y == y) {
                empty = false;
            }
        }
        return empty;
    }


    private void moveMhos() {
        for (int i = 0; i < mhos.size(); i++) {
            mhos.get(i).moveTowards(player.x, player.y);
        }
    }

    /* gameOver() method prints a game over screen if player has
    * hit a mho/fence OR if there are no mhos remaining */
    private void gameOver(Graphics g) {

        // if player's coords are not empty, and are occupied by another element
        if (!isEmpty(player.x, player.y)) {
            try {
                Thread.sleep(500);           // sleeps 50 milliseconds
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            g.setColor(Color.darkGray);
            g.fillRect(0, 0, 720, 720);
            g.setColor(Color.white);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
            g.drawString("Game Over", 270, 320);

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        if (key == KeyEvent.VK_R) {
            startGame();
            repaint();
            return;
        }
        player.move(key);      // calls move method inside of player for the key presses
        moveMhos();            // moves the mhos
        repaint();
    }

}
