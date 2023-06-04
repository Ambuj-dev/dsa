package patterns.kwaymerge;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class FindKPairsWithSmallestSums {
    public static void main(String[] args) {
        //System.out.println(kSmallestPairs(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3));
        System.out.println(kSmallestPairs(new int[]{1, 2, 4, 5, 6}, new int[]{3, 5, 7, 9}, 3));
    }

    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> (a[0]+a[1]) - (b[0]+b[1]));
        for(int i = 0; i< nums1.length && i < k ; i++){
            minHeap.add(new int[]{nums1[i], nums2[0], 0});// 2nd index value denotes index of second array
        }
        while(!minHeap.isEmpty() && k-- > 0){
            int[] element = minHeap.poll();
            ans.add(List.of(element[0], element[1]));
            if(++element[2] < nums2.length){
                minHeap.add(new int[]{element[0], nums2[element[2]], element[2] });
            }
        }
        return ans;
    }


}
