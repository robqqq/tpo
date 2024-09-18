package ru.robq;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ru.robq.functions.Function;
import ru.robq.functions.FunctionSystem;

public class SystemTest {
    private static Function secFunction;
    private static Function cotFunction;
    private static Function cscFunction;
    private static Function tanFunction;
    private static Function lnFunction;
    private static Function log2Function;
    private static Function log3Function;
    private static Function log5Function;
    private static Function log10Function;
    
    private FunctionSystem system;

    @BeforeAll
    static void init() throws IOException {
        secFunction = MockUtils.createMockFromCsv(Function.class, "csv/sec.csv");
        cotFunction = MockUtils.createMockFromCsv(Function.class, "csv/cot.csv");
        cscFunction = MockUtils.createMockFromCsv(Function.class, "csv/csc.csv");
        tanFunction = MockUtils.createMockFromCsv(Function.class, "csv/tan.csv");
        lnFunction = MockUtils.createMockFromCsv(Function.class, "csv/ln.csv");
        log2Function = MockUtils.createMockFromCsv(Function.class, "csv/log2.csv");
        log3Function = MockUtils.createMockFromCsv(Function.class, "csv/log3.csv");
        log5Function = MockUtils.createMockFromCsv(Function.class, "csv/log5.csv");
        log10Function = MockUtils.createMockFromCsv(Function.class, "csv/log10.csv");
    }

    @BeforeEach
    void setUp() {
        system = new FunctionSystem(secFunction, cotFunction, cscFunction, tanFunction, lnFunction, log2Function, log3Function, log5Function, log10Function);
    }

    @Test
    void testCalculateNegativeX() {
        double result = system.calculate(-1.0472, 0.001); // -pi / 3
        assertEquals(-248.3208, result, 0.1);
    }

    @Test
    void testCalculatePositiveX() {
        double result = system.calculate(2.0, 0.001);
        assertEquals(0.22054, result, 0.1);
    }

    @Test
    void testCscZeroException() {
        assertThrows(ArithmeticException.class, () -> system.calculate(-1.0, 0.001));
    }

    @Test
    void testLogZeroException() {
        assertThrows(ArithmeticException.class, () -> system.calculate(1.0, 0.001));
    }
}
