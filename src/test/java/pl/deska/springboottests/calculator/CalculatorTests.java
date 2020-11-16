package pl.deska.springboottests.calculator;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.deska.springboottests.model.Calculator;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CalculatorTests {


    static Stream<Arguments> correctAddOperations(){
        return Stream.of(
                arguments(10, 20, 30),
                arguments(10, 22.2, 32.2),
                arguments(-10, 5, -5),
                arguments(0, 0, 0),
                arguments(-14, -6, -20),
                arguments(3.3, 2.2, 5.5),
                arguments(-10, 3.5, -6.5),
                arguments(-1.5, -2.2, -3.7)
        );
    }

   static Stream<Arguments> uncorrectAddOperations(){
        return Stream.of(
                arguments(10, 20, 29),
                arguments(10, 22.2, 34.2),
                arguments(-10, 5, -2),
                arguments(0, 0, 0.5),
                arguments(-14, -6, -8),
                arguments(3.3, 2.2, 5.6),
                arguments(-10, 3.5, -5.5),
                arguments(-1.5, -2.2, -3.2)
        );
    }


   static Stream<Arguments> correctDivideOperations(){
        return Stream.of(
                arguments(10, 20, 0.5),
                arguments(10, 5, 2),
                arguments(3.3, 3.3, 1),
                arguments(-2, -4, 0.5),
                arguments(-16, 4, -4),
                arguments(-4.4, 2.2, -2),
                arguments(100, 10, 10)
        );
    }



    @ParameterizedTest(name = "should return {2} for add {0} to {1}")
    @MethodSource("correctAddOperations")
    public void should_Add_Two_Numbers(double a, double b, double result){
        //given
        Calculator calculator = new Calculator();

        //then
        assertThat(calculator.add(a, b)).isEqualTo(result);
    }

    @ParameterizedTest(name = "result {2} is not sum of {0} and {1}")
    @MethodSource("uncorrectAddOperations")
    public void should_Not_Add_Two_Numbers(double numb1, double numb2, double result){
        //given
        Calculator calculator = new Calculator();

        //then
        assertThat(calculator.add(numb1, numb2)).isNotEqualTo(result);
    }



    @ParameterizedTest(name = "should return {2} for divide {0} by {1}")
    @MethodSource("correctDivideOperations")
    void should_Correctly_Divide_Numbers(double numb1, double numb2, double result) {
        Calculator calculator = new Calculator();

        assertThat(calculator.divide(numb1, numb2)).isEqualTo(result);
    }

    @Test
    void should_Thrown_Exception_When_Divide_By_Zero(){
        Calculator calculator = new Calculator();

        assertThrows(Exception.class,() -> calculator.divide(5, 0));
    }
}
