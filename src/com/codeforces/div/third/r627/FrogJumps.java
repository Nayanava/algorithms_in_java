package com.codeforces.div.third.r627;

import java.util.Scanner;

public class FrogJumps {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while(T-- > 0) {
            String s = scanner.next();
            char[] arr = new char[s.length()+1];
            arr[0] = 'R';
            int index = 1;
            for(char c : s.toCharArray()) {
                arr[index++] = c;
            }
            int lo = 0, hi = arr.length;
            while(lo < hi) {
                int mid = (lo + hi) >>> 1;
                if(canJump(arr, mid)) {
                    hi = mid;
                } else {
                    lo = mid+1;
                }
            }
            System.out.println(lo);
        }
    }

    private static boolean canJump(char[] arr, int mid) {
        int farthest = -1, nextJump = -1, oldFarthest = -1;
        for(int i = 0; i < mid; i++) {
            if(arr[i] == 'R') {
                oldFarthest = farthest = i;
                nextJump = i;
            }
        }
        for(int i = nextJump; i < nextJump + mid && i < arr.length; i++) {
            if(arr[i] == 'R') {
                farthest = Math.max(farthest, mid + i);
            }
            if(i == nextJump) {
                if(farthest == oldFarthest) {
                    return false;
                }
                nextJump = farthest;
                oldFarthest = farthest;
            }
            if(farthest >= arr.length) {
                return true;
            }
        }
        return farthest >= arr.length;
    }
}
