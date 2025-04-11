package com.example;

public class MathUtils {

    public int add (int a, int b){
        return a + b;
    }

    public int substract(int a, int b){
        return a - b;
    }

    public int multiply (int a, int b){
        return a * b;
    }

    //Incorporacion de un try-catch al metodo divide
    public int divide (int a, int b){
        try {
            return a / b;

        } catch (ArithmeticException ae) {
            throw new ArithmeticException();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    //El area de un circulo se calcula como pi * radio al cuadrado
    public double computeCircleArea (int radio){
        return Math.PI * radio * radio;
    }
}
