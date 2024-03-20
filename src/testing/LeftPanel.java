package testing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;

@SuppressWarnings("serial")
public class LeftPanel extends JPanel {
	private LeftSubPanelGrid leftSubPanelGrid;
	private LeftSubPanelGraph leftSubPanelGraph;
    public LeftPanel(LeftSubPanelGrid leftSubPanelGrid, LeftSubPanelGraph leftSubPanelGraph) {
    	this.leftSubPanelGrid = leftSubPanelGrid;
    	this.leftSubPanelGraph =  leftSubPanelGraph;
    	this.setLayout(new BorderLayout());
    	add(leftSubPanelGrid, BorderLayout.CENTER);
    	add(leftSubPanelGraph, BorderLayout.SOUTH);
    	
    	addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension leftPanelsize = getSize(); 
                int leftPanelWidth = leftPanelsize.width;
                int leftPanelHeight = leftPanelsize.height;
               
                leftSubPanelGraph.setPreferredSize(new Dimension(leftPanelWidth, leftPanelHeight/3));
                revalidate(); 
                repaint();                  
            }
        });
    	
    	
    }
    
    
}
