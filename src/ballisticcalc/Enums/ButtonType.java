package ballisticcalc.Enums;

import ballisticcalc.GUI.AddCartridgeGUI;
import ballisticcalc.Service.BallisticCalcDataService;
import ballisticcalc.models.CartridgeDataContainer;
import ballisticcalc.models.ChartData;

import javax.swing.*;

import static javax.swing.JOptionPane.WARNING_MESSAGE;

public enum ButtonType {
    DATA {
        @Override
        public ChartData buttonEvent(CartridgeDataContainer cartridgeDataContainer, boolean isOneSelected) {
            ChartData data = new ChartData();
            if (isOneSelected) {
                BallisticCalcDataService service = BallisticCalcDataService.create(cartridgeDataContainer);
                service.showData();
            } else {
                ButtonType.noneSelected();
            }
            return data;
        }
    },
    DROP {
        @Override
        public ChartData buttonEvent(CartridgeDataContainer cartridgeDataContainer, boolean isOneSelected) {
            ChartData data = new ChartData();
            if (isOneSelected) {
                BallisticCalcDataService service = BallisticCalcDataService.create(cartridgeDataContainer);
                data.setChart(service.getGraph(GraphType.DROP));
            } else {
                ButtonType.noneSelected();
            }
            return data;
        }
    },
    WIND {
        @Override
        public ChartData buttonEvent(CartridgeDataContainer cartridgeDataContainer, boolean isOneSelected) {
            ChartData data = new ChartData();
            if (isOneSelected) {
                BallisticCalcDataService service = BallisticCalcDataService.create(cartridgeDataContainer);
                data.setChart(service.getGraph(GraphType.WIND));
            } else {
                ButtonType.noneSelected();
            }
            return data;
        }
    },
    ENERGY {
        @Override
        public ChartData buttonEvent(CartridgeDataContainer cartridgeDataContainer, boolean isOneSelected) {
            ChartData data = new ChartData();
            if (isOneSelected) {
                BallisticCalcDataService service = BallisticCalcDataService.create(cartridgeDataContainer);
                data.setChart(service.getGraph(GraphType.ENERGY));
            } else {
                ButtonType.noneSelected();
            }
            return data;
        }
    },
    MPBR {
        @Override
        public ChartData buttonEvent(CartridgeDataContainer cartridgeDataContainer, boolean isOneSelected) {
            ChartData data = new ChartData();
            if (isOneSelected) {
                BallisticCalcDataService service = BallisticCalcDataService.create(cartridgeDataContainer);
                data.setChart(service.getMPBR());
                data.setCartridgeDataContainer(service.getCartridgeDataContainer());
                data.setMpbr(service.getMpbr());
            } else {
                ButtonType.noneSelected();
            }
            return data;
        }
    },
    ADD_CARTRIDGE {
        @Override
        public ChartData buttonEvent(CartridgeDataContainer cartridgeDataContainer, boolean isOneSelected) {
            ChartData data = new ChartData();
            AddCartridgeGUI dialog = new AddCartridgeGUI(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    dialog.dispose();
                }
            });
            dialog.setVisible(true);
            return data;
        }
    },
    RESET {
        @Override
        public ChartData buttonEvent(CartridgeDataContainer cartridgeDataContainer, boolean isOneSelected) {
            ChartData data = new ChartData();
            return data;
        }
    };


    public abstract ChartData buttonEvent(CartridgeDataContainer cartridgeDataContainer, boolean isOneSelected);

    private static void noneSelected() {
        JOptionPane.showMessageDialog(null, "Please Select One or More Cartridges to View Data", "Invalid Action", WARNING_MESSAGE);
    }
}
