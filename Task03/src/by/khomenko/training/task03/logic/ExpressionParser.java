package by.khomenko.training.task03.logic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Transform binary expression to reverse Polish notation.
 * <p>
 * The symbols below represents bitwise binary and unary operation used
 * in this class.
 * <p>
 * ~    bitwise unary operation NOT.
 * &    bitwise AND.
 * |    bitwise OR.
 * ^    bitwise XOR operation(exclusive OR).
 * >>    right shift.
 * >>>    right shift with filling nils.
 * <<    left shift.
 */
public class ExpressionParser {

    /**
     * Instance of logger that provides logging capability for this class'
     * performance.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ExpressionParser.class);

    /**
     * Regular expression for binary expression parsing.
     */
    private static final String REG_EXP_BINARY_EXPRESSION
            = "[() ~&|^]|<{2}|>{2,3}|\\z";
    /**
     * Binary expression's operator or token priority .
     */
    private static final int PRIORITY_FIRST = 1;
    /**
     * Binary expression's operator or token priority .
     */
    private static final int PRIORITY_SECOND = 2;
    /**
     * Binary expression's operator or token priority .
     */
    private static final int PRIORITY_THIRD = 3;
    /**
     * Binary expression's operator or token priority .
     */
    private static final int PRIORITY_FOURTH = 4;
    /**
     * Binary expression's operator or token priority .
     */
    private static final int PRIORITY_FIFTH = 5;
    /**
     * Binary expression's operator or token priority .
     */
    private static final int PRIORITY_SIXTH = 6;


    /**
     * Set of operators occurring in binary expression.
     */
    private Set<String> operators = new HashSet<>();
    /**
     * Set of delimiters occurring in binary expression.
     */
    private Set<String> delimiters = new HashSet<>();

    /**
     * Constructor initializing all operators and delimiters for this class'
     * instance.
     */
    public ExpressionParser() {
        operators.add("~");
        operators.add("&");
        operators.add("|");
        operators.add("^");
        operators.add("<<");
        operators.add(">>");
        operators.add(">>>");

        delimiters.add("(");
        delimiters.add(")");
        delimiters.add(" ");
        delimiters.addAll(operators);
    }

    /**
     * Represents whether binary expression passed to method is valid.
     */
    private boolean valid = true;

    /**
     * @return true if binary expression is valid, otherwise return false.
     */
    boolean isValid() {
        return valid;
    }

    /**
     * @param token one of binary expression tokens.
     * @return true if passed to method token is delimiter,
     * otherwise return false.
     */
    private boolean isDelimiter(final String token) {
        return delimiters.contains(token);
    }

    /**
     * @param token one of binary expression tokens.
     * @return true if passed to method token is operator,
     * otherwise return false.
     */
    private boolean isOperator(final String token) {
        return operators.contains(token);
    }


    /**
     * @param token one of binary expression operators or tokens.
     * @return int value of passed to method token's priority.
     */
    private int priority(final String token) {
        if (("(").equals(token)) {
            return PRIORITY_FIRST;
        }
        if (("|").equals(token)) {
            return PRIORITY_SECOND;
        }
        if (("^").equals(token)) {
            return PRIORITY_THIRD;
        }
        if (("&").equals(token)) {
            return PRIORITY_FOURTH;
        }
        if (("<<").equals(token)
                || (">>").equals(token)
                || (">>>").equals(token)) {
            return PRIORITY_FIFTH;
        }
        return PRIORITY_SIXTH;
    }


    /**
     * @param s       String value of infix notation.
     * @param pattern according to this pattern we split String value of infix
     *                notation into separated tokens.
     * @return List of Strings represents separated into tokens String value
     * of infix notation.
     */
    private List<String> tokenize(final String s, final Pattern pattern) {
        List<String> tokenList = new ArrayList<>();
        Matcher matcher = pattern.matcher(s);
        int previousEnd = 0;
        while (previousEnd < s.length() && matcher.find(previousEnd)) {
            int index = matcher.start();
            if (previousEnd < index) {
                tokenList.add(s.substring(previousEnd, index));
            }
            if (matcher.start() < matcher.end()) {
                tokenList.add(s.substring(matcher.start(), matcher.end()));
            }

            previousEnd = matcher.end();

        }
        return tokenList;
    }


    /**
     * @param infix infix notation of binary expression.
     * @return List of strings represents Polish postfix notation.
     */
    public List<String> parseBinExp(final String infix) {

        List<String> postfix = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();

        Pattern pattern = Pattern.compile(REG_EXP_BINARY_EXPRESSION);
        List<String> tokens = tokenize(infix, pattern);
        Iterator<String> iterator = tokens.iterator();

        String previous = "";
        String current;

        while (iterator.hasNext()) {
            current = iterator.next();

            if (!iterator.hasNext() && isOperator(current)) {

                String message = "Wrong expression.";
                LOGGER.info(message);

                valid = false;
                return postfix;
            }
            if ((" ").equals(current)) {
                continue;
            }

            if (isDelimiter(current)) {
                if (("(").equals(current)) {
                    stack.push(current);
                } else if ((")").equals(current)) {
                    while (!("(").equals(stack.peek())) {
                        postfix.add(stack.pop());
                        if (stack.isEmpty()) {

                            String message
                                    = "There are wrong amount of parentheses.";
                            LOGGER.info(message);

                            valid = false;
                            return postfix;
                        }
                    }
                    stack.pop();
                    if (!stack.isEmpty()) {
                        postfix.add(stack.pop());
                    }
                } else {
                    if (("~").equals(current) && (("").equals(previous)
                            || (isDelimiter(previous)
                            && !(")").equals(previous)))) {
                        current = "~";
                    } else {
                        while (!stack.isEmpty() && (priority(current)
                                <= priority(stack.peek()))) {
                            postfix.add(stack.pop());
                        }

                    }
                    stack.push(current);
                }

            } else {
                postfix.add(current);
            }
            previous = current;
        }

        while (!stack.isEmpty()) {
            if (isOperator(stack.peek())) {
                postfix.add(stack.pop());
            } else {

                String message = "There are wrong amount of parentheses.";
                LOGGER.info(message);

                valid = false;
                return postfix;
            }
        }
        return postfix;
    }
}
