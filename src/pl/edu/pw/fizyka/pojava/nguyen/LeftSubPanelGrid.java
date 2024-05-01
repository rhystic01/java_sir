package pl.edu.pw.fizyka.pojava.nguyen;

import java.awt.Color;
import java.util.concurrent.BlockingQueue;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class LeftSubPanelGrid extends JPanel implements Runnable {
	private BlockingQueue<String[][]> queue;
	private int animationSpeed = 2;
	private volatile boolean running = false;
	
    public LeftSubPanelGrid(BlockingQueue<String[][]> queue) {
    	this.queue = queue;
        JLabel label = new JLabel("Simulation animation grid");
        add(label);
        this.setBackground(Color.GRAY);
        
        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 5);
        this.setBorder(border);
       
    }
    
    public void setAnimationSpeed(int fps) {
    	this.animationSpeed = 1000/fps;
    }
    
    public boolean isRunning() {
		return this.running;
	}
    
    public void stop() {
    	this.running = false;
    	this.queue.clear();
    }
    
    @Override
    public void run() {
        try {
        	this.running = true;
            // Continuously consume data from the queue and display it
            while (running) {
                String[][] grid = queue.take(); // Blocking call, waits for data if queue is empty
                for (int i = 0; i < grid.length; i++) {
        		    for (int j = 0; j < grid[i].length; j++) {
        		        System.out.print(grid[i][j] + " ");
        		    }
        		    System.out.println();
            	}
            	System.out.println();
            	Thread.sleep(animationSpeed);
            	if(queue.isEmpty()) running = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        queue.clear();
        this.running = false;
    }
}
