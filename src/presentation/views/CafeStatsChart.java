package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A line chart showing the number of coffees served per minute.
 * It is used for visualizing game statistics in a graphical form.
 */
public class CafeStatsChart extends JPanel {
    /**
     * List of integers representing the number of coffees served per minute
     */
    private final List<Integer> data;

    /**
     * Constructs a CafeStatsChart and displays it in a new JFrame.
     *
     * @param cafesPorMinuto List of integers, each representing the number of coffees served in a minute.
     */
    public CafeStatsChart(List<Integer> cafesPorMinuto) {
        this.data = new ArrayList<>(cafesPorMinuto);

        setBackground(Color.WHITE);
        JFrame frame = new JFrame("Game Statistics");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(this);
        frame.setVisible(true);
    }

    /**
     * Overridden method that gets called whenever the component needs to be redrawn.
     * It delegates the drawing to drawChart
     *
     * @param g The Graphics object used for drawing the component.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawChart((Graphics2D) g);
    }

    /**
     * Draws the line chart using the provided Graphics2D context.
     * Includes axes, data line, grid lines, labels, and a title.
     *
     * @param g2 The Graphics2D context used to render the chart.
     */
    private void drawChart(Graphics2D g2) {
        int width = getWidth();
        int height = getHeight();

        // Chart margins
        int leftMargin = 60;
        int rightMargin = 30;
        int topMargin = 40;
        int bottomMargin = 60;

        // Draw axes
        g2.setColor(Color.BLACK);
        g2.drawLine(leftMargin, height - bottomMargin, width - rightMargin, height - bottomMargin);
        g2.drawLine(leftMargin, topMargin, leftMargin, height - bottomMargin);

        int maxY = data.stream().max(Integer::compare).orElse(1);
        int stepX = (width - leftMargin - rightMargin) / Math.max(1, data.size() - 1);
        int graphHeight = height - topMargin - bottomMargin;

        // Plot data line
        g2.setColor(Color.BLUE);
        for (int i = 0; i < data.size() - 1; i++) {
            int x1 = leftMargin + i * stepX;
            int y1 = height - bottomMargin - (data.get(i) * graphHeight / maxY);
            int x2 = leftMargin + (i + 1) * stepX;
            int y2 = height - bottomMargin - (data.get(i + 1) * graphHeight / maxY);
            g2.drawLine(x1, y1, x2, y2);
        }

        // X-axis labels
        g2.setColor(Color.BLACK);
        for (int i = 0; i < data.size(); i++) {
            int x = leftMargin + i * stepX;
            String label = String.valueOf(i + 1);
            g2.drawString(label, x - 10, height - bottomMargin + 20);
        }

        // Y-axis labels & grid
        for (int i = 0; i <= 5; i++) {
            int value = maxY * i / 5;
            int y = height - bottomMargin - (value * graphHeight / maxY);
            g2.drawString(String.valueOf(value), leftMargin - 40, y + 5);
            g2.setColor(new Color(200, 200, 200));
            g2.drawLine(leftMargin, y, width - rightMargin, y);
            g2.setColor(Color.BLACK);
        }

        // Chart title
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 16f));
        g2.drawString("Coffees per minute", width / 2 - 80, topMargin - 10);
    }
}