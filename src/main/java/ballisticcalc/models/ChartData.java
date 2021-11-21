package ballisticcalc.models;

import javax.swing.*;

public class ChartData {
    private JPanel chart;
    private CartridgeDataContainer cartridgeDataContainer;
    private double mpbr;

    public ChartData() {
        this.chart = null;
        this.cartridgeDataContainer = new CartridgeDataContainer();
        this.mpbr = 0;
    }

    public JPanel getChart() {
        return chart;
    }

    public void setChart(JPanel chart) {
        this.chart = chart;
    }

    public CartridgeDataContainer getCartridgeDataContainer() {
        return cartridgeDataContainer;
    }

    public void setCartridgeDataContainer(CartridgeDataContainer cartridgeDataContainer) {
        this.cartridgeDataContainer = cartridgeDataContainer;
    }

    public double getMpbr() {
        return mpbr;
    }

    public void setMpbr(double mpbr) {
        this.mpbr = mpbr;
    }
}
