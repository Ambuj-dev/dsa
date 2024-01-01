package patterns.subsets;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class AbbreviatedWord {
    StringBuilder str;
    int start;
    int count;

    public AbbreviatedWord(StringBuilder str, int start, int count) {
        this.str = str;
        this.start = start;
        this.count = count;
    }
}
public class UniqueGeneralizedAbbreviations {


        public static List<String> generateGeneralizedAbbreviation(String word) {
            int wordLen = word.length();
            List<String> result = new ArrayList<>();
            Queue<AbbreviatedWord> queue = new LinkedList<>();
            queue.add(new AbbreviatedWord(new StringBuilder(), 0, 0));
            while (!queue.isEmpty()) {
                AbbreviatedWord abWord = queue.poll();
                if (abWord.start == wordLen) {
                    if (abWord.count != 0)
                        abWord.str.append(abWord.count);
                    result.add(abWord.str.toString());
                } else {
                    // continue abbreviating by incrementing the current abbreviation count
                    queue.add(new AbbreviatedWord(new StringBuilder(abWord.str),
                            abWord.start + 1, abWord.count + 1));

                    // restart abbreviating, append the count and the current character to the string
                    if (abWord.count != 0)
                        abWord.str.append(abWord.count);
                    queue.add(
                            new AbbreviatedWord(new StringBuilder(
                                    abWord.str).append(word.charAt(abWord.start)), abWord.start + 1, 0));
                }
            }

            return result;
        }

    public static List<String> generateGeneralizedAbbreviationRecursive(String word) {
        List<String> result = new ArrayList<String>();
        generateAbbreviationRecursive(word, new StringBuilder(), 0, 0, result);
        return result;
    }

    private static void generateAbbreviationRecursive(String word, StringBuilder abWord,
                                                      int start, int count, List<String> result) {

        if (start == word.length()) {
            if (count != 0)
                abWord.append(count);
            result.add(abWord.toString());
        } else {
            // continue abbreviating by incrementing the current abbreviation count
            generateAbbreviationRecursive(word, new StringBuilder(abWord),
                    start + 1, count + 1, result);

            // restart abbreviating, append the count and the current character to the string
            if (count != 0)
                abWord.append(count);
            generateAbbreviationRecursive(word,
                    new StringBuilder(abWord).append(word.charAt(start)), start + 1, 0, result);
        }
    }

        public static void main(String[] args) {
            List<String> result = UniqueGeneralizedAbbreviations.generateGeneralizedAbbreviation("BAT");
            System.out.println("Generalized abbreviation are: " + result);//["BAT", "BA1", "B1T", "B2", "1AT", "1A1", "2T", "3"]

            result = UniqueGeneralizedAbbreviations.generateGeneralizedAbbreviation("code");
            System.out.println("Generalized abbreviation are: " + result);//["code", "cod1", "co1e", "co2", "c1de", "c1d1", "c2e", "c3", "1ode", "1od1", "1o1e", "1o2", "2de", "2d1", "3e", "4"]

            result = UniqueGeneralizedAbbreviations.generateGeneralizedAbbreviationRecursive("BAT");
            System.out.println("Generalized abbreviation are: " + result);

            result = UniqueGeneralizedAbbreviations.generateGeneralizedAbbreviationRecursive("code");
            System.out.println("Generalized abbreviation are: " + result);
        }
}
