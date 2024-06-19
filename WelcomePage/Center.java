package WelcomePage;
import java.awt.Color;

import javax.swing.JPanel;

public class Center extends JPanel {

    final int BACKGROUNDCOLOR = 0xFFFFFF;
    
    public Center() {

        // changes the background color
        this.setBackground(new Color(BACKGROUNDCOLOR));

        // makes the frame visible
        this.setVisible(true);
    }
}
