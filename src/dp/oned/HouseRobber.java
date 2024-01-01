package dp.oned;

import java.util.ArrayList;
//https://www.codingninjas.com/studio/problems/house-robber-ii_839733?utm_source=striver&utm_medium=website&utm_campaign=a_zcoursetuf
public class HouseRobber {

    public static long houseRobber(int[] valueInHouse) {
        // Write your code here.
        if(valueInHouse.length == 0) return 0;
        if(valueInHouse.length == 1) return valueInHouse[0];
        ArrayList<Integer> al1 = new ArrayList<>();
        ArrayList<Integer> al2 = new ArrayList<>();
        for(int i =0; i< valueInHouse.length; i++){
            if(i !=0) al1.add(valueInHouse[i]);
            if(i!=valueInHouse.length-1) al2.add(valueInHouse[i]);
        }
        return Math.max(maximumNonAdjacentSum(al1), maximumNonAdjacentSum(al2));

    }

    public static long maximumNonAdjacentSum(ArrayList<Integer> nums) {
        // Write your code here.
        int n = nums.size();
        if(n == 0) return 0;
        long prev1 = 0;
        long prev = nums.get(0);
        for(int i = 1; i< n;i++){
            long pickCur = nums.get(i); if(i>1) pickCur += prev1;
            long skipCur = prev;
            long cur = Math.max(pickCur, skipCur);
            prev1 = prev;
            prev = cur;
        }
        return prev;
    }

    public static void main(String[] args) {
        System.out.println(houseRobber(new int[]{1, 3, 2, 1}));//4
    }
}
