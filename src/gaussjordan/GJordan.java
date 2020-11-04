package gaussjordan;

import java.util.Scanner;
import static codes.Matriz.esMatTriInf;
import static codes.Matriz.movEcus;

public class GJordan {
    private final int numEcus;
    private final double[][] mat;
    private final double[] incogs;

    GJordan() {
        Scanner sknr = new Scanner(System.in);
        this.$();
        System.out.print("\tNúmero de ecuaciones: ");

        this.numEcus = sknr.nextInt();
        this.mat = new double[this.numEcus][(this.numEcus + 1)];
        this.incogs = new double[this.numEcus];

        this.ecuacionInn(sknr);
    }

    private void ecuacionInn(Scanner s) {
        for (int v = 0; v < this.numEcus; ++v) {
            System.out.println("\n\tEcuación " + (v+1));

            for (int w = 0; w < this.numEcus; ++w) {
                System.out.printf("%d° coeficiente con signo: ", (w+1));
                this.mat[v][w] = s.nextDouble();
            }

            System.out.print("Solución de la ecuación: ");
            this.mat[v][(this.mat[v].length-1)] = s.nextDouble();
            incogs[v] = 0;
        }

        this.showMat();
        System.out.println(this.posible());
    }

    public void gaussJordan() {
        double val;

        movEcus(this.mat);
        for(int v = 0; v < this.numEcus; ++v) {
            for(int w = v; w < this.numEcus; ++w) {

                if (v == w) {
                    val = this.mat[v][w];

                    for(int x = 0; x < this.numEcus; ++x) {
                        this.mat[v][x] = this.mat[v][x] / val;
                    }

                    this.mat[v][(this.mat[v].length-1)] = this.mat[v][(this.mat[v].length-1)] / val;
                } else {
                    val = this.mat[w][v];

                    for(int x = 0; x < this.numEcus; ++x) {
                        this.mat[w][x] = this.mat[w][x] - (val * this.mat[v][x]);
                    }

                    this.mat[w][(this.mat[w].length-1)] = this.mat[w][(this.mat[w].length-1)] - (val * this.mat[v][(this.mat[v].length-1)]);
                }

            }

        }

        for(int v = this.numEcus-1; v >= 0; --v) {
            double y = this.mat[v][(this.mat[v].length-1)];

            for(int w = this.numEcus-1; w >= v; --w) {
                y = y - this.incogs[w] * this.mat[v][w];
            }

            this.incogs[v] = y;
        }

        esMatTriInf(this.mat, this.incogs);
        this.showMat();
        this.impResult();
        this.$();
    }

    private String posible() {
        double[][] row = new double[this.mat.length][this.mat[0].length];
        System.arraycopy(this.mat, 0, row, 0, this.mat.length);
        int ac = 0;
        ///////INCOMPLETO
        for(int f = 0; f < this.mat.length; ++f) {
            for(int c = 0; c < this.mat[f].length-1; ++c) {
                if(row[f][c] == 0.0d) ac++;
            }
        }

        for (double[] p : this.mat) {
            if (ac == p.length - 1 && p[p.length - 1] == 1) {
                return "Sistema sin solución";
            } else {
                return "f";
            }
        }
        return "INCOMPLETO";
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
