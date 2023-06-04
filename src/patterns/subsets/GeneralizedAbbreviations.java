package patterns.subsets;

import java.util.ArrayList;
import java.util.List;

public class GeneralizedAbbreviations {
    public static void main(String[] args) {
        System.out.println(abbreviations("pep"));//[pep, pe1, p1p, p2, 1ep, 1e1, 2p, 3]
    }
    public static List<String> abbreviations(String string){
        List<String> answer = new ArrayList<>();
        abbreviations("pep","", 0, 0, answer);
        return answer;
    }

    public static void abbreviations(String string, String curAns, int count, int pos, List<String> answer){
        if(pos == string.length()){
            if(count == 0)
                answer.add(curAns);
            else
                answer.add(curAns+count);

            return;
        }
        if(count>0)
            abbreviations(string, curAns+count+string.charAt(pos), 0, pos+1, answer);
        else
            abbreviations(string, curAns+string.charAt(pos), 0, pos+1, answer);
        abbreviations(string, curAns, count+1, pos+1, answer);
    }
}
