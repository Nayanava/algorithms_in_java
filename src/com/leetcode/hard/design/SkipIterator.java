package com.leetcode.hard.design;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class SkipIterator implements Iterator<Integer> {

	Map<Integer, Integer> skipMap = new HashMap<>();
	Integer intBuffer;
	Iterator<Integer> it;
	public SkipIterator(Iterator<Integer> it) {
		this.it = it;
	}

	public boolean hasNext() {
		while(it.hasNext()) {
			intBuffer = it.next();
			if(!skipMap.containsKey(intBuffer)) {
				break;
			}
			skipMap.put(intBuffer, skipMap.get(intBuffer) - 1);
			if(skipMap.get(intBuffer) == 0) {
				skipMap.remove(intBuffer);
			}
			intBuffer = null;
		}
		return it.hasNext() || intBuffer != null;
	}

	public Integer next() {
		if(intBuffer == null) {
			if(!hasNext()) {
				throw new UnsupportedOperationException();
			}
		}
		int temp = intBuffer;
		intBuffer = null;
		return temp;
	}

	/**
	* The input parameter is an int, indicating that the next element equals 'val' needs to be skipped.
	* This method can be called multiple times in a row. skip(5), skip(5) means that the next two 5s should be skipped.
	*/ 
	public void skip(int val) {
		skipMap.put(val, skipMap.getOrDefault(val,0)+1);
	}

	public static void main(String[] args) {
		SkipIterator itr = new SkipIterator(Arrays.asList(new Integer[]{2, 3, 5, 6, 5, 7, 5, -1, 5, 10}).iterator());
		System.out.println(itr.hasNext()); // true
		System.out.println(itr.next()); // returns 2
		itr.skip(5);
		System.out.println(itr.next()); // returns 3
		System.out.println(itr.next()); // returns 6 because 5 should be skipped
		System.out.println(itr.next()); // returns 5
		itr.skip(5);
		itr.skip(5);
		System.out.println(itr.next()); // returns 7
		System.out.println(itr.next()); // returns -1
		System.out.println(itr.next()); // returns 10
		System.out.println(itr.hasNext()); // false
		System.out.println(itr.next());
	}
}