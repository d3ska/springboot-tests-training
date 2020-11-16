package pl.deska.springboottests.model;

import java.util.Arrays;

public class Calculator {


    public double add(double a, double b) {
        return a + b;
    }

    public double divide(double number1, double number2){
        if(number2 == 0){
            throw new ArithmeticException("Number can not divide by 0");
        }
        return number1 / number2;
    }
}
