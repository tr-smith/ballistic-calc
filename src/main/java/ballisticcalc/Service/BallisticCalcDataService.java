package ballisticcalc.Service;

import ballisticcalc.Enums.GraphType;
import ballisticcalc.GUI.DataOutputFormGUI;
import ballisticcalc.models.CartridgeData;
import ballisticcalc.models.CartridgeDataContainer;

import javax.swing.*;

public class BallisticCalcDataService {
    private CartridgeDataContainer cartridgeDataContainer;
    private double mpbr;

    private BallisticCalcDataService(CartridgeDataContainer cartridgeDataContainer) {
        this.cartridgeDataContainer = cartridgeDataContainer;
    }

    public static BallisticCalcDataService create(CartridgeDataContainer cartridgeDataContainer) {
        return new BallisticCalcDataService(cartridgeDataContainer);
    }

    public void showData(){
        cartridgeDataContainer.getCartridgeDataList().stream().forEach((cd) -> {
            cartridgeDataContainer.getBallisticCalc().startCalc(cd);
        });
        int rangeInterval = getRangeInterval(cartridgeDataContainer.getBallisticCalc().getMaxRange());
        if (rangeInterval == -1){
            return;
        }

        DataOutputFormGUI dialog = new DataOutputFormGUI(null, "Range Card(s)", true, cartridgeDataContainer, rangeInterval);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                dialog.dispose();
            }
        });

        dialog.setVisible(true);
    }

    public JPanel getGraph(GraphType graphType) {
        cartridgeDataContainer.getCartridgeDataList().stream().forEach((cd) -> {
            cartridgeDataContainer.getBallisticCalc().startCalc(cd);
        });
        JPanel replaceGraph = graphType.getGraph(cartridgeDataContainer);

        return replaceGraph;
    }

    public JPanel getMPBR(){
        int selection;
        String messageMPBR = "Select Game Size (default = Medium Game 40 - 100 lbs)";
        Object[] selectValues = {"Select Game Size ", "Very Small Game <10lbs", "Small Game 10 - 35 lbs",
                "Medium Game 40 - 100 lbs", "Large Game 100 - 350lbs", "Very Large Game 350+lbs"};
        selection = selectData(selectValues, messageMPBR);
        switch (selection) {
            case 0:
                this.mpbr = 4;
                break;
            case 1:
                this.mpbr = 1;
                break;
            case 2:
                this.mpbr = 3;
                break;
            case 3:
                this.mpbr = 4;
                break;
            case 4:
                this.mpbr = 6;
                break;
            case 5:
                this.mpbr = 10;
                break;
            default:
                this.mpbr = 4;
                break;
        }

        cartridgeDataContainer.getBallisticCalc().setMaxRange(1000);
        cartridgeDataContainer.getBallisticCalc().setCrossWind(0);
        cartridgeDataContainer.getBallisticCalc().setRangeWind(0);

        for (CartridgeData cd : cartridgeDataContainer.getCartridgeDataList()) {

            cd.setZeroMPBR(findMBPRFirstZero(mpbr, cd, cartridgeDataContainer.getBallisticCalc()));
            cd.setZeroMPBR(findMBPRSecondZero(cd, cartridgeDataContainer.getBallisticCalc()));
            cd.setMaxRangeMPBR(findMBPRFinalRange(cd, mpbr, cartridgeDataContainer.getBallisticCalc()));
        }

        cartridgeDataContainer.getCartridgeDataList().stream().forEach((cd) -> {
            cartridgeDataContainer.getBallisticCalc().startCalc(cd);
        });

        JPanel panel = GraphType.DROP.getGraph(cartridgeDataContainer);

        return panel;
    }



    private int selectData(Object[] possibilities, String message) {

        int selection = 0;
        Object selectedValue = JOptionPane.showInputDialog(null,
                message, "Input",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibilities, possibilities[0]);

        for (int count = 0; count < possibilities.length; count++) {
            if (possibilities[count].equals(selectedValue)) {
                selection = count;
            }
        }
        return selection;
    }

    private double findMBPRFirstZero(double mpbr, CartridgeData cd, BallisticCalc ballisticCalc) {
        double zeroRange = 9;
        double maxHeight = 0;

        do {
            zeroRange++;
            cd.setZeroMPBR(zeroRange);
            ballisticCalc.startCalc(cd);
            double[] drop = cd.getDrop();
            for (int count = 0; count < drop.length - 1; count++) {
                if (drop[count] > drop[count + 1]) {
                    maxHeight = drop[count];
                    break;
                }
            }
        } while (maxHeight > (mpbr / 2) + 0.0625);

        return zeroRange;
    }

    private double findMBPRSecondZero(CartridgeData cd, BallisticCalc ballisticCalc) {
        double secondZero = 0;
        //BallisticCalc.setZeroRange(firstZero);
        ballisticCalc.startCalc(cd);
        double[] drop = cd.getDrop();
        for (int count = (int) (cd.getZeroMPBR() + 1); count < drop.length - 1; count++) {
            if (drop[count] > drop[count + 1]) {
                if (drop[count] < 0.0625 && drop[count] > -0.0625) {
                    secondZero = count;
                    break;
                }
            }
        }
        return secondZero;
    }

    private double findMBPRFinalRange(CartridgeData cd, double mpbr, BallisticCalc ballisticCalc) {
        double finalRange = 0;
        //BallisticCalc.setZeroRange(secondZero);
        ballisticCalc.startCalc(cd);
        double[] drop = cd.getDrop();
        for (int count = (int) (cd.getZeroMPBR()); count < drop.length - 1; count++) {

            if (drop[count] < -(mpbr / 2) + 0.0625 && drop[count] > -(mpbr / 2) - 0.0625) {
                finalRange = count;
                break;
            }
        }
        return finalRange;
    }

    private int getRangeInterval(double maxInterval) {
        int rangeInterval = -1;
        String input = JOptionPane.showInputDialog(
                null,
                "Enter Range Interval \nMinimum = 1\nMaximum = " + maxInterval + "\nDefault = 50",
                "Input Required",
                JOptionPane.PLAIN_MESSAGE
        );
        if (input != null) {
            if (input.length() > 0) {

                try {
                    rangeInterval = Integer.parseInt(input);
                    if (rangeInterval < 1 || rangeInterval > maxInterval) {
                        JOptionPane.showMessageDialog(null, "Range Interval must be between 1 and " + maxInterval, "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        rangeInterval = getRangeInterval(maxInterval);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Range Interval must be numeric", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    rangeInterval = getRangeInterval(maxInterval);
                }

            } else {
                rangeInterval = 50;
            }
        }
        return rangeInterval;

    }

    public CartridgeDataContainer getCartridgeDataContainer() {
        return cartridgeDataContainer;
    }

    public double getMpbr() {
        return mpbr;
    }
}
