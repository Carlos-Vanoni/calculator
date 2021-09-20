package com.carlosvnoni.cloud.tema8;

import com.carlosvnoni.cloud.tema8.Repository.Historic;
import com.carlosvnoni.cloud.tema8.operations.Operation;
import org.springframework.beans.factory.annotation.Autowired;

public class Calculator {

    @Autowired
    private Historic historic;

    public double calculate(double value1, double value2, Operation operation) {
        double result = operation.calculate(value1, value2);
        historic.addOperation(value1, operation.getSimbol(), value2, result);
        return result;
    }
}