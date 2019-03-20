package by.khomenko.training.task03.entity;

import java.util.List;
import java.util.Objects;

public class BinaryExpression implements TextComponent {

    private String expression;

    public BinaryExpression(final String expressionVal) {
        this.expression = expressionVal;
    }

    @Override
    public String getValue() {
        return expression;
    }

    @Override
    public List<TextComponent> getComponentsList() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BinaryExpression)) return false;
        BinaryExpression that = (BinaryExpression) o;
        return expression.equals(that.expression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression);
    }
}
