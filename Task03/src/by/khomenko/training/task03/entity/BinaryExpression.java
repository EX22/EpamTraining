package by.khomenko.training.task03.entity;

import by.khomenko.training.task03.expparsmain.BinExpCalc;
import by.khomenko.training.task03.expparsmain.ExpressionParser;

import java.util.List;
import java.util.StringJoiner;

public class BinaryExpression extends TextComposite {

    public BinaryExpression(final List<TextComponent> listVal) {
        super(listVal);
    }

    @Override
    public String getValue() {
        StringJoiner result = new StringJoiner("");
        for (TextComponent textComponent : list) {
            result.add(textComponent.getValue());
        }
        ExpressionParser expressionParser = new ExpressionParser();
        List<String> expression = expressionParser.parseBinExp(result.toString());

        return BinExpCalc.calc(expression).toString();
    }

}
