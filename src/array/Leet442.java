package array;
//Find duplicates in O(n) time and O(1) extra space | Set 1
/*Example:

        Input : n = 7 and array[] = {1, 2, 3, 6, 3, 6, 1}
        Output: 1, 3, 6

        Explanation: The numbers 1 , 3 and 6 appears more
        than once in the array.

        Input : n = 5 and array[] = {1, 2, 3, 4 ,3}
        Output: 3

        Explanation: The number 3 appears more than once
        in the array.*/
class Leet442 {

    public static void main(String args[])
    {
        int numRay[] = { 1, 4, 3, 2, 7, 8, 2, 3, 1 };

        for (int i = 0; i < numRay.length; i++) {
            int element = numRay[i] % numRay.length;
            numRay[element]
                    = numRay[element]
                    + numRay.length;
        }
        System.out.println("The repeating elements are : ");
        for (int i = 0; i < numRay.length; i++) {
            if (numRay[i] >= numRay.length * 2) {
                System.out.println(i + " ");
            }
        }
    }
}


   /* int profit = 0;

        for(int i = 1; i< prices.length; i++){
        profit += Math.max(0, prices[i]-prices[i-1]);
        }
        return profit;*/