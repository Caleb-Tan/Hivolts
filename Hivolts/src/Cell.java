import java.util.ArrayList;

/**
 * Created by engtechp7 on 9/28/2017.
 */
public class Cell {
    ImagePath images = new ImagePath();

	public ArrayList<Integer> getGridCoords(){
        ArrayList<Integer> grid = new ArrayList<>();
        int x = 0;     // counter
        for (int i = 0; i <= 11; i++) {
            x = i * 60;
            grid.add(x);
        }
        return grid;
    }
}
