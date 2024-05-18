package pl.edu.pw.fizyka.pojava.nguyen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

@SuppressWarnings("serial")
public class LeftSubPanelGraph extends JPanel implements Runnable{
	private BlockingQueue<double[][]> graphQueue;
	private XYSeries susceptible;
    private XYSeries infected;
    private XYSeries removed;
    private Random random;
    private SirCalculator sirCalculator;
    private int counter = 0;
    private volatile boolean running = false;
	
    public LeftSubPanelGraph(BlockingQueue<double[][]> graphQueue, SirCalculator sirCalculator) {
    	this.graphQueue = graphQueue;
    	this.sirCalculator = sirCalculator;
    	susceptible = new XYSeries("Susceptible");
    	infected = new XYSeries("Infected");
    	removed = new XYSeries("Removed");

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(susceptible);
        dataset.addSeries(infected);
        dataset.addSeries(removed);
        this.setLayout(new BorderLayout());
        
        JFreeChart chart = createChart(dataset);
        XYPlot plot = (XYPlot) chart.getPlot();
        // Additional customization can be done here
        XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(50, 230, 113)); 
        renderer.setSeriesPaint(1, new Color(245, 60, 60));
        renderer.setSeriesPaint(2, new Color(44, 54, 120));
        plot.setBackgroundPaint(new Color(240, 240, 240));
        plot.setDomainGridlinePaint(Color.BLACK);
        plot.setRangeGridlinePaint(Color.BLACK);
        ChartPanel chartPanel = new ChartPanel(chart);
        //chartPanel.setPreferredSize(new Dimension(800, 400));
        
        this.add(chartPanel);        
    }
    
    private JFreeChart createChart(XYSeriesCollection dataset) {
        return ChartFactory.createScatterPlot(
            "",
            "Time (arbitrary)",
            "Amount",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
    }
    
    public void stop() {
    	this.running = false;
    	this.graphQueue.clear();
    }
    
    public boolean isRunning() {
		return this.running;
	}

	@Override
	public void run() {
		this.running = true;
		susceptible.clear();
        infected.clear();
        removed.clear();
        Timer timer = new Timer(80, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	double totalSIR[][] = new double[sirCalculator.getSimTime()+1][3];
            	
            	if(!graphQueue.isEmpty()) {
            		try {           			
            			totalSIR = graphQueue.take();
            			susceptible.add(counter, totalSIR[counter][0]); 
                    	infected.add(counter, totalSIR[counter][1]); 
                    	removed.add(counter, totalSIR[counter][2]); 
                    	counter ++;
                    	if(counter >= sirCalculator.getSimTime()) counter = 0;
        			} catch (InterruptedException e1) {
        				e1.printStackTrace();
        			}
            	} else {
                    running = false;
                    ((Timer)e.getSource()).stop(); // Stop the timer if queue is empty
                    counter = 0;   
                }
            	
            	          	
            }
        });
        timer.start(); // Start the timer
	}
}
