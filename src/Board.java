import java.util.*;
import java.lang.*;

public class Board {
    private char[][] board;
    private int count;


    public Board(){
        count = 1;
        board = new char[8][8];
        for (int i = 0; i < 8; i ++) {
            for (int j = 0; j < 8; j ++) {
                if ((i + j) % 2 == 0) {
                    board[i][j] = 'B';
                } else {
                    board[i][j] = 'W';
                }
            }
        }
    }

    public boolean remove(int row, int col) {
        if (boundCheck(row, col) && check(row, col)) {
            board[row][col] = ' ';
            return true;
        }
        return false;
    }

    public boolean secondRemove(int row, int col) {
        if (boundCheck(row, col) && secondCheck(row, col)) {
            board[row][col] = ' ';
            return true;
        }
        return false;
    }

    public boolean jump(int prevRow, int prevCol, int row, int col) {
        if (boundCheck(prevRow, prevCol) && boundCheck(row, col)) {
            if (accessCheck(prevRow, prevCol, row, col)) {
                board[row][col] = board[][]
            }

        }
        return false;
    }



    private boolean check(int row, int col) {
        if (row == col && ((row == 0) || (row == 3) ||  (row == 4) || (row == 7))) {
            return true;
        }
        return false;
    }

    private boolean secondCheck(int row, int col) {
        if (Math.abs(row - col) == 1) {
            int sum = row + col;
            if ((sum == 1 && board[0][0] == ' ') ||
                    ((sum == 5 || sum == 7) && board[3][3] == ' ') ||
                    ((sum == 7 || sum == 9) && board[5][5] == ' ') ||
                    (sum == 13 && board[7][7] == ' ')) {
                return true;
            }
        }
        return false;
    }

    private boolean boundCheck(int row, int col) {
        if (row < 0 || row > board.length - 1 || col < 0 || col > board[0].length - 1) {
            return false;
        }
        return true;
    }

    private boolean accessCheck(int prevRow, int prevCol, int row, int col) {
        if (prevCol == col && prevRow != row) {
            int start = Math.min(prevRow, row);
            int end = Math.max(prevRow, row);
            for (int i = start; i <= end; i ++) {

            }
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }



}

