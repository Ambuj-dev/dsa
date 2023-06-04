package patterns.topkelement;

import java.util.*;

public class FreqStack {
    Map<Integer, Integer> map;
    List<Stack<Integer>> stkList;
    int maxFreq;
    public FreqStack() {
        map = new HashMap<>();
        stkList = new ArrayList<>();
        this.maxFreq = 0;
    }

    public void push(int val) {
        int freq =  map.getOrDefault(val, 0) + 1;
        map.put(val, freq);
        maxFreq = Math.max(maxFreq, freq);

        if (freq - 1 >= stkList.size()) {
            stkList.add(new Stack<Integer>());
        }
        stkList.get(freq - 1).push(val);
    }

    public int pop() {
        Stack<Integer> stack = stkList.get(maxFreq - 1);
        int x = stack.pop();
        if (stack.isEmpty()) {
           maxFreq--;
        }
        map.put(x, map.get(x) - 1);
        return x;
    }

    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(5);
        freqStack.push(7);
        freqStack.push(4);
        freqStack.push(5);
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
        System.out.println(freqStack.pop());
    }
}
