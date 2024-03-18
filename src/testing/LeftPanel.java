package testing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;

public class LeftPanel extends JPanel {
    public LeftPanel() {
    	this.setLayout(new BorderLayout());
        // Initialize and customize left panel components
    	LeftSubPanel_grid leftSubPanel_grid = new LeftSubPanel_grid();
    	LeftSubPanel_graph leftSubPanel_graph = new LeftSubPanel_graph();
    	add(leftSubPanel_grid, BorderLayout.CENTER);
    	add(leftSubPanel_graph, BorderLayout.SOUTH);
    	
    	addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension leftPanelsize = getSize(); 
                int leftPanelWidth = leftPanelsize.width;
                int leftPanelHeight = leftPanelsize.height;
               
                leftSubPanel_graph.setPreferredSize(new Dimension(leftPanelWidth, leftPanelHeight/3));
                revalidate(); 
                repaint();   
            }
        });
    	
    	
    }
    
    
}
