package screenshot;

import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

public class EditWindow extends JFrame {

    private JLabel picLabel;

    public EditWindow(BufferedImage capture) {
        super("Edit");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200,800);
        setLocation(500,500);

        picLabel = new JLabel(new ImageIcon(capture));
        picLabel.setSize(600,400);
        picLabel.setLocation(300,200);
        add(picLabel);

    }
}