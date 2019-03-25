package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.binexp.Expression;
import by.khomenko.training.task03.entity.BinaryExpression;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class ExpressionCalc {

    public Integer calculateBinaryExpression(BinaryExpression binaryExpression) {

        ExpressionParser expressionParser = new ExpressionParser();
        List<String> expression = expressionParser.parseBinExp(binaryExpression.getTextValue());
        if (expressionParser.flag) {
            return calc(expression);
        }
        return 0;
    }

    private Integer calc(List<String> postfix) {

        Deque<Expression> stack = new ArrayDeque<>();

        for (String x : postfix) {
            switch (x) {
                case "|": {
                    Expression b = stack.pop();
                    Expression a = stack.pop();
                    stack.push(() -> a.interpret() | b.interpret());
                    break;
                }
                case "^": {
                    Expression b = stack.pop();
                    Expression a = stack.pop();
                    stack.push(() -> a.interpret() ^ b.interpret());
                    break;
                }
                case "&": {
                    Expression b = stack.pop();
                    Expression a = stack.pop();
                    stack.push(() -> a.interpret() & b.interpret());
                    break;
                }
                case ">>": {
                    Expression b = stack.pop();
                    Expression a = stack.pop();
                    stack.push(() -> a.interpret() >> b.interpret());
                    break;
                }
                case "<<": {
                    Expression b = stack.pop();
                    Expression a = stack.pop();
                    stack.push(() -> a.interpret() << b.interpret());
                    break;
                }
                case ">>>": {
                    Expression b = stack.pop();
                    Expression a = stack.pop();
                    stack.push(() -> a.interpret() >>> b.interpret());
                    break;
                }
                case "~":
                    stack.push(() -> ~stack.pop().interpret());
                    break;
                default:
                    stack.push(() -> Integer.valueOf(x));
                    break;
            }
        }

        Expression expression = stack.pop();

        return expression.interpret();
    }
}
