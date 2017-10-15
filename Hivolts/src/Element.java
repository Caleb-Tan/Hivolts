import java.util.ArrayList;

/**
 * Created by engtechp7 on 9/28/2017.
 */
public class Element {
    final static String mhoPath = "res/mho.png";    // paths to the images to be used by fence, mho and player.java
    final static String fencePath = "res/fence.png";
    final static String playerPath = "res/player.png";

    /* returns the coordinates from 60-720 to be used by generateElements() when generating elements */
	public static ArrayList<Integer> getGridCoords(){
        ArrayList<Integer> grid = new ArrayList<>();
        int x = 0;     // counter
        for (int i = 0; i <= 11; i++) {
            x = i * 60;
            grid.add(x);
        }
        return grid;
    }
	
	int x, y;   // x and y variables to store the current coords of game, fence, and player
	int tx, ty; //sets target location before initiating smooth movement
    /* changes the x and y based upon the new directions given to it by mho, and player */
	protected void move(int xrel, int yrel) {
            x += (6 * xrel);
            y += (6 * yrel);
	}
}