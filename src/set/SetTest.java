package set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetTest {
    public static void main(String[] args) {
        Set<List> set = new HashSet<>();
        set.add(List.of("abc","xyz"));
        set.add(List.of("abc","xyz"));
        set.add(List.of("abc","xyzy"));
        set.add(List.of("abc"));
        System.out.println(set.size());
    }
}
