import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Mho extends Element {
	private BufferedImage mho;
	/* coordinates for the mho object and creating the image */
	public Mho (int x, int y) {
		try {
            mho = ImageIO.read(new File("res/mho.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        this.x = x;
        this.y = y;
	}
	
	public void paintMho(Graphics g) {
		g.drawImage(mho, x, y, null);
    }
	
    public void moveTowards(int px, int py) {
    	if (x == px) {
			if (y > py) move("up");
			else if (y < py) move("down");
		}
		else if (y == py) {
			if (x > px) move("left");
			else if (x < px) move("right");
		}
    }
}
