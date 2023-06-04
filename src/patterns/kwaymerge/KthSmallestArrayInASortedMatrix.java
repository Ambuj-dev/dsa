package patterns.kwaymerge;

public class KthSmallestArrayInASortedMatrix {
    public static void main(String[] args) {
        int[][] matrix = {  {1,5,9},
                            {10,11,13},
                            {12,13,15}};
        System.out.println(kthSmallest(matrix, 8));
        int[][] matrix1 = {{1,2},{1,3}};
        System.out.println(kthSmallest(matrix1, 3));

        System.out.println(kthSmallestOptimised(matrix, 8));
        System.out.println(kthSmallestOptimised(matrix1, 3));
        int matrix2[][] = { { 1, 4 }, { 2, 5 } };
        int result = KthSmallestArrayInASortedMatrix.findKthSmallest(matrix2, 2);
        System.out.println("Kth smallest number is: " + result);

        int matrix3[][] = { { -5 } };
        result = KthSmallestArrayInASortedMatrix.findKthSmallest(matrix3, 1);
        System.out.println("Kth smallest number is: " + result);

        int matrix4[][] = { { 2, 6, 8 }, { 3, 7, 10 }, { 5, 8, 11 } };
        result = KthSmallestArrayInASortedMatrix.findKthSmallest(matrix4, 5);
        System.out.println("Kth smallest number is: " + result);

        int matrix5[][] = { { 1, 5, 9 }, { 10, 11, 13 }, { 12, 13, 15 } };
        result = KthSmallestArrayInASortedMatrix.findKthSmallest(matrix5, 8);
        System.out.println("Kth smallest number is: " + result);

    }

    public static int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int start = matrix[0][0];
        int end = matrix[n - 1][n - 1];
        while(start < end){
            int mid = start + (end-start) / 2;
            int row = n-1;
            int col = 0;
            int count = 0;
            int smaller = start;
            int larger = end;
            while(row >= 0 && col < n){
                if(matrix[row][col] > mid){
                    larger = Math.min(larger, matrix[row][col]);
                    row--;
                }else{
                    smaller = Math.max(smaller, matrix[row][col]);
                    count += row+1;
                    col++;
                }

            }
            if(count == k) return smaller;
            if(count < k) start = larger;
            else end = smaller;
        }

        return start;
    }
    public static int kthSmallestOptimised(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length; // For general, the matrix need not be a square
        int left = matrix[0][0], right = matrix[m-1][n-1], ans = -1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (countLessOrEqual(matrix, mid, m , n) >= k) {
                ans = mid;
                right = mid - 1; // try to look for a smaller value left side
            } else left = mid + 1; // try to look for a bigger value right side
        }
        return ans;
    }
    static int countLessOrEqual(int[][] matrix, int x, int m, int n) {
        int count = 0, col = n - 1; // start with the rightmost column
        for (int row = 0; row < m; ++row) {
            while (col >= 0 && matrix[row][col] > x) --col;  // decrease column until matrix[r][col] <= x
            count += (col + 1);
        }
        return count;
    }

    public static int findKthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int start = matrix[0][0], end = matrix[n - 1][n - 1];
        while (start < end) {
            int mid = start + (end - start) / 2;
            // first number is the smallest and the second number is the largest
            int[] smallLargePair = { matrix[0][0], matrix[n - 1][n - 1] };

            int count = countLessEqual(matrix, mid, smallLargePair);

            if (count == k)
                return smallLargePair[0];

            if (count < k)
                start = smallLargePair[1]; // search higher
            else
                end = smallLargePair[0]; // search lower
        }

        return start;
    }

    private static int countLessEqual(int[][] matrix, int mid, int[] smallLargePair) {
        int count = 0;
        int n = matrix.length, row = n - 1, col = 0;
        while (row >= 0 && col < n) {
            if (matrix[row][col] > mid) {
                // as matrix[row][col] is bigger than the mid, let's keep track of the
                // smallest number greater than the mid
                smallLargePair[1] = Math.min(smallLargePair[1], matrix[row][col]);
                row--;
            } else {
                // as matrix[row][col] is less than or equal to the mid, let's keep track of the
                // biggest number less than or equal to the mid
                smallLargePair[0] = Math.max(smallLargePair[0], matrix[row][col]);
                count += row + 1;
                col++;
            }
        }
        return count;
    }


}