package pl.edu.pw.fizyka.pojava.nguyen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.util.concurrent.BlockingQueue;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LeftSubPanelGrid extends JPanel implements Runnable {
	private BlockingQueue<short[][]> queue;
	private int animationSpeed = 2;
	private volatile boolean running = false;
	private JPanel[][] gridPanels;
	protected JPanel cellPanel, animPanel, labelPanel;
	private JLabel isRunningLabel;
	private int counter = 0;
	protected boolean isCellBorderSet = true;
	
    public LeftSubPanelGrid(BlockingQueue<short[][]> queue) {
    	this.queue = queue;  
    	animPanel = new JPanel();
    	labelPanel = new JPanel();
    	this.setLayout(new BorderLayout());
    	add(animPanel, BorderLayout.CENTER);
    	add(labelPanel, BorderLayout.SOUTH);
    	isRunningLabel = new JLabel("Stopped");
    	labelPanel.setLayout(new BorderLayout());
    	labelPanel.add(isRunningLabel, BorderLayout.WEST);
    	
    }   
   
    public void initializeGrid(short[][] gridData) {
    	animPanel.removeAll();
        int numRows = gridData.length;
        int numCols = gridData[0].length;
        gridPanels = new JPanel[numRows][numCols];
        animPanel.setLayout(new GridLayout(numRows, numCols));
        
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                cellPanel = new JPanel();
                if(isCellBorderSet) cellPanel.setBorder(BorderFactory.createLineBorder(new Color(30, 30, 30), 1));             
                
                // Set background color based on grid data
                if (gridData[row][col] == 0) {
                    cellPanel.setBackground(new Color(50, 230, 113));
                } else if (gridData[row][col] == 1){
                    cellPanel.setBackground(new Color(245, 60, 60));
                } else if (gridData[row][col] == 2){
                    cellPanel.setBackground(new Color(44, 54, 120));
                }
                
                gridPanels[row][col] = cellPanel; // Store reference to the cell panel
                animPanel.add(cellPanel); // Add cell panel to the main panel
            }
        }
        
        animPanel.revalidate();
        animPanel.repaint();
    }
    
    public void setAnimationSpeed(int fps) {
    	this.animationSpeed = 1000/fps;
    }
    
    public boolean isRunning() {
		return this.running;
	}
    
    public void resetAnimationPanel() {
    	this.animPanel.removeAll();
    	this.animPanel.revalidate();
    	this.animPanel.repaint();
    	this.isRunningLabel.setText("Stopped");
    }
    
    public void stop() {
    	this.running = false;
    	this.queue.clear();
    }
    
    public void run() {
    	
        this.running = true;
        Timer timer = new Timer(animationSpeed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!queue.isEmpty()) {
                        /*String[][]*/short[][] grid = queue.take(); // Blocking call, waits for data if queue is empty
                        initializeGrid(grid);                       
                        isRunningLabel.setText("Time (arbitrary): " + counter);
                        counter++;
                    } else {
                        running = false;
                        ((Timer)e.getSource()).stop(); // Stop the timer if queue is empty
                        isRunningLabel.setText("Time (arbitrary): " + (counter-1) + " (Stopped)");
                        counter = 0;
                        
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        timer.start(); // Start the timer
    }
}
