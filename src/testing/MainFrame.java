package testing;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	public MainFrame() {
		
        setTitle("SIR Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainPanel mainPanel = new MainPanel();

        // Add main panel to frame
        add(mainPanel);
        mainPanel.setPreferredSize(new Dimension(884, 589));
        
     // Create an instance of the custom menu class
        MyMenu myMenu = new MyMenu();

        // Create a menu bar and add the custom menu to it
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(myMenu);

        // Set the menu bar to the frame
        setJMenuBar(menuBar);

        pack();
        setLocationRelativeTo(null); // Center frame on screen
        setVisible(true);
        
        
        
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });
    }
}
