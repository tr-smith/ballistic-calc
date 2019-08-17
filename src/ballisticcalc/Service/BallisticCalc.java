/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ballisticcalc.Service;

import Constants.ConstantsInterface;
import ballisticcalc.Enums.DragModel;
import ballisticcalc.models.CartridgeData;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Trajectory and Energy Calculator
 * 
 * Data That is calculated over the range:
 * Drop, Velocity, Wind Deflection, Energy, Time, Vx, Vy and Vz
 * 
 *
 * @author RibitIII
 */
public class BallisticCalc implements ConstantsInterface {
    
    private double muzzleVelocity, ballisticCoefficient, sightHeightInches,
            airDensityRatio, temperature, zeroRange, zeroHeightInches, maxRange,
            rangeWind, crossWind, sightHeightFeet, zeroHeightFeet, initialVelocityX,
            initialVelocityY, initialVelocityZ, velocityX, velocityY, velocityZ,
            boreAngle, bulletWeight;
    private double[] range, drop, windDeflection, totalVelocity, time,
            velocityRangeVx, velocityVerticalVy, velocityHorizontallVz,
            boreElevationAdjust, boreHeightAdjust, muzzleEnergyTv;

    private int dataIndexCount, boreAdjustCount;
    private DragModel dragModel = DragModel.G1;
    private double rangeCountYard, windCount, timeCount;
    private boolean zeroYN;

    // Arbitrary variable for calculations
    private double c3;
    private double b1;
    private double t0;
    private double v1;
    private double x1;
    private double c4;
    private double a1;
    private double a2;
    private double a3;
    private double b2;
    private double u;
    private double c5;
    private double a4;
    private double a5;
    private double a6;
    private double e2;
    private double v2;
    private double zeroErrorCheck;

    /**
     *
     * @param cd
     * @return 
     */
    public CartridgeData startCalc(CartridgeData cd) {
        int len = (int)cd.getMaxRangeMPBR() + 1;
        muzzleVelocity = cd.getMuzzleVelocity();
        maxRange = cd.getMaxRangeMPBR();
        bulletWeight = cd.getWeight();
        ballisticCoefficient = cd.getBallisticCoefficient();
        zeroRange = cd.getZeroMPBR();
        range = new double[len];
        drop = new double[len];
        windDeflection = new double[len];
        totalVelocity = new double[len];
        time = new double[len];
        velocityRangeVx = new double[len];
        velocityVerticalVy = new double[len];
        velocityHorizontallVz = new double[len];
        boreElevationAdjust = new double[22];
        boreHeightAdjust = new double[22];
        muzzleEnergyTv = new double[len];

        // Initialize Trajectory values that don't reset
        dataIndexCount = 1;
        boreAdjustCount = 0;
        boreAngle = 0; 
        // sightHeightInchesOutput = sightHeightInches; <--- for display of sight height only
        //sightHeightInches = -(sightHeightInches);
        zeroHeightFeet = zeroHeightInches / 12;
        rangeWind = (22 * rangeWind) / 15;
        crossWind = (22 * crossWind) / 15;

        if (zeroRange > 0) {
            zeroYN = true;
        } else {
            zeroYN = false;
            zeroRange = maxRange;
        }

        // Do one iteration 
        TrajCalc();

        // Check for zero range adjustment
        if (zeroYN) {
            do {
                zeroErrorCheck = Math.abs(sightHeightFeet - zeroHeightFeet); // Check Margin of error
                if (zeroErrorCheck >= ERROR_MARGIN) {
                    AdjustBoreAngle();
                    TrajCalc();
                } else {
                    zeroYN = false;
                    zeroRange = maxRange;
                    TrajCalc();
                }
            } while (zeroYN);
        }
        cd.setDrop(drop);
        cd.setEnergy(muzzleEnergyTv);
        cd.setWind(windDeflection);
        cd.setVelocity(totalVelocity);
        return cd;
        //PrintData();

    }

    private void TrajCalc() {
        initialVelocityX = muzzleVelocity * cos(boreAngle / 3437.74677);
        initialVelocityY = muzzleVelocity * sin(boreAngle / 3437.74677);
        initialVelocityZ = 0;
        rangeCountYard = 0;
        sightHeightFeet = -(sightHeightInches) / 12;
        windCount = 0;
        timeCount = 0;

        if (!zeroYN) {

            // Fill in first element 
            range[0] = rangeCountYard;
            drop[0] = -(sightHeightInches);
            windDeflection[0] = windCount;
            totalVelocity[0] = muzzleVelocity;
            time[0] = timeCount;
            velocityRangeVx[0] = initialVelocityX;
            velocityVerticalVy[0] = initialVelocityY;
            velocityHorizontallVz[0] = initialVelocityZ;
            muzzleEnergyTv[0] = (Math.pow(muzzleVelocity, 2) * bulletWeight) / 450240;
        }

        c3 = (BAROMETRIC_FACTOR * airDensityRatio) / ballisticCoefficient;
        b1 = Math.sqrt(Math.pow((initialVelocityX - rangeWind), 2.0) + Math.pow(initialVelocityY, 2.0)
                + Math.pow((initialVelocityZ - crossWind), 2.0));
        t0 = (temperature + 459.67) * Math.exp(TEMPERATURE_FACTOR * sightHeightFeet) - 459.67;
        v1 = VELOCITY_TEMPERATURE_FACTOR * Math.sqrt(t0 + 459.67);
        x1 = b1 / v1;

        c4 = (c3 * dragModel.dragCalc(x1) * b1 * Math.exp(HUMIDITY_FACTOR * sightHeightFeet)) / initialVelocityX;
        a1 = c4 * (initialVelocityX - rangeWind);
        a2 = c4 * initialVelocityY - GRAVITY / initialVelocityX;
        a3 = c4 * (initialVelocityZ - crossWind);

        // APPLY EULER PREDICTOR FORMULA until conditions met
        do {
            EulerPredictFormula();
            do {
                // Apply Huen Corrector
                HuenCorrector();
                e2 = Math.abs((b2 - u) / b2);
            } while (e2 > ERROR_MARGIN);

            // COMPUTE VALUES AT RangeCountFeetAdjust.
            sightHeightFeet = sightHeightFeet + ((initialVelocityY + velocityY) / (initialVelocityX + velocityX)) * YARD_AS_FEET;
            windCount = windCount + ((initialVelocityZ + velocityZ) / (initialVelocityX + velocityX)) * YARD_AS_FEET;
            timeCount = timeCount + (2 * YARD_AS_FEET) / (initialVelocityX + velocityX);
            v2 = Math.sqrt(Math.pow(velocityX, 2) + Math.pow(velocityY, 2) + Math.pow(velocityZ, 2));

            // RESET CONDITIONS AT RangeCountFeet TO NEW CONDITIONS AT RangeCountFeetAdjust.
            initialVelocityX = velocityX;
            initialVelocityY = velocityY;
            initialVelocityZ = velocityZ;
            a1 = a4;
            a2 = a5;
            a3 = a6;

            // CHECK STATUS OF ZeroRange CONDITIONS. if not met, don't fill array
            if (!zeroYN) {

                FillDataArray();
            }
        } while (rangeCountYard < zeroRange); // STOP TRAJECTORY CALCULATION

    }

    private void EulerPredictFormula() {
        rangeCountYard = rangeCountYard + DISTANCE_STEP;
        velocityX = initialVelocityX + a1 * YARD_AS_FEET;
        velocityY = initialVelocityY + a2 * YARD_AS_FEET;
        velocityZ = initialVelocityZ + a3 * YARD_AS_FEET;
        b2 = Math.sqrt(Math.pow((velocityX - rangeWind), 2) + Math.pow(velocityY, 2) + Math.pow((velocityZ - crossWind), 2));

    }

    private void HuenCorrector() {
        u = b2;
        t0 = (temperature + 459.67) * Math.exp(TEMPERATURE_FACTOR * sightHeightFeet) - 459.67;
        v1 = VELOCITY_TEMPERATURE_FACTOR * Math.sqrt(t0 + 459.67);
        x1 = b2 / v1;
        c5 = (c3 * dragModel.dragCalc(x1) * b2 * Math.exp(HUMIDITY_FACTOR * sightHeightFeet)) / velocityX;
        a4 = c5 * (velocityX - rangeWind);
        a5 = c5 * velocityY - GRAVITY / velocityX;
        a6 = c5 * (velocityZ - crossWind);
        velocityX = initialVelocityX + .5 * (a1 + a4) * YARD_AS_FEET;
        velocityY = initialVelocityY + .5 * (a2 + a5) * YARD_AS_FEET;
        velocityZ = initialVelocityZ + .5 * (a3 + a6) * YARD_AS_FEET;
        b2 = Math.sqrt(Math.pow((velocityX - rangeWind), 2) + Math.pow(velocityY, 2) + Math.pow((velocityZ - crossWind), 2));

    }

    private void FillDataArray() {
        range[dataIndexCount] = rangeCountYard;
        drop[dataIndexCount] = 12 * sightHeightFeet;     // Convert to inches
        windDeflection[dataIndexCount] = 12 * windCount; // Convert to inches
        totalVelocity[dataIndexCount] = v2;
        time[dataIndexCount] = timeCount;
        velocityRangeVx[dataIndexCount] = velocityX;
        velocityVerticalVy[dataIndexCount] = velocityY;
        velocityHorizontallVz[dataIndexCount] = velocityZ;

        muzzleEnergyTv[dataIndexCount] = (Math.pow(v2, 2) * bulletWeight) / 450240;

        dataIndexCount++;

    }

    private void AdjustBoreAngle() {
        // ALGORITHM TO ADJUST ELEVATION ANGLE
        boreAdjustCount++;
        boreElevationAdjust[boreAdjustCount] = boreAngle;
        boreHeightAdjust[boreAdjustCount] = sightHeightFeet;
        if (boreAdjustCount < 2) {
            boreElevationAdjust[boreAdjustCount + 1] = boreAngle + 2.0;
        } else if (boreAdjustCount < 20) {
            boreElevationAdjust[boreAdjustCount + 1] = boreElevationAdjust[boreAdjustCount]
                    + (zeroHeightFeet - boreHeightAdjust[boreAdjustCount]) * (boreElevationAdjust[boreAdjustCount - 1]
                    - boreElevationAdjust[boreAdjustCount]) / (boreHeightAdjust[boreAdjustCount - 1]
                    - boreHeightAdjust[boreAdjustCount]);
        }
        boreAngle = boreElevationAdjust[boreAdjustCount + 1];
    }

    private void PrintData() {
        System.out.printf("Range\t Drop\t Wind\tVelocity   Time\t    Vx\t     Vy\t    Vz\tEnergyVx\tEnergy Tv\n");
        for (int i = 0; i <= maxRange + 1; i = (int) (i + 25)) {
            System.out.printf("%4.0f\t%+2.2f\t% 2.2f\t %4.1f\t   %01.2f    %04.1f   %4.1f   %4.1f\t %04.1f\n", range[i], drop[i],
                    windDeflection[i], totalVelocity[i], time[i], velocityRangeVx[i],
                    velocityVerticalVy[i], velocityHorizontallVz[i], muzzleEnergyTv[i]);

        }
    }
    


    /**
     *
     * @param sightHeightInches - Sight Height
     */
    public void setSightHeightInches(double sightHeightInches) {
        this.sightHeightInches = sightHeightInches;
    }    

    /**
     *
     * @param airDensityRatio - Air Density Ratio
     */
    public void setAirDensityRatio(double airDensityRatio) {
        this.airDensityRatio = airDensityRatio;
    }    

    /**
     *
     * @param temperature - Temperature
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }    


    /**
     *
     * @param maxRange - Max Range Yardage
     */
    public void setMaxRange(double maxRange) {
        this.maxRange = maxRange;
    }

    public double getMaxRange() {
        return maxRange;
    }

    /**
     *
     * @param rangeWind - Head Tail wind Speed
     */
    public void setRangeWind(double rangeWind) {
        this.rangeWind = rangeWind;
    }
    

    /**
     *
     * @param crossWind - Cross Wind Speed
     */
    public void setCrossWind(double crossWind) {
        this.crossWind = crossWind;
    }
 

    /**
     *
     * @param dragModel - Drag Model to Use
     */
    public void setDragModel(DragModel dragModel) {
        this.dragModel = dragModel;
    }


}
