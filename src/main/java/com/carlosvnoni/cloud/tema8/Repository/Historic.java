package com.carlosvnoni.cloud.tema8.Repository;

import java.util.ArrayList;
import java.util.List;

public class Historic {


    private static final List<String> historicList = new ArrayList<>();

    public void addOperation(double firstNumber, String operation, double secondNumber, double result) {
        historicList.add(firstNumber + operation + secondNumber + " = " + result);
    }

    public List<String> getHistoricList() {
        return historicList;
    }

}