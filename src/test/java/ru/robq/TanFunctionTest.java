package ru.robq;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.robq.functions.Function;
import ru.robq.functions.TanFunction;

@ExtendWith(MockitoExtension.class)
public class TanFunctionTest {
    private static Function mockSinFunction;
    private static Function mockCosFunction;
    private TanFunction tanFunction;

    @BeforeAll
    public static void init() {
        mockSinFunction = MockUtils.createMockFromCsv(Function.class, "csv/sin.csv");
        mockCosFunction = MockUtils.createMockFromCsv(Function.class, "csv/cos.csv");
    }

    @BeforeEach
    public void setUp() {
        tanFunction = new TanFunction(mockSinFunction, mockCosFunction);
    }

    @Test
    public void testCalculateWithZero() {
        double result = tanFunction.calculate(0.0, 0.001);
        assertEquals(0.0, result, 0.001);

        verify(mockSinFunction).calculate(0, 0.001);
        verify(mockCosFunction).calculate(0, 0.001);
    }

    @Test
    public void testCalculateWithPositiveAngle() {

        double result = tanFunction.calculate(0.7854, 0.001); // pi / 4
        assertEquals(1.0, result, 0.001);

        verify(mockSinFunction).calculate(0.7854, 0.001);
        verify(mockCosFunction).calculate(0.7854, 0.001);
    }

    @Test
    public void testCalculateWithNegativeAngle() {

        double result = tanFunction.calculate(-0.7854, 0.001); // - pi / 4
        assertEquals(-1.0, result, 0.001);

        verify(mockSinFunction).calculate(-0.7854, 0.001);
        verify(mockCosFunction).calculate(-0.7854, 0.001);
    }

    @Test
    public void testCalculateThrowsExceptionOnDivisionByZero() {

        Exception exception = assertThrows(ArithmeticException.class, () -> {
            tanFunction.calculate(1.5708, 0.001); // pi / 2
        });

        assertEquals("X can't be PI/2 * n", exception.getMessage());
        
        verify(mockCosFunction).calculate(1.5708, 0.001);
    }
    
    @Test
    public void testCalculateWithPrecision() {

        double result = tanFunction.calculate(1.0472, 0.001); // pi / 3
        assertEquals(1.7321, result, 0.001);

        verify(mockSinFunction).calculate(1.0472, 0.001);
        verify(mockCosFunction).calculate(1.0472, 0.001);
    }
}
