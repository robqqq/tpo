package ru.robq;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import ru.robq.functions.CosFunction;
import ru.robq.functions.Function;

@ExtendWith(MockitoExtension.class)
public class CosFunctionTest {

    private static Function mockSinFunction;
    private CosFunction cosFunction;

    @BeforeAll
    public static void init() {
        mockSinFunction = MockUtils.createMockFromCsv(Function.class, "csv/sin.csv");
    }

    @BeforeEach
    public void setUp() {
        cosFunction = new CosFunction(mockSinFunction);
    }

    @Test
    public void testCalculateWithZero() {
        
        double result = cosFunction.calculate(0.0, 0.001);
        assertEquals(1.0, result, 0.001); // cos(0) = 1

        verify(mockSinFunction).calculate(0, 0.001);
    }

    @Test
    public void testCalculateWithPositiveAngle() {
        
        double result = cosFunction.calculate(0.7854, 0.001); // pi / 4
        assertEquals(Math.sqrt(2) / 2, result, 0.001); 

        verify(mockSinFunction).calculate(0.7854, 0.001);
    }
    
    @Test
    public void testCalculateWithAnotherPositiveAngle() {
        
        double result = cosFunction.calculate(1.0472, 0.001); // pi / 3
        assertEquals(0.5, result, 0.001);

        verify(mockSinFunction).calculate(1.0472, 0.001);
    }

    @Test
    public void testCalculateWithNegativeAngle() {
        
        double result = cosFunction.calculate(-0.7854, 0.001); 
        assertEquals(Math.sqrt(2) / 2, result, 0.001);

        verify(mockSinFunction).calculate(-0.7854, 0.001);
    }
}