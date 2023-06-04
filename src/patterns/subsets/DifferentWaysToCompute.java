package patterns.subsets;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DifferentWaysToCompute {
    public static void main(String[] args) {
        System.out.println(differentWaysToCompute("2*3-4*5"));
        System.out.println(differentWaysToComputeMemorized("2*3-4*5", new HashMap<>()));
    }
    public static List<Integer> differentWaysToCompute(String input){
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i<input.length(); i++){
            char c = input.charAt(i);
            if(c == '-' || c == '+' || c == '*'){
                String left = input.substring(0,i);
                String right = input.substring(i+1);
                List<Integer> left1 = differentWaysToCompute(left);
                List<Integer> right1 = differentWaysToCompute(right);
                for(int x:left1){
                    for(int y : right1){
                        if(c =='-'){
                            ans.add(x-y);
                        }
                        if(c =='+'){
                            ans.add(x+y);
                        }
                        if(c =='*'){
                            ans.add(x*y);
                        }
                    }
                }
            }

        }
        if(ans.size() == 0) ans.add(Integer.valueOf(input));
        return ans;
    }
    public static List<Integer> differentWaysToComputeMemorized(String input, Map<String, List<Integer>> map){
        if(map.containsKey(input)){
            return map.get(input);
        }
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i<input.length(); i++){
            char c = input.charAt(i);
            if(c == '-' || c == '+' || c == '*'){
                String left = input.substring(0,i);
                String right = input.substring(i+1);
                List<Integer> left1 = differentWaysToComputeMemorized(left, map);
                List<Integer> right1 = differentWaysToComputeMemorized(right, map);
                for(int x:left1){
                    for(int y : right1){
                        if(c =='-'){
                            ans.add(x-y);
                        }
                        if(c =='+'){
                            ans.add(x+y);
                        }
                        if(c =='*'){
                            ans.add(x*y);
                        }
                    }
                }
            }

        }
        if(ans.size() == 0) ans.add(Integer.valueOf(input));
        map.put(input,ans);
        return ans;
    }

}
