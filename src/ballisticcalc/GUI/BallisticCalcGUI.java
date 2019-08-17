/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ballisticcalc.GUI;

import ballisticcalc.Enums.*;
import ballisticcalc.Utilities.NumberFieldVerifier;
import ballisticcalc.models.CartridgeDataContainer;
import ballisticcalc.models.CartridgeData;
import ballisticcalc.Service.BallisticCalc;
import ballisticcalc.models.CartridgeRow;
import ballisticcalc.models.ChartData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartTheme;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import static ballisticcalc.Service.Graph.createCustomTheme;

/**
 * Main GUI for BallisticCalc
 *
 * @author RibitIII
 * @version 2.0
 */
public class BallisticCalcGUI extends javax.swing.JFrame {

    private DragModel dragModel = DragModel.G1;
    private CartridgeRow[] cartridgeRows = new CartridgeRow[3];

    private final Dimension chartDimension = new Dimension(1024, 450);

    private javax.swing.JPanel chartPanel;
    private javax.swing.JTextField jADRTextField;
    private javax.swing.JTextField jCrossWindTextField;
    private javax.swing.JTextField jMaxRangeTextField;
    private javax.swing.JTextField jRangeWindTextField;
    private javax.swing.JTextField jSightHeightTextField;
    private javax.swing.JTextField jTemperatureTextField;
    private javax.swing.JTextField jZeroTextField;

    /**
     * Creates new form BallisticCalcGUI
     */
    public BallisticCalcGUI() {

        initComponents();

        ChartTheme chartTheme = createCustomTheme();
        ChartFactory.setChartTheme(chartTheme);

        resetForm();
        showGraph(GraphType.WIND);
    }

    private void initComponents() {

        JRadioButton jG7RadioButton = new javax.swing.JRadioButton();
        JRadioButton jG1RadioButton = new javax.swing.JRadioButton();
        jG1RadioButton.setSelected(true);
        chartPanel = new javax.swing.JPanel();
        jTemperatureTextField = new javax.swing.JTextField();
        jADRTextField = new javax.swing.JTextField();
        jRangeWindTextField = new javax.swing.JTextField();
        jCrossWindTextField = new javax.swing.JTextField();
        jMaxRangeTextField = new javax.swing.JTextField();
        jZeroTextField = new javax.swing.JTextField();
        jSightHeightTextField = new javax.swing.JTextField();

        ButtonGroup dragModelButtonGroup = new ButtonGroup();
        dragModelButtonGroup.add(jG1RadioButton);
        dragModelButtonGroup.add(jG7RadioButton);

        JPanel jPanel1 = new JPanel();
        JPanel jPanel4 = new JPanel();
        JPanel jPanel3 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JPanel jPanel7 = new JPanel();

        JLabel airDensityLabel = new JLabel();
        JLabel temperatureLabel = new JLabel();
        JLabel rangeWindLabel = new JLabel();
        JLabel crossWindLabel = new JLabel();
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        JLabel jLabel12 = new JLabel();
        JLabel jLabel9 = new JLabel();
        JLabel jLabel13 = new JLabel();
        JLabel jLabel14 = new JLabel();
        JLabel sightHeightLabel = new JLabel();
        JLabel maxRangeLabel = new JLabel();
        JLabel zeroRangeLabel = new JLabel();

        JButton jCalcButton = new JButton();
        JButton jDropGraphButton = new JButton();
        JButton jResetButton = new JButton();
        JButton jEnergyGraphButton1 = new JButton();
        JButton jMaxPointBlankRangeButton = new JButton();
        JButton jAddCartridgeButton = new JButton();
        JButton jWindButton = new JButton();

        for (int i = 0; i < cartridgeRows.length; i++){
            cartridgeRows[i] = new CartridgeRow();
        }

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ballistic Calculator v1.0");
        setResizable(false);

        chartPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        chartPanel.setPreferredSize(chartDimension);

        javax.swing.GroupLayout chartPanelLayout = new javax.swing.GroupLayout(chartPanel);
        chartPanel.setLayout(chartPanelLayout);
        chartPanelLayout.setHorizontalGroup(
            chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        chartPanelLayout.setVerticalGroup(
            chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 446, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Weather"));

        jTemperatureTextField.setText("59.0");
        jTemperatureTextField.setInputVerifier(new NumberFieldVerifier("Temperature", -20, 130));
        jTemperatureTextField.setMinimumSize(new java.awt.Dimension(44, 30));
        jTemperatureTextField.setPreferredSize(new java.awt.Dimension(44, 30));

        airDensityLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        airDensityLabel.setLabelFor(jADRTextField);
        airDensityLabel.setText("Air Density Ratio");
        airDensityLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jADRTextField.setText("1");
        jADRTextField.setInputVerifier(new NumberFieldVerifier("Air Density Ratio", 0.7, 1.2));
        setProperties(temperatureLabel, rangeWindLabel, jADRTextField, jTemperatureTextField, "Temperature", jRangeWindTextField);
        rangeWindLabel.setText("Tail/Head Wind");
        rangeWindLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jRangeWindTextField.setText("0");
        jRangeWindTextField.setInputVerifier(new NumberFieldVerifier("Tail/Head Wind", -50, 50));
        jRangeWindTextField.setMinimumSize(new java.awt.Dimension(44, 30));
        jRangeWindTextField.setPreferredSize(new java.awt.Dimension(44, 30));

        jCrossWindTextField.setText("10");
        jCrossWindTextField.setInputVerifier(new NumberFieldVerifier("Cross Wind", -50, 50));
        jCrossWindTextField.setMinimumSize(new java.awt.Dimension(44, 30));
        jCrossWindTextField.setPreferredSize(new java.awt.Dimension(44, 30));

        crossWindLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        crossWindLabel.setLabelFor(jCrossWindTextField);
        crossWindLabel.setText("Cross Wind");
        crossWindLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(crossWindLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rangeWindLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(temperatureLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(airDensityLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCrossWindTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRangeWindTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTemperatureTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jADRTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(temperatureLabel)
                    .addComponent(jTemperatureTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRangeWindTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rangeWindLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCrossWindTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(crossWindLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jADRTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(airDensityLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Cartridge Ballistics"));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Muzzle Velocity");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);



        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setLabelFor(cartridgeRows[0].getBallisticCoefficient());
        jLabel2.setText("B. Coefficient");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);



        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setLabelFor(jCrossWindTextField);
        jLabel12.setText("Weight (gr)");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel9.setText("Manufacturer");
        jLabel13.setText("Brand Name");

        cartridgeRows[0].getCaliber().setFocusTraversalPolicyProvider(true);

        jLabel14.setText("Caliber");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cartridgeRows[0].getCheckBox())
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(cartridgeRows[0].getCaliber(), javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cartridgeRows[0].getManufacturer(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cartridgeRows[0].getBrand(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cartridgeRows[2].getCheckBox())
                            .addComponent(cartridgeRows[1].getCheckBox()))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cartridgeRows[1].getCaliber(), javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cartridgeRows[2].getCaliber(), javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(cartridgeRows[2].getManufacturer(), javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cartridgeRows[2].getBrand(), javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cartridgeRows[1].getManufacturer(), javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cartridgeRows[1].getBrand(), javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cartridgeRows[1].getWeight(), javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cartridgeRows[0].getWeight(), javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cartridgeRows[2].getWeight(), javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(cartridgeRows[0].getMuzzleVelocity(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(cartridgeRows[0].getBallisticCoefficient(), javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(cartridgeRows[1].getMuzzleVelocity(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(cartridgeRows[1].getBallisticCoefficient(), javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(cartridgeRows[2].getMuzzleVelocity(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cartridgeRows[2].getBallisticCoefficient(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, cartridgeRows[0].getBallisticCoefficient(), cartridgeRows[1].getBallisticCoefficient(), cartridgeRows[2].getBallisticCoefficient(), jLabel2, cartridgeRows[0].getMuzzleVelocity(), cartridgeRows[1].getMuzzleVelocity(), cartridgeRows[2].getMuzzleVelocity());

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, cartridgeRows[0].getCaliber(), cartridgeRows[1].getCaliber(), cartridgeRows[2].getCaliber());

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, cartridgeRows[0].getManufacturer(), cartridgeRows[1].getManufacturer(), cartridgeRows[2].getManufacturer());

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, cartridgeRows[0].getBrand(), cartridgeRows[1].getBrand(), cartridgeRows[2].getBrand());

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, cartridgeRows[0].getWeight(), cartridgeRows[1].getWeight(), cartridgeRows[2].getWeight());

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel1)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cartridgeRows[0].getMuzzleVelocity(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cartridgeRows[0].getBallisticCoefficient(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cartridgeRows[0].getCaliber(), javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cartridgeRows[0].getManufacturer(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cartridgeRows[0].getBrand(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cartridgeRows[0].getWeight(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cartridgeRows[0].getCheckBox(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cartridgeRows[1].getManufacturer(), javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cartridgeRows[1].getBrand(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cartridgeRows[1].getWeight(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cartridgeRows[1].getCheckBox(), javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cartridgeRows[1].getMuzzleVelocity(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cartridgeRows[1].getBallisticCoefficient(), javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cartridgeRows[1].getCaliber(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cartridgeRows[2].getBrand(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cartridgeRows[2].getWeight(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cartridgeRows[2].getCheckBox(), javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cartridgeRows[2].getMuzzleVelocity(), javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cartridgeRows[2].getBallisticCoefficient(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cartridgeRows[2].getCaliber(), javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cartridgeRows[2].getManufacturer(), javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, cartridgeRows[0].getBallisticCoefficient(), cartridgeRows[1].getBallisticCoefficient(), cartridgeRows[2].getBallisticCoefficient(), cartridgeRows[0].getBrand(), cartridgeRows[1].getBrand(), cartridgeRows[2].getBrand(), cartridgeRows[0].getWeight(), cartridgeRows[1].getWeight(), cartridgeRows[2].getWeight(), cartridgeRows[0].getCaliber(), cartridgeRows[1].getCaliber(), cartridgeRows[2].getCaliber(), cartridgeRows[0].getMuzzleVelocity(), cartridgeRows[1].getMuzzleVelocity(), cartridgeRows[2].getMuzzleVelocity(), cartridgeRows[0].getManufacturer(), cartridgeRows[1].getManufacturer(), cartridgeRows[2].getManufacturer());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Other Adjustments"));

        sightHeightLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        sightHeightLabel.setText("Sight Height");
        sightHeightLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jMaxRangeTextField.setText("500");
        jMaxRangeTextField.setInputVerifier(new NumberFieldVerifier("Maximum Range", 5, 1000));
        setProperties(maxRangeLabel, zeroRangeLabel, jMaxRangeTextField, jMaxRangeTextField, "Max Range", jZeroTextField);
        zeroRangeLabel.setText("Zero Range");
        zeroRangeLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jZeroTextField.setText("100");
        jZeroTextField.setInputVerifier(new NumberFieldVerifier("Zero Range", 0, 1000));
        jZeroTextField.setMinimumSize(new java.awt.Dimension(44, 30));
        jZeroTextField.setPreferredSize(new java.awt.Dimension(44, 30));

        jSightHeightTextField.setText("jTextField1");
        jSightHeightTextField.setInputVerifier(new NumberFieldVerifier("Sight Height", 0.5, 5.0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(zeroRangeLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(maxRangeLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(sightHeightLabel)
                        .addGap(8, 8, 8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(jMaxRangeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jZeroTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap()))
                    .addComponent(jSightHeightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, jMaxRangeTextField, jSightHeightTextField, jZeroTextField);

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sightHeightLabel)
                    .addComponent(jSightHeightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxRangeLabel)
                    .addComponent(jMaxRangeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zeroRangeLabel)
                    .addComponent(jZeroTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, jMaxRangeTextField, jSightHeightTextField, jZeroTextField);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Drag Model"));

        jG7RadioButton.setText("G7");
        jG7RadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dragModel = DragModel.G7;
            }
        });

        jG1RadioButton.setText("G1");
        jG1RadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dragModel = DragModel.G1;
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jG1RadioButton)
                    .addComponent(jG7RadioButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jG1RadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jG7RadioButton)
                .addContainerGap())
        );

        jCalcButton.setText("Show Data");
        jCalcButton.addActionListener(new ButtonEventHandler(ButtonType.DATA));

        jDropGraphButton.setText("Show Drop");
        jDropGraphButton.addActionListener(new ButtonEventHandler(ButtonType.DROP));

        jResetButton.setText("Reset Form");
        jResetButton.addActionListener(new ButtonEventHandler(ButtonType.RESET));

        jEnergyGraphButton1.setText("Show Energy");
        jEnergyGraphButton1.addActionListener(new ButtonEventHandler(ButtonType.ENERGY));

        jMaxPointBlankRangeButton.setText("Find MPBR");
        jMaxPointBlankRangeButton.addActionListener(new ButtonEventHandler(ButtonType.MPBR));

        jAddCartridgeButton.setText("Add Cartridges");
        jAddCartridgeButton.addActionListener(new ButtonEventHandler(ButtonType.ADD_CARTRIDGE));

        jWindButton.setText("Show Wind");
        jWindButton.addActionListener(new ButtonEventHandler(ButtonType.WIND));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCalcButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(338, 338, 338))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDropGraphButton, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jEnergyGraphButton1)
                    .addComponent(jMaxPointBlankRangeButton)
                    .addComponent(jAddCartridgeButton)
                    .addComponent(jWindButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, jAddCartridgeButton, jCalcButton, jDropGraphButton, jEnergyGraphButton1, jMaxPointBlankRangeButton, jResetButton, jWindButton);

        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCalcButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jDropGraphButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jWindButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jEnergyGraphButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jMaxPointBlankRangeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jAddCartridgeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jResetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel7Layout.linkSize(javax.swing.SwingConstants.VERTICAL, jAddCartridgeButton, jCalcButton, jDropGraphButton, jEnergyGraphButton1, jMaxPointBlankRangeButton, jResetButton, jWindButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(chartPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1132, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(chartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(null);
    }

    private void setProperties(JLabel temperatureLabel, JLabel rangeWindLabel, JTextField jADRTextField, JTextField jTemperatureTextField, String temperature, JTextField jRangeWindTextField) {
        jADRTextField.setMinimumSize(new Dimension(44, 30));
        jADRTextField.setPreferredSize(new Dimension(44, 30));

        temperatureLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        temperatureLabel.setLabelFor(jTemperatureTextField);
        temperatureLabel.setText(temperature);
        temperatureLabel.setHorizontalTextPosition(SwingConstants.RIGHT);

        rangeWindLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        rangeWindLabel.setLabelFor(jRangeWindTextField);
    }

    private CartridgeDataContainer collectCartridgeData() {
        CartridgeDataContainer cdc =  new CartridgeDataContainer();

        for (CartridgeRow row : cartridgeRows){
            if (row.getCheckBox().isSelected()) {
                CartridgeData cart = setSelectedFields(row);
                cdc.getCartridgeDataList().add(cart);
            }
        }
        return cdc;
    }

    private void resetForm() {
        jSightHeightTextField.setText("1.5");
        jADRTextField.setText("1.0");
        jTemperatureTextField.setText("59.0");
        jMaxRangeTextField.setText("500");
        jZeroTextField.setText("100");
        jRangeWindTextField.setText("0");
        jCrossWindTextField.setText("0");
        for (CartridgeRow row : cartridgeRows) {
            row.initializeComponents();
        }
        showGraph(GraphType.WIND);
    }

    private BallisticCalc collectFormData() {
        BallisticCalc ballisticCalc = new BallisticCalc();
        ballisticCalc.setDragModel(dragModel);
        ballisticCalc.setAirDensityRatio(Double.parseDouble(jADRTextField.getText()));
        ballisticCalc.setCrossWind(Double.parseDouble(jCrossWindTextField.getText()));

        ballisticCalc.setRangeWind(Double.parseDouble(jRangeWindTextField.getText()));
        ballisticCalc.setSightHeightInches(Double.parseDouble(jSightHeightTextField.getText()));
        ballisticCalc.setTemperature(Double.parseDouble(jTemperatureTextField.getText()));
        return ballisticCalc;
    }

    private void showGraph(GraphType graphType) {
        JPanel replaceGraph = graphType.getGraph(new CartridgeDataContainer());
        GroupLayout layout = (GroupLayout) getContentPane().getLayout();
        layout.replace(chartPanel, replaceGraph);
        chartPanel = replaceGraph;

    }

    private CartridgeData setSelectedFields(CartridgeRow row) {
        CartridgeData cartridge = new CartridgeData();
        cartridge.setMuzzleVelocity(Double.parseDouble(row.getMuzzleVelocity().getText()));
        cartridge.setBallisticCoefficient(Double.parseDouble(row.getBallisticCoefficient().getText()));
        cartridge.setWeight(Double.parseDouble(row.getWeight().getSelectedItem().toString()));
        cartridge.setCaliber(row.getCaliber().getSelectedItem().toString());
        cartridge.setManufacturer(row.getManufacturer().getSelectedItem().toString());
        cartridge.setBrandName(row.getBrand().getSelectedItem().toString());
        cartridge.setMaxRangeMPBR(Double.parseDouble(jMaxRangeTextField.getText()));
        cartridge.setZeroMPBR(Double.parseDouble(jZeroTextField.getText()));
        return cartridge;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BallisticCalcGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                new BallisticCalcGUI().setVisible(true);
            }
        });
    }

    private void replaceGraph(JPanel graph) {
        GroupLayout layout = (GroupLayout) getContentPane().getLayout();
        layout.replace(chartPanel, graph);
       chartPanel = graph;
    }

    private void showMbprData(CartridgeDataContainer cartridgeDataContainer, double mpbr) {
        MPBROutputGUI dialog = new MPBROutputGUI(this, "MPBR Properties", true, mpbr, cartridgeDataContainer);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                dialog.dispose();
            }
        });

        dialog.setVisible(true);
    }

    class ButtonEventHandler implements ActionListener {
        private ButtonType buttonType;


        public ButtonEventHandler(ButtonType buttonType) {
            this.buttonType = buttonType;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            CartridgeDataContainer cdc = collectCartridgeData();
            cdc.setBallisticCalc(collectFormData());
            ChartData chartData = buttonType.buttonEvent(cdc, checkSelected(cdc));
            if (chartData.getChart() != null) {
                replaceGraph(chartData.getChart());
                if (ButtonType.MPBR.equals(buttonType)){
                    showMbprData(chartData.getCartridgeDataContainer(), chartData.getMpbr());
                }
            }
            if (ButtonType.ADD_CARTRIDGE.equals(buttonType) || ButtonType.RESET.equals(buttonType)){
                resetForm();
            }

        }

        private boolean checkSelected(CartridgeDataContainer cdc){
            for (CartridgeRow row : cartridgeRows){
                if (row.getCheckBox().isSelected()){
                    return true;
                }
            }
            return false;
        }
    }
}
