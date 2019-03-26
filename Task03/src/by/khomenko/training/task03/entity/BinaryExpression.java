package by.khomenko.training.task03.entity;

import by.khomenko.training.task03.logic.ExpressionCalc;

import java.util.List;

/**
 * Represents a binary expression's instance.
 */
public class BinaryExpression extends TextComposite {

    /**
     * @param listVal text's components list to instantiate a class instance.
     */
    public BinaryExpression(final List<TextComponent> listVal) {
        super(listVal);
    }

    /**
     * @return String value of binary expression's calculation.
     */
    @Override
    public String getValue() {

        ExpressionCalc expressionCalc = new ExpressionCalc();
        return expressionCalc.calculateBinaryExpression(this).toString();
    }

}
