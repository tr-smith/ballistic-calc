package ballisticcalc.Enums;

import ballisticcalc.models.CartridgeData;
import ballisticcalc.models.CartridgeDataContainer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

public enum GraphType {
    DROP{
        @Override
        public JPanel getGraph(CartridgeDataContainer cdc) {
            int count = 1;
            XYSeriesCollection dataset = new XYSeriesCollection();
            for (CartridgeData cartData : cdc.getCartridgeDataList()) {
                XYSeries series = createDataset(cartData.getDrop(), count);
                dataset.addSeries(series);
                count++;
            }
            XYDataset trajectoryDataset = dataset;
            JFreeChart trajectoryChart = ChartFactory.createXYLineChart("",
                    "Range (Yards)", "Drop (in.)", trajectoryDataset);
            XYPlot trajectoryPlot = trajectoryChart.getXYPlot();
            NumberAxis rangeAxis = (NumberAxis) trajectoryPlot.getRangeAxis();
            rangeAxis.setUpperMargin(0.20);
            return new ChartPanel(trajectoryChart);
        }
    },
    WIND{
        @Override
        public JPanel getGraph(CartridgeDataContainer cdc) {
            int count = 1;
            XYSeriesCollection dataset = new XYSeriesCollection();
            for (CartridgeData cartData : cdc.getCartridgeDataList()) {
                XYSeries series = createDataset(cartData.getWind(), count);
                dataset.addSeries(series);
                count++;
            }
            XYDataset windDataset = dataset;
            JFreeChart windChart = ChartFactory.createXYLineChart("",
                    "Range (Yards)", "Drift (in.)", windDataset);
            return new ChartPanel(windChart);
        }
    },
    ENERGY{
        @Override
        public JPanel getGraph(CartridgeDataContainer cdc) {
            int count = 1;
            XYSeriesCollection dataset = new XYSeriesCollection();
            for (CartridgeData cartData : cdc.getCartridgeDataList()) {
                XYSeries series = createDataset(cartData.getEnergy(), count);
                dataset.addSeries(series);
                count++;
            }
            XYDataset energyDataset = dataset;
            JFreeChart energyChart = ChartFactory.createXYLineChart("",
                    "Range (Yards)", "Energy (lbs/in2)", energyDataset);
            return new ChartPanel(energyChart);
        }
    };

    public abstract JPanel getGraph(CartridgeDataContainer cdc);

    private static XYSeries createDataset(double[] y, int count) {
        // creates an XY dataset...
        // returns the dataset
        XYSeries series = new XYSeries("Cartridge " + count);
        for (int i = 0; i < y.length; i++) {
            series.add(i, y[i]);
        }
        return series;
    }
}
