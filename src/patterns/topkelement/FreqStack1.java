package patterns.topkelement;

import java.util.ArrayList;
import java.util.HashMap;

public class FreqStack1 {
    HashMap<Integer,Integer> freq_map;
    int max_freq;
    ArrayList<ArrayList<Integer>> freq_stack;

    public FreqStack1() {
        freq_map= new HashMap<>();
        freq_stack= new ArrayList<>();
        freq_stack.add(new ArrayList<>());
        max_freq=0;
    }

    public void push(int val) {
        int freq=freq_map.getOrDefault(val,0)+1;
        freq_map.put(val,freq);
        if(freq>max_freq)max_freq=freq;


        if(freq_stack.size()<=freq)freq_stack.add(new ArrayList<>());
        freq_stack.get(freq).add(val);


    }

    public int pop(){
        ArrayList<Integer> s= freq_stack.get(max_freq);
        int top= s.remove(s.size()-1);
        if(s.isEmpty()){
            max_freq--;
        }
        freq_map.put(top,freq_map.get(top)-1);
        return top;
    }

    public static void main(String[] args) {
        FreqStack1 freqStack = new FreqStack1();
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
