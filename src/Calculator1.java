import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Calculator1 {
    public static void main(String[] args) {
        Calculator1 cal = new Calculator1();
        System.out.println(cal.evaluate("4*3"));
        System.out.println(cal.evaluate("4/3"));
        System.out.println(cal.calculate("(5*(6*2))"));
        System.out.println(cal.calculate("((5*5)/(6*2))"));
        System.out.println(cal.calculate("((5*2)/(6*2))"));
        System.out.println(cal.calculate("((5*9)/(6*2))"));
    }


    public int calculate(String s) {
        Stack<String> stack = new Stack();
        int idx = 0;
        while (idx < s.length()) {
            if (s.charAt(idx) == ')') {
                StringBuilder sb = new StringBuilder();
                String ch;
                do {
                    ch = stack.pop();
                    if (!ch.equals("(")) sb.insert(0, ch);
                }
                while (!ch.equals("(") && !stack.isEmpty());
                stack.push(String.valueOf(evaluate(sb.toString())));
                idx++;
                continue;
            }
            stack.add(s.substring(idx, idx + 1));
            idx++;
        }
        return Integer.parseInt(stack.pop());
    }

    private int evaluate(String s) {
        int result = 0;
        int left = 0;
        int right = 0;
        if (s.indexOf("+") != -1) {
            left = Integer.parseInt(s.substring(0, s.indexOf("+")));
            right = Integer.parseInt(s.substring(s.indexOf("+") + 1));
            result = left + right;
        } else if (s.indexOf("-") != -1) {
            left = Integer.parseInt(s.substring(0, s.indexOf("-")));
            right = Integer.parseInt(s.substring(s.indexOf("-") + 1));
            result = left - right;
        } else if (s.indexOf("*") != -1) {
            left = Integer.parseInt(s.substring(0, s.indexOf("*")));
            right = Integer.parseInt(s.substring(s.indexOf("*") + 1));
            result = left * right;
        } else if (s.indexOf("/") != -1) {
            left = Integer.parseInt(s.substring(0, s.indexOf("/")));
            right = Integer.parseInt(s.substring(s.indexOf("/") + 1));
            result = left / right;
        }

        return result;
    }
}
