package ballisticcalc.Enums;

import static Constants.ConstantsInterface.*;

public enum DragModel {

    G1 {
        @Override
        public double dragCalc(double x1) {
            int i = 0;

            while (x1 > G1_MACH[i + 1]) {
                i++;
            }
            double s = (G1_DRAG[i + 1] - G1_DRAG[i]) / (G1_MACH[i + 1] - G1_MACH[i]);
            return G1_DRAG[i] + s * (x1 - G1_MACH[i]);
        }
    },
    G7{
        @Override
        public double dragCalc(double x1) {
            int i = 0;
            double x2 = 0.0;
            while (x1 > G7_MACH[i + 1]) {
                i++;
            }
            double s = (G7_DRAG[i + 1] - G7_DRAG[i]) / (G7_MACH[i + 1] - G7_MACH[i]);
            return G7_DRAG[i] + s * (x1 - G7_MACH[i]);
    }};

    public abstract double dragCalc(double x1);
}
