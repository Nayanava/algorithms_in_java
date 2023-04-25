//import java.util.Arrays;
//import java.util.List;
//
//class Solution {
//    int maxSum = 0;
//    int mod = (int)1e9+7;
//    public int[] pathsWithMaxScore(List<String> board) {
//        int m = board.size();
//        int n = board.get(0).length();
//        char[][] charBoard = new char[m][n];
//
//        for(int i = 0; i < m; i++) {
//            for(int j = 0; j < board.get(i).length(); j++) {
//                charBoard[i][j] = board.get(i).charAt(j);
//            }
//        }
//        charBoard[m-1][n-1] = '0';
//        charBoard[0][0] = '0';
//
//        int dp[][][] = new int[m][n][2];
//        dp[m-1][n-1][0] = 0;
//        dp[m-1][n-1][1] = 1;
//        for(int i = m-2; i >= 0; i--) {
//            dp[i][n-1][0] = (dp[i+1][n-1][1] == 0 || charBoard[i][n-1] == 'X') ? 0 : dp[i+1][n-1][0] + charBoard[i][n-1] - '0';
//            dp[i][n-1][1] = charBoard[i][n-1] == 'X' ? 0 : dp[i+1][n-1][1];
//        }
//
//        for(int j = n-2; j >= 0; j--) {
//            dp[m-1][j][0] = (dp[m-1][j+1][1] == 0 || charBoard[m-1][j] == 'X') ? 0 : dp[m-1][j+1][0] + charBoard[m-1][j] - '0';
//            dp[m-1][j][1] = charBoard[m-1][j] == 'X' ? 0 : dp[m-1][j+1][1];
//        }
//
//        for(int i = m-2; i >= 0; i--) {
//            for(int j = n-2; j >= 0; j--) {
//                if(charBoard[i][j] == 'X')
//                    dp[i][j][0] = dp[i][j][1] = 0;
//                else {
//                    dp[i][j][0] = Math.max(dp[i+1][j+1][0], Math.max(dp[i+1][j][0], dp[i][j+1][0]));
//                    dp[i][j][1] = (dp[i+1][j+1][0] == dp[i][j][0] ? dp[i+1][j+1][1] : 0) +
//                            (dp[i+1][j][0] == dp[i][j][0] ? dp[i+1][j][1] : 0) +
//                            (dp[i][j+1][0] == dp[i][j][0] ? dp[i][j+1][1] : 0);
//                    dp[i][j][0] += + charBoard[i][j] - '0';
//                }
//            }
//        }
//        for(int i = 0; i < n; i++) {
//            System.out.println(dp[0][i][0] + " " + dp[0][i][1]);
//        }
//        return dp[0][0];
//    }
//
//    public static void main(String[] args) {
//        List<String> list = Arrays.asList(new String[] {"E11","XXX","11S"});
//        Solution sol = new Solution();
//        sol.pathsWithMaxScore(list);
//    }
//}