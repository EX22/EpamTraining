package by.khomenko.training.task03.expparsmain;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class BinExpCalc {

    /**
     * ~	побитовый унарный оператор NOT.
     * &	побитовый AND.
     * |	побитовый OR.
     * ^	побитовый исключающее OR.
     * >>		сдвиг вправо.
     * >>>	    сдвиг вправо с заполнением нулями.
     * <<		сдвиг влево.
     * @param postfix
     * @return
     */
    public static Integer calc(List<String> postfix) {

        Deque<Integer> stack = new ArrayDeque<>();

        for (String x : postfix) {
            switch (x) {
                case "|": {
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a | b);
                    break;
                }
                case "^": {
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a ^ b);
                    break;
                }
                case "&": {
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a & b);
                    break;
                }
                case ">>": {
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a >> b);
                    break;
                }
                case "<<": {
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a << b);
                    break;
                }
                case ">>>": {
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a >>> b);
                    break;
                }
                case "~":
                    stack.push(~stack.pop());
                    break;
                default:
                    stack.push(Integer.valueOf(x));
                    break;
            }
        }
        return stack.pop();
    }



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        ExpressionParser expressionParser = new ExpressionParser();
        List<String> expression = expressionParser.parseBinExp(s);
        boolean flag = expressionParser.flag;
        if (flag) {
            for (String x : expression) System.out.print(x + " ");
            System.out.println();
            System.out.println(calc(expression));
        }
    }
}
