import java.util.List;

public class Game {


    Board board;

    public Game() {
        board = new Board();
    }


    public static void main(String[] args) {
        Game game = new Game();
        game.board.remove(4, 4);
        game.board.secondRemove(3, 4);

        // game.board.jump(4, 2, 4, 4);


        Board b = game.board.getSuccesors().get(0);

        System.out.println("Before:");
        System.out.println(b);
        List<Board> list = b.getSuccesors();


        for (Board bo: list){
            System.out.println(bo);
        }

        // game.board.jump(1, 4, 2, 4);

        //System.out.println(game.board);
    }


}
