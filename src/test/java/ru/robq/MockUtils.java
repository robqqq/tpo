package ru.robq;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.mockito.Mockito;

public class MockUtils {
    public static <T> T createMockFromCsv(Class<T> mockClass, String csvFilePath) {
        // Чтение данных из CSV файла
        T mock = Mockito.mock(mockClass);
        try (Reader reader = new FileReader(csvFilePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord record : csvParser) {
                try {
                    Mockito.when(mockClass.getMethod("calculate", double.class, double.class).invoke(mock, Double.parseDouble(record.get(0)), 0.001)).thenReturn(Double.parseDouble(record.get(1)));
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
                        | NoSuchMethodException | SecurityException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mock;
    }
}
