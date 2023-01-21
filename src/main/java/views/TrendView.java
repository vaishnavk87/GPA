package views;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.XChartPanel;
import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.gpaTrend.TrendModel;

import javax.swing.*;
import java.awt.*;

public class TrendView {
    public TrendView(EntityGateway entityGateway, EntityFactory entityFactory, TrendModel model, String name) {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JFrame frame = new JFrame();
        frame.setTitle("Just Bob");
        frame.setLocation(new Point(500, 300));
        frame.add(panel);
        frame.setSize(new Dimension(1000, 500));
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CategoryChart chart = new CategoryChart(1000, 500);
        if (model.yData.isEmpty()) {
            // No Committed Data Label
            JLabel label1 = new JLabel("No Committed Grades");
            label1.setBounds(100, 8, 193, 20);
            panel.add(label1);
        } else {
            chart.addSeries(name, model.xData, model.yData);
            JPanel chartPanel = new XChartPanel<>(chart);
            frame.add(chartPanel);
        }

        frame.setVisible(true);
    }
}
