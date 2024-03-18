package testing;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {
	public MainFrame() {
		setSize(840,580);
        setTitle("Symulacja SIR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MainPanel mainPanel = new MainPanel();

        // Add main panel to frame
        add(mainPanel);
        mainPanel.setPreferredSize(new Dimension(350, 250));

        //pack();
        setLocationRelativeTo(null); // Center frame on screen
        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });
    }
}
