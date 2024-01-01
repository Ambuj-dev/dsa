package dp.twod;

import java.util.Stack;

public class MaximumRectangleWithAllOnes {
    //Time Complexity: O(N * (M+M)), where N = total no. of rows and M = total no. of columns.
    //Space Complexity: O(M), where M = total no. of columns.
    static int maximalAreaOfSubMatrixOfAll1(int[][] mat, int n, int m) {
    // Write your code here.
    int maxArea = 0;
    int[] height = new int[m];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (mat[i][j] == 1) height[j]++;
            else height[j] = 0;
        }
        int area = largestRectangleArea(height);
        maxArea = Math.max(maxArea, area);
    }
    return maxArea;
}
    static int largestRectangleArea(int[] histo) {
        Stack< Integer > st = new Stack < > ();
        int maxA = 0;
        int n = histo.length;
        for (int i = 0; i <= n; i++) {
            while (!st.empty() && (i == n || histo[st.peek()] >= histo[i])) {
                int height = histo[st.peek()];
                st.pop();
                int width;
                if (st.empty())
                    width = i;
                else
                    width = i - st.peek() - 1;
                maxA = Math.max(maxA, width * height);
            }
            st.push(i);
        }
        return maxA;
    }
    public static void main(String[] args) {

        int[][] mat = {
                {1, 0, 1, 0, 0}, {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1}, {1, 0, 0, 1, 0}
        };
        int n = 4, m = 5;
        int maxArea = maximalAreaOfSubMatrixOfAll1(mat, n, m);
        System.out.println("The maximum are is: "+maxArea );
    }

}
