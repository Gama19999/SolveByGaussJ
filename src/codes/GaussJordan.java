package codes;

import javax.swing.JOptionPane;

/**
 * Algoritmo que te permite resolver ecuaciones lineales mediante el metodo de gauss
 */

public class GaussJordan {

    public static void main(String[] args) {
        // variables
        int i, j, s, k, h, n;
        double d;
        double[][] m;
        double[] r;
        double[] x;

        n = Integer.parseInt(JOptionPane.showInputDialog("Introduce el numero de incognitas"));
        m = new double[n][n];
        r = new double[n];
        x = new double[n];

        for (i = 0; i <= n-1; i++) {
            k = i + 1;
            r[i] = Double.parseDouble(JOptionPane.showInputDialog("Introduzca la solucion " + k));
            x[i] = 0;

            for (j = 0; j <= n-1; j++) {
                h = j + 1;
                m[i][j] = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el elemento " + k + "," + h + " de la matriz de coeficientes"));
            }
        }

        for (i = 0; i < n; i++) {
            for (j = i; j < n; j++) {

                if (i == j) {
                    d = m[i][j];

                    for (s = 0; s < n; s++) {
                        m[i][s] = ((m[i][s]) / d);
                    }

                    r[i] = ((r[i]) / d);
                } else {
                    d = m[j][i];

                    for (s = 0; s < n; s++) {
                        m[j][s] = m[j][s] - (d * m[i][s]);
                    }

                    r[j] = r[j] - (d * r[i]);
                }

            }

        }

        for (i = n - 1; i >= 0; i--) {
            double y = r[i];

            for (j = n - 1; j >= i; j--) {
                y = y - x[j] * m[i][j];
            }

            x[i] = y;
        }

        for (i = 0; i < n; i++) {
            k = i + 1;

            JOptionPane.showMessageDialog(null, "El valor de la incognita x" + k + " es " + x[i]);
        }

    }
}
