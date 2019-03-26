package by.khomenko.training.task03.binexp;

/**
 * Functional interface used to calculate binary expression.
 */
@FunctionalInterface
public interface Expression {

    /**
     * @return int result of passed binary or unary bitwise operation.
     */
    int interpret();
}
