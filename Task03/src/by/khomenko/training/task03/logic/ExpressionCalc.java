package by.khomenko.training.task03.logic;

import by.khomenko.training.task03.binexp.Expression;
import by.khomenko.training.task03.entity.BinaryExpression;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Performs binary expression calculation.
 */
public class ExpressionCalc {

    /**
     * @param binaryExpression instance of binary expression.
     * @return Integer value of binary expression calculation.
     */
    public Integer
    calculateBinaryExpression(final BinaryExpression binaryExpression) {

        ExpressionParser expressionParser = new ExpressionParser();
        List<String> expression
                = expressionParser.parseBinExp(binaryExpression.getTextValue());
        if (expressionParser.flag) {
            return calc(expression);
        }
        return 0;
    }

    /**
     * Lambda expressions in switch-case statement here represent functionality
     * of Expression Functional Interface from binexp package.
     *
     * @param postfix reverse Polish notation, result of given binary
     *                expression.
     * @return Integer value of binary expression calculation.
     */
    private Integer calc(final List<String> postfix) {

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
