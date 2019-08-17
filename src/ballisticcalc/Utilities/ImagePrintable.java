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

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

/**
 *
 * @author RibitIII
 */
public class ImagePrintable implements Printable {

    private BufferedImage image;
    private int pageCount;

    /**
     *
     * @param im
     * @param pc
     */
    public ImagePrintable(BufferedImage im, int pc) {
        pageCount = pc;
        image = im;
    }

    /**
     *
     * @param g
     * @param pageFormat
     * @param pageIndex
     * @return
     * @throws PrinterException
     */
    @Override

    public int print(Graphics g, PageFormat pageFormat, int pageIndex) throws PrinterException {

        if (pageIndex >= pageCount) {
            return Printable.NO_SUCH_PAGE;
        } else {
            

            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            g2d.translate(0, -(pageIndex * pageFormat.getImageableHeight()));

            
            g2d.drawImage(image, 0, 0, null);

            g2d.dispose();
            return Printable.PAGE_EXISTS;
        }

    }

}
