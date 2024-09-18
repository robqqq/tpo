package ru.robq.functions;

public class SinFunction implements Function{
    
    @Override
    public double calculate(double x, double precision) {
        if (precision <= 0 || precision >= 1) {
            throw new ArithmeticException("precision must be more than 0 and less than 1");
        }

        double sum = 0;
        double prev = 0;
        int i = 0;

        if (x >= 0) {
            while (x > Math.PI * 2) {
                x -= Math.PI * 2;
            }
        } else if (x < 0) {
            while (x < Math.PI * 2) {
                x += Math.PI * 2;
            }
        }

        do {
            prev = sum;
            if (i % 2 == 0) {
                sum += term(x, 2 * i + 1);
            } else {
                sum -= term(x, 2 * i + 1);
            }
            i++;
        } while (precision * 0.1 < Math.abs(sum - prev));
        
        return sum;
    }

    private static double term(double x, int n) {
        double res = 1;
        for (int i = 1; i <= n; i++) {
            res *= x / i;
        }
        return res;
    }
}
