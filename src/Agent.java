import java.util.List;

public class Agent {
    int limitDepth;
    int cutOffs;
    int numOfEval;
    boolean isMinmax;

    public Agent(int limitDepth, boolean isMinmax){
        cutOffs = 0;
        this.limitDepth = limitDepth;
        this.isMinmax = isMinmax;
    }

    public Board findRoute(Board board){
        if (isMinmax){
            return minmaxDecision(board);
        } else {
            return alphaBetaSearch(board);
        }
    }

    public Board minmaxDecision(Board board) {
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }



        List<Board> successors = board.getSuccessors();

        int utility = Integer.MIN_VALUE;
        Board ret = null;
        for(Board successor: successors){
            int tempUtility = minmaxValue(successor, 2);
            if (tempUtility > utility){
                utility = tempUtility;
                ret = successor;
            }
        }
        return ret;
    }


    public Board alphaBetaSearch(Board board){
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        cutOffs = 0;
        List<Board> successors = board.getSuccessors();
        int utility = Integer.MIN_VALUE;
        Board ret = null;
        for(Board successor: successors){
            int tempUtility = alphaBetaValue(successor, 2, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (tempUtility > utility){
                utility = tempUtility;
                ret = successor;
            }
        }
        return ret;
    }

    public int getCutOffs() {
        return cutOffs;
    }

    private int minmaxValue(Board board, int curDepth){
        List<Board> successors = board.getSuccessors();
        int utility = successors.size();
        if(curDepth <= limitDepth && utility != 0) {
            boolean isMax = (curDepth % 2 == 1);
            if (isMax) {
                utility = Integer.MIN_VALUE;
                for (Board successor : successors) {
                    utility = Math.max(utility, minmaxValue(successor, curDepth + 1));
                }
            } else {
                utility = Integer.MAX_VALUE;
                for (Board successor : successors) {
                    utility = Math.min(utility, minmaxValue(successor, curDepth + 1));
                }
            }
        }
        return utility;
    }



    private int alphaBetaValue(Board board, int curDepth, int alpha, int beta){
        List<Board> successors = board.getSuccessors();
        int utility = successors.size();
        if(curDepth<= limitDepth && utility != 0) {
            boolean isMax = (curDepth % 2 == 1);
            if (isMax) {
                utility = Integer.MIN_VALUE;
                for (Board successor : successors) {
                    utility = Math.max(utility, alphaBetaValue(successor, curDepth + 1, alpha, beta));
                    if (utility >= beta) {
                        cutOffs ++;
                        return utility;
                    }
                    beta = Math.max(alpha, utility);
                }
            } else {
                utility = Integer.MAX_VALUE;
                for (Board successor : successors) {
                    utility = Math.min(utility, alphaBetaValue(successor, curDepth + 1, alpha, beta));
                    if (utility <= alpha){
                        cutOffs ++;
                        return utility;
                    }
                    beta = Math.min(beta, utility);
                }
            }
        }
        return utility;
    }
}
