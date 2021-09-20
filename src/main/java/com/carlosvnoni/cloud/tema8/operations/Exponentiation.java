package com.carlosvnoni.cloud.tema8.operations;

public class Exponentiation implements Operation {

    private static final String  SIMBOL = " ^ ";


    @Override
    public String getSimbol() {
        return SIMBOL;
    }

    @Override
    public double calculate(double firstNumber, double secondNumber) {
        double result = Math.pow(firstNumber, secondNumber);
        return result;
    }
}