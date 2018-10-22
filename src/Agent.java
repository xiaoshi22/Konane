import java.util.List;

public class Agent {
    int limitDepth;

    public Agent(int limitDepth){
        this.limitDepth = limitDepth;
    }

    public Board minmaxDecision(Board board) {
        List<Board> successors = board.getSuccessors();
        int utility = Integer.MIN_VALUE;
        Board ret = null;
        for(Board successor: successors){
            int tempUtility = minmaxValue(successor, 2);
//            System.out.println("Utility: "+ tempUtility);
//            System.out.println(successor);
            if (tempUtility > utility){
                utility = tempUtility;
                ret = successor;
            }
        }
        return ret;
    }

    private int minmaxValue(Board board, int curDepth){
        List<Board> successors = board.getSuccessors();
        int utility = successors.size();
        if(curDepth<= limitDepth && utility != 0) {
            boolean isMax = (curDepth % 2 == 1);
            if (isMax) {
                utility = Integer.MIN_VALUE;
                for (Board successor : successors) {
                    int tempUtility = minmaxValue(successor, curDepth + 1);
                    if (tempUtility > utility) {
                        utility = tempUtility;
                    }
                }
            } else {
                utility = Integer.MAX_VALUE;
                for (Board successor : successors) {
                    int tempUtility = minmaxValue(successor, curDepth + 1);
                    if (tempUtility < utility) {
                        utility = tempUtility;
                    }
                }
            }
        }
        return utility;
    }
}
