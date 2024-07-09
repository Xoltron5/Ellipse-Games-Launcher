package OldSwingCode.WelcomePage;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class West extends JPanel {
    final int WIDTH = 100;
    final int HEIGHT = 500;
    final int BACKGROUNDCOLOR = 0x123456;

    West() {
        // changes the background color
        this.setBackground(new Color(BACKGROUNDCOLOR));

        // set the frame to have a width and a height
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        // makes the frame visible
        this.setVisible(true);
    }
}
