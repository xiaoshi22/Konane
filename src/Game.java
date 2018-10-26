import java.util.List;

public class Game {
    Board board;
    Agent agent;
    boolean AIIsPlaying;

    public Game() {
        AIIsPlaying = true;
        board = new Board();
        agent = new Agent(4, true);

    }
//
//    public void oneTimeStep(){
//        if (AIIsPlaying){
//            if (board.getCount() == 1){
//                board.remove(4, 4);
//            } else {
//
//            }
//        } else {
//            if (board.getCount() == 2){
//
//            }
//        }
//    }


    public static void main(String[] args) {
        Game game = new Game();
        game.board.remove(4, 4);
        game.board.secondRemove(3, 4);


        // game.board.jump(4, 2, 4, 4);


//        Board b = game.board.getSuccessors().get(0);
//
//        System.out.println("Before:");
//        System.out.println(b);
//        List<Board> list = b.getSuccessors();
//
//
//        for (Board bo: list){
//            System.out.println(bo);
//        }
        System.out.println("before: ");
        System.out.println(game.board);
//        System.out.println("after: ");
//        Board route = game.agent.minmaxDecision(b);
////        Board route2 = game.agent.alphaBetaSearch();
//        System.out.println(route);
//        System.out.println(route2);
        System.out.println("cutoffs: "+game.agent.getCutOffs());

        //System.out.println(game.board);
    }


}
