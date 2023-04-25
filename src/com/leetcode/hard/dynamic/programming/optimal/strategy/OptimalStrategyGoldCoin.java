package com.leetcode.hard.dynamic.programming.optimal.strategy;

public class OptimalStrategyGoldCoin {
    private String optimalGameStrategy(String coins) {
        if(coins.isEmpty())
            return "";
        int total = 0;
        if (coins.charAt(0) == 'G' || coins.charAt(coins.length()-1) == 'G')
            return "First";
        for(char c : coins.toCharArray()) {
            if(c == 'G')
                continue;
            total++;
        }
        return total % 2 == 0 ? "Second" : "First";
    }

    public static void main(String[] args) {
        String coins = "SGSSS";
        OptimalStrategyGoldCoin osgc = new OptimalStrategyGoldCoin();
        System.out.print(osgc.optimalGameStrategy(coins));
    }
}
