package ru.robq;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import ru.robq.functions.Function;

public class ResultsCsvWriter {
    public static void writeToCsv(Function function, double start, double end, double step, double precision, String filePath) {
        CSVFormat csvFormat = CSVFormat.DEFAULT;

        try (Writer writer = new FileWriter(filePath);
             CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat)) {

            // Запись заголовка
            csvPrinter.printRecord("Input", "Result");

            for (double x = start; x <= end; x += step) {
                double result;
                try {
                    result = function.calculate(x, precision);
                } catch (ArithmeticException e) {
                    e.printStackTrace();
                    continue;
                }
                // Запись данных
                csvPrinter.printRecord(x, result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
