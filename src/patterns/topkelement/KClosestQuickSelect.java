package patterns.topkelement;

import java.util.Arrays;
import java.util.Random;

public class KClosestQuickSelect {

    public static void main(String[] args) {
        KClosestQuickSelect kClosestQuickSelect = new KClosestQuickSelect();
        int[][] input = {{1,3},{-2,2}};
        for(int[] res: kClosestQuickSelect.kClosest(input, 1)){
            System.out.println(Arrays.toString(res));
        }
        int[][] input1 = {{3,3},{5,-1},{-2,4}};
        for(int[] res: kClosestQuickSelect.kClosest(input1, 2)){
            System.out.println(Arrays.toString(res));
        }

    }

    private final Random rand = new Random();

    public int[][] kClosest(int[][] points, int k) {
        quickSelect(points, 0, points.length - 1, k - 1);
        return Arrays.copyOf(points, k);
    }

    private void quickSelect(int[][] points, int l, int r, int k) {
        while (l < r) {
            int p = partition(points, l, r);

            if (p == k) return;
            else if (p > k) r = p;
            else l = p + 1;
        }
    }

    private int partition(int[][] points, int l, int r) {
        int pivot = getDist( points[ l + rand.nextInt(r - l + 1) ] );
        int i = l - 1, j = r + 1;

        while (true) {
            while (getDist(points[--j]) > pivot);
            while (getDist(points[++i]) < pivot);

            if (i >= j) return j;

            swap(points, i, j);
        }
    }

    private void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }

    private int getDist(int[] p) {
        return p[0] * p[0] + p[1] * p[1];
    }
}
