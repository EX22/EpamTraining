package by.khomenko.training.task03.binexp;

public class Num implements Expression {

    private int e1;

    public Num(int e1) {
        this.e1 = e1;
    }

    @Override
    public int interpret() {
        return e1;
    }
}
