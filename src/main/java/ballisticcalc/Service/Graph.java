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
package ballisticcalc.Service;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Paint;
import java.awt.Stroke;

import org.jfree.chart.ChartTheme;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.DefaultDrawingSupplier;

/**
 *
 * @author RibitIII
 */
public class Graph {

    
    private Graph(){
    }

    /**
     *
     * @return Chart Theme
     */
    public static ChartTheme createCustomTheme() {
        StandardChartTheme theme = new StandardChartTheme("Custom");
        theme.setExtraLargeFont(new Font("Tahoma", Font.BOLD, 10));
        theme.setLargeFont(new Font("Tahoma", Font.BOLD, 10));
        theme.setRegularFont(new Font("Tahoma", Font.PLAIN, 10));
        theme.setSmallFont(new Font("Tahoma", Font.PLAIN, 10));

        theme.setChartBackgroundPaint(Color.black);
        theme.setPlotBackgroundPaint(Color.black);
        theme.setPlotOutlinePaint(Color.WHITE);
        theme.setBaselinePaint(Color.CYAN);

        theme.setTickLabelPaint(Color.white);
        theme.setAxisLabelPaint(Color.white);

        theme.setDrawingSupplier(new DefaultDrawingSupplier(
                new Paint[]{Color.decode("0xFFFF00"),
                    Color.decode("0x0036CC"), Color.decode("0xFF0000"),
                    Color.decode("0xFFFF7F"), Color.decode("0x6681CC"),
                    Color.decode("0xFF7F7F"), Color.decode("0xFFFFBF"),
                    Color.decode("0x99A6CC"), Color.decode("0xFFBFBF"),
                    Color.decode("0xA9A938"), Color.decode("0x2D4587")},
                new Paint[]{Color.decode("0xFFFF00"),
                    Color.decode("0x0036CC")},
                new Stroke[]{new BasicStroke(1.5f)},
                new Stroke[]{new BasicStroke(0.5f)},
                DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));

        theme.setDomainGridlinePaint(Color.white);

        return theme;
    }
}
