package patterns.dp.knapsack;

// { Driver Code Starts
import java.io.*;
import java.lang.*;

 class gfg
{
    public static void main(String args[])throws IOException
    {
        //reading input using BufferedReader class

            //reading number of elements and weight
            int n = 4;
            int w = 7;

            int val[] = new int[n];
            int wt[] = new int[n];

            //inserting the values

                val = new int[]{1, 6, 10, 16};


                wt = new int[]{1, 2, 3, 5};

            //calling method knapSack() of class Knapsack
            System.out.println(new Knapsack1().knapSack1(w, wt, val, n));
        }
    }




// } Driver Code Ends


class Knapsack1
{
    // Returns the maximum value that can be put in a knapsack of capacity W
    static int knapSack1(int W, int wt[], int val[], int n)
    {
        // your code here
        int i, w;
        int k[][] = new int[n+1][W+1];
        for(i = 0; i <= n; i++)
        {
            k[i][0] = 0;
        }

        for(w = 0; w <= W; w++)
        {
            k[0][w] = 0;
        }

        for(i = 1; i <= n; i++){
            for(w = 1; w <= W; w++){
                if(wt[i-1] <= w){
                    k[i][w] = max( val[i-1] + k[i-1][w-wt[i-1]], k[i-1][w]);
                }else{
                    k[i][w] = k[i-1][w];
                }
            }
        }
        return k[n][W];
    }

    static int max(int a, int b){
        return a > b ? a : b;
    }
}



