package OldSwingCode.WelcomePage;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class North extends JPanel {
    final int WIDTH = 500;
    final int HEIGHT = 100;
    final int BACKGROUNDCOLOR = 0x123456;

    North() {
        // changes the background color
        this.setBackground(new Color(BACKGROUNDCOLOR));

        // set the frame to have a width and a height
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        // makes the frame visible
        this.setVisible(true);
    }
}
