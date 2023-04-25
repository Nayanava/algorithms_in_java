/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
import java.util.*;
*/

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

import java.util.*;

class TestClass {
    //static int res = 0;
    public static void main(String args[] ) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        //Scanner
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();                 // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        */

        // Write your code here

        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        Set<Integer>[] adj = new Set[n];
        for(int i = 0; i < n; i++) {
            adj[i] = new HashSet<>();
        }
        while(m-- > 0) {
            int u = s.nextInt();
            int v = s.nextInt();
            adj[u-1].add(v-1);
            adj[v-1].add(u-1);
        }
        boolean visited[] = new boolean[n];
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(i+1);
            dfs(adj,i, visited, 0, n, list, res);
        }
//        for(List<Integer> list : res) {
//            for(int ints : list) {
//                System.out.print(ints + " ");
//            }
//            System.out.println();
//        }
    }

    static void dfs(Set<Integer>[] adj, int i, boolean[] visited, int count, int n, List<Integer> path, List<List<Integer>> res) {
        if(count == n-1) {
            System.out.println("start index = " + i);
            path.forEach(p -> System.out.print(p + " "));
            System.out.println();
            res.add(new ArrayList<>(path));
            return;
        }
        Set<Integer> list = adj[i];
        visited[i] = true;
        for(Integer u : list) {
            if(visited[u])
                continue;
            path.add(u+1);
            dfs(adj, u, visited, count+1, n, path, res);
            path.remove(path.size()-1);
        }
        visited[i] = false;
    }
}
