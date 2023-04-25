///* IMPORTANT: Multiple classes and nested static classes are supported */
//
///*
// * uncomment this if you want to read input.
////imports for BufferedReader
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//
////import for Scanner and other utility classes
//import java.util.*;
//*/
//
//// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail
//import java.util.*;
//class TestClass {
//    public static void main(String args[] ) throws Exception {
//        /* Sample code to perform I/O:
//         * Use either of these methods for input
//
//        //BufferedReader
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String name = br.readLine();                // Reading input from STDIN
//        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT
//
//        //Scanner
//        Scanner s = new Scanner(System.in);
//        String name = s.nextLine();                 // Reading input from STDIN
//        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT
//
//        */
//
//        // Write your code here
//        Scanner s = new Scanner(System.in);
//        int T = s.nextInt();
//        while(T-- > 0) {
//            int N = s.nextInt();
//            int M = s.nextInt();
//            int arr[][] = new int[N][M];
//            for(int i = 0; i < N; i++) {
//                for(int j = 0; j < M; j++) {
//                    arr[i][j] = 1;
//                }
//            }
//            int X = s.nextInt();
//            while(X-- > 0) {
//                int L = s.nextInt()-1;
//                int R = s.nextInt()-1;
//                for(int i = 0; i < N; i++) {
//                    arr[i][R] = 0;
//                }
//                for(int i = 0; i < M; i++) {
//                    arr[L][i] = 0;
//                }
//            }
//
//            int partition = 0;
//            int maxCount = 0;
//            int minCount = M*N;
//            for(int i = 0; i < N; i++) {
//                for(int j = 0; j < M; j++) {
//                    if(arr[i][j] == 1) {
//                        currCount = 0;
//                        dfs(arr, i, j);
//                        partition++;
//                        if(currCount < minCount) {
//                            minCount = currCount;
//                        } else if(maxCount < currCount) {
//                            maxCount = currCount;
//                        }
//                    }
//                }
//            }
//            System.out.println(partition + " " + minCount + " " + maxCount);
//        }
//    }
//    private static final int row[] = {0, 0, -1, 1};
//    private static final int col[] = {-1, 1, 0, 0};
//
//    static int currCount = 0;
//    private static void dfs(int[][] grid, int i, int j) {
//        if(grid[i][j] == 0)
//            return;
//
//        grid[i][j] = 0;
//        for(int k = 0; k < 4; k++) {
//            int currRow = i + row[k];
//            int currCol = j + col[k];
//
//            if(isSafe(grid, currRow, currCol)) {
//                currCount += 1;
//                dfs(grid, currRow, currCol);
//            }
//        }
//    }
//    private static boolean isSafe(int[][] grid, int i, int j) {
//        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] != 0;
//    }
//}
