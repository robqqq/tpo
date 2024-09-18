package ru.robq.functions;

public class SecFunction implements Function{
    private final Function cosFunction;
    
    public SecFunction() {
        this.cosFunction = new CosFunction();
    }

    public SecFunction(Function cosFunction) {
        this.cosFunction = cosFunction;
    }

    @Override
    public double calculate(double x, double precision) {
        double cos = cosFunction.calculate(x, precision);
        if (Math.abs(cos) < precision) {
            throw new ArithmeticException("X can't be PI/2 * n");
        }
        return 1 / cos;
    }

}
