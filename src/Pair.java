public class Pair<K, V> {

    private K first;
    private V secound;

    public Pair(K first, V secound) {
        this.first = first;
        this.secound = secound;
    }

    public K getFirst() {
        return first;
    }

    public V getSecound() {
        return secound;
    }
}
