/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ballisticcalc.models;

/**
 * Class to hold Ballistic data in single Object
 *
 * @author RibitIII
 */
public class Cartridge {

    private String caliber, manufacturer, brandName;
    private double weight, ballisticCoefficient, muzzleVelocity;

    /**
     * Base Class to hold Cartridge Data in one object. DBUtilities uses this class
     * to add new cartridges to the database. CartridgeData uses this as Base Class.
     */
    public Cartridge() {
    }

    /**
     *
     * @return Caliber
     */
    public String getCaliber() {
        return caliber;
    }

    /**
     *
     * @param caliber - Caliber
     */
    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }

    /**
     *
     * @return Manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     *
     * @param manufacturer - Manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     *
     * @return Brand Name
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     *
     * @param brandName - Brand Name
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     *
     * @return Bullet Weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     *
     * @param weight - Bullet Weight
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     *
     * @return - Ballistic Coefficient
     */
    public double getBallisticCoefficient() {
        return ballisticCoefficient;
    }

    /**
     *
     * @param ballisticCoefficient - Ballistic Coefficient
     */
    public void setBallisticCoefficient(double ballisticCoefficient) {
        this.ballisticCoefficient = ballisticCoefficient;
    }

    /**
     *
     * @return Muzzle Velocity
     */
    public double getMuzzleVelocity() {
        return muzzleVelocity;
    }

    /**
     *
     * @param muzzleVelocity - Muzzle Velocity
     */
    public void setMuzzleVelocity(double muzzleVelocity) {
        this.muzzleVelocity = muzzleVelocity;
    }

}
