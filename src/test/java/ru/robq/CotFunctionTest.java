package ru.robq;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.robq.functions.CotFunction;
import ru.robq.functions.Function;

@ExtendWith(MockitoExtension.class)
public class CotFunctionTest {

    private static Function mockSinFunction;
    private static Function mockCosFunction;
    private CotFunction cotFunction;

    @BeforeAll
    public static void init() {
        mockSinFunction = MockUtils.createMockFromCsv(Function.class, "csv/sin.csv");
        mockCosFunction = MockUtils.createMockFromCsv(Function.class, "csv/cos.csv");
    }

    @BeforeEach
    public void setUp() {
        cotFunction = new CotFunction(mockSinFunction, mockCosFunction);
    }

    @Test
    public void testCalculateWithZero() {

        Exception exception = assertThrows(ArithmeticException.class, () -> {
            cotFunction.calculate(0, 0.01);
        });

        assertEquals("X can't be PI * n", exception.getMessage());

        verify(mockSinFunction).calculate(0, 0.01);
    }

    @Test
    public void testCalculateWithPositiveAngle() {

        double result = cotFunction.calculate(0.7854, 0.001); // pi / 4
        assertEquals(1.0, result, 0.001); // cot(π/4) = 1

        verify(mockSinFunction).calculate(0.7854, 0.001);
        verify(mockCosFunction).calculate(0.7854, 0.001);
    }

    @Test
    public void testCalculateWithNegativeAngle() {

        double result = cotFunction.calculate(-0.7854, 0.001); // - pi / 4
        assertEquals(-1.0, result, 0.01);

        verify(mockSinFunction).calculate(-0.7854, 0.001);
        verify(mockCosFunction).calculate(-0.7854, 0.001);
    }

    @Test
    public void testCalculateThrowsExceptionOnDivisionByZero() {

        Exception exception = assertThrows(ArithmeticException.class, () -> {
            cotFunction.calculate(4.71238, 0.001); // 3 * pi / 2
        });

        assertEquals("X can't be PI * n", exception.getMessage());

        verify(mockSinFunction).calculate(4.71238, 0.001);
    }

    @Test
    public void testCalculateWithPrecision() {

        double result = cotFunction.calculate(1.0472, 0.001);
        assertEquals(0.5774, result, 0.001); // pi / 3

        verify(mockSinFunction).calculate(1.0472, 0.001);
        verify(mockCosFunction).calculate(1.0472, 0.001);
    }
}
