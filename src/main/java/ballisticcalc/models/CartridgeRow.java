package ballisticcalc.models;

import ballisticcalc.Enums.Column;
import ballisticcalc.GUI.BallisticCalcGUI;
import ballisticcalc.Utilities.DBUtilities;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CartridgeRow {
    private JCheckBox checkBox = new JCheckBox();
    private JComboBox<String> caliber = new JComboBox<>();
    private JComboBox<String> manufacturer = new JComboBox<>();
    private JComboBox<String> brand = new JComboBox<>();
    private JComboBox<String> weight = new JComboBox<>();
    private JTextField muzzleVelocity= new JTextField();
    private JTextField ballisticCoefficient = new JTextField();

    public CartridgeRow() {
        initializeComponents();
    }

    public void initializeComponents() {
        checkBox.setSelected(false);

        caliber.removeAllItems();
        caliber.addItem("Select Caliber");
        caliber.addActionListener(new ComboBoxEventHandler(this, Column.CALIBER));

        manufacturer.removeAllItems();
        manufacturer.addItem("Select Manufacturer");
        manufacturer.setEnabled(false);
        manufacturer.setMinimumSize(new java.awt.Dimension(200, 25));
        manufacturer.setPreferredSize(new java.awt.Dimension(200, 25));
        manufacturer.addActionListener(new ComboBoxEventHandler(this, Column.MANU));

        brand.removeAllItems();
        brand.addItem("Select Brand Name");
        brand.setEnabled(false);
        brand.setMinimumSize(new java.awt.Dimension(200, 25));
        brand.setPreferredSize(new java.awt.Dimension(200, 25));
        brand.addActionListener(new ComboBoxEventHandler(this, Column.BRAND));

        weight.removeAllItems();
        weight.addItem("Select Weight");
        weight.setEnabled(false);
        weight.setMinimumSize(new java.awt.Dimension(100, 25));
        weight.setPreferredSize(new java.awt.Dimension(100, 25));
        weight.addActionListener(new ComboBoxEventHandler(this, Column.WEIGHT));

        setProperties(muzzleVelocity);

        setProperties(ballisticCoefficient);




        populateCaliber();

    }

    private void setProperties(JTextField ballisticCoefficient) {
        ballisticCoefficient.setText("0");
        ballisticCoefficient.setEditable(false);
        ballisticCoefficient.setHorizontalAlignment(JTextField.CENTER);
        ballisticCoefficient.setText("0");
        ballisticCoefficient.setMinimumSize(new java.awt.Dimension(72, 30));
        ballisticCoefficient.setPreferredSize(new java.awt.Dimension(75, 25));
    }

    private void populateCaliber() {
        DBUtilities db = new DBUtilities();
        ArrayList<String>  caliberList = db.distinctSelect("Caliber", " ORDER BY Caliber");
        caliberList.stream().forEach((s) -> {
            caliber.addItem(s);
        });
    }

    public JCheckBox getCheckBox() {
        return checkBox;
    }

    public JComboBox<String> getCaliber() {
        return caliber;
    }

    public JComboBox<String> getManufacturer() {
        return manufacturer;
    }

    public JComboBox<String> getBrand() {
        return brand;
    }

    public JComboBox<String> getWeight() {
        return weight;
    }

    public JTextField getMuzzleVelocity() {
        return muzzleVelocity;
    }

    public JTextField getBallisticCoefficient() {
        return ballisticCoefficient;
    }

    public class ComboBoxEventHandler implements ActionListener {
        private CartridgeRow cartridgeRow;
        private Column column;

        public ComboBoxEventHandler(CartridgeRow cartridgeRow, Column column){
            this.cartridgeRow = cartridgeRow;
            this.column = column;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            column.getSelections(cartridgeRow);
        }
    }


}
