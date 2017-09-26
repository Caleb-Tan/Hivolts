/**
 * Created by Caleb Tan on 9/26/2017.
 */
import javax.swing.*;

public class Main {
    public static void main(String[] args){
        Grid grid = new Grid();				// creates flag object
        JFrame frame = new JFrame("Flag");	// creates jframe
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(grid);	// adds the flag panel to the jframe

        frame.pack();		// sizes frame so that all contents are at preferred sizes
        frame.setVisible(true);
    }
}
