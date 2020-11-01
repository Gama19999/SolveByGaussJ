package codes;

import java.util.Scanner;

public class Matriz {
    private final float[][] MAT;
    private final int FIL, COL;
    private String id;

    /**
     * Crea un nuevo objeto Matriz
     * @param fil tamaño de filas
     * @param col tamaño de columnas
     */
    Matriz(int fil, int col) {
        this.MAT = new float[fil][col];
        this.FIL = fil;
        this.COL = col;
        this.id = "";
    }

    /**
     * Crea una copia de un objeto Matriz
     * @param m Objeto Matriz que se copiará
     */
    Matriz(Matriz m) {
        this.MAT = m.MAT;
        this.FIL = m.FIL;
        this.COL = m.COL;
        this.id = m.id;
    }

    /**
     * Establece el ID del objeto Matriz
     * @param id String que identifica el objeto Matriz
     */
    public void setId(String id) { this.id = id; }



    public Matriz possible() {
        Scanner sknr = new Scanner(System.in);
        int vars, ecus;

        System.out.print("\nNúmero de variables: "); vars = sknr.nextInt();
        System.out.print("Número de ecuaciones: "); ecus = sknr.nextInt();

        if(vars == ecus) {
            return ecuacionInn(ecus, vars);
        } else {
            System.out.println("\nNúmero de variables y ecuaciones distinto. ERROR!");
            return null;
        }
    }

    private Matriz ecuacionInn(int nEcus, int nVars) {
        Scanner sknr = new Scanner(System.in);
        Matriz nueva = new Matriz(nEcus, (nVars+1));

        for(int f = 0; f < nEcus; ++f) {
            System.out.println("\nEcuación " + (f+1));
            for(int c = 0; c <= nVars; ++c) {
                if(c == nVars) {
                    System.out.print("Resultado de la ecuación: ");
                } else {
                    System.out.printf("Coeficiente con signo %d° termino: ", (c + 1));
                }
                nueva.MAT[f][c] = sknr.nextInt();
            }
        }

        return nueva;
    }



    private void movEcus() {
        float[] temp;

        for(int f = 0; f < (this.FIL-1); ++f) {
            for(int c = 0; c < (this.COL-1); ++c) {
                if (this.MAT[f][c] == 0.0d) {
                    temp = this.MAT[f];
                    this.MAT[f] = this.MAT[f + 1];
                    this.MAT[f + 1] = temp;
                }
            }
        }
    }

    /**
     * Multiplica la fila del parametro por el inverso del principal la diagonal de esa fila
     * @param row fila que será modificada
     */
    private void invertirA1(int row) {
        float newVal, inverso;

        try {
            inverso = 1 / this.MAT[row][row];
            for (int c = 0; c < this.COL; ++c) {
                newVal = this.MAT[row][c] * inverso;
                this.MAT[row][c] = newVal;
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Inicia el proceso del método de GaussJordan
     * <p>Método que se deberá llamar desde una copia del objeto original Matriz</p>
     */
    public void startGJ() {
        this.movEcus();

        if(this.MAT[0][0] != 1) {
            this.invertirA1(0);
        }

        for(int f = 0; f < this.FIL; ++f) {
            for(int c = 0; c < this.COL; ++c) {

            }
        }

    }



    private float[] sumaAlgeb(int f, float val) {
        float[] rowTemp = new float[this.MAT[f].length];

        for(int p = 0; p < this.COL; ++p) {
            rowTemp[p] = this.MAT[f][p] + val;
        }

        return rowTemp;
    }



    public Matriz copyThisMat() {
        return new Matriz(this);
    }

    public void impMat() {
        System.out.println("\n" + this.id);
        for (int f = 0; f < this.FIL; ++f) {
            for (int c = 0; c < this.COL; ++c) {
                System.out.printf("%7.1f", this.MAT[f][c]);
            }
            System.out.println("\n");
        }
    }

    public static void $() { System.out.println("Autor: Asis Gamaliel Rios Serrano"); }
}
