package ballisticcalc.Enums;

import ballisticcalc.Utilities.DBUtilities;
import ballisticcalc.models.CartridgeRow;

import java.util.ArrayList;
import java.util.List;

public enum Column {
    CALIBER {
        @Override
        public void getSelections(CartridgeRow cartridgeRow) {
            cartridgeRow.getCheckBox().setSelected(false);
            cartridgeRow.getCheckBox().setEnabled(false);

            cartridgeRow.getManufacturer().setEnabled(false);
            cartridgeRow.getWeight().setEnabled(false);
            cartridgeRow.getBrand().setEnabled(false);

            if (cartridgeRow.getCaliber().getSelectedIndex() > 0) {
                cartridgeRow.getManufacturer().removeAllItems();
                cartridgeRow.getManufacturer().addItem("Select Manufacturer");
                String selection = cartridgeRow.getCaliber().getSelectedItem().toString();
                List<String> manuList = populateComboBox("Manufacturer", "WHERE Caliber = '" + selection + "' ORDER BY Manufacturer");
                manuList.stream().forEach((s) -> {
                    cartridgeRow.getManufacturer().addItem(s);
                });
                cartridgeRow.getManufacturer().setEnabled(true);
            }
        }
    },

    MANU {
        @Override
        public void getSelections(CartridgeRow cartridgeRow) {
            cartridgeRow.getCheckBox().setSelected(false);
            cartridgeRow.getCheckBox().setEnabled(false);

            cartridgeRow.getWeight().setEnabled(false);
            cartridgeRow.getBrand().setEnabled(false);

            if (cartridgeRow.getManufacturer().getSelectedIndex() > 0) {

                cartridgeRow.getBrand().removeAllItems();
                cartridgeRow.getBrand().addItem("Select Brand Name");

                String manu = cartridgeRow.getManufacturer().getSelectedItem().toString();
                String caliber = cartridgeRow.getCaliber().getSelectedItem().toString();
                List<String> brandList = populateComboBox("BrandName", "WHERE Manufacturer = '" + manu + "' AND "
                        + "Caliber = '" + caliber + "' ORDER BY BrandName");
                brandList.stream().forEach((s) -> {
                    cartridgeRow.getBrand().addItem(s);
                });
                cartridgeRow.getBrand().setEnabled(true);
            }
        }
    },

    BRAND {
        @Override
        public void getSelections(CartridgeRow cartridgeRow) {
            cartridgeRow.getCheckBox().setSelected(false);
            cartridgeRow.getCheckBox().setEnabled(false);

            cartridgeRow.getWeight().setEnabled(false);

            if (cartridgeRow.getBrand().getSelectedIndex() > 0) {

                cartridgeRow.getWeight().removeAllItems();
                cartridgeRow.getWeight().addItem("Select Weight");

                String manu = cartridgeRow.getManufacturer().getSelectedItem().toString();
                String brand = cartridgeRow.getBrand().getSelectedItem().toString();
                String caliber = cartridgeRow.getCaliber().getSelectedItem().toString();
                List<String> weightList = populateComboBox("Weight", "WHERE Manufacturer = '" + manu + "' AND "
                        + "BrandName = '" + brand + "' AND Caliber = '" + caliber + "' ORDER BY Weight");
                weightList.stream().forEach((s) -> {
                    cartridgeRow.getWeight().addItem(s);
                });
                cartridgeRow.getWeight().setEnabled(true);
            }
        }
    },

    WEIGHT {
        @Override
        public void getSelections(CartridgeRow cartridgeRow) {
            if (cartridgeRow.getWeight().getSelectedIndex() > 0) {
                String manu = cartridgeRow.getManufacturer().getSelectedItem().toString();
                String brand = cartridgeRow.getBrand().getSelectedItem().toString();
                String weight = cartridgeRow.getWeight().getSelectedItem().toString();
                String selection = "MuzzleVelocity, BallisticCoefficient";
                String where = "WHERE Manufacturer = '" + manu + "' AND BrandName = '"
                        + brand + "' AND Weight = '" + weight + "'";
                double[] data;
                data = db.dataSelect(selection, where);
                cartridgeRow.getMuzzleVelocity().setText(String.valueOf(data[0]));
                cartridgeRow.getBallisticCoefficient().setText(String.valueOf(data[1]));
                cartridgeRow.getCheckBox().setEnabled(true);
            } else {
                cartridgeRow.getCheckBox().setEnabled(false);
            }
        }
    };
    private static final DBUtilities db = new DBUtilities();


    public abstract void getSelections(CartridgeRow cartridgeRow);

    private static ArrayList<String> populateComboBox(String selection, String where) {
        ArrayList<String> arrayList = db.distinctSelect(selection, where);
        return arrayList;
    }


}
