package patterns.kwaymerge;

public class MedianOfSortedArray {

    //O((m+n)/2)
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
       int i = 0, i1 = 0, i2 = 0;

       int n1 = nums1.length;
       int n2 = nums2.length;
       int n = n1+n2;

       int[] res = new int[n/2+1];
       while(i < res.length){
           if(i1 < n1 & i2 < n2){
               res[i++] = nums1[i1] > nums2[i2] ? nums2[i2++] : nums1[i1++];
           }
           else if(i1 < n1){
               res[i++] = nums1[i1++];
           }
           else if(i2 < n2){
               res[i++] = nums2[i2++];
           }
       }
       if(n%2 == 1) return res[res.length - 1];
       else
           return (res[res.length - 2] + res[res.length - 1]) / 2.00;
    }

    // Driver Code
    public static void main(String[] args) {

        System.out.println(findMedianSortedArrays(new int[]{1,3}, new int[]{2}));
        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));
        System.out.println(findMedianSortedArrays(new int[]{1,3}, new int[]{2,4}));
    }
}
