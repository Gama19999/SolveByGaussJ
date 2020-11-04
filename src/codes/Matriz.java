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
     * Crea un nuevo objeto Matriz vacío
     */
    Matriz() {
        this.MAT = null;
        this.FIL = 0;
        this.COL = 0;
        this.id = null;
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
            return this.ecuacionInn(ecus, vars);
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



    public static void movEcus(double[][] m) {
        double[] temp;

        for(int f = 0; f < (m.length-1); ++f) {
            for(int c = 0; c < (m[f].length-1); ++c) {
                if (m[f][c] == 0.0d) {
                    temp = m[f];
                    m[f] = m[f + 1];
                    m[f + 1] = temp;
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
            System.out.println("x");
        }
    }

    /**
     * Inicia el proceso del método de GaussJordan
     * <p>Método que se deberá llamar desde una copia del objeto original Matriz</p>
     */
    public void startGJ() {
        int cont, col = 0, pos = 0;
        movEcus(new double[0][0]);

        if(this.MAT[pos][pos] != 1) {
            this.invertirA1(pos);
            pos++;
        }

        for(int f = 1; f < this.FIL; ++f) {
            this.MAT[f] = this.sumaAlgeb(f, this.contrario(f, col));
        }

        if(this.MAT[pos][pos] != 1) {
            this.invertirA1(pos);
            this.MAT[pos] = this.sumaAlgeb(pos+1, this.contrario(pos+1, pos));
            pos++;
        }

        if(this.MAT[pos][pos] != 1) {
            this.invertirA1(pos);
        } // Se supone que aquí termina Gauss; Comienza GJ


        /*
        while(!this.esMatTriInf()) {
            cont = 0;
            for(int f = 1; f < this.FIL; ++f) {

            }
        }*/

    }



    private float[] contrario(int f, int c) {
        float[] rowMult = new float[this.MAT[f].length];

        for(int p = 0; p < this.COL; ++p) {
            rowMult[p] = this.MAT[f][p] * (this.MAT[f][c] * -1);
        }

        return rowMult;
    }

    private float[] sumaAlgeb(int f, float[] val) {
        float[] rowTemp = new float[this.MAT[f].length];

        for(int p = 0; p < this.COL; ++p) {
            rowTemp[p] = this.MAT[f][p] + val[p];
        }

        return rowTemp;
    }



    /**Retorna TRUE si es una Matriz Triangular Superior; contrario FALSE --> SOLO MATRIZ CUADRADA*/
    private boolean esMatTriSup() {
        boolean flag = false;

        for(int f = 0; f < this.FIL; ++f) {
            for(int c = 0; c < (this.COL-1); ++c) {
                if(c < f) {
                    if (this.MAT[f][c] == 0) flag = true;
                    else {
                        flag = false;
                        break;
                    }
                }
            }
        }
        return flag;
    }

    /**Retorna TRUE si es una Matriz Triangular Inferior; contrario FALSE --> SOLO MATRIZ CUADRADA*/
    public static void esMatTriInf(double[][] m, double[] r) {
        for(int f = 0; f < m.length; ++f) {
            for(int c = 0; c < m[f].length-1; ++c) {
                if(c > f) {
                    m[f][c] = 0;
                }
            }
            m[f][m[f].length-1] = r[f];
        }
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

    public static void matr(double[] res) {
        Matriz x = new Matriz(res.length, res.length+1);
        System.out.println("\n\tMatriz Escalonada Reducida");

        for(int f = 0; f < x.MAT.length; ++f) {
            for (int c = 0; c < x.MAT[f].length; ++c) {
                if (f == c) x.MAT[f][c] = 1.0f;
                if (c == x.MAT[f].length-1) x.MAT[f][c] = (float) res[f];
            }
        }

        for(int f = 0; f < x.MAT.length; ++f) {
            for(int c = 0; c < x.MAT[f].length; ++c) {
                System.out.printf("%7.1f", x.MAT[f][c]);
            }
            System.out.println("\n");
        }
    }

    public static void $() { System.out.println("Autor: Asis Gamaliel Rios Serrano"); }
}
