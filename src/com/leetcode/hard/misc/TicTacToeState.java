package com.leetcode.hard.misc;

public class TicTacToeState {
    public boolean validTicTacToe(String[] board) {
        return match(board);
    }

    private boolean match(String[] board) {
        int row[] = new int[3];
        int col[] = new int[3];
        int countX = 0, countY = 0;
        for(String r : board) {
            for(char c : r.toCharArray()) {
                if(c == 'X') {
                    countX++;
                } else if(c == 'O') {
                    countY++;
                }
            }
        }
        if(countX > 5 || countY > 4) {
            return false;
        }
        if(countX < countY || countX - countY > 1) {
            return false;
        }
        int diag = 0, antiDiag = 0;
        for(int i = 0; i < board.length; i++) {
            int count = 0, n = board[i].length();
            for(int j = 0; j < n; j++) {
                if(board[i].charAt(j) == ' ') {
                    continue;
                }
                int val = (board[i].charAt(j) == 'O') ? -1 : 1;
                count += val;
                if(i == j) diag += val;
                if(j == n-1-i) antiDiag += val;
                row[i] += val;
                col[j] += val;
            }
        }
        boolean xMatch = false, yMatch = false;
        for(int i = 0; i < 3; i++) {
            if(Math.abs(row[i]) == 3) {
                xMatch = xMatch || (row[i] == 3);
                yMatch = yMatch || (row[i] == -3);
            }
            if(Math.abs(col[i]) == 3) {
                xMatch = xMatch || (col[i] == 3);
                yMatch = yMatch || (col[i] == -3);
            }
        }
        if(Math.abs(diag) == 3) {
            xMatch = xMatch || (diag == 3);
            yMatch = yMatch || (diag == -3);
        }
        if(Math.abs(antiDiag) == 3) {
            xMatch = xMatch || (antiDiag == 3);
            yMatch = yMatch || (antiDiag == -3);
        }
        if(xMatch && yMatch) {
            return false;
        }
        if(xMatch && countX <= countY) {
            return false;
        }
        if(yMatch && countY > countX) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] board = {"XO ","XO ","XO "};
        TicTacToeState ttts = new TicTacToeState();
        System.out.println(ttts.validTicTacToe(board));
    }
}
