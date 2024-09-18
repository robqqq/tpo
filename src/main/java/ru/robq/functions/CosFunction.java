package ru.robq.functions;

public class CosFunction implements Function{
    private final Function sinFunction;

    public CosFunction() {
        this.sinFunction = new SinFunction();
    }

    public CosFunction(Function sinFunction) {
        this.sinFunction = sinFunction;
    }

    @Override
    public double calculate(double x, double precision) {
        return Math.sqrt(1 - Math.pow(sinFunction.calculate(x, precision), 2));
    }
    
}
