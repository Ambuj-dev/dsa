package trie;

public class Trie1 {
    private Node1 root;

    //Initialize your data structure here

    Trie1() {
        root = new Node1();
    }


    //Inserts a word into the trie

    public void insert(String word) {
        Node1 node = root;
        for(int i = 0;i<word.length();i++) {
            if(!node.containsKey(word.charAt(i))) {
                node.put(word.charAt(i), new Node1());
            }
            node = node.get(word.charAt(i));
            node.increasePrefix();
        }
        node.increaseEnd();
    }


    public int countWordsEqualTo(String word) {
        Node1 node = root;
        for(int i = 0;i<word.length();i++) {
            if(node.containsKey(word.charAt(i))) {
                node = node.get(word.charAt(i));
            }
            else {
                return 0;
            }
        }
        return node.getEnd();
    }

    public int countWordsStartingWith(String word) {
        Node1 node = root;
        for(int i = 0;i<word.length();i++) {
            if(node.containsKey(word.charAt(i))) {
                node = node.get(word.charAt(i));
            }
            else {
                return 0;
            }
        }
        return node.getPrefix();
    }

    public void erase(String word) {
        Node1 node = root;
        for(int i = 0;i<word.length();i++) {
            if(node.containsKey(word.charAt(i))) {
                node = node.get(word.charAt(i));
                node.reducePrefix();
            }
            else {
                return;
            }
        }
        node.deleteEnd();
    }
    public static void main(String args[]) {
        Trie1 T=new Trie1();
        T.insert("apple");
        T.insert("apple");
        T.insert("apps");
        T.insert("apps");
        String word1 = "apps";
        System.out.println("Count Words Equal to "+word1+" "+T.countWordsEqualTo(word1));
        String word2 = "abc";
        System.out.println("Count Words Equal to "+word2+" "+T.countWordsEqualTo(word2));
        String word3 = "ap";
        System.out.println("Count Words Starting With "+word3+" "+T.countWordsStartingWith
                (word3));
        String word4 = "appl";
        System.out.println("Count Words Starting With "+word4+" "+T.countWordsStartingWith
                (word4));
        T.erase(word1);
        System.out.println("Count Words equal to "+word1+" "+T.countWordsEqualTo(word1));

    }
}
