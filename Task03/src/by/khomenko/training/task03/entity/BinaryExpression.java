package by.khomenko.training.task03.entity;

import by.khomenko.training.task03.logic.ExpressionCalc;

import java.util.List;

public class BinaryExpression extends TextComposite {

    public BinaryExpression(final List<TextComponent> listVal) {
        super(listVal);
    }

    @Override
    public String getValue() {

        ExpressionCalc expressionCalc = new ExpressionCalc();
        return expressionCalc.calculateBinaryExpression(this).toString();
    }

}
