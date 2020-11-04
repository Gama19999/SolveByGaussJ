package gaussjordan;

import java.util.Scanner;
import static codes.Matriz.matr;
import static codes.Matriz.movEcus;

public class GJordan {
    private final int numEcus;
    private final double[][] mat;
    private final double[] incogs;
    private boolean flag;

    GJordan() {
        Scanner sknr = new Scanner(System.in);
        this.$();
        System.out.print("\tNúmero de ecuaciones: ");

        this.numEcus = sknr.nextInt();
        this.mat = new double[this.numEcus][(this.numEcus + 1)];
        this.incogs = new double[this.numEcus];
        this.flag = false;

        this.ecuacionInn(sknr);
    }

    private void ecuacionInn(Scanner s) {
        for (int v = 0; v < this.numEcus; ++v) {
            System.out.println("\n\tEcuación " + (v+1));

            for (int w = 0; w < this.numEcus; ++w) {
                System.out.printf("\t%d° coeficiente con signo: ", (w+1));
                this.mat[v][w] = s.nextDouble();
            }

            System.out.print("\tSolución de la ecuación: ");
            this.mat[v][(this.mat[v].length-1)] = s.nextDouble();
            incogs[v] = 0;
        }

        this.showMat();
        System.out.println(this.posible());
    }

    public void gaussJordan() {
        double val;

        if (flag) {
            for (int v = 0; v < this.numEcus; ++v) {
                for (int w = v; w < this.numEcus; ++w) {

                    if (v == w) {
                        val = this.mat[v][w];

                        for (int x = 0; x < this.numEcus; ++x) {
                            this.mat[v][x] = this.mat[v][x] / val;
                        }

                        this.mat[v][(this.mat[v].length - 1)] = this.mat[v][(this.mat[v].length - 1)] / val;
                    } else {
                        val = this.mat[w][v];

                        for (int x = 0; x < this.numEcus; ++x) {
                            this.mat[w][x] = this.mat[w][x] - (val * this.mat[v][x]);
                        }

                        this.mat[w][(this.mat[w].length - 1)] = this.mat[w][(this.mat[w].length - 1)] - (val * this.mat[v][(this.mat[v].length - 1)]);
                    }

                }

            }

            for (int v = this.numEcus - 1; v >= 0; --v) {
                double y = this.mat[v][(this.mat[v].length - 1)];

                for (int w = this.numEcus - 1; w >= v; --w) {
                    y = y - this.incogs[w] * this.mat[v][w];
                }

                this.incogs[v] = y;
            }

            matr(this.incogs);
            //this.showMat();
            this.impResult();
        }
        this.$();
    }

    private String posible() {
        double[] row;
        int ac;
        double val;
        String r = "";

        movEcus(this.mat);
        for (double[] fila : this.mat) {
            row = fila; ac = 0; val = 0;

            for (int v = 0; v < row.length; ++v) {
                if (v == row.length-1) val = row[v];
                if (row[v] == 0.0d) ac++;
            }

            if (ac == row.length) {
                r = "\n\tSistema con múltiples soluciones";
                this.flag = false;
                break;
            } else if (ac == row.length-1 && val != 0.0d) {
                r = "\n\tSistema sin solución";
                this.flag = false;
                break;
            } else {
                r = "\n\tSistema con solución";
                this.flag = true;
            }

        }
        return r;
    }

    private void impResult() {
        System.out.println("\n\tValores de incognitas");
        for(int v = 0; v < this.numEcus; ++v) {
            System.out.printf("\tX%d = %.1f%n", (v+1), this.incogs[v]);
        }
    }

    private void showMat() {
        System.out.println("\n\tMatriz de coeficientes del sistema");

        for(double[] f : this.mat) {
            for(double c : f) {
                System.out.printf("%7.1f", c);
            }
            System.out.println("\n");
        }

    }

    private void $() { System.out.println("\n\tAutor: Asis Gamaliel Rios Serrano"); }

}
