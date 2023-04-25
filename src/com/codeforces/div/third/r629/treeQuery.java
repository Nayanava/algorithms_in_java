package com.codeforces.div.third.r629;

import java.util.ArrayList;
import java.io.*;
import java.util.List;
import java.util.StringTokenizer;
 
public class treeQuery {
	static List<Integer>[] adjList;
	static int[] parent;
	
	public static void main (String [] args) throws Exception {
	    BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
	    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

	    StringTokenizer st = new StringTokenizer(f.readLine());
	    int N = Integer.parseInt(st.nextToken());
	    int M = Integer.parseInt(st.nextToken());
	    adjList = new List[N+1];
	    parent = new int[N+1];
	    timeIn = new int[N+1];
	    timeOut = new int[N+1];
	    for(int i = 0; i <= N; i++) {
	    	adjList[i] = new ArrayList<>();
		}
	    for(int i = 0; i < N-1; i++) {
	    	st = new StringTokenizer(f.readLine());
	    	int n1 = Integer.parseInt(st.nextToken());
	    	int n2 = Integer.parseInt(st.nextToken());
	    	adjList[n1].add(n2);
	    	adjList[n2].add(n1);
	    }
	    
	    dfs(1, 1);
	    loop:
	    while(M --> 0) {
	    	st = new StringTokenizer(f.readLine());
	    	int n = Integer.parseInt(st.nextToken());
	    	int curDesc = parent[Integer.parseInt(st.nextToken())];
	    	for(int i = 1; i < n; i++) {
	    		int X = parent[Integer.parseInt(st.nextToken())];
	    		if(isAncestor(X, curDesc)){
	    		} else if (isAncestor(curDesc, X)) {
	    			curDesc = X;
	    		} else {
	    			out.println("NO");
	    			continue loop;
	    		}
	    	}
	    	out.println("YES");
	    }

	    out.close();
	    f.close();
	}
	
	public static boolean isAncestor(int u, int v) { 
		return (timeIn[u] <= timeIn[v] && timeOut[v] <= timeOut[u]);
	} 
	
	static int[] timeIn;
	static int[] timeOut;
	
	static int count = 0;
	
	public static void dfs(int node, int par) {
		parent[node] = par;
		count++;
		timeIn[node] = count;
		for(int each: adjList[node]) {
			if(each != par)
				dfs(each, node);
		}
		count++;
		timeOut[node] = count;
	}
	
}