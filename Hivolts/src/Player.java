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
    }

    protected void paintPlayer(Graphics g) {
        g.drawImage(player, x, y, null);
    }

    protected void up(){
        y+=60;
    }

    protected void down(){
        y-=60;
    }

    protected void left() {
        x-=60;
    }

    protected void right() {
        x+=60;
    }
}
