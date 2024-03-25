package pl.edu.pw.fizyka.pojava.nguyen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {
	private RightPanel rightPanel;
	private LeftPanel leftPanel;
    public MainPanel(RightPanel rightPanel,LeftPanel leftPanel) { 
    	this.rightPanel = rightPanel;
    	this.leftPanel = leftPanel;
    	
    	this.setLayout(new BorderLayout()); 

        // Add left and right panels to main panel
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
                            
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension mainPanelsize = getSize(); 
                int mainPanelWidth = mainPanelsize.width;
                int mainPanelHeight = mainPanelsize.height;
               
                leftPanel.setPreferredSize(new Dimension((int) (mainPanelWidth*0.75), mainPanelHeight));
                revalidate(); 
                repaint(); 
            }
        });
    }
}
