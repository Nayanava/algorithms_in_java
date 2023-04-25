package com.leetcode.hard.matrices;

import java.util.Deque;
import java.util.LinkedList;

public class PushingGame {
    Deque<Integer> q;
    int N, rowCount, totalCount = 0, boardSize;
    int[] col;
    public PushingGame(int n) {
        boardSize = n * n;
        this.N = n;
        q = new LinkedList<>();
        col = new int[n];
        rowCount = 0;
    }
    public boolean isAWin(int pushValue) {
        //let's first perform all shifts
        if (++totalCount > boardSize) {
        	totalCount = boardSize;
            if (q.pollLast() == 1) {
                col[N - 1]--;
            }
        }
        int temp = col[N - 1];
        for (int i = N-1; i >= 1; i--) {
            col[i] = col[i-1];
        }
        col[0] = temp + pushValue;
        if(pushValue == 1) {
        	if(++rowCount == N) {
        		return true; //considering the game is over once the player has won.
			}
		} else {
        	rowCount = 0;
		}
        q.addFirst(pushValue);
        return col[0] == 3;
    }

	public static void main(String[] args) {
		PushingGame game1 = new PushingGame(3);
		int[] stream1 = {1,1,1};
		for(int s :stream1) {
			System.out.println(game1.isAWin(s));
		}
		System.out.println("\n------------------------------------");
		PushingGame game2 = new PushingGame(3);
		int[] stream2 = {1,0,0,1,0,0,1};
		for(int s :stream2) {
			System.out.println(game2.isAWin(s));
		}
		System.out.println("\n------------------------------------");

		PushingGame game3 = new PushingGame(3);
		int[] stream3 = {0,0,1,1,0,1,0,0,1};
		for(int s :stream3) {
			System.out.println(game3.isAWin(s));
		}
		System.out.println("\n------------------------------------");
	}
}
