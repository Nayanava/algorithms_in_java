package com.codeforces.div.third.r617;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class YetAnotherWalkingRobot {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while (T-- > 0) {
            int n = s.nextInt();
            char[] arr = s.next().toCharArray();
            Map<String, Integer> map = new HashMap<>();
            Map<Character, int[]> moves = new HashMap<>();
            moves.put('L', new int[]{0, -1});
            moves.put('R', new int[]{0, 1});
            moves.put('U', new int[]{-1, 0});
            moves.put('D', new int[]{1, 0});
            int r = 0, c = 0, res = n+1, end = -1;
            map.put("0_0", 0);
            for (int i = 1; i <= n; i++) {
                int[] move = moves.get(arr[i-1]);
                r += move[0];
                c += move[1];
                String key = r + "_" + c;
                if(map.containsKey(key)) {
                    if(res > i - map.get(key)) {
                        res = i - map.get(key);
                        end = i;
                    }
                }
                map.put(key, i);
            }
            System.out.println(end == -1 ? end : (end - res + 1) + " " + end);
        }
    }
}
