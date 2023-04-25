package com.codechef.longchallenge.april;

import java.util.*;
import java.lang.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef {
	public static void main (String[] args) throws java.lang.Exception {
		Scanner s = new Scanner(System.in);
		int T = s.nextInt();
		while(T-- > 0) {
			int n = s.nextInt(), prev = -1, i = 1;
			for(; i <= n; i++) {
				int curr = s.nextInt();
				if(curr == 1) {
					if(prev != -1 && i - prev < 6) {
						break;
					} else {
						prev = i;
					}
				}
			}
			System.out.print( i == n+1 ? "NO" : "YES");
		}
	}
}