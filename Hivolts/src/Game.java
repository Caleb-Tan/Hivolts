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
	boolean invincible = false;
	Timer t = new Timer(1, this);
	private Player player; // declares player object to use later
	static ArrayList<Fence> fences = new ArrayList<>(); // contains all the fences
	static ArrayList<Mho> mhos = new ArrayList<>(); // contains all the mhos
	static ArrayList<ArrayList<Integer>> jumpArea; //valid jumping locations (non-fences)
	int key;    // set to the keycode to be used by actionListener method
	Random rand = new Random();  // for random functions
	int state;  // stores what state the program is in (1 = running, 2 = defeat, 3 = victory)
	int moves;  // keeps track of how many moves were made.

	Game() {
		addKeyListener(this);
		setFocusable(true);
		requestFocusInWindow();
		startGame();
	}

	public void startGame() {
		state = 1;
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
		} else if (state == 2) {
			paintEndScreen(g, "Game Over! :(", 250); // if player has collided
		} else if (state == 3) {
			// if there are no more mhos
			paintEndScreen(g, "Congrats! You won in " + Integer.toString(moves) + " moves", 100);
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
		g.drawString("Controls: (U/D/L/R=Up/Down/Left/Right)", 230, 750);
		g.drawString("Q: UL   W: U   E: UR   A: L", 280, 770);
		g.drawString("Z: DL   X: D   C: DR   D: R", 285, 790);
		g.drawString("S: Stay     J: Jump", 330, 810);
		g.drawString("Mhos Remaining: " + mhos.size(), 530, 780);
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
	 * gameOver() method prints a game over screen if player has hit a mho/fence OR
	 * if there are no mhos remaining
	 */
	private void gameOver() {
		// if player's coords are not empty, and are occupied by another element
		if (isEmpty(player.x, player.y) > 1) {
			// if player hits another mhoe or fence, paint the end screen saying game over
			state = 2;
			t.stop();
		} else if (mhos.size() == 0) { // indicates that there are no more mhos left
			state = 3;
			t.stop(); // stop the timer
		}
	}

	/* method paints the end screen */
	private void paintEndScreen(Graphics g, String message, int offsetx) {
		try {
			Thread.sleep(400); // sleeps 40 milliseconds to show how the player lost
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, 720, 720);
		g.setColor(Color.white);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
		g.drawString(message, offsetx, 320); // draws message with appropriate offset
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.drawString("Press R to Restart", 290, 360);
	}
	
	/*
	 * isEmpty checks if a given coordinate on the grid is empty and/or will be empty
	 * @param coordinates
	 * @return whether there is a mho, fence, or if it will be occupied
	 */
	public static int isEmpty(int x, int y) {
		int empty = 1;
		boolean mhop = false, fencep = false, targp = false;
		for (Mho mho : mhos) {
			if ((mho.x + 12) / 60 * 60 == x && (mho.y + 12) / 60 * 60 == y) mhop = true;
			if ((mho.tx + 12) / 60 * 60 == x && (mho.ty + 12) / 60 * 60 == y) targp = true;
		}
		for (Fence fence : fences) {
			if ((fence.x + 12) / 60 * 60 == x && (fence.y + 12) / 60 * 60 == y) fencep = true;
		}
		/*
		 * Codes:
		 * 0 = square will become occupied
		 * 1 = square is empty and will be empty
		 * 2 = there is a mho and it will be occupied
		 * 3 = there is a mho
		 * 4 = there is a fence
		 * Priority (highest to lowest): fence, mho, targeted
		 * Mhos: 1st choice: 1 or 3
		 * 		 2nd choice: 4
		 * 		 3rd choice: 0 or 2
		 * Player: Dies if 2, 3, or 4
		 */
		if (targp) empty = 0;
		if (mhop) empty += 2;
		if (fencep) empty = 4;
		return empty;
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
		int key = e.getKeyCode(); // key value that the user pressed is stored as an int
		if (key == KeyEvent.VK_R) { // if the key pressed was r
            moves = 0;
			startGame(); // resets the board and repaints everything
			repaint();
			return;
		} else if (key == KeyEvent.VK_J) { // if J was pressed, the mhos don't move
			ArrayList<Integer> choice;
			choice = Game.jumpArea.get(rand.nextInt(Game.jumpArea.size()));
			player.x = choice.get(0);
			player.y = choice.get(1);
			moves++; // adds 1 move to the move counter
			repaint();
			gameOver(); // calls game over method (see java doc)
			return;
		}
		// List of possible keys the user can press to move
		int[] possibleKeys = { KeyEvent.VK_Q, KeyEvent.VK_W, KeyEvent.VK_E, KeyEvent.VK_A, 
				KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_Z, KeyEvent.VK_X, KeyEvent.VK_C };
		for (int i = 0; i < possibleKeys.length; i++) {
			if (possibleKeys[i] == key) { // if one of the keys pressed is a possible k
				this.key = key; // assigns the global variable key to match the key pressed
				if (state == 1){
					t.start(); // begins the timer
                    moves++; // adds 1 move to the move counter
                }
				break;
			}
		}
	}
	
	int counter = 0; // limits number of times actionPerformed is called
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
			for (int i = 0; i < mhos.size(); i++) {
				mhos.get(i).moveTowards(player.x, player.y);
			}
			repaint();
		} else {
			t.stop(); // stops timer once done and resets counter to 0
			counter = 0; // resets counter
			roundCoords(); // rounds the coordinates to the correct places
			for (int i = mhos.size()-1; i >= 0; i--) {
				if (isEmpty(mhos.get(i).x, mhos.get(i).y) == 4) {
					mhos.remove(i); // loops through mhos to check if any mhos hit a fence
				}
			}
			repaint(); // repaints everything again
			if (!invincible) gameOver(); // calls game over method (see java doc)
		}
	}
	
	/*
	 * Fixes rounding errors caused by the smooth movement. Adds 12 to bring all
	 * errors to above the actual value (bc max is +6) and uses division and
	 * multiplication to round it down.
	 */
	private void roundCoords() {
		for (int i = 0; i < mhos.size(); i++) {
			mhos.get(i).x = (mhos.get(i).x + 12) / 60 * 60;
			mhos.get(i).y = (mhos.get(i).y + 12) / 60 * 60;
		}
		player.x = (player.x + 12) / 60 * 60;
		player.y = (player.y + 12) / 60 * 60;
	}
}
