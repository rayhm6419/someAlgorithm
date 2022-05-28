package HashMap;

import java.util.Arrays;

//in interview, design a HashMap
//HashMap:
//
//class:
//Method/ApI;
//key: String
//value: Integer
//value put(String key, Integer value)
//Integer get (String Key)
//Integer remove (String key)
//size()
//isEmpty()
//containsKey
//
//Member field:
//array of entries(Node)

//loadfactor = 0.75 -> loadfactor > 0.75 -> expand 扩容 -> rehashing
//Constructor -> initialize -> capacity

public class MyHashMap<K,v> {
    public static class Node {
        final String key;
        Integer value;
        Node next;

        Node(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    private static final int DEFAULT_CAPACITY = 16; //初始容量16
    private static final float DEFAULT_LOAD_FACTOR = 0.75f; //java 默认

    private static final float SCALE_FACTOR = 2 * 0.75f;

    private Node[] array; //存数据的地方
    private int size;
    private float loadFactor; //determine when to rehash

    public MyHashMap() { //default
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    //check the capcity and loadfactor
    //constructor
    public MyHashMap(int cap, float loadFactor) { // when it rehash, the new array for the cap and loadfactor
        if (cap <= 0) {
            throw new IllegalArgumentException("cap can not be <= 0");
        }
        //类型转换   //new 一个array
        this.array = (Node[]) (new Node[cap]); //初始化一下array
        this.size = 0;
        this.loadFactor = loadFactor;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() { //to clear all the value in the HashMap
        Arrays.fill(this.array, null);
        size = 0;
    }

    private int hash(String key) { //算出hash number
        // null key
        if (key == null) {
            return 0;
        }
        return key.hashCode() & 0X7FFFFFFF; //guarantee non-negative
        //HashCode()
        //int code = key.hashCode()
        //return code >= 0 ? code : -code;
        //int range =[-2^31, 2^31-1]
        //-Integer.MIN_VALUE = Integer.MIN_VALUE; ->overflow.
        //Reason: java's % return remainder rather than modulus.The remainder can be negative
    }

    //when you know the hashCode % length -> get the index of the bucket
    private int getIndex(String key) {
        return hash(key) % array.length;
    }

    private boolean equalsValue(Integer v1, Integer v2) {
        // v1, v2 all possibly to be null
        if (v1 == null && v2 == null) { //如果在比较里，两个value都是null，那他们就相等
            return true;
        }
        if (v1 == null || v2 == null) { //如果其中一个null return false
            return false;
        }
        return v1.equals(v2); //如果都有value就比较，传进来的value和HashMap里的value是否相等

        //上面的简化
        //return v1== v2 || v1 != null && v1.equals(v2);
    }

    //this is a traverse linked list in the array
    //O(N) -> 所以比较少用，因为想用Hashmap的目的都是因为希望能在O（1）时间里
    public boolean containsValue(Integer value) {
        if (isEmpty()) {
            return false;
        }
        for (Node node : array) {
            while (node != null) {
                //check if the value equals()
                //value, node.getValue() all possible to the null
                if (equalsValue(node.value, value)) { //传进来的value和HashMap里的value是否相等
                    return true;
                }
                node = node.next;
            }
        }
        return false;
    }

    //sameThing as equalValue -> equalsKey is what we always use
    private boolean equalsKeys(String k1, String k2) {
        // v1, v2 all possibly to be null
        if (k1 == null && k2 == null) { //如果在比较里，两个value都是null，那他们就相等
            return true;
        }
        if (k1 == null || k2 == null) { //如果其中一个null return false
            return false;
        }
        return k1.equals(k2); //如果都有value就比较，传进来的value和HashMap里的value是否相等

        //上面的简化
        //return v1== v2 || v1 != null && v1.equals(v2);

    }

    //average O(1)
    public boolean containsKey(String key){
        //get the index of the key
        int index = getIndex(key); //直接拿到index，在对应的index linked list里遍历
        Node node = array[index];
        while  (node != null){
            if (equalsKeys(node.key, key)){
                return true;
            }
            node = node.next;
        }
        return false;
    }
    public Integer get (String key){
        int index = getIndex(key);
        Node node = array[index];
        while (node != null){
            if (equalsKeys(node.key, key)){
                return node.value;
            }
            node = node.next;
        }
        return null;
    }
    //insert/ update
    //if the key already exists, return the old corresponding value
    //if the key not exists, return null
    public Integer put (String key, Integer value) {
        //找到对应bucket
        int index = getIndex(key);
        Node head = array[index];
        Node node = head;

        //在对应的bucket里遍历
        while (node != null) {
            //如果找到了，更新
            if (equalsKeys(node.key, key)) {
                Integer result = node.value;
                node.value = value;
                return result;
            }
            node = node.next;

        }
        //append the new node before the head and update the new head
        //insert operation
        Node newNode = new Node(key, value);
        newNode.next = head;
        array[index] = newNode; //new head is here
        size++;
        if (needRehashing()){ //如果loadFacotr超过0.75
            rehashing();
        }
        return null;
    }
    private boolean needRehashing(){
        //float loadFactor;
        float ratio = (size + 0.0f) / array.length;
        return ratio >= loadFactor;
    }
    private void rehashing(){
        //new double sized array
        //for each node in the old array，
        // put (add to head of linked list) to the new larger array
        Node[] oldArray = array;
        array = (Node[]) (new Node[array.length *  2]); //* 0.75 ] ); //* SCALE_FACTOR
        for (Node node : oldArray){
            while   (node != null){
                //所有node的hashcode都需要被recalculate，并不能一整个linked list拿到新的bucket里
                Node next = node.next;
                int index = getIndex(node.key); //get当前index的node ->新array的index
                node.next = array[index]; //原来的接到新的array的投
                array[index] = node; //加完不要忘了指到头,
                node = next;        //再把下一个接到头前面
            }
        }

    }
    public Integer remove (String key){
        //get index
        //delete operation on the linked list
        //size --
        int index = getIndex(key);
        Node node = array[index];
        Node pre = null;
        while (node != null){
            if (equalsKeys(node.key, key)){
                if (pre != null){
                    pre.next = node.next;
                } else {
                    array[index] = node.next;
                }
                size--;
                return node.value;
            }
            pre = node;
            node = node.next;
        }
        return null;
    }
}
