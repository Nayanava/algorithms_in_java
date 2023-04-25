package com.leetcode.hard.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomisedTrie {
    //Step 1. Create Trie and Node
    static class Trie {
        Node root;
        List<String> words;
        Trie(String[] words) {
        	root = new Node();
        	this.words = new ArrayList<>();
        	for(String word : words) {
        		buildTree(word);
			}
		}
		//Step 2: Build the tree for a word
		public void buildTree(String word) {
			Node temp = root;
			words.add(word);
			for (int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				if (temp.children[c - 'a' + 1] == null) {
					temp.children[c - 'a' + 1] = new Node();
				}
				int index = (i == word.length() -1 ) ? 0 : c -'a' + 1;
				temp.wordCount[index] += 1;
				temp = temp.children[c - 'a' + 1];
			}
			temp.endOfWord = true;
			temp.wordIndex = words.size() - 1;
		}

		//Step 3: Find a random String
		public String getRandomWord() {
			Node temp = root;
			int preSum[] = preSum(temp.wordCount);
			Random random = new Random();
			int next = random.nextInt(preSum[26]);
			System.out.println(next);
			if(next == 0) {
				next = 1;
			}
			while (true) {
				int index = wordIndex(preSum, next);
				System.out.println(index);
				if (next == 1 && temp.children[index].endOfWord) {
					return words.get(temp.children[index].wordIndex);
				}
				temp = temp.children[index];
				next = next - preSum[index];
				preSum = preSum(temp.wordCount);
			}
		}

		//Step 3.1: Find the presum
		private int[] preSum(int[] arr) {
			int[] sum = new int[27];
			for (int i = 1; i < 27; i++) {
				sum[i] = sum[i-1] + arr[i];
			}
			return sum;
		}

		//Step 3.2: Find the character in which the word lies.
		private int wordIndex(int[] arr, int key) {
        	for(int index : arr)
        		System.out.print(index + " ");
        	System.out.println(key);
			int low = 1, high = arr.length - 1;
			while (low <= high) {
				int mid = (low + high) >>> 1;
				if (arr[mid] >= key) {
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			}
			return low;
		}
    }

    static class Node {
        boolean endOfWord;
        int wordIndex;
        int[] wordCount;
        Node[] children;

        Node() {
            wordCount = new int[27];
            children = new Node[27];
        }
    }

    public static void main(String[] args) {
    	String[] words = {"all", "able", "bale", "are", "ares"};
    	Trie trie = new Trie(words);
    	RandomisedTrie randomisedTrie = new RandomisedTrie();
    	for(int i = 0; i < 10; i++) {
			System.out.println(trie.getRandomWord());
		}

	}
}
