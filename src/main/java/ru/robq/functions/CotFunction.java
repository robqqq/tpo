package ru.robq.functions;

public class CotFunction implements Function{
    private final Function sinFunction;
    private final Function cosFunction;
    
    public CotFunction() {
        this.sinFunction = new SinFunction();
        this.cosFunction = new CosFunction();
    }

    public CotFunction(Function sinFunction, Function cosFunction) {
        this.sinFunction = sinFunction;
        this.cosFunction = cosFunction;
    }

    @Override
    public double calculate(double x, double precision) {
        double sin = sinFunction.calculate(x, precision);
        if (Math.abs(sin) < precision) {
            throw new ArithmeticException("X can't be PI * n");
        }
        double cos = cosFunction.calculate(x, precision);
        return cos / sin;
    }
}
