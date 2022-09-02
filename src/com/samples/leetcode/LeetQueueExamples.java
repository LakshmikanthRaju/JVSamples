package com.samples.leetcode;

import java.util.*;

public class LeetQueueExamples {
    private static LeetQueueExamples examples = new LeetQueueExamples();

    private LeetQueueExamples() {}

    public static LeetQueueExamples getInstance() {
        return examples;
    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> maps = new HashMap<>();
        for (String word: words) {
            maps.put(word, maps.getOrDefault(word, 0)+1);
        }

        Queue<String> pq = new PriorityQueue<>((w1, w2) -> Objects.equals(maps.get(w1), maps.get(w2)) ? w1.compareTo(w2): maps.get(w2)-maps.get(w1));
        for (String key: maps.keySet()) {
            pq.offer(key);
            System.out.println(key + " " + maps.get(key));
        }
        List<String> result = new ArrayList<>();
        for (int i=0; i<k; i++) {
            result.add(pq.poll());
        }
        return result;
    }

    public void testSamples() throws Exception {
        System.out.println(topKFrequent(new String[]{"i","love","leetcode","i","love","coding"}, 2));
    }
}
