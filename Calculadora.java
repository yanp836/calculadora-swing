package CalculadoraPackage;

public class Calculadora {

    private double ultimoResultado;

    public double somar(double a, double b) {
        ultimoResultado = a + b;
        return ultimoResultado;
    }

    public double subtrair(double a, double b) {
        ultimoResultado = a - b;
        return ultimoResultado;
    }

    public double multiplicar(double a, double b) {
        ultimoResultado = a * b;
        return ultimoResultado;
    }

    public double dividir(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero!");
        }
        ultimoResultado = a / b;
        return ultimoResultado;
    }

    public double getUltimoResultado() {
        return ultimoResultado;
    }

    public void reset() {
        ultimoResultado = 0;
    }
}
