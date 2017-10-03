import java.awt.*;
import java.util.Map;

/**
 * Created by engtechp7 on 9/28/2017.
 */
public class Cell {

    public int[][] getGridCoords(){
        int[][] grid = new int[12][12];
        int x = 0;     // counter
        for (int i=0; i<=11; i++) {
<<<<<<< HEAD
            for (int j = 0; j <= 11; j++) {
                x = j * 70;
                grid[i][j] = x;
            }
=======
            x = i*60;
            grid[i] = x;
>>>>>>> ca7760c9baa57ba04b82cbe4f671190ff78ac180
        }
        return grid;
    }
    public void paintGrid(Graphics g){
        g.setColor(Color.black);
        for (int i=0; i<=11; i++){
<<<<<<< HEAD
            g.drawLine(0, getGridCoords()[0][i], 840, getGridCoords()[0][i]);
            g.drawLine(getGridCoords()[0][i], 0, getGridCoords()[0][i], 840);
=======
            g.drawLine(0, getGridCoords()[i], 720, getGridCoords()[i]);
            g.drawLine(getGridCoords()[i], 0, getGridCoords()[i], 720);
>>>>>>> ca7760c9baa57ba04b82cbe4f671190ff78ac180
        }
    }
}
