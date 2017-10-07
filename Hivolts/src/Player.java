import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Cell{
    private BufferedImage player;
    int x, y;

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
