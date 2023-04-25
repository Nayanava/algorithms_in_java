package com.leetcode.hard.design;

import java.util.HashMap;
import java.util.Map;

class LFUCache {
    int minFreq;
    Map<Integer, Integer> keyFreqMap;
    Map<Integer, LinkedList.Node> keyNodeMap;
    Map<Integer, LinkedList> freqListMap;
    int cacheSize;
    public LFUCache(int capacity) {
        cacheSize = capacity;
        keyFreqMap = new HashMap<>();
        keyNodeMap = new HashMap<>();
        freqListMap = new HashMap<>();
    }
    
    public int get(int key) {
        if(!keyNodeMap.containsKey(key)) {
            return -1;
        }
        LinkedList.Node node = keyNodeMap.get(key);
        upgradeFreq(key);
        return node.val;
    }
    
    public void put(int key, int value) {
        //new key
        if(!keyFreqMap.containsKey(key)) {
            if(keyNodeMap.size() >= cacheSize) {
                LinkedList.Node delNode = freqListMap.get(minFreq).removeLast();
                keyNodeMap.remove(delNode.key);
                keyFreqMap.remove(delNode.key);
                if(freqListMap.get(minFreq).head == null) {
                    freqListMap.remove(minFreq);
                    minFreq++;
                }
            }
            minFreq = 1;
            LinkedList.Node node = freqListMap.computeIfAbsent(minFreq, x -> new LinkedList())
                .addFirst(key, value);
            keyFreqMap.put(key, minFreq);
            keyNodeMap.put(key, node);
        } else {
            LinkedList.Node node = keyNodeMap.get(key);
            node.val = value;
            upgradeFreq(key);
        }
    }
    
    private void upgradeFreq(int key) {
        LinkedList.Node node = keyNodeMap.get(key);
        int freq = keyFreqMap.get(key);
        freqListMap.get(freq).remove(node);
        keyFreqMap.put(key, freq+1);
        freqListMap.computeIfAbsent(freq+1, x -> new LinkedList()).addFirst(node);
        if(freqListMap.get(freq).head == null) {
            minFreq += 1;
            freqListMap.remove(freq);
        }
    }

    class LinkedList {
        Node head, rear;
        LinkedList() {
            this.head = this.rear = null;
        }
        public Node addFirst(int key, int val) {
            Node node = new Node(key, val);
            return addFirst(node);
        }
        public Node addFirst(Node node) {
            if(head == null) {
                return head = rear = node;
            }
            node.next = head;
            head.prev = node;
            return head = node;
        }
        
        public Node remove(Node node) {
            if(node == rear) {
                if(head == rear) {
                    head = rear = null;
                } else {
                    rear.prev.next = null;
                    rear = rear.prev;
                }
            } else if(head == node) {
                head = node.next;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            node.next = node.prev = null;
            return node;
        }
        
        public Node removeLast() {
            return remove(rear);
        }
        
        class Node {
            int key, val;
            Node next, prev;
            Node(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }
    }
    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
//        cache.put(1, 5);
//        cache.put(1, 2);
//
//        System.out.println(cache.get(2));

    }
}