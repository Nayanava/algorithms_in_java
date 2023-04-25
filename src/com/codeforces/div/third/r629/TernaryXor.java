package com.codeforces.div.third.r629;

import java.util.Scanner;

public class TernaryXor {
    public static void main(String[] args)  {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-- > 0) {
            int n = s.nextInt();
            s.nextLine();
            String str = s.nextLine();
            int[] a = new int[n];
            int[] b = new int[n];
            boolean greater = false;
            for (int i = 0; i < n; i++) {
                int val = str.charAt(i) - '0';
                if(i == 0) {
                    if(val == 0) {
                        a[i] = 2; b[i] = 1;
                        greater = true;
                    } else if( val == 1) {
                        a[i] = b[i] = 2;
                    } else {
                        a[i] = b[i] = 1;
                    }
                } else {
                    if(val == 0) {
                        a[i] = b[i] = 0;
                    } else if(val == 1) {
                        if(!greater) {
                            a[i] = 1;
                            b[i] = 0;
                            greater = true;
                        } else {
                            a[i] = 0;
                            b[i] = 1;
                        }
                    } else {
                        if (!greater) {
                            a[i] = 1;
                            b[i] = 1;
                        } else {
                            a[i] = 0;
                            b[i] = 2;
                        }
                    }
                }
            }
            for(int i = 0; i < n; i++) {
                System.out.print(a[i]);
            }
            System.out.println();
            for(int i = 0; i < n; i++) {
                System.out.print(b[i]);
            }
            System.out.println();
        }
    }
}
