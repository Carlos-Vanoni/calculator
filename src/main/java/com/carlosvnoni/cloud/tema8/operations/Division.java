package com.carlosvnoni.cloud.tema8.operations;

public class Division implements Operation {

    private static final String  SIMBOL = " / ";


    @Override
    public String getSimbol() {
        return SIMBOL;
    }

    @Override
    public double calculate(double divider, double dividend) {
        if(dividend == 0)
            throw new IllegalArgumentException("Divisor 0 é invalido");
        double result = divider / dividend;
        return result;
    }
}