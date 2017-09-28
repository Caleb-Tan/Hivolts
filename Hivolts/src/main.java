import javax.swing.*;

/**
 * Created by Caleb Tan on 9/26/2017.
 */
public class Main {
    public static void main(String[] args){
        Game game = new Game();				// creates grid object
        JFrame frame = new JFrame("Hivolts");	// creates jframe
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);	// adds the flag panel to the jframe
        frame.pack(); // sizes frame so that all contents are at preferred sizes
        frame.setVisible(true);
    }
    
}
