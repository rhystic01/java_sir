package testing;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	public MainFrame() throws HeadlessException {
    	this.setSize(840,580);
    	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public MainFrame(GraphicsConfiguration gc) {
        super(gc);
        this.setSize(840,580);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public MainFrame(String title) throws HeadlessException {
        super(title);
        this.setSize(840,580);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public MainFrame(String title, GraphicsConfiguration gc) {
        super(title, gc);
        this.setSize(840,580);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public static void main(String[] args) {
    }
}
