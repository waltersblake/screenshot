package screenshot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;

public class CaptureWindow extends JFrame implements MouseMotionListener, MouseListener {
    private JLabel xLabel;
    private JLabel yLabel;
    private Graphics drawing;
    private Rectangle rect;

    public CaptureWindow(JLabel xLabel, JLabel yLabel, Rectangle capture) {
        super();
        setUndecorated(true);
        setAlwaysOnTop(true);
        setOpacity(0.35f);
        
        this.xLabel = xLabel;
        this.yLabel = yLabel;
        this.rect = capture;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize);
        addMouseListener(this);
        addMouseMotionListener(this);
        //addComponentListener(this);
        
    }

    public void setGraphics() {
        drawing = getGraphics();
        drawing.setColor(Color.red);
    }

    public void mousePressed(MouseEvent e) { 
        rect.x = e.getXOnScreen();
        rect.y = e.getYOnScreen();
        System.out.printf("Initial position: x = %d, y = %d\n",rect.x, rect.y);
    }

    public void mouseDragged(MouseEvent e) {
        drawing.clearRect(rect.x, rect.y, rect.width + 1, rect.height + 1);
        int x = e.getXOnScreen();
        int y = e.getYOnScreen();
        xLabel.setText("X pos: " + x);
        yLabel.setText("Y pos: " + y);
        rect.width = Math.abs(x - rect.x);
        rect.height = Math.abs(y - rect.y);
        drawing.drawRect(rect.x, rect.y, rect.width, rect.height);
    }

    
    public void mouseReleased(MouseEvent e) {
        
        setVisible(false);
        drawing.dispose();
        
    }
    

    // Unused mouse listening methods
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}

    
    public void mouseMoved(MouseEvent e) {}

    

}