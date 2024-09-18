package ru.robq;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ru.robq.functions.LnFunction;

public class LnFunctionTest {

    private final LnFunction lnFunction = new LnFunction();

    @Test
    public void testCalculatePositiveValue() {
        double result = lnFunction.calculate(2, 0.001);
        assertEquals(Math.log(2), result, 0.001);
    }

    @Test
    public void testCalculateAnotherPositiveValue() {
        double result = lnFunction.calculate(3, 0.001);
        assertEquals(Math.log(3), result, 0.001);
    }

    @Test
    public void testCalculateInvalidXZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            lnFunction.calculate(0, 0.001);
        });
        assertEquals("x must be more than zero", exception.getMessage());
    }

    @Test
    public void testCalculateInvalidXNegative() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            lnFunction.calculate(-1, 0.001);
        });
        assertEquals("x must be more than zero", exception.getMessage());
    }

    @Test
    public void testCalculateInvalidPrecisionTooLow() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            lnFunction.calculate(2, -0.1);
        });
        assertEquals("precision must be more than 0 and less than 1", exception.getMessage());
    }

    @Test
    public void testCalculateInvalidPrecisionTooHigh() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            lnFunction.calculate(2, 1.1);
        });
        assertEquals("precision must be more than 0 and less than 1", exception.getMessage());
    }
}
