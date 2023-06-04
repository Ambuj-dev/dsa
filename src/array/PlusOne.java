package array;

public class PlusOne {
    public int[] plusOne(int[] digits){
        int i=digits.length-1;
        for(int digit: digits){
            if(digit != 9){
                digits[i--]+=1;
                return digits;
            }else{
                digits[i--]+=0;
                if(digits[0] == 0){
                    int[] result = new int[digits.length+1];
                    result[0]= 1;
                    return result;
                }
            }
        }
        return null;
    }

}
