package ru.robq;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.robq.functions.SinFunction;

@ExtendWith(MockitoExtension.class)
public class SinFunctionTest {
    private final SinFunction sinFunction = new SinFunction();

    @Test
    public void testCalculatePositiveValue() {
        double result = sinFunction.calculate(1.5708, 0.001); // pi / 2
        assertEquals(1.0, result, 0.001);
    }

    @Test
    public void testCalculateNegativeValue() {
        double result = sinFunction.calculate(-1.5708, 0.001); // -pi / 2
        assertEquals(-1.0, result, 0.001);
    }

    @Test
    public void testCalculateZero() {
        double result = sinFunction.calculate(0, 0.001);
        assertEquals(0.0, result, 0.001);
    }

    @Test
    public void testCalculateLargeValue() {
        double result = sinFunction.calculate(100 * Math.PI, 0.001);
        assertEquals(0.0, result, 0.001);
    }

    @Test
    public void testCalculateInvalidPrecisionTooLow() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            sinFunction.calculate(Math.PI / 4, -0.1);
        });
        assertEquals("precision must be more than 0 and less than 1", exception.getMessage());
    }

    @Test
    public void testCalculateInvalidPrecisionTooHigh() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            sinFunction.calculate(Math.PI / 4, 1.1);
        });
        assertEquals("precision must be more than 0 and less than 1", exception.getMessage());
    }
}
