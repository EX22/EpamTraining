package by.khomenko.training.task03.expparsmain;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ~	побитовый унарный оператор NOT.
 * * &	побитовый AND.
 * * |	побитовый OR.
 * * ^	побитовый исключающее OR.
 * * >>		сдвиг вправо.
 * * >>>	    сдвиг вправо с заполнением нулями.
 * * <<		сдвиг влево.
 */
public class ExpressionParser {

    private static final String REG_EXP_BINARY_EXPRESSION = "[() ~&|^]|<{2}|>{2,3}|\\z";

    private Set<String> operators = new HashSet<>();
    private Set<String> delimiters = new HashSet<>();

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

    public boolean flag = true;

    private boolean isDelimiter(String token) {
        return delimiters.contains(token);
    }

    private boolean isOperator(String token) {
        return operators.contains(token);
    }


    private int priority(String token) {
        if (("(").equals(token)) {
            return 1;
        }
        if (("|").equals(token)) {
            return 2;
        }
        if (("^").equals(token)) {
            return 3;
        }
        if (("&").equals(token)) {
            return 4;
        }
        if (("<<").equals(token) || (">>").equals(token) || (">>>").equals(token)) {
            return 5;
        }
        return 6;
    }


    private List<String> tokenize(String s, Pattern pattern) {
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


    public List<String> parseBinExp(String infix) {

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
                System.out.println("Wrong expression.");
                flag = false;
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
                            System.out.println("There are wrong amount of parentheses.");
                            flag = false;
                            return postfix;
                        }
                    }
                    stack.pop();
                    if (!stack.isEmpty()) {
                        postfix.add(stack.pop());
                    }
                } else {
                    if (("~").equals(current) && (("").equals(previous) || (isDelimiter(previous) && !(")").equals(previous)))) {
                        current = "~";
                    } else {
                        while (!stack.isEmpty() && (priority(current) <= priority(stack.peek()))) {
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
                System.out.println("There are wrong amount of parentheses.");
                flag = false;
                return postfix;
            }
        }
        return postfix;
    }
}
