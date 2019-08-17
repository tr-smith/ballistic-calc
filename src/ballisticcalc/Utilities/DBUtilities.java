/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ballisticcalc.Utilities;

import ballisticcalc.models.Cartridge;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Database Handling Class
 *
 * @author RibitIII
 */
public class DBUtilities {

    private final String URL = "jdbc:sqlite::resource:" + getClass().getClassLoader().getResource("resources/CartridgeData.db");
    //private static final String URL = "jdbc:sqlite:C:\\Users\\RibitIII\\CartridgeData.db";
    private Cartridge cartridge = new Cartridge();

    /**
     * Connect to the CartridgeData.db database
     *
     * @return the Connection object
     */
    private Connection connect() {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Insert a new row into the Cartridge table
     *
     * @param cd Data Container
     */
    public void insert(Cartridge cd) {
        cartridge = cd;
        String sql = "INSERT INTO Cartridge(Caliber, Manufacturer, BrandName, Weight, MuzzleVelocity, BallisticCoefficient) VALUES(?,?,?,?,?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cartridge.getCaliber());
            pstmt.setString(2, cartridge.getManufacturer());
            pstmt.setString(3, cartridge.getBrandName());
            pstmt.setDouble(4, cartridge.getWeight());
            pstmt.setDouble(5, cartridge.getMuzzleVelocity());
            pstmt.setDouble(6, cartridge.getBallisticCoefficient());
            pstmt.executeUpdate();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param selection - DB select statement
     * @param where - DB where statement
     * @return result set
     */
    public ArrayList<String> distinctSelect(String selection, String where) {
        String sql = "SELECT DISTINCT " + selection + " FROM Cartridge " + where;
        ArrayList<String> data = new ArrayList<>();
        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                data.add(rs.getString(selection));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return data;
    }

    /**
     *
     * @param selection - DB Select statement
     * @param where - DB where statement
     * @return results
     */
    public double[] dataSelect(String selection, String where) {
        String sql = "SELECT " + selection + " FROM Cartridge " + where;
        double[] data = new double[2];
        try (Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                data[0] = Double.parseDouble(rs.getString("MuzzleVelocity"));
                data[1] = Double.parseDouble(rs.getString("BallisticCoefficient"));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return data;
    }
}
