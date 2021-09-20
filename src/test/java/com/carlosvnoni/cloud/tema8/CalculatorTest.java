package com.carlosvnoni.cloud.tema8;


import com.carlosvnoni.cloud.tema8.configuration.Config;
import com.carlosvnoni.cloud.tema8.operations.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalculatorTest {


    @Test
    public void shouldSum(){
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Calculator calculator = (Calculator) context.getBean("calculator");
        Addition sum = (Addition) context.getBean("sum");
        assertEquals(6, calculator.calculate(2,4, sum));
    }

    @Test
    public void shouldSub(){
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Calculator calculator = (Calculator) context.getBean("calculator");
        Subtraction sub = (Subtraction) context.getBean("sub");
        assertEquals(8, calculator.calculate(10,2, sub));
    }

    @Test
    public void shouldMultply(){
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Calculator calculator = (Calculator) context.getBean("calculator");
        Multiplication mult = (Multiplication) context.getBean("mult");
        assertEquals(8, calculator.calculate(2,4, mult));
    }

    @Test
    public void shouldDivide(){
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Calculator calculator = (Calculator) context.getBean("calculator");
        Division div = (Division) context.getBean("div");
        assertEquals(5, calculator.calculate(10,2, div));
    }

    @Test
    public void shouldPow(){
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Calculator calculator = (Calculator) context.getBean("calculator");
        Exponentiation pow = (Exponentiation) context.getBean("pow");
        assertEquals(8, calculator.calculate(2,3, pow));
    }

    @Test
    public void shouldReturnExceptionWhenNumberDividedByZero() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        Calculator calculator = (Calculator) context.getBean("calculator");
        Division div = (Division) context.getBean("div");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> calculator.calculate(10, 0, div));
        assertTrue(exception.getMessage().contains("Divisor 0 é invalido"));
    }


}