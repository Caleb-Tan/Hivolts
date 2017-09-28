import java.awt.*;
import java.util.Arrays;

/**
 * Created by engtechp7 on 9/28/2017.
 */
public class Cell {

    public int[] getGridCoords(){
        int[] grid = new int[12];   // contains coord points for each cell
        int x = 0;     // counter
        for (int i=0; i<=11; i++) {
            x = i*70;
            grid[i] = x;
        }
        return grid;
    }
    public void paintGrid(Graphics g){
        g.setColor(Color.black);
        for (int i=0; i<=11; i++){
            g.drawLine(0, getGridCoords()[i], 840, getGridCoords()[i]);
            g.drawLine(getGridCoords()[i], 0, getGridCoords()[i], 840);
        }
    }
}
