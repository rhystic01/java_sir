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
        
        BlockingQueue<short[][]> queue = new LinkedBlockingQueue<>(); //BlockingQueue<String[][]> queue = new LinkedBlockingQueue<>();        
        SirCalculator sirCalculator = new SirCalculator(queue);
        LeftSubPanelGrid leftSubPanelGrid = new LeftSubPanelGrid(queue, sirCalculator);
        
        
        LeftSubPanelGraph leftSubPanelGraph = new LeftSubPanelGraph();
        LeftPanel leftPanel = new LeftPanel(leftSubPanelGrid, leftSubPanelGraph);
        RightPanel rightPanel = new RightPanel(sirCalculator, leftSubPanelGrid);
        
        MainPanel mainPanel = new MainPanel(rightPanel, leftPanel);       
        mainPanel.setPreferredSize(new Dimension(700, 700));                                    
        
        MyMenuController myMenuController = new MyMenuController(leftSubPanelGrid,sirCalculator,rightPanel);
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
