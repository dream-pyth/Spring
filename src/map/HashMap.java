package map;

public class HashMap<K, V> implements Map<K, V> {

    Entry<K, V>[] table = null;

    int size = 0;

    public HashMap() {
        table = new Entry[16];
    }


    /**
     * 1、K 算出哈希值，取模
     * 2、数组下标的位置 对象是否为空
     * 3、如果为空，则没有冲突，直接可以用数组来存储
     * 4、如果不为空，则表示冲突，这个时候用链表来存储
     * 5、然后返回 V
     * @param k
     * @param v
     * @return
     */
    @Override
    public V put(K k, V v) {
        int index = hash(k);
        Entry<K, V> entry = table[index];
        if (entry == null) {
            table[index] = new Entry<>(k, v, index, null);
            size++;
            // 数组来存储
        } else {
            table[index] = new Entry<>(k, v, index, entry);
            // 链表来存储
        }

        return table[index].getValue();
    }

    public int hash(K k) {
        int i = k.hashCode()%15;
        return i>=0 ? i:-i;
    }

    @Override
    public V get(K k) {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    class Entry<K, V> implements Map.Entry<K, V> {

        K k; V v; int hash; Entry<K, V> next;

        public Entry(K k, V v, int hash, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.hash = hash;
            this.next = next;
        }

        @Override
        public K getKey() {
            return null;
        }

        @Override
        public V getValue() {
            return null;
        }
    }
}
