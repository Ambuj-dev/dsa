package patterns.modifiedbinarysearch;

public class SearchCeilingOfNumber {

    public static void main(String[] args) {
        System.out.println(searchCeilingOfNumber(new int[]{1, 3, 8, 10, 15, 20}, 12));
        System.out.println(searchCeilingOfNumber(new int[]{1, 3, 8, 10, 15, 20}, 10));
        System.out.println(searchCeilingOfNumber(new int[]{1, 3, 8, 10, 15, 20}, 9));
        System.out.println(searchCeilingOfNumber(
                new int[]{4, 6, 10}, 6));
        System.out.println(searchCeilingOfNumber(
                new int[]{1, 3, 8, 10, 15}, 12));
        System.out.println(searchCeilingOfNumber(
                new int[]{4, 6, 10}, 17));
        System.out.println(searchCeilingOfNumber(
                new int[]{4, 6, 10}, -1));
    }

    public static int searchCeilingOfNumber(int[] arr, int key) {
        if (key > arr[arr.length - 1]) // if the 'key' is bigger than the biggest element
            return -1;

        int start = 0;
        int end = arr.length - 1;
        if (arr[end] < key) return -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] > key) {
                end = mid - 1;
            } else if (arr[mid] < key) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return start;
    }
}
