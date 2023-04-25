package com.spoj.number.theory;

import java.util.Scanner;

public class FCTRLFactorial {
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-- > 0) {
            System.out.println("");
            int n = s.nextInt();
            System.out.print(countZeros(n));
        }
    }

    private static int countZeros(int n) {
        int res = 0;
        for(int i = 5; n/i > 0; i *= 5) {
            res += n/i;
        }
        return res;
    }
}
