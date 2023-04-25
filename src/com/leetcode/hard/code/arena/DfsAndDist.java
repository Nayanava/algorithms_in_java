class Solution {
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        Path[][] dp = new Path[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                dp[i][j] = new Path();
            }
        }
        //0 to store value, 1 to store
        dp[0][0].count = grid[0][0];
        for(int i = 1; i < n; i++) {
            if(grid[i][0] != -1) {
                dp[i][0].count = dp[i-1][0].count + grid[i][0];
                dp[i][0].i = i-1;
                dp[i][0].j = 0;
            }
        }
        for(int j = 1; j < n; j++) {
            if(grid[0][j] != -1) {
                dp[0][j].count = dp[0][j-1].count + grid[0][j];
                dp[0][j].i = 0;
                dp[0][j].j = j-1;
            }
        }

        for(int i = 1; i < n; i++) {
            for(int j = 1; j < n; j++) {
                if(grid[i][j] != -1) {
                    if(dp[i-1][j].count > dp[i][j-1].count) {
                        dp[i][j].count += dp[i-1][j].count;
                        dp[i][j].i = i-1;
                        dp[i][j].j = j;
                    }else {
                        dp[i][j].count += dp[i][j-1].count;
                        dp[i][j].i = i;
                        dp[i][j].j = j-1;
                    }
                    dp[i][j].count += grid[1][j];
                }
            }
        }
        int res = dp[n-1][n-1].count;
        int i = n-1, j = n-1;

        while(i != 0 && j != 0) {
            grid[i][j] = 0;
            i = dp[i][j].i;
            j = dp[i][j].j;
        }

        int resDP[][] = new int[n][n];
        resDP[n-1][n-1] = grid[n-1][n-1];
        for(i = n-2; i >= 0; i--) {
            if(grid[i][0] != -1) {
                resDP[i][0] = grid[i][0] + resDP[i+1][0];
            }
        }
        for(j = n-2; j >= 0; j--) {
            if(grid[i][j] != -1) {
                resDP[0][j] = grid[0][j] + resDP[0][j+1];
            }
        }

        for(i = n-2; i >= 0; i--) {
            for(j = n-2; j >= 0; j--) {
                if(grid[i][j] != -1) {
                    resDP[i][j] = Math.max(resDP[i+1][j], resDP[i][j+1]) + grid[i][j];
                }
            }
        }
        return resDP[0][0] + res;
    }

    class Path{
        int count;
        int i, j;
    }
}