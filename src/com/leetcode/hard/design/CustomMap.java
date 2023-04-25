package com.leetcode.hard.design;

import java.util.HashMap;
import java.util.Map;

class CustomMap {
    Map<Integer, KeyInfo> map = new HashMap<>();
    int timer = 0;
    KeyInfo allSameValue;

    public void set(int key, int value) {
        map.put(key, new KeyInfo(value, timer++));
    }

    public int get(int key) {
    	if(!map.containsKey(key)) {
    		return -1;
		}
        KeyInfo keyInfo = map.get(key);
        if (allSameValue != null && allSameValue.timestamp > keyInfo.timestamp) {
            return allSameValue.value;
        }
        return keyInfo.value;
    }

    public void setAll(int value) {
        if (allSameValue == null) {
            allSameValue = new KeyInfo(value, timer++);
        } else {
            allSameValue.value = value;
            allSameValue.timestamp = timer++;
        }
    }

    class KeyInfo {
        int value;
        int timestamp;

        KeyInfo(int value, int timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }

    public static void main(String[] args) {
    	CustomMap customMap = new CustomMap();
    	customMap.set(4, 12);
		customMap.set(3, 13);
		System.out.println("before");
		for(int i = 1; i <= 5; i++) {
			System.out.println( i + " = " + customMap.get(i));
		}
		customMap.setAll(31);
		customMap.set(5, 15);
		customMap.set(1, 1);
		System.out.println("before");
		for(int i = 0; i <= 5; i++) {
			System.out.println( i + " = " + customMap.get(i));
		}
		customMap.set(2, 52);
	}
}
