package dp.twod;

public class NinjaTraining {

    public static int ninjaTraining(int n, int points[][]) {

        // Write your code here..
        Integer[][] dp = new Integer[n][4];
        return ninjaTraining(n-1, 3, points, dp);
    }
    public static int ninjaTraining(int day, int last, int points[][], Integer[][] dp){

        if(dp[day][last] != null) return dp[day][last];
        if(day == 0){
            int maxi = 0;
            for(int i=0; i<=2; i++){
                if(i!= last)
                    maxi = Math.max(maxi, points[0][i]);
            }
            return dp[day][last] = maxi;
        }
        int maxi = 0;
        for(int i=0; i<=2; i++){
            if(i != last) {
                int activity = points[day][i]+ ninjaTraining(day-1, i, points, dp);
                maxi = Math.max(maxi, activity);
            }
        }

        return dp[day][last] = maxi;
    }

    public static int ninjaTrainingTabulation(int n, int points[][]) {

        // Write your code here..
        int[][] dp = new int[n][4];
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        for(int day =1; day<n; day++){
            for(int last = 0; last<4; last++){
                //dp[day][last] = 0;
                for(int task = 0; task < 3; task++){
                    if(task != last)
                        dp[day][last] = Math.max(dp[day][last], points[day][task]+dp[day-1][task]);
                }
            }
        }
        return dp[n-1][3];

    }

    public static int ninjaTrainingTabulationOptimized(int n, int points[][]) {

        // Write your code here..
        int[] prev = new int[4];

        prev[0] = Math.max(points[0][1], points[0][2]);
        prev[1] = Math.max(points[0][0], points[0][2]);
        prev[2] = Math.max(points[0][0], points[0][1]);
        prev[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        for(int day =1; day<n; day++){
            int[] cur = new int[4];
            for(int last = 0; last<4; last++){
                cur[last] = 0;
                for(int task = 0; task < 3; task++){
                    if(task != last)
                        cur[last] = Math.max(cur[last], points[day][task]+prev[task]);
                }
            }
            prev = cur;
        }
        return prev[3];

    }

    public static int ninjaTrainingTabulationOptimized1(int n, int points[][]) {

        // Write your code here..
        int[] prev = new int[4];

        prev[0] = Math.max(points[0][1], points[0][2]);
        prev[1] = Math.max(points[0][0], points[0][2]);
        prev[2] = Math.max(points[0][0], points[0][1]);
        prev[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        for(int day =1; day<n; day++){
            //Integer[] cur = new Integer[4];
            for(int last = 3; last>=0; last--){
               // prev[last] = 0;
                for(int task = 0; task < 3; task++){
                    if(task != last)
                        prev[last] = Math.max(prev[last], points[day][task]+prev[task]);
                }
            }
            //prev = cur;
        }
        return prev[3];

    }

    public static void main(String args[]) {

        int[][] points = {{10,40,70},
                {20,50,80},
                {30,60,90}};

        int n = points.length;
        System.out.println(ninjaTraining(n, points));
        System.out.println(ninjaTrainingTabulation(n, points));
        System.out.println(ninjaTrainingTabulationOptimized(n, points));
        System.out.println(ninjaTrainingTabulationOptimized1(n, points));
    }

}
