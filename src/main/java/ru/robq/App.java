package ru.robq;

import ru.robq.functions.CosFunction;
import ru.robq.functions.CotFunction;
import ru.robq.functions.CscFunction;
import ru.robq.functions.LnFunction;
import ru.robq.functions.LogFunction;
import ru.robq.functions.SecFunction;
import ru.robq.functions.SinFunction;
import ru.robq.functions.TanFunction;

public class App 
{
    public static void main( String[] args )
    {
        double start = -5.0;
        double end = 5.0;
        double step = 0.01;
        double precision = 0.01;

        // Записываем результаты каждой функции в CSV файл
        ResultsCsvWriter.writeToCsv(new SinFunction(), start, end, step, precision, "sin.csv");
        ResultsCsvWriter.writeToCsv(new CosFunction(), start, end, step, precision, "cos.csv");
        ResultsCsvWriter.writeToCsv(new TanFunction(), start, end, step, precision, "tan.csv");
        ResultsCsvWriter.writeToCsv(new CotFunction(), start, end, step, precision, "cot.csv");
        ResultsCsvWriter.writeToCsv(new SecFunction(), start, end, step, precision, "sec.csv");
        ResultsCsvWriter.writeToCsv(new CscFunction(), start, end, step, precision, "csc.csv");
        
        // Для логарифмических функций
        ResultsCsvWriter.writeToCsv(new LnFunction(), 0.01, 5.0, step, precision, "ln.csv"); // только положительные значения
        ResultsCsvWriter.writeToCsv(new LogFunction(2), 0.01, 5.0, step, precision, "log2.csv");
        ResultsCsvWriter.writeToCsv(new LogFunction(3), 0.01, 5.0, step, precision, "log3.csv");
        ResultsCsvWriter.writeToCsv(new LogFunction(5), 0.01, 5.0, step, precision, "log5.csv");
        ResultsCsvWriter.writeToCsv(new LogFunction(10), 0.01, 5.0, step, precision, "log10.csv");
    }
}
