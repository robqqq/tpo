package ru.robq.functions;

public class CscFunction implements Function{
    private final Function sinFunction;
    
    public CscFunction() {
        this.sinFunction = new SinFunction();
    }

    public CscFunction(Function sinFunction) {
        this.sinFunction = sinFunction;
    }

    @Override
    public double calculate(double x, double precision) {
        double sin = sinFunction.calculate(x, precision);
        if (Math.abs(sin) < precision) {
            throw new ArithmeticException("X can't be PI * n");
        }
        return 1 / sin;
    }
}
