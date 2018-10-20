public class Agent {
    private Board board;
    int depth;


    public Agent(Board board, int depth) {
        this.board = board;
        this.depth = depth;
    }

    private int[] getRoute() {

    }

    private int[] minmax(){
        int[] ret = new int[4];

        return dfsHelper(curDepth, );
    }

    private int[] dfsHelper(int curDepth, boolean isMax) {
        if (curDepth == depth || check())
    }
}
