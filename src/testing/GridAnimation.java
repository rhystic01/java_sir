package testing;

import javax.swing.*;
import java.awt.*;

public class GridAnimation extends JPanel {
    private static final int CELL_SIZE = 20; // Size of each cell
    private static final int GRID_SIZE = 20; // Number of cells in each row and column
    private static final int ANIMATION_DELAY = 100; // Delay between each frame (in milliseconds)

    private Color[][] gridColors; // 2D array to store colors for each cell

    public GridAnimation() {
        gridColors = new Color[GRID_SIZE][GRID_SIZE]; // Initialize the gridColors array
        
        // Initialize grid with random colors (you can modify this to suit your needs)
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                gridColors[i][j] = getRandomColor();
            }
        }

        // Create a timer to change colors rapidly for animation
        Timer timer = new Timer(ANIMATION_DELAY, e -> {
            // Change colors randomly for each cell
            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    gridColors[i][j] = getRandomColor();
                }
            }
            repaint(); // Repaint the panel to reflect the changes
        });
        timer.start(); // Start the timer
    }

    // Method to generate a random color
    private Color getRandomColor() {
        return new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256));
    }

    // Override the paintComponent method to draw the grid
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the grid with colors
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                g.setColor(gridColors[i][j]);
                g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    // Main method to create and display the frame
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Grid Animation");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new GridAnimation());
            frame.pack();
            frame.setVisible(true);
        });
    }
}
