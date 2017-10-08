import java.util.ArrayList;

/**
 * Created by engtechp7 on 9/28/2017.
 */
public class Element {
    ImagePath images = new ImagePath();

	public static ArrayList<Integer> getGridCoords(){
        ArrayList<Integer> grid = new ArrayList<>();
        int x = 0;     // counter
        for (int i = 0; i <= 11; i++) {
            x = i * 60;
            grid.add(x);
        }
        return grid;
    }
	
	int x, y;
	protected void move(int xrel, int yrel) {
		x += 60 * xrel;
		y += 60 * yrel;
	}
}
