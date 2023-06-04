package patterns.bitmanipulation;

public class BasicBitManipulation {
    public static void main(String[] args) {
        System.out.println(on(57,3));
        System.out.println(on(57,0));
        System.out.println(on(57,1));
        System.out.println(off(57,3));
        System.out.println(toggle(57,3));
        System.out.println(check(57,3));
        System.out.println(rightMostSetBit(58));
        System.out.println(rightMostSetBit(57));
    }
    public static int on(int n, int i){
        int onMask = (1 << i);
        return n | onMask;
    }
    public static int off(int n, int i){
        int offMask = ~(1 << i);
        return n & offMask;
    }
    public static int toggle(int n, int i){
        int toggleMask = (1 << i);
        return n ^ toggleMask;
    }
    public static boolean check(int n, int i){
        int checkMask = (1 << i);
        return (n & checkMask) == 0 ? false : true;
    }
    public static String rightMostSetBit(int n){
        return Integer.toBinaryString(n & -n);
    }

    public static String rightMostSetBit1(int n){
        return Integer.toBinaryString(n & -n);
    }
}
