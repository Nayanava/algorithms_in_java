package com.leetcode.hard.backtracking;

import java.util.*;

public class SynonymousWords {
    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        DSU dsu = new DSU();
        for(List<String> pair : synonyms) {
            dsu.union(pair.get(0), pair.get(1));
        }
        Map<String, List<String>> cache = new HashMap<>();
        for(List<String> pair : synonyms) {
            if(!cache.containsKey(pair.get(0))) {
                cache.put(pair.get(0), new ArrayList<>(dsu.wordsMap.get(dsu.find(pair.get(0)))));
            }
            if(!cache.containsKey(pair.get(1))) {
                cache.put(pair.get(1), new ArrayList<>(dsu.wordsMap.get(dsu.find(pair.get(1)))));
            }
        }

        String[] words = text.split(" ");
        Queue<Integer> indices = new LinkedList<>();
        TreeSet<String> seen = new TreeSet<>();
        Map<Integer, String> indexMap = new HashMap<>();
        int index = 0;
        for(String word : words) {

            if(cache.containsKey(word)) {
                indices.offer(index);
                indexMap.put(index, word);
            }
            index++;
        }
        seen.add(text);
        Queue<String[]> q = new LinkedList<>();
        q.offer(words);
        while(!indices.isEmpty()) {
            index = indices.poll();
            int size = q.size();
            while(size-- > 0) {
                words = q.poll();
                q.offer(Arrays.copyOf(words, words.length));
                for(String str : cache.get(indexMap.get(index))) {
                    words[index] = str;
                    String word = String.join(" ", words);
                    if(seen.add(word)) {
                        q.offer(Arrays.copyOf(words, words.length));
                    }
                }
            }
        }
        return new ArrayList<>(seen);
    }

    class DSU {
        Map<String, String> map = new HashMap<>();
        Map<String, Set<String>> wordsMap = new HashMap<>();
        DSU() {

        }
        public void union(String a, String b) {
            String pA = find(a);
            String pB = find(b);
            if(pA.equals(pB)) {
                return;
            }
            map.put(pA, pB);
            wordsMap.computeIfAbsent(pA, x-> new HashSet<>()).remove(a);
            wordsMap.computeIfAbsent(pB, x-> new HashSet<>()).add(a);
            wordsMap.computeIfAbsent(pB, x-> new HashSet<>()).add(b);
        }

        public String find(String a) {
            if(!map.containsKey(a)) {
                return a;
            }
            while(map.containsKey(a) && !map.get(a).equals(a)) {
                a = map.get(a);
            }
            return a;
        }
    }
}
