package com.leetcode.hard.graph;

import java.util.*;

public class WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if(dict.contains(beginWord))
            dict.remove(beginWord);
        Map<String, List<String>> graph = new HashMap<>();
        Set<String> startSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        startSet.add(beginWord);
        endSet.add(endWord);
        biDirectionalBFS(startSet, endSet, dict, graph, false);
        //bfs(startSet, dict, endWord, graph);

        List<List<String>> result = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.add(beginWord);
        dfs(result, list, beginWord, endWord, graph);

        return result;
    }

    void bfs(Set<String> set, Set<String> dict,String endWord, Map<String, List<String>> map) {
        if(set.size() == 0)
            return;
        dict.removeAll(set);
        boolean foundWord = false;
        Set<String> temp = new HashSet<>();
        for(String str : set) {
            List<String> words = adjWords(str, dict);
            map.put(str, words);
            temp.addAll(words);
            if(temp.contains(endWord)) {
                foundWord = true;
            }
        }
        if(foundWord)
            return;
        bfs(temp, dict, endWord, map);
    }

    private void biDirectionalBFS(Set<String> startSet, Set<String> endSet, Set<String> dict, Map<String, List<String>> graph, boolean flip) {
        if(startSet.size() == 0)
            return;
        if(startSet.size() > endSet.size()) {
            biDirectionalBFS(endSet, startSet, dict, graph, !flip);
            return;
        }
        Set<String> tmp = new HashSet<>();
        boolean foundWord = false;
        dict.removeAll(startSet);
//        dict.removeAll(endSet);
        for(String str : startSet) {
            List<String> words = adjWords(str, dict);
            tmp.addAll(words);
            if(!flip)
                graph.put(str, words);
            else {
                for(String word : words) {
                    graph.putIfAbsent(word, new ArrayList<>());
                    graph.get(word).add(str);
                }
            }
            for(String word : words) {
                if(endSet.contains(word)) {
                    foundWord = true;
                }
            }
        }
        if(foundWord)
            return;
        biDirectionalBFS(tmp, endSet, dict, graph, flip);
    }
    private void dfs(List<List<String>> result, List<String> list, String beginWord, String endWord, Map<String, List<String>> graph) {
        if(beginWord.equals(endWord)) {
            result.add(new ArrayList<>(list));
            return;
        }
        if(!graph.containsKey(beginWord))
            return;

        for(String node : graph.get(beginWord)) {
            list.add(node);
            dfs(result, list, node, endWord, graph);
            list.remove(list.size()-1);
        }
    }

    private List<String> adjWords(String str, Set<String> dict) {
        char[] chs = str.toCharArray();
        List<String> list = new ArrayList<>();
        for(int i = 0; i < chs.length; i++) {
            char old = chs[i];
            for(char c = 'a'; c <= 'z'; c++) {
                chs[i] = c;
                String newWord = String.valueOf(chs);
                if(!dict.contains(newWord)) {
                    continue;
                }
                if(!newWord.equals(str))
                    list.add(newWord);
            }
            chs[i] = old;
        }
        return list;
    }

    public static void main(String[] args) {
        String beginWord = "a";
        String endWord ="c";
        String[] words= {"a", "b", "c"};
        List<String> wordsList = Arrays.asList(words);
        WordLadderII wl = new WordLadderII();
        System.out.print(wl.findLadders(beginWord, endWord, wordsList));
    }
}
