package com.leetcode.hard.backtracking;

import java.io.*;
import java.util.*;

public class Sudoku {


    static boolean sudokuSolve(char[][] board) {
        int n = board.length;
        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(board[i][j] == '.'){

                    count++;
                }
            }
        }
        if(count == 0){
            return true;
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(board[i][j] == '.'){
                    for(char k='1';k<='9';k++){
                        if(isSafe(board, k,i,j)){
                            board[i][j] = k;
                            if(sudokuSolve(board)){
                                return true;
                            }
                            else{
                                board[i][j] = '.';
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    static boolean isSafe(char[][] board, char k, int r, int c){
        int n = board.length;
        for(int j=0;j<n;j++){
            if(board[r][j] == k){
                return false;
            }
        }
        for(int i=0;i<n;i++){
            if(board[i][c] == k){
                return false;
            }
        }
        int startI = r - r % 3;
        int startJ = c - c%3;
        int endI = startI + 2;
        int endJ = startJ + 2;
        for(int i=startI;i<=endI;i++){
            for(int j=startJ;j<=endJ;j++){
                if(board[i][j] == k){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {{'.','.','.','7','.','.','3','.','1'},{'3','.','.','9','.','.','.','.','.'},{'.','4','.','3','1','.','2','.','.'},{'.','6','.','4','.','.','5','.','.'},{'.','.','.','.','.','.','.','.','.'},{'.','.','1','.','.','8','.','4','.'},{'.','.','6','.','2','1','.','5','.'},{'.','.','.','.','.','9','.','.','8'},{'8','.','5','.','.','4','.','.','.'}};
        sudokuSolve(board);
//        char c = (char) (1 + '0');
//        System.out.println(c);
//        for(int i = 0; i < board.length; i++) {
//            for(int j = 0; j < board[0].length; j++) {
//                System.out.print(board[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

}
