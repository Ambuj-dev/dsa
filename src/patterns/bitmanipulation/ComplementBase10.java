package patterns.bitmanipulation;

public class ComplementBase10 {
    public static void main(String[] args) {
        System.out.println(bitwiseComplement(4));
        System.out.println(bitwiseComplement(5));

        System.out.println(bitwiseComplement1(4));
        System.out.println(bitwiseComplement1(5));
        System.out.println(bitwiseComplement2(4));
        System.out.println(bitwiseComplement2(5));
    }
    public static int bitwiseComplement(int n){
        if(n == 0) return 1;
        int result = 0;
        int factor = 1;
        while(n>0){
            result += factor * (n % 2 == 0 ? 1 : 0);
            factor *= 2;
            n /= 2;
        }
        return result;
    }
    public static int bitwiseComplement1(int n){
       return n == 0 ? 1 : (( 1 << Integer.toBinaryString(n).length()) -1) ^ n;
    }

    //TC is O(b) where b is the number of bits required to store the given number
    public static int bitwiseComplement2(int num) {
        // count number of total bits in 'num'
        int bitCount = 0;
        int n = num;
        while (n > 0) {
            bitCount++;
            n = n >> 1;
        }

        // for a number which is a complete power of '2' i.e., it can be written as
        // pow(2, n), if we subtract '1' from such a number, we get a number which has 'n'
        // least significant bits set to '1'. For example, '4' which is a complete power of
        // '2', and '3' (which is one less than 4) has a binary representation of '11' i.e.,
        // it has '2' least significant bits set to '1'
        int all_bits_set = (int) Math.pow(2, bitCount) - 1;

        // from the solution description: complement = number ^ all_bits_set
        return num ^ all_bits_set;
    }
}
