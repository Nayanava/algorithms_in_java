package com.leetcode.hard.backtracking;

import java.util.ArrayList;
import java.util.List;

public class StrobogrammaticNumbers {

    private List<Pair> pairs = new ArrayList<>();
    private List<Pair> samePairs = new ArrayList<>();
    private void printAllSNumbers(int n, int start, char[] str, List<String> result) {
        if(start == (n+1)/2) {
            result.add(String.valueOf(str));
            return;
        }

        int index = 0;
        if(start <= ((n-1)>>1)) {
            if((n-start-1 == start)) {
               for(Pair pair : samePairs) {
                   str[start] = pair.key;
                   printAllSNumbers(n, start + 1, str, result);
               }
            }
            else{
                for (Pair pair : pairs) {
                    if (start == 0 && index == 0) {
                        index++;
                        continue;
                    }
                    str[start] = pair.key;
                    str[n - 1 - start] = pair.value;
                    printAllSNumbers(n, start + 1, str, result);
                }
            }
        }
    }

    class Pair {
        char key, value;
        Pair(){}

        Pair(char key, char value) {
            this.key = key;
            this.value = value;
        }
    }

    private void populatePairs() {
        pairs.add(new Pair('0', '0'));
        pairs.add(new Pair('1', '1'));
        pairs.add(new Pair('6', '9'));
        pairs.add(new Pair('8', '8'));
        pairs.add(new Pair('9', '6'));

        samePairs.add(new Pair('0', '0'));
        samePairs.add(new Pair('1', '1'));
        samePairs.add(new Pair('8', '8'));
    }

    public static void main(String[] args) {
        StrobogrammaticNumbers sn = new StrobogrammaticNumbers();
        sn.populatePairs();
        int n = 3;
        char[] arr = new char[n];
        List<String> result = new ArrayList<>();
        sn.printAllSNumbers(n, 0, arr, result);
        result.stream().forEach(string -> System.out.print( string + " "));

    }
}
