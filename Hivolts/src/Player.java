import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Element{
    private BufferedImage player;

    Player (int x, int y){
        try {
            player = ImageIO.read(new File("res/player.png"));
        } catch (IOException e){
            e.printStackTrace();
        }
        this.x = x;
        this.y = y;
    }

    protected void paintPlayer(Graphics g) {
        g.drawImage(player, x, y, null);
    }
}
