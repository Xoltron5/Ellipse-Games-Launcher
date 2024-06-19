package WelcomePage;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class Footer extends JPanel {

    final int WIDTH = 500;
    final int HEIGHT = 50;
    final int BACKGROUNDCOLOR = 0x123456;
    
    
    public Footer () {
        this.setBackground(new Color(BACKGROUNDCOLOR));
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        this.setVisible(true);
    }
}
