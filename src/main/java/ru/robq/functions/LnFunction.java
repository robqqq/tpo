package ru.robq.functions;

public class LnFunction implements Function{

    @Override
    public double calculate(double x, double precision) {
        if (x <= 0) {
            throw new ArithmeticException("x must be more than zero");
        }
        if (precision <= 0 || precision >= 1) {
            throw new ArithmeticException("precision must be more than 0 and less than 1");
        }

        double sum = 0;
        double prev = 0;
        x = (x / (x - 1));
        int i = 1;

        do {
            prev = sum;
            sum += 1 / (i * Math.pow(x, i));
            i++;
        } while (precision * 0.1 < Math.abs(sum - prev));
        
        return sum;
    }
}
