package ru.robq;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.robq.functions.Function;
import ru.robq.functions.LogFunction;

public class LogFunctionTest {
    private static Function mockLnFunction;
    private LogFunction logFunction;

    @BeforeAll
    public static void init() {
        mockLnFunction = MockUtils.createMockFromCsv(Function.class, "csv/ln.csv");
    }

    @BeforeEach
    public void setUp() {
        logFunction = new LogFunction(mockLnFunction, 2.0);
    }

    @Test
    public void testCalculateValidInput() {
        double x = 8.0;
        double precision = 0.001;

        double result = logFunction.calculate(x, precision);
        assertEquals(3.0, result, precision);

        verify(mockLnFunction).calculate(x, precision);
        verify(mockLnFunction).calculate(2.0, precision);
    }

    @Test
    public void testCalculateNegativeInput() {
        double x = -10;
        double precision = 0.001;

        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            logFunction.calculate(x, precision);
        });

        assertEquals("x must be more than zero", exception.getMessage());
    }

    @Test
    public void testCalculateZeroInput() {
        double x = 0;
        double precision = 0.001;

        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> {
            logFunction.calculate(x, precision);
        });

        assertEquals("x must be more than zero", exception.getMessage());
    }
    
    @Test
    public void testCalculateBaseChange() {
        logFunction = new LogFunction(mockLnFunction, 3.0);
        double x = 3.0;
        double precision = 0.001;

        double result = logFunction.calculate(x, precision);
        assertEquals(1.0, result, precision);

        verify(mockLnFunction, times(2)).calculate(x, precision);
    }
}
