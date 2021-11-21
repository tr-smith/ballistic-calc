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

import ballisticcalc.Service.BallisticCalc;

import java.util.ArrayList;

/**
 *
 * @author RibitIII
 */
public class CartridgeDataContainer {
    
    private ArrayList<CartridgeData> cartridgeDataList = new ArrayList<>();
    private BallisticCalc ballisticCalc;
    
    
    /**
     * Container for ArrayList of Cartridge Data
     */
    public CartridgeDataContainer() {
    }

    /**
     *
     * @return ArrayList of CartridgeData
     */
    public ArrayList<CartridgeData> getCartridgeDataList() {
        return cartridgeDataList;
    }

    /**
     *
     * @param cartridge
     */
    public void setCartridgeDataList(ArrayList<CartridgeData> cartridge) {
        this.cartridgeDataList = cartridge;
    }

    public BallisticCalc getBallisticCalc() {
        return ballisticCalc;
    }

    public void setBallisticCalc(BallisticCalc ballisticCalc) {
        this.ballisticCalc = ballisticCalc;
    }
}
