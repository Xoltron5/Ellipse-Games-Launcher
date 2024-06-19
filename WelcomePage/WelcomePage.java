package WelcomePage;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;

public class WelcomePage extends JFrame {

    North north;
    East east;
    South south;
    West west;
    Center center; 

    final int WIDTH = 750;
    final int HEIGHT = 750;
    final int BACKGROUNDCOLOR = 0xFFFFFF;

    public WelcomePage() {

        // when you click the close button it closes the program
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set the frame to have a width and a height
        this.setSize(WIDTH, HEIGHT);
        // center the frame 
        this.setLocationRelativeTo(null); 
        // makes it so you can't resize the frame
        this.setResizable(false); 
        // changes the background color
        this.getContentPane().setBackground(new Color(BACKGROUNDCOLOR));

        // creates each panel 
        north = new North();
        south = new South();
        east = new East();
        west = new West();
        center = new Center();
        
        // adds each panel to the main frame
        this.add(north, BorderLayout.NORTH);
        this.add(east, BorderLayout.EAST);
        this.add(south,BorderLayout.SOUTH);
        this.add(west, BorderLayout.WEST);
        this.add(center, BorderLayout.CENTER);
        
        // makes the frame visible
        this.setVisible(true);
    }
}
