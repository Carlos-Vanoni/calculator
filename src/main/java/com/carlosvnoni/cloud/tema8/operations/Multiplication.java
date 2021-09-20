package com.carlosvnoni.cloud.tema8.operations;

public class Multiplication implements Operation {

    private static final String  SIMBOL = " x ";


    @Override
    public String getSimbol() {
        return SIMBOL;
    }

    @Override
    public double calculate(double firstNumber, double secondNumber) {
        double result = firstNumber * secondNumber;
        return result;
    }
}