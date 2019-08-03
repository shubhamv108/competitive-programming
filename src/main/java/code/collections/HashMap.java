package code.collections;

import java.util.TreeMap;

public class HashMap<K, V> {

    int size;
    int bucketCount = 16;
    float bucketCountThreshold = 0.75f;
    int treefyThresholdForBucketItems = 8;
    NodeMap<K, V>[] arr = new NodeMap[bucketCount];

    HashMap() {}
    HashMap(float bucketCountThreshold) {
        this.bucketCountThreshold = bucketCountThreshold;
    }
    HashMap(int initialBucketCount, float bucketCountThreshold) {
        this(bucketCountThreshold);
        this.bucketCount = initialBucketCount;
    }
    HashMap(int initialBucketCount, float bucketCountThreshold, int treefyThresholdForBucketItems) {
        this(initialBucketCount, bucketCountThreshold);
        this.treefyThresholdForBucketItems = treefyThresholdForBucketItems;
    }
    HashMap(int treefyThresholdForBucketItems) {
        this.treefyThresholdForBucketItems = treefyThresholdForBucketItems;
    }

    void put(K k, V v) {
        if (size >= 0.75 * bucketCount)
            increaseBuckets();
        int hashCode = k.hashCode();
        int bucketNo = hashCode % bucketCount;
        NodeMap<K, V> bucketItem = arr[bucketNo];
        if (bucketItem == null) {
            arr[bucketNo] = new MapNode<>(k, v);
            size++;
        } else {
            if (bucketItem instanceof TreeMapNode) {
                ((RBTreeMap) bucketItem).put(k, v);
                size++;
            } else {
                int bucketItemCount = 0;
                MapNode bucketListItem = (MapNode) bucketItem;
                while (bucketListItem != null) {
                    if (++bucketItemCount == treefyThresholdForBucketItems) {
                        arr[bucketNo] = treefyAndInsert(bucketNo, k, v);
                        size++;
                        break;
                    }
                    if (bucketListItem.k.equals(k)) {
                        bucketListItem.v = v;
                        break;
                    }
                    if (bucketListItem.next == null) {
                        bucketListItem.next = new MapNode<>(k, v);
                        size++;
                        break;
                    }
                    bucketListItem = bucketListItem.next;
                }
            }
        }
    }

    private void increaseBuckets() {
        NodeMap[] arrCopy = new NodeMap[bucketCount * 2];
        for (int i = 0; i < bucketCount; i++)
            arrCopy[i] = arr[i];
        arr = arrCopy;
        bucketCount *= 2;
    }

    NodeMap<K, V> treefyAndInsert(int bucketNo, K k, V v) {
        RBTreeMap<K, V> tree = treefy(bucketNo);
        tree.put(k, v);
        return tree;
    }

    RBTreeMap<K, V> treefy(int bucketNo) {
        NodeMap<K, V> bucketItem = arr[bucketNo];
        RBTreeMap<K, V> rbTreeMap = new RBTreeMap<>();
        MapNode<K, V> bucketListItem = (MapNode) bucketItem;
        while (bucketListItem != null) {
            rbTreeMap.put(bucketListItem.k, bucketListItem.v);
            bucketListItem = bucketListItem.next;
        }
        return rbTreeMap;
    }

    V get(K k) {
        int hashCode = k.hashCode();
        int bucketNo = hashCode % bucketCount;
        NodeMap<K, V> bucketItem = arr[bucketNo];
        if (bucketItem instanceof RBTreeMap) {
            return (V) ((RBTreeMap) bucketItem).get(k);
        } else {
            MapNode<K, V> buckListItem = (MapNode<K, V>) bucketItem;
            if (buckListItem != null) {
                while (buckListItem != null) {
                    if (buckListItem.k.equals(k))
                        return buckListItem.v;
                    buckListItem = buckListItem.next;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        HashMap<Integer, Integer> m = new HashMap<>(1, 1, 0);
        m.put(1, 1);
        m.put(2, 1);
        m.put(3, 1);
        m.put(4, 1);
        m.put(5, 2);
        System.out.println(m.get(5));
    }

}


interface NodeMap<K, V> {}

class MapNode<K, V> implements NodeMap<K, V> {
    K k;
    V v;
    MapNode<K, V> next;
    MapNode(K k, V v) {
        this.k = k;
        this.v = v;
    }
}

class TreeMapNode<K, V> extends MapNode<K, V> {
    TreeMapNode<K, V> left;
    TreeMapNode<K, V> right;
    TreeMapNode(K k, V v) {
        super(k, v);
    }
}

class RBTreeMap<K, V> implements NodeMap {

    TreeMapNode<K, V> root;

    /**
     * Replace java.util.TreeMap with own implementation of RBTree
     */
    TreeMap<K, V> tree = new TreeMap<>();

    /**
     * ToDo
     *
     * @param k
     * @param v
     */
    void put(K k, V v) {
        TreeMapNode<K, V> n = new TreeMapNode<>(k, v);

        tree.put(k, v);

    }

    V get(K k) {
        return tree.get(k);
    }
}
