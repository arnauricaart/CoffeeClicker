package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * A panel showing the cafe stats.
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
        this.data = cafesPorMinuto;
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
        //Cojo el tamaño del panel
        int width = getWidth();
        int height = getHeight();

        //Marco un margen para la gráfica
        int leftMargin = 60;
        int rightMargin = 30;
        int topMargin = 40;
        int bottomMargin = 60;

        //Ejes del gráfico
        g2.setColor(Color.BLACK);
        g2.drawLine(leftMargin, height - bottomMargin, width - rightMargin, height - bottomMargin);
        g2.drawLine(leftMargin, topMargin, leftMargin, height - bottomMargin);

        //Tamaño máximo del gráfico (ejes dinamicos)
        long maxY = data.stream().max(Integer::compare).orElse(1);// Changed to long to handle large numbers
        if(maxY == 0){
            maxY = 100;
        }
        int stepX = (width - leftMargin - rightMargin) / Math.max(1, data.size() - 1);
        int graphHeight = height - topMargin - bottomMargin;

        //Valores en el eje Y
        // Show max value at the top and 0 at the bottom, with 4 evenly distributed values in between
        for (int i = 5; i >= 0; i--) {
            long value = (maxY * i) / 5;  // Use long for calculations
            int y = height - bottomMargin - (int)((value * graphHeight) / maxY);  // Cast only at final step
            
            // Right-align the value labels
            String label = String.format("%,d", value);  // Format with thousands separator
            FontMetrics fm = g2.getFontMetrics();
            int labelWidth = fm.stringWidth(label);
            g2.setColor(Color.BLACK);
            g2.drawString(label, leftMargin - labelWidth - 5, y + 5);
            
            // Add subtle horizontal grid line
            g2.setColor(new Color(200, 200, 200));
            g2.drawLine(leftMargin, y, width - rightMargin, y);
        }

        //Linea de la grafica que representa los cafés
        g2.setColor(Color.BLUE);
        for (int i = 0; i < data.size() - 1; i++) {
            int x1 = leftMargin + i * stepX;
            int y1 = height - bottomMargin - (int)((long)data.get(i) * graphHeight / maxY);  // Handle large numbers
            int x2 = leftMargin + (i + 1) * stepX;
            int y2 = height - bottomMargin - (int)((long)data.get(i + 1) * graphHeight / maxY);  // Handle large numbers
            g2.drawLine(x1, y1, x2, y2);
        }

        //minutos en el eje X (10 valores distribuidos uniformemente)
        g2.setColor(Color.BLACK);
        int totalMinutes = data.size();


        // Always show 0 and max value
        //TODO revisar si aixo esta be
        //g2.drawString("0", leftMargin - 10, height - bottomMargin + 20);
        //g2.drawString(String.valueOf(totalMinutes-1), width - rightMargin - 20, height - bottomMargin + 20);
        
        // Show exactly 10 evenly distributed values between 0 and max
        if (totalMinutes > 2) {  // Only if we have more than 2 points
            double interval = totalMinutes / 11.0;  // Divide into 11 parts to get 10 points between
            for (int i = 1; i <= 10; i++) {
                int minuteValue = (int) Math.round(i * interval);
                if (minuteValue < totalMinutes) {  // Skip the last one as we already showed it
                    int x = leftMargin + (minuteValue * (width - leftMargin - rightMargin) / (totalMinutes - 1));
                    String label = String.valueOf(minuteValue);
                    // Center the label on the x position
                    FontMetrics fm = g2.getFontMetrics();
                    int labelWidth = fm.stringWidth(label);
                    g2.drawString(label, x - labelWidth/2, height - bottomMargin + 20);
                    
                    // Add subtle vertical grid line
                    g2.setColor(new Color(220, 220, 220));
                    g2.drawLine(x, height - bottomMargin, x, topMargin);
                    g2.setColor(Color.BLACK);
                }
            }
        }

        //Titulo
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 16f));
        g2.drawString("Coffees per minute", width / 2 - 80, topMargin - 10);
    }
} 