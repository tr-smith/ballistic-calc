/*
 * Copyright (C) 2017 RibitIII
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ballisticcalc.models;

/**
 *
 * @author RibitIII
 */
public class CartridgeData extends Cartridge {
    
    private double[] drop = null, energy = null, wind = null, velocity = null;
    private double zeroMPBR, maxRangeMPBR;

    /**
     * ArrayList that holds calculated data for each Cartridge adding to inherited fields
     * from Cartridge
     * 
     */
    public CartridgeData() {
        super();
    }

    /**
     *
     * @return drop data array
     */
    public double[] getDrop() {
        return drop;
    }

    /**
     *
     * @param drop
     */
    public void setDrop(double[] drop) {
        this.drop = drop;
    }

    /**
     *
     * @return energy data array
     */
    public double[] getEnergy() {
        return energy;
    }

    /**
     *
     * @param energy
     */
    public void setEnergy(double[] energy) {
        this.energy = energy;
    }

    /**
     *
     * @return wind data array
     */
    public double[] getWind() {
        return wind;
    }

    /**
     *
     * @param wind
     */
    public void setWind(double[] wind) {
        this.wind = wind;
    }

    /**
     *
     * @return Used for both zeroRange and zeroMPBR
     */
    public double getZeroMPBR() {
        return zeroMPBR;
    }

    /**
     *
     * @param zeroMPBR
     */
    public void setZeroMPBR(double zeroMPBR) {
        this.zeroMPBR = zeroMPBR;
    }

    /**
     *
     * @return
     */
    public double getMaxRangeMPBR() {
        return maxRangeMPBR;
    }

    /**
     *
     * @param maxRangeMPBR
     */
    public void setMaxRangeMPBR(double maxRangeMPBR) {
        this.maxRangeMPBR = maxRangeMPBR;
    }

    /**
     *
     * @return
     */
    public double[] getVelocity() {
        return velocity;
    }

    /**
     *
     * @param velocity
     */
    public void setVelocity(double[] velocity) {
        this.velocity = velocity;
    }
        
}
