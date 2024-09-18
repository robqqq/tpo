package ru.robq;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.robq.functions.Function;
import ru.robq.functions.SecFunction;

@ExtendWith(MockitoExtension.class)
public class SecFunctionTest {
    private static Function mockCosFunction;
    private SecFunction secFunction;

    @BeforeAll
    public static void init() {
        mockCosFunction = MockUtils.createMockFromCsv(Function.class, "csv/cos.csv");
    }

    @BeforeEach
    public void setUp() {
        secFunction = new SecFunction(mockCosFunction);
    }

    @Test
    public void testCalculateValidInput() {
        double x = 0.0;
        double precision = 0.001;

        double result = secFunction.calculate(x, precision);
        assertEquals(1.0, result, precision);

        verify(mockCosFunction).calculate(x, precision);
    }

    @Test
    public void testCalculateCosCloseToZero() {
        double x = 0.7854; // pi / 4
        double precision = 0.001;

        double result = secFunction.calculate(x, precision);
        assertEquals(1.4142, result, precision);

        verify(mockCosFunction).calculate(x, precision);
    }

    @Test
    public void testCalculateCosNearZero() {
        double x = 1.5708; // pi / 2
        double precision = 0.001;

        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            secFunction.calculate(x, precision);
        });

        assertEquals("X can't be PI/2 * n", exception.getMessage());

        verify(mockCosFunction).calculate(x, precision);
    }

    @Test
    public void testCalculateNegativeInput() {
        double x = -1.0472; // - pi / 3
        double precision = 0.001;

        double result = secFunction.calculate(x, precision);
        assertEquals(2.0, result, precision);

        verify(mockCosFunction).calculate(x, precision);
    }
}
