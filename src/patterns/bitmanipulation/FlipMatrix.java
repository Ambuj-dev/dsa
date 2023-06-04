package patterns.bitmanipulation;

import java.util.Arrays;

public class FlipMatrix {
    public static void main(String[] args) {
        int[][] image = {{1,0,1},{1,1,1},{0,1,1}};
        for(int[] arr : flipAndInvertImage(image)){
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("=====>");
        int[][] image1 = {{1,0,1},{1,1,1},{0,1,1}};
        for(int[] arr : flipAndInvertImage1(image1)){
            System.out.println(Arrays.toString(arr));
        }

    }

    public static int[][] flipAndInvertImage(int[][] image) {
        if(image == null || image.length == 0 | image[0].length == 0){
            return image;
        }
        for(int[] row : image){
            int start = 0;
            int end = row.length - 1;
            while(start <= end){
                //If the start and end elements are different no need to do anything because after flip and invert, they will be same.
                // So XOR and assign to both start and end if they are same.
                if(row[start] == row[end]){
                    row[start]^= 1;
                    row[end] = row[start];
                }
                start++;
                end--;
            }
        }

        return image;
    }

    public static int[][] flipAndInvertImage1(int[][] image) {
        if (image == null || image.length == 0 | image[0].length == 0) {
            return image;
        }
        for (int[] row : image) {
            int start = 0;
            int end = row.length - 1;
            while (start <= end) {

                int temp = row[start] ^ 1;
                row[start] = row[end] ^ 1;
                row[end] = temp;

                start++;
                end--;
            }
        }

        return image;
    }

}
