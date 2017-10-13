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
	int state;

	Game() {
		addKeyListener(this);
		setFocusable(true);
		requestFocusInWindow();
		startGame();
	}

	public void startGame() {
		state = 1;
		System.out.println("Game Restarted");
		fences.clear();
		mhos.clear();
		generateElements();
		repaint();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(720, 830);
	}

	// Paints everything
	public void paint(Graphics g) {
		paintBackground(g);
		if (state == 1) {
			for (Fence fence : fences)
				fence.paintFence(g); // paints the fences
			player.paintPlayer(g); // paints the player
			for (Mho mho : mhos)
				mho.paintMho(g); // paints the mhos
		}
		else if (state == 2) {
			paintEndScreen(g, "Game Over! :(", 250);
		}
		else if (state == 3) {
			paintEndScreen(g, "Congrats! You Won!", 200);
		}
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
	 * generateElements() does 2 things: 1) generates outer perimeter fences 2)
	 * randomly generates internal fences, mho starting position, and player's
	 * starting position
	 */
	private void generateElements() {
		ArrayList<Integer> coords = Element.getGridCoords(); // get the grid coords
		// makes the arraylist that contains arraylists to represent each cell coord
		ArrayList<ArrayList<Integer>> shuffledCoords = new ArrayList<>();

		for (int i = 0; i <= 11; i++) { // creates the external fence objects
			fences.add(new Fence(0, coords.get(i)));
			fences.add(new Fence(660, coords.get(i)));
		}
		for (int i = 1; i <= 10; i++) {
			fences.add(new Fence(coords.get(i), 0));
			fences.add(new Fence(coords.get(i), 660));
		}

		coords.remove(0); // removes 60 and 660 so that only inside cells are considered
		coords.remove(10); // when randomly generating internal fences and mhos

		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j <= 9; j++) {
				ArrayList<Integer> x = new ArrayList<>();
				x.add(coords.get(i));
				x.add(coords.get(j));
				shuffledCoords.add(x);
			}
		}
		jumpArea = shuffledCoords;
		int cx, cy;
		// shuffles the collection with each cell coord in it
		Collections.shuffle(shuffledCoords);
		for (int i = 0; i <= 32; i++) {
			cx = shuffledCoords.get(i).get(0);
			cy = shuffledCoords.get(i).get(1);
			if (i <= 11) {
				// first 12 of the shuffled coords are mhos
				mhos.add(new Mho(cx, cy));
			} else if (i <= 31) {
				// next 20 of the shuffled coords are fences
				fences.add(new Fence(cx, cy));
				jumpArea.remove(i);
			} else {
				// 33rd coordinate is player
				player = new Player(cx, cy);
			}
		}
	}

	/*
	 * isEmpty checks if a given coordinate on the grid is empty (void of mhos or
	 * fences).
	 * 
	 * @param coordinates
	 * 
	 * @return whether there is a mho, fence, or neither at the location
	 */
	public static int isEmpty(int x, int y) {
		int empty = 0;
		for (Mho mho : mhos) {
			
			if ((mho.x + 12) / 60 * 60 == x && (mho.y + 12) / 60 * 60 == y) {
				empty = 2; // the code 2 means there is a mho at the location
			}
		}
		for (Fence fence : fences) {
			if ((fence.x + 12) / 60 * 60 == x && (fence.y + 12) / 60 * 60 == y) {
				empty = 1; // the code 1 means there is a fence at the location
			}
		}
		return empty;
	}

	/* moves the mhos */
	private void moveMhos() {
		for (int i = 0; i < mhos.size(); i++) {
			mhos.get(i).moveTowards(player.x, player.y);
		}
	}

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
	private void gameOver() {
		// if player's coords are not empty, and are occupied by another element
		if (isEmpty(player.x, player.y) > 0) {
			// if player hits another mhoe or fence, paint the end screen saying game over
			state = 2;
			t.stop();
		} else if (mhos.size() == 0) {
			// if there are no more mhos, paint congrats you won
			state = 3;
			t.stop(); // stop the timer
		}
	}


    /* method paints the end screen */
    private void paintEndScreen(Graphics g, String message, int offsetx) {
        try {
            Thread.sleep(400); // sleeps 40 milliseconds to show how the player has collided with a mho or fence
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, 720, 720);
        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        g.drawString(message, offsetx, 320);       // draws Game Over or Congrats you won with the offset that corresponds to that
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.drawString("Press R to Restart", 290, 360);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    /* checks if a key pressed is equal to a key */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();   // the code of the key that the user pressed is stored as in int variable
        if (key == KeyEvent.VK_R) { // if the key pressed was r
            startGame();    // resets the board and repaints everything
            repaint();
            return;
        } else if (key == KeyEvent.VK_J) {  // if key pressed was j, which is the jump method, the player is transported to a random location
            ArrayList<Integer> choice;
            choice = Game.jumpArea.get(rand.nextInt(Game.jumpArea.size())); // chooses a random coordinate pair from the possible coords to jump to
            player.x = choice.get(0);
            player.y = choice.get(1);
            repaint();
            return;
        }

        int[] possibleKeys = {
                KeyEvent.VK_Q, KeyEvent.VK_W, KeyEvent.VK_E,
                KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D,
                KeyEvent.VK_Z, KeyEvent.VK_X, KeyEvent.VK_C
        };   // list of possible keys stored in an array that the user can press to move
        for (int i = 0; i < possibleKeys.length; i++) {
            if (possibleKeys[i] == key) {   // if one of the keys pressed is a possible k
                this.key = key;     // assigns the global variable key to match the key pressed (global variable)
                if (state == 1) t.start();      // begins the timer
                break;
            }
        }

    }

	int counter = 0;   // counter to ensure that actionPerformed is not called an infinite amount of times when timer is begun

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
			gameOver(); // calls game over method (see java doc)
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
