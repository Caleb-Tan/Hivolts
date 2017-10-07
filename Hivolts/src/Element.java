import java.util.ArrayList;

/**
 * Created by engtechp7 on 9/28/2017.
 */
public class Element {
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
	
	int x, y;
	protected void move(String movement){
        switch (movement) {
            case "up":
                y-=60;
                break;
            case "down":
                y+=60;
                break;
            case "left":
                x-=60;
                break;
            case "right":
                x+=60;
                break;
            case "upLeft":
                x-=60;
                y-=60;
                break;
            case "upRight":
                x+=60;
                y-=60;
                break;
            case "downLeft":
                x-=60;
                y+=60;
                break;
            case "downRight":
                x+=60;
                y+=60;
                break;
        }
    }
}
