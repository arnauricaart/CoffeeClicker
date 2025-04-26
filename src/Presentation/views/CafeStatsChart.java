package Presentation.views;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.List;

public class CafeStatsChart extends JFrame {

    public CafeStatsChart(List<Integer> cafesPorMinuto) {
        //Le pongo título
        super("Estadísticas de Cafés");

        //Creo un dataset para poder añadir la infermación y crear la grafica
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Pongo los cafes por minuto en el dataset
        for (int i = 0; i < cafesPorMinuto.size(); i++) {
            dataset.addValue(cafesPorMinuto.get(i), "Cafés", (i + 1) + "");
        }

        // Creo el gráfico en lineChart para poner la información del dataset
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Cafés",
                "Tiempo (min)",
                "Número de Cafés",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Muestro el gráfico en un panel
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);
    }
}

