import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Player extends Element {
	private BufferedImage player;
	Player(int x, int y) {
		try {
			player = ImageIO.read(new File(ImagePath.playerPaths));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.x = x;
		this.y = y;
	}

	protected void paintPlayer(Graphics g) {
		g.drawImage(player, x, y, null);
	}

	protected void movePlayer(int key) {
		switch (key) {
			case KeyEvent.VK_W: {
				move(0, -1);
				break;
			}
			case KeyEvent.VK_X: {
				move(0, 1);
				break;
			}
			case KeyEvent.VK_A: {
				move(-1, 0);
				break;
			}
			case KeyEvent.VK_D: {
				move(1, 0);
				break;
			}
			case KeyEvent.VK_Q: {
				move(-1, -1);
				break;
			}
			case KeyEvent.VK_E: {
				move(1, -1);
				break;
			}
			case KeyEvent.VK_Z: {
				move(-1, 1);
				break;
			}
			case KeyEvent.VK_C: {
				move(1, 1);
				break;
			}
			case KeyEvent.VK_S: {
				move(0, 0);
				break;
			}
			default: {
				return;
			}
		}
	}
}
