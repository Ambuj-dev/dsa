package patterns.slidingwindow;

public class LongestRepeatingCharReplacement {

    public static int characterReplacement(String s, int k) {
        if(s.length()==1) return 1;
        int[] array=new int[26];
        int start=0;
        int maxCount=0;
        int ans=0;
        for(int end=0;end<s.length();end++){
            array[s.charAt(end)-'A']++;
            maxCount=Math.max(array[s.charAt(end)-'A'],maxCount);
            if((end-start+1)-maxCount>k){
                array[s.charAt(start)-'A']--;
                start++;
            }
            ans=Math.max(ans,end-start+1);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(characterReplacement("AAABABCAA", 2));
    }
}
