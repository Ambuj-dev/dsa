package array;

public class Jump1 {
    public static int jump(int[] nums) {
        int jump = 0;
        int pos = 0;
        int des = 0;
        for(int i=0; i< nums.length-1; i++){
            des = Math.max(des, nums[i]+i);

            if(pos == i){
                pos = des;
                jump++;
            }
        }
        return jump;
    }

    public static void main(String[] args){
        System.out.println(jump(new int[]{2,3,1,1,4}));
    }
}
