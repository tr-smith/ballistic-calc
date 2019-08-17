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
package ballisticcalc.Utilities;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import javax.swing.JTextField;

/**
 *
 * @author RibitIII
 */
public class NumberFieldVerifier extends InputVerifier {

    double min;
    double max;

    String title = "Invalid Input";
    String messageNum;
    String messageMinMax;
    String message;

    /**
     *
     * @param f
     */
    public NumberFieldVerifier(String f) {
        this.min = -100;

        messageNum = f + " must be numeric";
        

    }
    
    /**
     *
     * @param f
     * @param min
     * @param max
     */
    public NumberFieldVerifier(String f, double min, double max) {
        this.min = min;
        this.max = max;

        messageNum = f + " must be numeric";
        messageMinMax = f + " must be between " + min + " and " + max;

    }

    /**
     *
     * @param jc
     * @return
     */
    @Override
    public boolean verify(JComponent jc) {
        JTextField jt = (JTextField) jc;
        try {
            if (min == -100){
                Double.parseDouble(jt.getText());
            } else if (Double.parseDouble(jt.getText()) < min || Double.parseDouble(jt.getText()) > max) {
                message = messageMinMax;
                return false;
            }
            
        } catch (NumberFormatException e) {
            message = messageNum;
            return false;
        }

        return true;
    }

    /**
     *
     * @param jc
     * @return
     */
    @Override
    public boolean shouldYieldFocus(JComponent jc) {
        if (verify(jc)){
            return true;
        }
        
        JOptionPane.showMessageDialog(null, message, title, WARNING_MESSAGE);
        return false;
    }
    

}
