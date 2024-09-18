package ru.robq.functions;

public class FunctionSystem implements Function{
    private final Function secFunction;
    private final Function cotFunction;
    private final Function cscFunction;
    private final Function tanFunction;
    private final Function lnFunction;
    private final Function log2Function;
    private final Function log3Function;
    private final Function log5Function;
    private final Function log10Function;

    public FunctionSystem(Function secFunction, Function cotFunction, Function cscFunction, Function tanFunction,
            Function lnFunction, Function log2Function, Function log3Function, Function log5Function,
            Function log10Function) {
        this.secFunction = secFunction;
        this.cotFunction = cotFunction;
        this.cscFunction = cscFunction;
        this.tanFunction = tanFunction;
        this.lnFunction = lnFunction;
        this.log2Function = log2Function;
        this.log3Function = log3Function;
        this.log5Function = log5Function;
        this.log10Function = log10Function;
    }

    public FunctionSystem() {
        this.secFunction = new SecFunction();
        this.cotFunction = new CotFunction();
        this.cscFunction = new CscFunction();
        this.tanFunction = new TanFunction();
        this.lnFunction = new LnFunction();
        this.log2Function = new LogFunction(2);
        this.log3Function = new LogFunction(3);
        this.log5Function = new LogFunction(5);
        this.log10Function = new LogFunction(10);
    }

    @Override
    public double calculate(double x, double precision) {
        if (x <= 0) {
                double csc = cscFunction.calculate(x, precision);
            if (Math.abs(csc) < precision) {
                throw new ArithmeticException("csc(x) can't be zero");
            }
            double sec = secFunction.calculate(x, precision);
            double cot = cotFunction.calculate(x, precision);
            double tan = tanFunction.calculate(x, precision);
            return Math.pow(((((sec + cot) * csc) - (tan / csc)) * sec), 3);
        } else {
            double log10 = log10Function.calculate(x, precision);
            double log2 = log2Function.calculate(x, precision);
            double lower = log10 - (log10 + log2);
            System.out.println(lower);
            if (Math.abs(lower) < precision) {
                throw new ArithmeticException("log10(x) - (log10(x) + log2(x)) can't be zero");
            }
            double log3 = log3Function.calculate(x, precision);
            double log5 = log5Function.calculate(x, precision);
            double ln = lnFunction.calculate(x, precision);
            return (Math.pow(log3, 3) + log5 - log2) * ln / lower;
        }
    }
}
