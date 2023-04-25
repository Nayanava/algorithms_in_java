package com.codeforces.graphs;

public class CountInvitees {
    public static void main(String[] args) {
        for(int x = 0; x <= 33; x++) {
            for(int y = 0; y <= 50; y++) {
                for(int z = 0; z <= 300; z++) {
                    if(x + y + z == 100 && 9*x + 6*y + z == 300) {
                        System.out.println(x + " " + y + " " + z);
                        break;
                    }
                }
            }
        }
    }
}
