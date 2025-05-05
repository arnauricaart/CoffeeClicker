package presentation.views;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CafeStatsChart extends JPanel {

    private final List<Integer> data;

    public CafeStatsChart(List<Integer> cafesPorMinuto) {
        this.data = cafesPorMinuto;

        setBackground(Color.WHITE);
        JFrame frame = new JFrame("Game Statistics");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(this);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawChart((Graphics2D) g);
    }

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
        int maxY = data.stream().max(Integer::compare).orElse(1);
        int stepX = (width - leftMargin - rightMargin) / Math.max(1, data.size() - 1);
        int graphHeight = height - topMargin - bottomMargin;

        //Linea de la grafica que representa los cafés
        g2.setColor(Color.BLUE);
        for (int i = 0; i < data.size() - 1; i++) {
            int x1 = leftMargin + i * stepX;
            int y1 = height - bottomMargin - (data.get(i) * graphHeight / maxY);
            int x2 = leftMargin + (i + 1) * stepX;
            int y2 = height - bottomMargin - (data.get(i + 1) * graphHeight / maxY);
            g2.drawLine(x1, y1, x2, y2);
        }

        //minutos en el eje X
        g2.setColor(Color.BLACK);
        for (int i = 0; i < data.size(); i++) {
            int x = leftMargin + i * stepX;
            String label = String.valueOf(i + 1);
            g2.drawString(label, x - 10, height - bottomMargin + 20);
        }

        //Valores en el eje Y
        for (int i = 0; i <= 5; i++) {
            int value = maxY * i / 5;
            int y = height - bottomMargin - (value * graphHeight / maxY);
            g2.drawString(String.valueOf(value), leftMargin - 40, y + 5);
            g2.setColor(new Color(200, 200, 200));
            g2.drawLine(leftMargin, y, width - rightMargin, y);
            g2.setColor(Color.BLACK);
        }

        //Titulo
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 16f));
        g2.drawString("Coffees per minute", width / 2 - 80, topMargin - 10);
    }
}