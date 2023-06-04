package patterns.twoheaps;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class SlidingWindowMedian {
    public static void main(String[] args) {
        SlidingWindowMedian slidingWindowMedian = new SlidingWindowMedian();
        /*System.out.println(Arrays.toString(slidingWindowMedian.findSlidingWindowMedian(new int[]{1,2,-1,3,5},2)));
        slidingWindowMedian = new SlidingWindowMedian();
        System.out.println(Arrays.toString(slidingWindowMedian.findSlidingWindowMedian(new int[]{1,2,-1,3,5},3)));
        slidingWindowMedian = new SlidingWindowMedian();
        System.out.println(Arrays.toString(slidingWindowMedian.findSlidingWindowMedian(new int[]{2147483647,2147483647},2)));
        slidingWindowMedian = new SlidingWindowMedian();*/
        System.out.println(Arrays.toString(slidingWindowMedian.findSlidingWindowMedian(new int[]{2147483647,1,2,3,4,5,6,7,2147483647},2)));
    }
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    public SlidingWindowMedian(){
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public double[] findSlidingWindowMedian(int[] nums, int k){
        int n = nums.length;
        double[] result = new double[n - k +1];
        for(int i = 0; i< n ; i++){
            addNum(nums[i]);
            if(i-k+1 >= 0){
                result[i-k+1] = findMedian();
                removeNum(nums[i-k+1]);
            }
        }
        return result;
    }

    public void addNum(int num){
        if(maxHeap.size() == 0 || maxHeap.peek() >= num){
            maxHeap.offer(num);
        }else{
            minHeap.offer(num);
        }
        balance();
    }
    public void removeNum(int num){
            if(!maxHeap.remove(num)){
                minHeap.remove(num);
            }
        balance();
    }

    public double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else if (maxHeap.size() < minHeap.size()) {
            return minHeap.peek();
        }
        return (minHeap.peek().longValue() + maxHeap.peek().longValue()) / 2.0;
    }

    public void balance(){
        if(maxHeap.size() - minHeap.size() > 1){
            minHeap.offer(maxHeap.poll());
        }else if(minHeap.size() - maxHeap.size() > 1){
            maxHeap.offer(minHeap.poll());
        }
    }


}
