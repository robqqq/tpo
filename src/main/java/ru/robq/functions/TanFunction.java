package ru.robq.functions;

public class TanFunction implements Function{
    private final Function sinFunction;
    private final Function cosFunction;
    
    public TanFunction() {
        this.sinFunction = new SinFunction();
        this.cosFunction = new CosFunction();
    }

    public TanFunction(Function sinFunction, Function cosFunction) {
        this.sinFunction = sinFunction;
        this.cosFunction = cosFunction;
    }

    @Override
    public double calculate(double x, double precision) {
        double cos = cosFunction.calculate(x, precision);
        if (Math.abs(cos) < precision) {
            throw new ArithmeticException("X can't be PI/2 * n");
        }
        double sin = sinFunction.calculate(x, precision);
        return sin / cos;
    }
}
