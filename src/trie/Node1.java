package trie;

public class Node1 {
    Node1 links[] = new Node1[26];
    int cntEndWith = 0;
    int cntPrefix = 0;

    public Node1() {
    }

    boolean containsKey(char ch) {
        return (links[ch - 'a'] != null);
    }
    Node1 get(char ch) {
        return links[ch-'a'];
    }
    void put(char ch, Node1 node) {
        links[ch-'a'] = node;

    }
    void increaseEnd() {
        cntEndWith++;
    }
    void increasePrefix() {
        cntPrefix++;
    }
    void deleteEnd() {
        cntEndWith--;
    }
    void reducePrefix() {
        cntPrefix--;
    }
    int getEnd() {
        return cntEndWith;
    }
    int getPrefix() {
        return cntPrefix;
    }
}
