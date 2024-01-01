package patterns.twopointer;

public class BackspaceStringCompare {
    public boolean backspaceCompare(String str1, String str2) {
        //use two pointer approach to compare the strings
        int pointerOne = str1.length() -1;
        int pointerTwo = str2.length() -1;

        while(pointerOne >= 0 || pointerTwo >= 0){
            int i = getNextChar(str1, pointerOne);
            int j = getNextChar(str2, pointerTwo);

            if(i < 0 && j < 0){
                //reached the end of both strings
                return true;
            }
            if(i < 0 || j < 0){
                //reached the end of one strings
                return false;
            }
            if(str1.charAt(i) != str2.charAt(j)){
                //check if the characters are equal
                return false;
            }
            pointerOne = i - 1;
            pointerTwo = j - 1;
        }
        return true;
    }


    public int getNextChar(String str, Integer index) {
        int backspaceCount = 0;
        while(index >= 0) {
            if(str.charAt(index) == '#'){
                //found a backspace character
                backspaceCount++;
            } else if(backspaceCount > 0) {
                //a non-backspace character
                backspaceCount--;
            } else {
                break;
            }
            //skip a backspace or valid character
            index--;
        }
        return index;
    }

    public static boolean compare(String str1, String str2) {
        // use two pointer approach to compare the strings
        int index1 = str1.length() - 1, index2 = str2.length() - 1;
        while (index1 >= 0 || index2 >= 0) {

            int i1 = getNextValidCharIndex(str1, index1);
            int i2 = getNextValidCharIndex(str2, index2);

            if (i1 < 0 && i2 < 0) // reached the end of both the strings
                return true;

            if (i1 < 0 || i2 < 0) // reached the end of one of the strings
                return false;

            if (str1.charAt(i1) != str2.charAt(i2)) // check if the characters are equal
                return false;

            index1 = i1 - 1;
            index2 = i2 - 1;
        }

        return true;
    }

    private static int getNextValidCharIndex(String str, int index) {
        int backspaceCount = 0;
        while (index >= 0) {
            if (str.charAt(index) == '#') // found a backspace character
                backspaceCount++;
            else if (backspaceCount > 0) // a non-backspace character
                backspaceCount--;
            else
                break;

            index--; // skip a backspace or a valid character
        }
        return index;
    }

    public static void main(String[] args) {
        BackspaceStringCompare backspaceStringCompare = new BackspaceStringCompare();
        System.out.println(backspaceStringCompare.backspaceCompare("ab#c", "ad#c"));
        System.out.println(backspaceStringCompare.backspaceCompare("a#c", "d"));

        System.out.println(BackspaceStringCompare.compare("xy#z", "xzz#"));
        System.out.println(BackspaceStringCompare.compare("xy#z", "xyz#"));
        System.out.println(BackspaceStringCompare.compare("xp#", "xyz##"));
        System.out.println(BackspaceStringCompare.compare("xywrrmp", "xywrrmu#p"));
    }
}
