package com.codeforces.educational.round84;

import java.util.*;

public class PrincesAndPrincesses {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int T = s.nextInt();
        while(T-- > 0 ) {
            int n = s.nextInt();
            int[] D = new int[n+1];
            int[] K = new int[n+1];
            List<Integer>[] dMap = new List[n+1];
            for(int i = 1; i <= n; i++) {
                int k = s.nextInt();
                dMap[i] = new ArrayList<>(k);
                for(int j = 0; j < k; j++) {
                    dMap[i].add(s.nextInt());
                }
            }
            Set<Integer> taken = new HashSet<>();
            for(int i = 1; i <= n; i++) {
                for(int k = 0; k < dMap[i].size(); k++) {
                    if(taken.add(dMap[i].get(k))) {
                        D[i] = dMap[i].get(k);
                        K[dMap[i].get(k)] = i;
                        break;
                    }
                }
            }
            int prince = -1, princess = -1;
            for(int i = 1; i <= n; i++) {
                if(D[i] == 0 && princess == -1) {
                    princess = i;
                }
                if(K[i] == 0 && prince == -1) {
                    prince = i;
                }
            }
            if(prince == -1 && princess == -1)
                System.out.println("OPTIMAL");
            else {
                System.out.println("IMPROVE");
                System.out.println(princess + " " + prince);
            }
        }
    }
}
