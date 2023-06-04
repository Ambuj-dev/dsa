package patterns.warmup;

public class Sqrt {
    public int mySqrt(int x) {
        if (x < 2)
            return x; // return x if it is 0 or 1

        int left = 2, right = x / 2; // initialize left and right pointers
        int pivot;
        long num; // use long to store result of pivot * pivot to prevent overflow
        while (left <= right) { // binary search for the square root
            pivot = left + (right - left) / 2; // find the middle element
            num = (long) pivot * pivot;
            if (num > x)
                right = pivot - 1; // if pivot * pivot is greater than x, set right to pivot - 1
            else if (num < x)
                left = pivot + 1; // if pivot * pivot is less than x, set left to pivot + 1
            else
                return pivot; // if pivot * pivot is equal to x, return pivot
        }

        return right; // return right after the loop
    }

    public static void main(String[] args) {
        Sqrt solution = new Sqrt();

        int input1 = 4;
        int expectedOutput1 = 2;
        int result1 = solution.mySqrt(input1);
        System.out.println(result1 == expectedOutput1); // Expected output: true

        int input2 = 8;
        int expectedOutput2 = 2;
        int result2 = solution.mySqrt(input2);
        System.out.println(result2 == expectedOutput2); // Expected output: true

        int input4 = 2;
        int expectedOutput4 = 1;
        int result4 = solution.mySqrt(input4);
        System.out.println(result4 == expectedOutput4); // Expected output: true

        int input5 = 3;
        int expectedOutput5 = 1;
        int result5 = solution.mySqrt(input5);
        System.out.println(result5 == expectedOutput5); // Expected output: true

        int input6 = 15;
        int expectedOutput6 = 3;
        int result6 = solution.mySqrt(input6);
        System.out.println(result6 == expectedOutput6); // Expected output: true
    }

}
