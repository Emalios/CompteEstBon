public class Pair<K, V> {

    private K first;
    private V secound;

    public Pair(K first, V secound) {
        this.first = first;
        this.secound = secound;
    }

    public K _1() {
        return first;
    }

    public V _2() {
        return secound;
    }

    @Override
    public String toString() {
        return "(" + first + "," + secound + ")";
    }
}
