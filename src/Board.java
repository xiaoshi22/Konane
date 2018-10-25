import java.util.*;
import java.lang.*;

public class Board {
    private int count;
    private int[][] directions;


    public char[][] getBoard() {
        return board;
    }

    private char[][] board;

    public int getCount() {
        return count;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                stringBuilder.append(board[i][j]);
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public Board() {
        directions = new int[][]{{0,2}, {2,0},{0,-2},{-2,0}};//right, down, left, up
        count = 1;
        board = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    board[i][j] = 'B';
                } else {
                    board[i][j] = 'W';
                }
            }
        }
    }

    public Board(char[][] board, int count) {
        this.board = board;
        directions = new int[][]{{0,2}, {2,0},{0,-2},{-2,0}};//right, down, left, up
        this.count = count;
    }

    public boolean remove(int row, int col) {
        if (boundCheck(row, col) && check(row, col)) {
            board[row][col] = ' ';
            count ++;
            return true;
        }
        return false;
    }

    public boolean secondRemove(int row, int col) {
        if (boundCheck(row, col) && secondCheck(row, col)) {
            board[row][col] = ' ';
            count++;
            return true;
        }
        return false;
    }

    public boolean jump(int prevRow, int prevCol, int row, int col) {
        if (boundCheck(prevRow, prevCol) && boundCheck(row, col) && accessCheck(prevRow, prevCol, row, col)) {
            count ++;
            char original = board[prevRow][prevCol];
            if (original == ' ') {
                System.out.println("null here!!!!");
            }
            if (prevCol == col) {
                int start = Math.min(prevRow, row);
                int end = Math.max(prevRow, row);
                for (int i = start; i <= end; i++) {
                    board[i][col] = ' ';
                }
            } else {
                int start = Math.min(prevCol, col);
                int end = Math.max(prevCol, col);
                for (int i = start; i <= end; i++) {
                    board[row][i] = ' ';
                }
            }
            board[row][col] = original;
            return true;
        }
        return false;
    }

    public List<Board> getSuccessors(){
        List<Board> ret = new ArrayList<>();
        char[][] tempBoard = twoDemArrayClone(board);

        char identity = 'B';
        if (count%2 ==0){
            identity = 'W';
        }

        for (int i = 0; i< board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if (board[i][j] == identity){
                    for(int[] direction : directions) {
                        int tempX = i;
                        int tempY = j;
                        while (true){
                            tempX += direction[0];
                            tempY += direction[1]; 
                            if (jump(tempX-direction[0],tempY-direction[1],tempX, tempY)) {
                                ret.add(new Board(board, count));
                                count --;
                            } else {
                                break;
                            }
                        }
                        board = twoDemArrayClone(tempBoard);
                    }
                }
            }
        }
        return ret;
    }


    private boolean check(int row, int col) {
        if (row == col && ((row == 0) || (row == 3) || (row == 4) || (row == 7))) {
            return true;
        }
        return false;
    }

    private boolean secondCheck(int row, int col) {
        if (Math.abs(row - col) == 1) {
            int sum = row + col;
            if ((sum == 1 && board[0][0] == ' ') ||
                    ((sum == 5 || sum == 7) && board[3][3] == ' ') ||
                    ((sum == 7 || sum == 9) && board[4][4] == ' ') ||
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
        if (board[prevRow][prevCol] == ' ' || board[row][col] != ' ') {
            return false;
        }

        if (prevCol == col && prevRow != row && ((prevRow - row) % 2 == 0)) {
            int start = Math.min(prevRow, row);
            int end = Math.max(prevRow, row);
            boolean flag = true;
            for (int i = start + 1; i < end; i++) {
                if ((flag && board[i][col] == ' ') || (!flag && board[i][col] != ' ')) {
                    return false;
                }
                flag = !flag;
            }
            return true;
        } else if (prevRow == row && prevCol != col && (prevCol - col) % 2 == 0) {
            int start = Math.min(prevCol, col);
            int end = Math.max(prevCol, col);
            boolean flag = true;
            for (int i = start + 1; i < end; i++) {
                if ((flag && board[row][i] == ' ') || (!flag && board[row][i] != ' ')) {
                    return false;
                }
                flag = !flag;
            }
            return true;
        } else {
            return false;
        }

    }

    private char[][] twoDemArrayClone(char[][] a) {
        char[][] b = new char[a.length][];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i].clone();
        }
        return b;
    }


}

