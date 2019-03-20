package by.khomenko.training.task03.binexp;

public class Rsh implements Expression {

    private Expression e1;
    private Expression e2;

    public Rsh(final Expression e1Val, final Expression e2Val) {
        this.e1 = e1Val;
        this.e2 = e2Val;
    }

    @Override
    public int interpret() {
        return e1.interpret()>>e2.interpret();
    }
}
