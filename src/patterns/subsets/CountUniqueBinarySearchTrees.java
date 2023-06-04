package patterns.subsets;

import java.util.HashMap;
import java.util.Map;

public class CountUniqueBinarySearchTrees {

    public static void main(String[] args) {
        System.out.println(countTrees(3));
        System.out.println(countTreesMemorized(3));
        System.out.println(countTreesDp(3));
    }
    public static int countTrees(int n) {
        if(n <= 1) return 1;
        int count = 0;

        for(int i = 1; i< n+1; i++){
            int leftCount = countTrees(i-1);
            int rightCount = countTrees(n-i);
            count+=(leftCount* rightCount);
        }
        return count;
    }
    public static int countTreesMemorized(int n) {
        Map<Integer, Integer> map  = new HashMap<>();
        return countTreesMemorized(n,map);
    }

    public static int countTreesMemorized(int n, Map<Integer, Integer> map) {
        if(map.containsKey(n)){
            return map.get(n);
        }
        if(n <= 1) return 1;
        int count = 0;

        for(int i = 1; i< n+1; i++){
            int leftCount = countTreesMemorized(i-1, map);
            int rightCount = countTreesMemorized(n-i, map);
            count+=(leftCount* rightCount);
        }
        map.put(n, count);
        return count;
    }

    public static int countTreesDp(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i<= n; i++){
            int leftCount = 0;
            int rightCount = i-1;
            while(leftCount <= i-1){
                dp[i] += dp[leftCount] * dp[rightCount];
                leftCount++;
                rightCount--;
            }
        }
        return dp[n];
    }

}
