import java.util.List;

public class Agent {
    int limitDepth;
    int cutOffs;

    public Agent(int limitDepth){
        cutOffs = 0;
        this.limitDepth = limitDepth;
    }

    public Board minmaxDecision(Board board) {
        List<Board> successors = board.getSuccessors();
        int utility = Integer.MIN_VALUE;
        Board ret = null;
        for(Board successor: successors){
            int tempUtility = minmaxValue(successor, 2);
//            System.out.println("minmax Utility: "+ tempUtility);
//            System.out.println(successor);
            if (tempUtility > utility){
                utility = tempUtility;
                ret = successor;
            }
        }
        System.out.println("minmax utility: "+ utility);
        return ret;
    }


    public Board alphaBetaSearch(Board board){
        cutOffs = 0;
        List<Board> successors = board.getSuccessors();
        int utility = Integer.MIN_VALUE;
        Board ret = null;
        for(Board successor: successors){
            int tempUtility = alphaBetaValue(successor, 2, Integer.MIN_VALUE, Integer.MAX_VALUE);
//            System.out.println("ab Utility: "+ tempUtility);
//            System.out.println(successor);
            if (tempUtility > utility){
                utility = tempUtility;
                ret = successor;
            }
        }
        System.out.println("ab utility: "+ utility);
        return ret;
    }

    public int getCutOffs() {
        return cutOffs;
    }

    private int minmaxValue(Board board, int curDepth){
        System.out.println("minmax here");
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
        System.out.println("ab here");
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
