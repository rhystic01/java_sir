package testing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.*;

public class MainPanel extends JPanel {
    public MainPanel() {   
    	this.setLayout(new BorderLayout());   
        
        LeftPanel leftPanel = new LeftPanel();
        RightPanel rightPanel = new RightPanel();

        // Add left and right panels to main panel
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
                            
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension rightPanelsize = getSize(); 
                int rightPanelWidth = rightPanelsize.width;
                int rightPanelHeight = rightPanelsize.height;
               
                leftPanel.setPreferredSize(new Dimension((int) (rightPanelWidth*0.75), rightPanelHeight));
                revalidate(); 
                repaint(); 
                System.out.println(getSize().width);
                System.out.println(getSize().height);
            }
        });
    }
}
