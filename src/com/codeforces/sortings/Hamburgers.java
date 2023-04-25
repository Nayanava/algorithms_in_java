package com.codeforces.sortings;

import java.util.Scanner;

public class Hamburgers {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String str = s.next();
        int items[] = new int[26];
        int price[] = new int[26];
        char[] chs = {'B', 'S', 'C'};
        for(int i = 0; i < 3; i++) {
            items[chs[i] - 'A'] = s.nextInt();
        }
        for(int i = 0; i < 3; i++) {
            price[chs[i] - 'A'] = s.nextInt();
        }
        int[] req = new int[26];
        for(int i = 0; i < str.length(); i++) {
            req[str.charAt(i) - 'A']++;
        }
        long amount = s.nextLong();
        long res = 0;
        while(amount > 0) {
            long total = 0;
            for(int i = 0; i < 3; i++) {
                if(items[chs[i] - 'A'] >= req[chs[i] - 'A']) {
                    items[chs[i] - 'A'] -= req[chs[i] - 'A'];
                } else {
                    total += price[chs[i] - 'A'] * (req[chs[i] - 'A'] - items[chs[i] - 'A']);
                    items[chs[i] - 'A'] -= req[chs[i] - 'A'] > items[chs[i] - 'A'] ? items[chs[i] - 'A'] : req[chs[i] - 'A'];
                }
            }
            if(total > amount) {
                break;
            }
            res ++;
            amount -= total;
            if((req[chs[0] - 'A'] == 0 || items[chs[0] - 'A'] <= 0) &&
                    (req[chs[1] - 'A'] == 0 || items[chs[1] - 'A'] <= 0) &&
                    (req[chs[2] - 'A'] == 0 || items[chs[2] - 'A'] <= 0)) {
                long reqTotal = 0;
                for(int i = 0; i < 3; i++) {
                    reqTotal += price[chs[i] - 'A']*req[chs[i]- 'A'];
                }
                res += (amount / reqTotal);
                break;
            }
        }
        System.out.println(res);
    }
}
