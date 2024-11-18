package com.episs;

import java.util.Random;

public class clsNeurona {
    public double[] w;   // Arreglo de pesos
    public double sp;    // Salida ponderada o valor de salida
    public double fa;    // Función de activación
    public double delta; // Error para el ajuste de pesos

    // Constructor para inicializar el arreglo de pesos con un tamaño dado
    public clsNeurona(int numPesos) {
        w = new double[numPesos];
    }

    public void ini_w() {
        Random ran = new Random();
        for (int i = 0; i < w.length; i++) {
            w[i] = ran.nextDouble() * 2 - 1; // Asigna un valor aleatorio entre -1 y 1
        }
    }

    //para multipliacion escalar con entradas
    public void sumPon (double[] e) {
        this.sp = 0.0; // Reinicia la suma ponderada
        for (int i = 0; i < e.length; i++) {
            this.sp = this.sp +  e[i] * this.w[i]; // Suma ponderada de entrada * peso
        }
    }

    //funcion ponderada con resultados de la capa oculta
    public void sumPon (double b, clsNeurona [] o) {
        this.sp = b * this.w[0]; // Reinicia la suma ponderada
        for (int i = 0; i < o.length; i++) {
            this.sp = this.sp +  o[i].fa * this.w[i+1]; // Suma ponderada de entrada * peso
        }
    }

    public void funcAct () {
        this.fa = 1 / (1 + Math.exp(-this.sp));

    }

    public void entDelSal (double d){
        this.delta = (d - this.fa) * this.fa * (1 - this.fa);
    }

    public void entDelOcul (clsNeurona [] s, int pos){
        double spDelSal = 0;
        for (int i = 0; i <s.length ; i ++) {
            spDelSal = spDelSal + (s[i].w[pos] * s[i].delta);
        }
        this.delta = this.fa * (1 - this.fa) * spDelSal;
    }
}