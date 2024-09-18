package ru.robq.functions;

public class LogFunction implements Function{
    private final Function lnFunction;
    private final double base;

    public LogFunction(int base) {
        this.lnFunction = new LnFunction();
        this.base = base;
    }

    public LogFunction(Function lnFunction, double base) {
        this.base = base;
        this.lnFunction = lnFunction;
    }

    @Override
    public double calculate(double x, double precision) {
        if (x <= 0) {
            throw new ArithmeticException("x must be more than zero");
        }
        return lnFunction.calculate(x, precision) / lnFunction.calculate(base, precision);
    }

}
