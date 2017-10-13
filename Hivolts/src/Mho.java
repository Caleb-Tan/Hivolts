import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Mho extends Element {
	private BufferedImage mho;
	boolean moving;
	int dx, dy;
	int frame;

	/* coordinates for the mho object and creating the image */
	public Mho(int x, int y) {
		try {
			mho = ImageIO.read(new File(mhoPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.x = x;
		this.y = y;
	}

	public void paintMho(Graphics g) {
		g.drawImage(mho, x, y, null);
	}

	/* method causes mhos to move towards player based on player's position t*/
	public void moveTowards(int px, int py) {
		if (moving == false) {
			int rightOf, above, farx;
			if (x < px) rightOf = 1;
			else if (x == px) rightOf = 0;
			else rightOf = -1;
			if (y < py) above = 1;
			else if (y == py) above = 0;
			else above = -1;
			if (Math.abs((px - x)) > Math.abs((py - y))) farx = 1;
			else farx = 0;
			
			frame = 0;
			if ((rightOf == 0) || (above == 0)) {
				setD(rightOf, above);
			} else {
				if (Game.isEmpty(x - 60 * rightOf, y - 60 * above) == 0) {
					setD(rightOf, above);
				} else if (Game.isEmpty(x + 60 * rightOf * farx, y + 60 * above * (1 - farx)) == 0) {
					setD(rightOf * farx, above * (1 - farx));
				} else if (Game.isEmpty(x + 60 * rightOf * (1 - farx), y + 60 * above * farx) == 0) {
					setD(rightOf * (1 - farx), above * farx);
				}
				else if (Game.isEmpty(x - 60 * rightOf, y - 60 * above) == 1) {
					setD(rightOf, above);
				} else if (Game.isEmpty(x + 60 * rightOf * farx, y + 60 * above * (1 - farx)) == 1) {
					setD(rightOf * farx, above * (1 - farx));
				} else if (Game.isEmpty(x + 60 * rightOf * (1 - farx), y + 60 * above * farx) == 1) {
					setD(rightOf * (1 - farx), above * farx);
				}
				else System.out.print(Game.isEmpty(x - 60 * rightOf, y - 60 * above) + " ");
			}
			moving = true;
		}
		else move(dx, dy);
		frame++;
		if (frame >= 10)
			moving = false;
	}

	private void setD(int xx, int yx) {
		dx = xx;
		dy = yx;
	}
}
