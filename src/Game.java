public class Game {


    Board board;

    public Game() {
        board = new Board();
    }


    public static void main(String[] args) {
        Game game = new Game();
        game.board.remove(4, 4);
        game.board.secondRemove(3, 4);
        game.board.jump(1, 4, 2, 4);
        System.out.println(game.board);
    }

}
