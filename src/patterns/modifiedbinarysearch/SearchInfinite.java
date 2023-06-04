package patterns.modifiedbinarysearch;

public class SearchInfinite {
    public static void main(String[] args) {
        ArrayReader reader = new ArrayReader(new int[]{1,2,4,5,8,9,11,35,67,87});
        System.out.println(searchInfinite(reader, 35));
        System.out.println(searchInfinite(reader, 42));
        System.out.println(searchInfinite(reader, 90));
    }
    public static int searchInfinite(ArrayReader reader, int key){
        int start = 0;
        int end = 1;
        while(reader.getValue(end) < key){
            start = end+1;
            end = end *2 ;
        }
        return binarySearch(reader, key, start, end);
    }
    public static int binarySearch(ArrayReader reader, int key, int start, int end){
        while(start <= end){
            int mid = start+(end-start)/2;
            if(reader.getValue(mid) == key) return mid;
            if(reader.getValue(mid) < key) {
                start = mid+1;
            }else{
                end = mid - 1;
            }

        }
        return -1;
    }
    static class ArrayReader{
        int[] arr;
        public ArrayReader(int[] arr) {
            this.arr = arr;
        }
        int getValue(int index){
            if(index >= this.arr.length) return Integer.MAX_VALUE;
            return this.arr[index];
        }
    }
}
