package patterns.bitmanipulation;

import java.util.Arrays;

public class TwoSingleNumber {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(singleNumber(new int[]{23, 27, 23, 17, 17, 37})));
        System.out.println(Arrays.toString(findSingleNumbers(new int[]{23, 27, 23, 17, 17, 37})));
        int[] arr = new int[] { 1, 4, 2, 1, 3, 5, 6, 2, 3, 5 };
        int[] result = TwoSingleNumber.findSingleNumbers(arr);
        System.out.println("Single numbers are: " + result[0] + ", " + result[1]);

        arr = new int[] { 2, 1, 3, 2 };
        result = TwoSingleNumber.findSingleNumbers(arr);
        System.out.println("Single numbers are: " + result[0] + ", " + result[1]);
        System.out.println(Integer.toBinaryString(5));
        System.out.println(Integer.toBinaryString(-5));
    }
    public static int[] singleNumber(int[] nums) {
        int xxory = 0;
        for(int num: nums){
            xxory ^= num;
        }
        int rmsb = xxory & -xxory;
        int x = 0;
        int y = 0;
        for(int num : nums){
            if((num & rmsb) == 0){
                x ^= num;
            }else{
                y ^= num;
            }
        }
        return new int[]{x,y};
    }
    public static int[] findSingleNumbers(int[] nums) {
        // get the XOR of the all the numbers
        int n1xn2 = 0;
        for (int num : nums) {
            n1xn2 ^= num;
        }

        // get the rightmost bit that is '1'
        int rightmostSetBit = 1;
        while ((rightmostSetBit & n1xn2) == 0) {
            rightmostSetBit = rightmostSetBit << 1;
        }
        int num1 = 0, num2 = 0;
        for (int num : nums) {
            if ((num & rightmostSetBit) != 0) // the bit is set
                num1 ^= num;
            else // the bit is not set
                num2 ^= num;
        }
        return new int[] { num1, num2 };
    }

}
