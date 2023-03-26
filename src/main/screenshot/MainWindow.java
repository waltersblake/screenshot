package screenshot;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

public class MainWindow extends JFrame implements ComponentListener, ActionListener {
    private JLabel label;
    private JLabel mouseX;
    private JLabel mouseY;
    private Rectangle screenCapture;
    private CaptureWindow sc;
    private EditWindow ew;
    private BufferedImage captureImage;


    public MainWindow() {
        super("ScreenShot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(1000,700);
        setSize(900,600);

        mouseX = new JLabel("X pos: ", JLabel.LEFT);
        mouseX.setFont(new Font("Calibri", Font.BOLD, 30));
        mouseX.setSize(200,100);
        mouseX.setLocation(5,5);
        add(mouseX);

        mouseY = new JLabel("Y pos: ", JLabel.LEFT);
        mouseY.setFont(new Font("Calibri", Font.BOLD, 30));
        mouseY.setSize(200,100);
        mouseY.setLocation(5,45);
        add(mouseY);

        JButton takeScreenCap = new JButton("ScreenShot");
        takeScreenCap.setSize(200,50);
        takeScreenCap.setLocation(695,5);
        takeScreenCap.setFont(new Font("Calibri", Font.PLAIN, 30));
        takeScreenCap.addActionListener(this);
        add(takeScreenCap);

        screenCapture = new Rectangle();
        sc = new CaptureWindow(mouseX, mouseY, screenCapture);
        sc.addComponentListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Button clicked");
        sc.setVisible(true);
        sc.setGraphics();
    }

    public void componentHidden(ComponentEvent e) {
        // dispose of capture window
        sc.dispose();
        // wait for window to complete disposing so we don't capture the draw rectangle and
        // opaque screen
        try {
            Thread.sleep(200);
        } catch (InterruptedException ie) {

        }
        
        System.out.printf("Main position: x = %d, y = %d\n", screenCapture.x, screenCapture.y);
        System.out.printf("Main dimensions: width = %d, height = %d\n",
            screenCapture.width, screenCapture.height);

        try {
            // divide x and y by 2 for accurate placement, no idea why???
            captureImage = new Robot().createScreenCapture(new Rectangle(screenCapture.x / 2, 
                screenCapture.y / 2, screenCapture.width, screenCapture.height));

            ew = new EditWindow(captureImage);
        ew.setVisible(true);
        } catch(AWTException ex) {
            System.err.println("AWT Exception");
        }
        
        
    }

    // Unused listening methods
    public void componentMoved(ComponentEvent e) {}
    public void componentResized(ComponentEvent e) {}
    public void componentShown(ComponentEvent e) {
        System.out.println("Showing capture window");
    }
}