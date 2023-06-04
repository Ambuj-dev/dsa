package patterns.fastandslowpointer;

public class CycleInACircularArray {
    public static void main(String[] args) {
        System.out.println(circularLoopExists(new int[]{1, 2, -1, 2, 2}));
        System.out.println(circularLoopExists(new int[]{2, 2, -1, 2}));
        System.out.println(circularLoopExists(new int[]{2, 1, -1, -2}));

    }

    private static boolean circularLoopExists(int[] arr){

        for(int i= 0; i<arr.length; i++){
            boolean isForward = arr[i] > 0;
            int slow = i;
            int fast = i;
            while(true){
                slow = findNextIndex(arr, isForward, slow);
                fast = findNextIndex(arr, isForward, fast);
                if(fast != -1){
                    fast = findNextIndex(arr, isForward, fast);
                }
                if(slow == -1 || fast == -1 || slow == fast){
                    break;
                }
            }
            if(slow != -1 && slow == fast){
                return true;
            }
        }

        return false;
    }

    private static int findNextIndex(int[] arr, boolean isForward, int currentIndex) {
        if(arr[currentIndex] > 0  != isForward){
            return -1;
        }
        int nextIndex = (currentIndex + arr[currentIndex]) % arr.length;
        if(nextIndex < 0 ){
            nextIndex+=arr.length;
        }
        if(currentIndex == nextIndex){
            return -1;
        }
        return nextIndex;
    }
}
