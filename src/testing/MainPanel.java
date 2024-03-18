package testing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.*;

public class MainPanel extends JPanel {
    public MainPanel() {   
    	//this.setLayout(new BorderLayout());
    	this.setLayout(new GridLayout(1,2));
        
        LeftPanel leftPanel = new LeftPanel();
        RightPanel rightPanel = new RightPanel();

        // Add left and right panels to main panel
        //add(leftPanel, BorderLayout.WEST);
        //add(rightPanel, BorderLayout.CENTER);
        add(leftPanel);
        add(rightPanel);
        
        //Dimension mainPanelSize = this.getSize();
        //int mainPanelWidth = mainPanelSize.width / 5;
        //leftPanel.setPreferredSize(new Dimension(mainPanelWidth, mainPanelSize.height));
    }
}
