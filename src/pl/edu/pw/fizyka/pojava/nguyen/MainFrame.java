package pl.edu.pw.fizyka.pojava.nguyen;

import java.awt.Dimension;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	public MainFrame() {		
        setTitle("SIR Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        BlockingQueue<String[][]> queue = new LinkedBlockingQueue<>();
        
        LeftSubPanelGrid leftSubPanelGrid = new LeftSubPanelGrid(queue);
        LeftSubPanelGraph leftSubPanelGraph = new LeftSubPanelGraph();
        LeftPanel leftPanel = new LeftPanel(leftSubPanelGrid, leftSubPanelGraph);
        
        SirCalculator sirCalculator = new SirCalculator(queue);
        
        RightPanel rightPanel = new RightPanel(sirCalculator, leftSubPanelGrid);
        
        MainPanel mainPanel = new MainPanel(rightPanel, leftPanel);
        
        mainPanel.setPreferredSize(new Dimension(884, 589));
        
        MyMenuController myMenuController = new MyMenuController();
        MyMenu myMenu = new MyMenu(myMenuController);
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(myMenu);
        setJMenuBar(menuBar);
        
        add(mainPanel);
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
