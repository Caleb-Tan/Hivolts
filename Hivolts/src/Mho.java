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
		int rightOf, above;
		
		if (x < px) rightOf = 1;
		else if (x == px) rightOf = 0;
		else rightOf = -1;
		if (y < py) above = 1;
		else if (y == py) above = 0;
		else above = -1;
		
		int farx = (Math.abs((px - x)) > Math.abs((py - y))) ? 1 : 0;
		
	    	if ((rightOf == 0)||(above == 0)) {
	    		System.out.println("blindly moving: "+x/60+" "+y/60+" went "+rightOf+" "+above);
			move(rightOf, above);
		}
		else {
			if (Game.isEmpty(x-60*rightOf, y-60*above)) {
				System.out.println("it's empty: "+x/60+" "+y/60+" went "+rightOf+" "+above);
				move(rightOf, above);
			}
			else if (Game.isEmpty(x+60*rightOf*farx, y+60*above*(1-farx))) {
				System.out.println("it's empty: "+x/60+" "+y/60+" went "+rightOf+" "+above);
				move(rightOf*farx, above*(1-farx));
			}
			else if (Game.isEmpty(x+60*rightOf*(1-farx), y+60*above*farx)) {
				System.out.println("it's empty: "+x/60+" "+y/60+" went "+rightOf+" "+above);
				move(rightOf*(1-farx), above*farx);
			}
			else {
				System.out.println("I will die: "+x/60+" "+y/60+" went "+rightOf+" "+above);
				move(rightOf, above);
			}
		}
    }
}
