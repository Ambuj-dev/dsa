package patterns.subsets;

import java.util.*;

public class GenerateParenthesis {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
        System.out.println(generateParenthesisIterative(3));
    }
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        findAll("(", 1,0,n,result);
        return result;
    }

    public static void findAll(String current, int open, int close, int n, List<String> result){
        if(current.length() == 2*n){
            result.add(current);
            return;
        }
        if(open<n)
            findAll(current+"(",open+1, close, n, result);
        if(close<open)
            findAll(current+")", open, close+1, n, result);

    }

    public static List<String> generateParenthesisIterative(int n) {
        List<String> result = new ArrayList<>();
        Stack<ParenthesisString> stack = new Stack<>();
        stack.push(new ParenthesisString("(",1,0));
        while(!stack.isEmpty()){
            ParenthesisString ps = stack.pop();
            if(ps.parenthesis.length() == 2*n){
                result.add(ps.parenthesis);
            }
            if(ps.open < n){
                stack.push(new ParenthesisString(ps.parenthesis+"(", ps.open+1, ps.close));
            }
            if(ps.close < ps.open){
                stack.push(new ParenthesisString(ps.parenthesis+")", ps.open, ps.close+1));
            }
        }
        return result;
    }

    public static List<String> generateParenthesisQueue(int n) {
        List<String> result = new ArrayList<>();
        Queue<ParenthesisString> queue = new LinkedList();
        queue.add(new ParenthesisString("(",1,0));
        while(!queue.isEmpty()){
            ParenthesisString ps = queue.poll();
            if(ps.parenthesis.length() == 2*n){
                result.add(ps.parenthesis);
            }
            if(ps.open < n){
                queue.add(new ParenthesisString(ps.parenthesis+"(", ps.open+1, ps.close));
            }
            if(ps.close < ps.open){
                queue.add(new ParenthesisString(ps.parenthesis+")", ps.open, ps.close+1));
            }
        }
        return result;
    }
    static class ParenthesisString{
        String parenthesis;
        int open;
        int close;

        public ParenthesisString(String parenthesis, int open, int close) {
            this.parenthesis = parenthesis;
            this.open = open;
            this.close = close;
        }
    }
}
