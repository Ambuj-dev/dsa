package patterns.dp.matrixmultiplication;

import java.util.Arrays;

public class MatrixMultiplicationTabulation1 {
    static int matrixMultiplication(int N, int arr[])
    {
        // code here
        int[][] m = new int[N][N];
        int temp,j;
        for (int i = 1; i < N; i++)
            m[i][i] = 0;
        for(int d=1; d<N-1;d++){// difference is just 1 and 2 when N is 4
            for(int i=1; i<N-d; i++){//In difference 1, 2 times and in difference 2, 1 time
                j=i+d;// j is i plus difference
                m[i][j] = Integer.MAX_VALUE;
                for(int k=i;k<=j-1;k++){
                    temp = m[i][k]+m[k+1][j]+arr[i-1]*arr[k]*arr[j];
                    if(temp < m[i][j]){
                        m[i][j] = temp;
                    }
                }
            }
        }

        System.out.println(Arrays.deepToString(m));
        return m[1][N-1];
    }


    public static void main(String args[]) {

        int[] arr = {10, 20, 30, 40, 50};

        int n = arr.length;

        System.out.println("The minimum number of operations are " +
                matrixMultiplication(n, arr));


    }

}
