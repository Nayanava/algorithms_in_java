package com.leetcode.hard.arrays;
class PrimePalindrome {
    static int total = 14142, index = 0;
    static boolean[] nonPrimes = new boolean[total+1];
    static int primes[] = new int[3000];
    static {
        for(int i = 2; i*i <= total; i++) {
            for(int j = i*i; j <= total; j += i) {
                nonPrimes[j] = true;
            }
        }
        for(int i = 2; i <= total; i++) {
            if(!nonPrimes[i])
                primes[index++] = i;
        }
    }
    public int primePalindrome(int N) {
        if(N < 2) {
            N = 2;
        }
        while(N < 2*total*total) {
            boolean arr[] = new boolean[100001];
            int right = N + 100000;
            for(int i = 0; i < index; i++) {
                int start = (N/primes[i])*primes[i];
                if(start < N) {
                    start += primes[i];
                }
                for(int j = start; j <= right; j += primes[i]) {
                    if(j == primes[i]) {
                        continue;
                    }
                    arr[j-N] = true;
                }
            }
            for(int i = 0; i <= right-N; i++) {
                if(!arr[i] && isPalindrome(N + i)) {
                    return N+i;
                }
            }
            N = right+1;
        }
        return -1;
    }

    private boolean isPalindrome(int a) {
        String s = String.valueOf(a);
        for(int start = 0, end = s.length()-1; start < end; start++, end--) {
            if(s.charAt(start) != s.charAt(end)) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int N = 100000;
        PrimePalindrome pp = new PrimePalindrome();
        System.out.println(pp.primePalindrome(N));
    }
}