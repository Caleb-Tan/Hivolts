import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player {
    private BufferedImage player;
    int x, y;

    Player (Graphics g, int x, int y){
        try {
            player = ImageIO.read(new File("./res/player.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        this.x = x;
        this.y = y;

        paintPlayer(g);
    }

    private void paintPlayer(Graphics g) {
        g.drawImage(player, x, y, null);
    }

    public void up(Graphics g){
        y+=70;
        paintPlayer(g);
    }
}