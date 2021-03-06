import java.util.*;

public class Agent {

    int limitDepth;
    boolean isMinmax;
    int numOfEval;
    int numOfBranches;
    int numOfNodes;
    int cutOffs;


    public Agent(int limitDepth, boolean isMinmax) {
        this.limitDepth = limitDepth;
        this.isMinmax = isMinmax;

        numOfEval = 0;
        numOfBranches = 0;
        numOfNodes = 0;
        cutOffs = 0;
    }

    public Board findRoute(Board board) {
        if (isMinmax) {
            return minmaxDecision(board);
        } else {
            return alphaBetaSearch(board);
        }
    }

    public Board minmaxDecision(Board board) {
        List<Board> successors = board.getSuccessors();

        int heuristic = Integer.MIN_VALUE;
        Board ret = null;
        numOfNodes++;


        for (Board successor : successors) {
            numOfBranches++;

            int tempHeuristic = minmaxValue(successor, 2);
            if (tempHeuristic > heuristic) {
                heuristic = tempHeuristic;
                ret = successor;
            }
        }

        return ret;
    }


    public Board alphaBetaSearch(Board board) {

        ArrayList<Board> successors = new ArrayList<Board>(board.getSuccessors());
        int heuristic = Integer.MIN_VALUE;
        Board ret = null;
        numOfNodes++;

        for (Board successor : successors) {
            numOfBranches++;

            int tempHeuristic = alphaBetaValue(successor, 2, Integer.MIN_VALUE, Integer.MAX_VALUE);

            if (tempHeuristic > heuristic) {

                heuristic = tempHeuristic;
                ret = successor;
            }
        }
        return ret;
    }

    public int getNumOfEval() {
        return numOfEval;
    }

    public int getAverageBranches() {
        if (numOfNodes == 0) {
            return 0;
        }
        return numOfBranches / numOfNodes;
    }

    public int getCutOffs() {
        return cutOffs;
    }


    private int minmaxValue(Board curBoard, int curDepth) {
        List<Board> successors = curBoard.getSuccessors();
        int numOfSuccessors = successors.size();
        int heuristic = numOfSuccessors;
        if (curDepth <= limitDepth && numOfSuccessors != 0) {
            numOfNodes++;
            numOfBranches += numOfSuccessors;

            boolean isMax = (curDepth % 2 == 1);
            heuristic = heuristicFunc(curBoard, numOfSuccessors);
            if (isMax) {
                heuristic = Integer.MIN_VALUE;
                for (Board successor : successors) {
                    heuristic = Math.max(heuristic, minmaxValue(successor, curDepth + 1));
                }
            } else {
                heuristic = Integer.MAX_VALUE;
                for (Board successor : successors) {
                    heuristic = Math.min(heuristic, minmaxValue(successor, curDepth + 1));
                }
            }
        }
        return heuristic;
    }

    private int alphaBetaValue(Board curBoard, int curDepth, int alpha, int beta) {
        List<Board> successors = new ArrayList<Board>(curBoard.getSuccessors());
        int numOfSuccessors = successors.size();
        int heuristic = numOfSuccessors;
        if (curDepth <= limitDepth && numOfSuccessors != 0) {

            numOfNodes++;
            numOfBranches += numOfSuccessors;

            boolean isMax = (curDepth % 2 == 1);
            heuristic = heuristicFunc(curBoard, numOfSuccessors);
            if (isMax) {
                heuristic = Integer.MIN_VALUE;
                for (Board successor : successors) {
                    heuristic = Math.max(heuristic, alphaBetaValue(successor, curDepth + 1, alpha, beta));
                    if (heuristic >= beta) {
                        cutOffs++;
                        return heuristic;
                    }
                    alpha = Math.max(alpha, heuristic);
                }
            } else {
                heuristic = Integer.MAX_VALUE;
                for (Board successor : successors) {
                    heuristic = Math.min(heuristic, alphaBetaValue(successor, curDepth + 1, alpha, beta));
                    if (heuristic <= alpha) {
                        cutOffs++;
                        return heuristic;
                    }
                    beta = Math.min(beta, heuristic);
                }
            }
        }
        return heuristic;
    }


    private int heuristicFunc(Board curBoard, int numOfSuccessors) {
        numOfEval++;

        int ret = numOfSuccessors;
        char identity = 'B';
        char opponent = 'W';
        if (curBoard.getCount() % 2 == 0) {
            identity = 'W';
            opponent = 'B';
        }

        char[][] grids = curBoard.getBoard();

        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids[0].length; j++) {
                if ((i == 1 && grids[0][j] == opponent && grids[1][j] == identity && grids[2][j] == ' ') ||
                        (i == 6 && grids[7][j] == opponent && grids[6][j] == identity && grids[5][j] == ' ')) {
                    ret++;
                }
                if ((j == 1 && grids[i][0] == opponent && grids[i][1] == identity && grids[i][2] == ' ') ||
                        (j == 6 && grids[i][7] == opponent && grids[i][6] == identity && grids[i][5] == ' ')) {
                    ret++;
                }


            }
        }

        if (grids[0][0] == identity && ((grids[0][1] == opponent && grids[0][2] == ' ') || (grids[1][0] == opponent && grids[2][0] == ' '))) {
            ret++;
        }
        if (grids[7][0] == identity && ((grids[7][1] == opponent && grids[7][2] == ' ') || (grids[6][0] == opponent && grids[5][0] == ' '))) {
            ret++;
        }
        if (grids[0][7] == identity && ((grids[0][6] == opponent && grids[0][5] == ' ') || (grids[1][7] == opponent && grids[2][7] == ' '))) {
            ret++;
        }
        if (grids[7][7] == identity && ((grids[6][7] == opponent && grids[5][7] == ' ') || (grids[7][6] == opponent && grids[7][5] == ' '))) {
            ret++;
        }

        return ret;
    }
}