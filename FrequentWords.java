package HashTable;

import java.util.*;

public class FrequentWords {
    public static   String[] topKFrequent(String[] combo, int k) {
        //coner case check
        if (combo == null || k == 0) {
            return new String[0];
        }
        //对应step 1 -> loop the map, get the frequence of each word or char in the map
        Map<String, Integer> freqMap = getFreq(combo);
        //step 2 -> minHeap, get the top k frequence word
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare (Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2){
                return o1.getValue().compareTo(o2.getValue()); //by default, minHeap
            }
        });
        //traverse the recored Map
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()){
            if (minHeap.size() < k){
                minHeap.offer(entry);
            } else if (entry.getValue() > minHeap.peek().getValue()) {
                minHeap.poll();
                minHeap.offer(entry);
            }
        }
        return freqArray(minHeap);

    }
    //step 1: create a HashMap
    private static HashMap<String, Integer> getFreq (String[] combo){
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : combo){
            Integer freq = map.get(s); //设置每个字符在combo里get到的次数
            if (freq == null){ //如果次数是空
                map.put(s,1); // = 1
            } else {
                map.put(s, freq + 1); //不是空，freq + 1 = counter + 1
            }
        }
        return map;
    }
    private static String[] freqArray(PriorityQueue<Map.Entry<String, Integer>> minheap){
        String[] result = new String[minheap.size()];
        for (int i = minheap.size() - 1; i >= 0; i--){
            result[i] = minheap.poll().getKey();
        }
        return result;
    }

    public static void main(String[] args) {
        String[] compo = {"a", "a", "b", "b", "b", "b", "c", "c", "c", "d"};
        String[] re = topKFrequent(compo,2);
        System.out.println(Arrays.toString(re));
    }
}

