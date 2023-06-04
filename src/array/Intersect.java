package array;

import java.util.*;

public class Intersect {
    public int[] intersect(int[] nums1, int[] nums2) {
       /* int size = Math.min(nums1.length, nums2.length);
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> table = new HashMap<>();
        for(int num: nums1){
            table.put(num, table.getOrDefault(num, 0)+1);
        }
        int i = 0;
        for(int num: nums2){
            if(table.get(num) != null && table.get(num)>0){
                result.add(num);
                table.put(num, table.get(num)-1);
            }
        }*/
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        int m = nums1.length;
        int n = nums2.length;
        List<Integer> result = new ArrayList<>();

        while (i < m && j < n) {

            if (nums2[j] > nums1[i]) {
                i++;
            }

            else if (nums1[i] > nums2[j]) {
                j++;
            }
            else {
                // when both are equal
                result.add( nums1[i]);
                i++;
                j++;
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

}
