package codes;

public class GaussJ {

    public static void main(String[] arr) {
        Matriz obj = new Matriz(); //OBJETO VACÍO; SIN VALORES EN MATRIZ
        Matriz A, ResDeA;

        A = obj.possible();
        ResDeA = A.copyThisMat();

        A.setId("Matriz A: Coeficientes de las ecuaciones ingresadas");
        A.impMat();

        ResDeA.startGJ();
        ResDeA.setId("Sistema resuelto");
        ResDeA.impMat();
        
        //Resulta que si lo puedo editar desde aquí

    }

}
