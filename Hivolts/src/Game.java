import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.*;

public class Game extends JPanel implements KeyListener, ActionListener {
    Timer t = new Timer(1, this);
    private Player player; // declares player object to use later
    static ArrayList<Fence> fences = new ArrayList<>(); // contains all the fences
    static ArrayList<Mho> mhos = new ArrayList<>(); // contains all the mhos
    static ArrayList<ArrayList<Integer>> jumpArea;
    int key;
    Random rand = new Random();

    Game() {
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        startGame();
    }

    public void startGame() {
        fences.clear();
        mhos.clear();
        generateElements();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(720, 830); // sets dimension
    }

    // Paints everything
    public void paint(Graphics g) {
        paintBackground(g);

        for (Fence fence : fences) fence.paintFence(g); // paints the fences
        player.paintPlayer(g); // paints the player
        for (Mho mho : mhos) mho.paintMho(g); // paints the mhos

        gameOver(g); // calls game over method (see java doc)
    }

    /* paints background and scoreboard */
    private void paintBackground(Graphics g) {
        Color SNOWWHITE = new Color(255, 250, 250); // snow white color

        g.setColor(SNOWWHITE);
        g.fillRect(0, 0, 720, 720); // fills background rectangle to be snow white colored
        g.setColor(Color.darkGray);
        g.fillRect(0, 720, 720, 110); // paints dark grey scoreboard on right

        g.setColor(SNOWWHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 27)); // paints titles on right scoreboard
        g.drawString("Winter Hivolts", 50, 770);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        g.drawString("By Caleb, Brion, and Julius", 30, 800);
        g.drawString("Mhos Remaining: " + mhos.size(), 500, 780);
    }

    /*
     * generateElements() does 2 things: 1) generates outer perimeter fences
     * 2) randomly generates internal fences, mho starting position, and player's
     * starting position
     */
    private void generateElements() {
        ArrayList<Integer> coords = Element.getGridCoords(); // get the grid coords
        ArrayList<ArrayList<Integer>> shuffledCoords = new ArrayList<>(); // makes the arraylist that contains arraylists to represent each cell coord (will be shuffled later)

        for (int i = 0; i <= 11; i++) { // creates the external fence objects and adds them to the fences array
            fences.add(new Fence(0, coords.get(i)));
            fences.add(new Fence(660, coords.get(i)));
        }
        for (int i = 1; i <= 10; i++) {
            fences.add(new Fence(coords.get(i), 0));
            fences.add(new Fence(coords.get(i), 660));
        }

        coords.remove(0); // removes 60 and 660 so that only inside cells are considered
        coords.remove(10); // when randomly generating internal fences and mhos

        // loops through the internal cells to add their coords to the shuffledCoords
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                ArrayList<Integer> x = new ArrayList<>();   // creates a temporary arraylist which will store the individual cell's coords
                x.add(coords.get(i));   // adds the individual coords to the temporary integer arraylist
                x.add(coords.get(j));
                shuffledCoords.add(x);  // adds the integer arraylist to the shuffledCoords array
            }
        }
        jumpArea = shuffledCoords; // sets jumpArea to equal shuffled coords. jumpArea will be used later
        // shuffles the collection with each cell coord in it

        Collections.shuffle(shuffledCoords); // shuffles the cell coordinate arraylist

        for (int i = 0; i <= 32; i++) {
            int cx, cy;
            cx = shuffledCoords.get(i).get(0);
            cy = shuffledCoords.get(i).get(1);
            if (i <= 11) {
                // first 12 of the shuffled coords are for mhos
                mhos.add(new Mho(cx, cy));
            } else if (i <= 31) {
                // next 20 of the shuffled coords are for fences
                fences.add(new Fence(cx, cy));
                jumpArea.remove(i);     // removes that cell instance from jumpArea, so that player does not jump on fence
            } else {
                // 33rd set of coordinates are given to player
                player = new Player(cx, cy);
            }
        }
    }

    /* isEmpty() method is used to check if a set of coords are the
    * same as the mhos or fences. If they are, then it will return
    * a digit representing that */
    private static int isEmpty(int x, int y) {
        int empty = 0;
        for (Mho mho : mhos) {  // checks if any mhos are the same as the given coord
            if (mho.x == x && mho.y == y) {
                empty = 2;
            }
        }
        for (Fence fence : fences) {    // checks if any fences are the same as the given coord
            if (fence.x == x && fence.y == y) {
                empty = 1;
            }
        }
        return empty;
    }

    /* Calls the moveTowards player method in mho class to move each mho towards player if necessary */
    private void moveMhos() {
        for (int i = 0; i < mhos.size(); i++) {
            mhos.get(i).moveTowards(player.x, player.y);
        }
    }

    /* */
    private void resetCoord() {
        for (int i = 0; i < mhos.size(); i++) {
            mhos.get(i).x = (mhos.get(i).x + 12) / 60 * 60;
            mhos.get(i).y = (mhos.get(i).y + 12) / 60 * 60;
        }
        player.x = (player.x + 12) / 60 * 60;
        player.y = (player.y + 12) / 60 * 60;
    }

    /*
     * gameOver() method prints a game over screen if player has hit a mho/fence OR
     * if there are no mhos remaining
     */
    private void gameOver(Graphics g) {
        // if player's coords are not empty, and are occupied by another element
        if (isEmpty(player.x, player.y) > 0) {
            // if player hits another mho or fence, paint the end screen saying game over
            paintEndScreen(g, "Game Over! :(", 250);
        } else if (mhos.size() == 0) {
            // if there are no more mhos, paint congrats you won
            paintEndScreen(g, "Congrats! You Won!", 200);
            t.stop(); // stop the timer
        }
    }

    /* method to paint the end screen */
    private void paintEndScreen(Graphics g, String message, int offsetx) {
        try {
            Thread.sleep(400); // sleeps 40 milliseconds
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, 720, 720);
        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        // draws Game Over or Congrats you won with the offset that corresponds to that
        g.drawString(message, offsetx, 320);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawString("Press R to Restart", 290, 360);
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
        } else if (key == KeyEvent.VK_J) {
            ArrayList<Integer> choice;
            choice = Game.jumpArea.get(rand.nextInt(Game.jumpArea.size()));
            player.x = choice.get(0);
            player.y = choice.get(1);
            repaint(); // must remove/edit to prevent J from restarting the game
            return;
        }

        int[] keys = {
                KeyEvent.VK_Q, KeyEvent.VK_W, KeyEvent.VK_E,
                KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D,
                KeyEvent.VK_Z, KeyEvent.VK_X, KeyEvent.VK_C};
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] == key) {
                this.key = key;
                t.start();
                break;
            }
        }

    }

    int counter = 0;

    /*
     * implemented method is called when the timer starts. every 1 ms, the method
     * will be called until timer stops. move() method in Element.java increments
     * the x by 6 each time, so method must be called 10 times for each component to
     * reach 60
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // increments counter
        counter++;
        // first 10 times moves player
        if (counter <= 10) {
            player.movePlayer(key); // calls move method inside of player
            repaint();
        } else if (counter <= 20) { // next 10 times moves mhos
            System.out.print("\n");
            moveMhos();
            repaint();
        } else {
            t.stop(); // stops timer once done and resets counter to 0
            counter = 0;
            resetCoord();
            for (int i = 0; i < mhos.size(); i++) {
                if (isEmpty(mhos.get(i).x, mhos.get(i).y) == 1) {
                    mhos.remove(i);
                }
            }
            repaint();

            // for debugging purposes
            System.out.println("Player coords: " + player.x + " " + player.y);
            for (Mho mho : mhos) {
                System.out.println("MHO: " + mho.x + " " + mho.y);
            }
            for (int x = 43; x < 64; x++) {
                System.out.println("FENCE " + fences.get(x).x + " " + fences.get(x).y);
            }
            System.out.println("--------");
        }
    }
}
