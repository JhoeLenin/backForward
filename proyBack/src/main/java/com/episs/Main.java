package com.episs;

public class Main {
    public static void main(String[] args) {
        // Declaración de variables
        int i, j;
        double b = 1.0;      // Bias
        int ent = 2;         // Número de entradas
        int epocas = 10000;  // Número de épocas (iteraciones de entrenamiento)
        double tasaAprendizaje = 0.1;  // Tasa de aprendizaje para ajustar los pesos

        // Datos de entrenamiento XOR: {bias, entrada1, entrada2, salida esperada}
        double[][] datos = {
                {b, 0, 0, 0},
                {b, 0, 1, 1},
                {b, 1, 0, 1},
                {b, 1, 1, 0}
        };

        long repet = 0;
        int epoca = 0;
        double alpha = 0.25;
        int marErrRed = 1; //los margenes de error se redonden a n decimales

        //asignar neuronas de las capas ocultas

        int neuCapOcul = 2;

        //asignando la clase cls neurona

        clsNeurona [] o = new clsNeurona[neuCapOcul];
        for (i=0; i<o.length; i++){
            // e[] = bias, entradas, salidas deseadas. no incluir sal. des.
            o[i] = new clsNeurona(datos[0].length-1);
            o[i].ini_w();
        }

        int neuSal = 1;
        clsNeurona [] s = new clsNeurona[neuSal];

        for (j=0;j<s.length;j++){
            // se ingresa la bias
            s[j] = new clsNeurona(o.length+1);
            s[j].ini_w();
        }

        while (repet>=0){
            System.out.println("Comienzo del entrenamiento");
            //se consignan 4 epocas
            while(epoca < datos.length){
                /*************************
                PRIMERA FASE (FORWARD)
                **************************/
                //generar 
                for ( i = 0; i < o.length; i++) {
                    o[i].sumPon(datos[epoca]);
                    o[i].funcAct();
                }
                for (j = 0; j < s.length; j++) {
                    s[j].sumPon(b, o);
                    s[j].funcAct();

                    //como colocar la restriccion para ver si aprendio la RNA
                    System.out.println(precision(s[j].fa, marErrRed)+"---->"+
                            precision(datos[epoca][1+ent+j], marErrRed));
                }



            }
        }
    }

    static double precision (double num, int digit){
        int aux = 1;
        for (int i = 0; i < digit; i++) {
            num = num * aux;
            num = (double)Math.round(num);
            num = num/aux;

        }
        return num;
    }
}