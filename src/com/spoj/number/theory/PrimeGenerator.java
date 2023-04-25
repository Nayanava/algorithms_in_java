package com.spoj.number.theory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
            PRIME1 - Prime Generator
            https://www.spoj.com/problems/PRIME1/
 */
public class PrimeGenerator {
    public static void main(String[] args) {
        int[] primes = populateMaxPrimes();
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-- > 0) {
            System.out.println("");
            int start = s.nextInt();
            int end = s.nextInt();
            if(start < 2) {
                start = 2;
            }
            boolean arr[] = new boolean[1000003];
            for(int i = 0;  primes[i] * primes[i] <= end; i++) {
                int prime = primes[i];
                int left = (start/prime) * prime;
                left += left < start ? prime : 0;
                for(int j = left; j <= end; j += prime) {
                    arr[j-start] = true;
                }
                if(left == prime) {
                    arr[left-start] = false;
                }
            }
            int res = 0;
            for(int i = start; i <= end; i++) {
                if(!arr[i-start]) {
                    res++;
                    //System.out.println(i);
                }
            }
            System.out.println(res);
        }
    }

    private static int[] populateMaxPrimes() {
        int[] primes = new int[3433];
        int index = 0;
        boolean[] nonPrimeSieves = new boolean[32001];
        for(int i = 2; i*i <= 32000; i++) {
            if(!nonPrimeSieves[i]) {
                for (int j = i * i; j <= 32000; j += i) {
                    nonPrimeSieves[j] = true;
                }
            }
        }
        for(int i = 2; i < 32001; i++) {
            if(!nonPrimeSieves[i]) {
                primes[index++] = i;
            }
        }
        return primes;
    }
}
