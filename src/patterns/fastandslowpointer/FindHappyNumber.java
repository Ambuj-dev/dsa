package patterns.fastandslowpointer;

public class FindHappyNumber {


    public boolean findHappyNumber(int num){
        int slow = num;
        int fast = num;
        while(true){
            slow = findSquareSum(slow);
            fast = findSquareSum(findSquareSum(fast));

            if(slow == fast){
                break;
            }
        }
        return slow == 1;
    }
    private int findSquareSum(int num) {
        int sum = 0;
        while(num > 0) {
            int digit = num % 10;
            sum += digit * digit;
            num = num / 10;
        }
        return sum;
    }

    public boolean find(int num) {
        int slow = num, fast = num;
        do {
            slow = findSquareSum(slow); // move one step
            fast = findSquareSum(findSquareSum(fast)); // move two steps
        } while (slow != fast); // found the cycle

        return slow == 1; // see if the cycle is stuck on the number '1'
    }

    public static void main(String[] args) {
        FindHappyNumber happyNumber = new FindHappyNumber();
        System.out.println(happyNumber.findHappyNumber(12));
        System.out.println(happyNumber.findHappyNumber(23));
        System.out.println(happyNumber.find(12));
        System.out.println(happyNumber.find(23));
    }
}
