package patterns.warmup;

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1; // initialize two pointers, one at the start and one at the end of the string
        while (i < j) { // continue until the two pointers cross over
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) { // move i forward until a letter or digit is found
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) { // move j backward until a letter or digit is found
                j--;
            }

            // compare the characters at i and j after converting them to lowercase
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
                return false; // if they are not equal, the string is not a palindrome
            i++; // move i forward
            j--; // move j backwards
        }

        return true; // if the two pointers have crossed over, the string is a palindrome
    }

    public static void main(String[] args) {
        ValidPalindrome sol = new ValidPalindrome();

        // Test case 1: "A man, a plan, a canal, Panama!"
        // Expected output: true
        System.out.println(sol.isPalindrome("A man, a plan, a canal, Panama!"));

        // Test case 2: "race a car"
        // Expected output: false
        System.out.println(sol.isPalindrome("race a car"));

        // Test case 3: "Was it a car or a cat I saw?"
        // Expected output: true
        System.out.println(sol.isPalindrome("Was it a car or a cat I saw?"));

        // Test case 4: "Madam, in Eden, I'm Adam."
        // Expected output: true
        System.out.println(sol.isPalindrome("Madam, in Eden, I'm Adam."));

        // Test case 5: "empty string"
        // Expected output: true
        System.out.println(sol.isPalindrome(""));
    }

}
