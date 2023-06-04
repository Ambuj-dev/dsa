package patterns.slidingwindow;

public class MinimumWindowSubstring {
    static String minimumWindow(char []s, char []t)
    {

        int m[] = new int[256];

        // Answer
        int ans = Integer.MAX_VALUE; // length of ans
        int start = 0; // starting index of ans
        int count = 0;
        // creating map
        for (int i = 0; i < t.length; i++) {
            if (m[t[i]] == 0)
                count++;
            m[t[i]]++;
        }

        // References of Window
        int i = 0;
        int j = 0;

        // Traversing the window
        while (j < s.length)
        {

            // Calculations
            m[s[j]]--;
            if (m[s[j]] == 0)
                count--;

            // Condition matching
            if (count == 0)
            {
                while (count == 0)
                {

                    // Sorting ans
                    if (ans > j - i + 1)
                    {
                        ans = Math.min(ans, j - i + 1);
                        start = i;
                    }

                    // Sliding I
                    // Calculation for removing I
                    m[s[i]]++;
                    if (m[s[i]] > 0)
                        count++;

                    i++;
                }
            }
            j++;
        }

        if (ans != Integer.MAX_VALUE)
            return String.valueOf(s).substring(start, ans+start);
        else
            return "-1";
    }

    static String minimumWindow1(char []s, char []t)
    {
        int[] ds = new int[256];
        int start = 0, count = 0;
        int ans = Integer.MAX_VALUE;

        for(char ch: t){
            if(ds[ch] == 0){
                count++;
            }
            ds[ch]++;
        }

        int i = 0, j = 0;

        while(j < s.length){
            ds[s[j]]--;
            if(ds[s[j]] == 0){
                count--;
            }
            if(count == 0){
                while(count == 0){
                    if(ans > j-i+1){
                        ans = j-i+1;
                        start = i;
                    }
                    ds[s[i]]++;
                    if(ds[s[i]] > 0){
                        count++;
                    }
                    i++;
                }
            }
            j++;
        }
        if(ans != Integer.MAX_VALUE){
            return String.valueOf(s).substring(start, ans+start);
        }
        return "-1";

    }

    public static void main(String[] args)
    {
        String s = "ADOBECODEBANC";
        String t = "ABC";

        System.out.print("-->Smallest window that contain all character : ");
        System.out.print(minimumWindow1(s.toCharArray(), t.toCharArray()));

    }
}

