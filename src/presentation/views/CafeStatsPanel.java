package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A panel showing the coffee stats.
 * It is used for visualizing game statistics in a graphical form.
 */
public class CafeStatsPanel extends JPanel {

    /**
     * List of integers representing the number of coffees served per minute
     */
    private final List<Integer> data;

    /**
     * Constructs a CafeStatsPanel and displays it in a new JFrame.
     *
     * @param cafesPorMinuto List of integers, each representing the number of coffees served in a minute.
     */
    public CafeStatsPanel(List<Integer> cafesPorMinuto) {
        this.data = new ArrayList<>(cafesPorMinuto);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(600, 500));
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
        int leftMargin = 75;
        int rightMargin = 30;
        int topMargin = 40;
        int bottomMargin = 60;

        // Draw axes
        g2.setColor(Color.BLACK);
        g2.drawLine(leftMargin, height - bottomMargin, width - rightMargin, height - bottomMargin);
        g2.drawLine(leftMargin, topMargin, leftMargin, height - bottomMargin);

        long maxY = data.stream().max(Integer::compare).orElse(1);
        if(maxY == 0){
            maxY = 100;
        }
        int stepX = (width - leftMargin - rightMargin) / Math.max(1, data.size() - 1);
        int graphHeight = height - topMargin - bottomMargin;

        // Y-axis labels & grid
        for (int i = 5; i >= 0; i--) {
            long value = (maxY * i) / 5;
            int y = height - bottomMargin - (int)((value * graphHeight) / maxY);

            String label = String.format("%,d", value);
            FontMetrics fm = g2.getFontMetrics();
            int labelWidth = fm.stringWidth(label);
            g2.setColor(Color.BLACK);
            g2.drawString(label, leftMargin - labelWidth - 5, y + 5);

            g2.setColor(new Color(200, 200, 200));
            g2.drawLine(leftMargin, y, width - rightMargin, y);
        }

        // Plot data line
        g2.setColor(Color.BLUE);
        for (int i = 0; i < data.size() - 1; i++) {
            int x1 = leftMargin + i * stepX;
            int y1 = height - bottomMargin - (int)((long)data.get(i) * graphHeight / maxY);
            int x2 = leftMargin + (i + 1) * stepX;
            int y2 = height - bottomMargin - (int)((long)data.get(i + 1) * graphHeight / maxY);
            g2.drawLine(x1, y1, x2, y2);
        }

        // X-axis
        g2.setColor(Color.BLACK);
        int totalMinutes = data.size();

        if (totalMinutes > 2) {
            double interval = totalMinutes / 11.0;
            for (int i = 1; i <= 10; i++) {
                int minuteValue = (int) Math.round(i * interval);
                if (minuteValue < totalMinutes) {
                    int x = leftMargin + (minuteValue * (width - leftMargin - rightMargin) / (totalMinutes - 1));
                    String label = String.valueOf(minuteValue);
                    FontMetrics fm = g2.getFontMetrics();
                    int labelWidth = fm.stringWidth(label);
                    g2.drawString(label, x - labelWidth/2, height - bottomMargin + 20);
                    g2.setColor(new Color(220, 220, 220));
                    g2.drawLine(x, height - bottomMargin, x, topMargin);
                    g2.setColor(Color.BLACK);
                }
            }
        }

        // Chart title
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 16f));
        g2.drawString("Coffees per minute", width / 2 - 80, topMargin - 10);
    }
} 