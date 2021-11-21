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
public class TextFieldVerifier extends InputVerifier{
    private final String title = "Invalid Input";
    private final String messageText;

    /**
     *
     * @param field
     */
    public TextFieldVerifier(String field) {
        messageText = field + " Must not be Empty";
    }

    /**
     *
     * @param jc
     * @return
     */
    @Override
    public boolean verify(JComponent jc) {
        return !((JTextField)jc).getText().equals("");
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
        
        JOptionPane.showMessageDialog(null, messageText, title, WARNING_MESSAGE);
        
        return false;
        
    }
}
