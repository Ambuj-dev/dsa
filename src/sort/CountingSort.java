package sort;

import java.util.Arrays;
import java.util.Random;
public class CountingSort {
    public static void main(String[] args) {
        Random random = new Random(System.currentTimeMillis());
        int min = 0;
        int max = 7;
        //int[] input = random.ints(min, max).parallel().limit(100000000).toArray();
        int[] input = new int[]{1,4,1,2,6,5,2};
        CountingSort countingSort = new CountingSort();
        int[] sortedArray = countingSort.sort(input, min, max);
        Arrays.stream(sortedArray).forEach(System.out::println);
    }

    public int[] sort(int[] input, int min, int max) {
        int counting[] = new int[max - min + 1];
//Compute the count of each item
        for (int number : input) {
            ++counting[number - min];
        }
//Compute the total number of items occurring before the current item in sorted output (histogram)
        for (int i = 1; i < counting.length; i++) {
            counting[i] += counting[i - 1];
        }
//Fill the output array with correct number of zeros, ones, twos and so on.
        int[] output = new int[input.length];
        for (int i : input) {
            output[counting[i - min] - 1] = i;
            --counting[i - min];
        }
        return output;
    }
}
