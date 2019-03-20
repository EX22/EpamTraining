package by.khomenko.training.task03.binexp;

public class Not implements Expression {

    private Expression e1;

    public Not(final Expression e1Val) {
        this.e1 = e1Val;
    }

    @Override
    public int interpret() {
        return ~e1.interpret();
    }
}
