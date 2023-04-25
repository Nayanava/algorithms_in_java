package com.leetcode.hard.dynamic.programming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ShoppingOffers {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return dfs(price, special, needs, needs.size());
    }

    private int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int index) {
        if (index == 0) {
            return calculateTotal(price, needs);
        }
        if (!moreItems(special, index - 1, needs)) {
            return dfs(price, special, needs, index - 1);
        }
        int excl = dfs(price, special, needs, index - 1);

        List<Integer> list = special.get(index - 1);
        List<Integer> temp = new ArrayList<>(needs);
        for (int i = 0; i < temp.size(); i++) {
            temp.set(i, temp.get(i) - list.get(i));
        }
        int incl = dfs(price, special, temp, index) + list.get(list.size() - 1);
        return Math.min(incl, excl);
    }

    private boolean moreItems(List<List<Integer>> special, int index, List<Integer> needs) {
        List<Integer> list = special.get(index);
        for (int i = 0; i < needs.size(); i++) {
            if (needs.get(i) < list.get(i)) {
                return false;
            }
        }
        return true;
    }

    private int calculateTotal(List<Integer> price, List<Integer> needs) {
        int total = 0;
        for (int i = 0; i < price.size(); i++) {
            total += price.get(i) * needs.get(i);
        }
        return total;
    }

    public static void main(String[] args) {
        List<Integer> price = Arrays.asList(2, 5);
        List<List<Integer>> special = new ArrayList<>();
        special.add(Arrays.asList(3, 0, 5));
        special.add(Arrays.asList(1, 2, 10));
        List<Integer> needs = Arrays.asList(3, 2);

        ShoppingOffers so = new ShoppingOffers();
        System.out.println(so.shoppingOffers(price, special, needs));
    }
}