import javax.swing.*;

/**
 * Created by Caleb Tan on 9/26/2017.
 */
public class main {
    public static void main(String[] args) {
        Game game = new Game();                // creates grid object
        JFrame frame = new JFrame("Winter Hivolts");    // creates jframe
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);    // adds the flag panel to the jframe
        frame.pack(); // sizes frame so that all contents are at preferred sizes
        frame.setResizable(false);
        frame.setVisible(true);
    }


}