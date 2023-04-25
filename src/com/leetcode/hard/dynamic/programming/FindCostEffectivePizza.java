package com.leetcode.hard.dynamic.programming;

import java.util.HashMap;
import java.util.Map;

public class FindCostEffectivePizza {
	Map<String, Integer> map = new HashMap<>();
    public int findClosestPricedPizza(int[] pizza, int[] toppings, int price) {
        int closest = 0;
        for (int i = 0; i < pizza.length; i++) {
            int amount = closestPrice(toppings, toppings.length, price - pizza[i], 2) + pizza[i];
            if(Math.abs(price - closest) > Math.abs(price - amount)) {
            	closest = amount;
			}
        }
        return closest;
    }

    private int closestPrice(int top[], int n, int price, int k) {
        if (k < 0 || n == 0) {
            return 0;
        }
        String key = n + "_" + price + "_" + k;
        if(map.containsKey(key)) {
        	return map.get(key);
		}
        int excl = closestPrice(top, n - 1, price, k);
        int incl = closestPrice(top, n - 1, price - top[n-1], k - 1) + top[n - 1];

		int res = Math.abs(price - excl) > Math.abs(price - incl) ? incl : excl;
        map.put(key, res);
        return res;
    }

    public static void main(String[] args) {
    	int[] pizza = {800, 850, 900};
    	int[] toppings = {50, 100, 150};
    	int price = 1000;
    	FindCostEffectivePizza fcep = new FindCostEffectivePizza();
    	System.out.println(fcep.findClosestPricedPizza(pizza, toppings, price));
	}
}
