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
		fences.clear();
		mhos.clear();
		generateElements();
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(720,720);
	}
	//Paints everything
	public void paint(Graphics g) {
		super.paint(g);
		Color green = new Color(10,130,0);
		g.setColor(green);
		g.fillRect(0,0,720,720); // fills background rectangle to be green
		
		player.paintPlayer(g);                          // paints the player
		for (Fence fence : fences) fence.paintFence(g); // paints the fences
		for (Mho mho : mhos) mho.paintMho(g);           // paints the mhos in the array
		if (!isEmpty(player.x, player.y)) System.out.print(".");
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
		
		for (int i=0; i<=11; i++) {                        // creates the external fence objects
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

        Collections.shuffle(shuffledCoords); // shuffles the collection with each cell coord in it
        Set<ArrayList<Integer>> test = new HashSet<ArrayList<Integer>>(shuffledCoords);
        
        System.out.println(shuffledCoords.size() == test.size());
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
	
	public static boolean isEmpty(int x, int y) {
		boolean empty = true;
		for (Fence fence : fences) {
			if (fence.x == x && fence.y == y){
				empty = false;
			}
		}
		for (Mho mho : mhos) {
			if (mho.x == x && mho.y == y){
                empty = false;
            }
        }
		return empty;
    }
	private void moveMhos() {
		for (int i=0; i<mhos.size(); i++) {
			mhos.get(i).moveTowards(player.x, player.y);
		}
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {
    		int key = e.getKeyCode();
    		if (key == KeyEvent.VK_R) startGame();
        player.move(key);
        moveMhos();
        repaint();
    }

}
