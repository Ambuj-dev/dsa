package cache;


class LRUEvictionPolicyTest {
    private static LRUEvictionPolicy<Integer> lruEvictionPolicy = new LRUEvictionPolicy<>();


    public static void main(String[] args) {
        //testKeysAreEvictedInTheOrderInWhichTheyAreAccessedAccess();
        testReaccesingKeyPreventsItFromEviction();
    }

    static void testKeysAreEvictedInTheOrderInWhichTheyAreAccessedAccess() {
        lruEvictionPolicy.keyAccessed(1);
        lruEvictionPolicy.keyAccessed(2);
        lruEvictionPolicy.keyAccessed(3);
        lruEvictionPolicy.keyAccessed(4);
        System.out.println(lruEvictionPolicy.evictKey());//1
        System.out.println(lruEvictionPolicy.evictKey());//2
        System.out.println(lruEvictionPolicy.evictKey());//3
        System.out.println(lruEvictionPolicy.evictKey());//4
    }


   static void testReaccesingKeyPreventsItFromEviction() {
        lruEvictionPolicy.keyAccessed(1);
        lruEvictionPolicy.keyAccessed(2);
        lruEvictionPolicy.keyAccessed(3);
        lruEvictionPolicy.keyAccessed(2);
        lruEvictionPolicy.keyAccessed(4);
        lruEvictionPolicy.keyAccessed(1);
        lruEvictionPolicy.keyAccessed(5);
        System.out.println(lruEvictionPolicy.evictKey());//3
        System.out.println(lruEvictionPolicy.evictKey());//2
        System.out.println(lruEvictionPolicy.evictKey());//4
        System.out.println(lruEvictionPolicy.evictKey());//1
        System.out.println(lruEvictionPolicy.evictKey());//5
    }
}