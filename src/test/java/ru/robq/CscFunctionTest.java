package ru.robq;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.robq.functions.CscFunction;
import ru.robq.functions.Function;

@ExtendWith(MockitoExtension.class)
public class CscFunctionTest {
    private static Function mockSinFunction;
    private CscFunction cscFunction;

    @BeforeAll
    public static void init() {
        mockSinFunction = MockUtils.createMockFromCsv(Function.class, "csv/sin.csv");
    }

    @BeforeEach
    public void setUp() {
        cscFunction = new CscFunction(mockSinFunction);
    }

    @Test
    public void testCalculateValidInput() {
        double angle = 0.5236; // pi / 6
        double precision = 0.001;

        double result = cscFunction.calculate(angle, precision);
        assertEquals(2.0, result, precision);
        
        verify(mockSinFunction).calculate(angle, precision);
    }

    @Test
    public void testCalculateSinIsZero() {
        double angle = 3.1416; // pi
        double precision = 0.001;

        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            cscFunction.calculate(angle, precision);
        });

        assertEquals("X can't be PI * n", exception.getMessage());
        
        verify(mockSinFunction).calculate(angle, precision);
    }

    @Test
    public void testCalculateNegativeAngle() {
        double angle = -0.7854; // - pi / 4
        double precision = 0.001;

        double result = cscFunction.calculate(angle, precision);
        assertEquals(-1.4142, result, precision);
        
        verify(mockSinFunction).calculate(angle, precision);
    }
}
