import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class GameGUI extends JFrame {
    JButton[][] grids = new JButton[8][8];
    Board board;
    Agent agent;
    boolean AIIsPlaying;
    int[] clickHistory;
    boolean isFirstClick;
    ImageIcon whiteIcon;
    ImageIcon blackIcon;
    ImageIcon nullIcon;


    public GameGUI(int difficulty, boolean isMinmax, boolean isBlack) {
        AIIsPlaying = false;
        board = new Board();
        agent = new Agent(difficulty, isMinmax);
        clickHistory = new int[2];
        Arrays.fill(clickHistory, -1);
        isFirstClick = true;
        whiteIcon = new ImageIcon("src/white.png");
        blackIcon = new ImageIcon("src/black.png");
        nullIcon = new ImageIcon("src/null.png");


        setLayout(new GridLayout(8, 8));


        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids.length; j++) {
                grids[i][j] = new JButton();
                if ((i+j) % 2 == 1) {
                    grids[i][j].setIcon(whiteIcon);
                } else {
                    grids[i][j].setIcon(blackIcon);
                }
                final int iTemp = i;
                final int jTemp = j;
                grids[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (isFirstClick) {
                            clickHistory[0] = iTemp;
                            clickHistory[1] = jTemp;
                        } else {
                            userOneTimeStep(clickHistory[0], clickHistory[1], iTemp, jTemp);
                        }
                        isFirstClick = !isFirstClick;
                    }
                });
                add(grids[i][j]);
                setVisible(true);
            }
        }

        if (!isBlack){
            AIOneTimeStep();
        }


    }

    public void AIOneTimeStep() {
        if(board.getCount()>2 && board.getSuccessors().size() <= 0){
            int res = JOptionPane.showConfirmDialog(this, "You win! Play again? ","Restart",
                    JOptionPane.YES_NO_OPTION);
            restart(res);
            return;
        }


        if (board.getCount() == 1) {
            board.remove(4, 4);
        } else if (board.getCount() == 2) {
            if (clickHistory[0] == 0) board.secondRemove(0, 1);
            else if (clickHistory[0] == 3) board.secondRemove(3, 4);
            else if (clickHistory[0] == 4) board.secondRemove(4, 5);
            else if (clickHistory[0] == 7) board.secondRemove(7, 6);
        } else {
            Board route = agent.findRoute(board);
            board = route;
        }
        drawBoard();
        AIIsPlaying = false;


        if(board.getCount()>2 && board.getSuccessors().size() <= 0){
            int res = JOptionPane.showConfirmDialog(this, "AI wins! Try again?", "Restart",
                    JOptionPane.YES_NO_OPTION);
            restart(res);
        }
    }


    public void userOneTimeStep(int pervRow, int pervCol, int row, int col) {
        if (board.getCount() == 1) {
            if (board.remove(pervRow, pervCol)) AIIsPlaying = true;
        } else if (board.getCount() == 2) {
            if (board.secondRemove(pervRow, pervCol)) AIIsPlaying = true;
        } else {
            if (board.jump(pervRow, pervCol, row, col)) AIIsPlaying = true;
        }
        drawBoard();


        if (AIIsPlaying) {
            AIOneTimeStep();
        }
        // System.out.println(board.getSuccessors());
    }


    private void drawBoard() {
        // System.out.println(board);
        char[][] curBoard = board.getBoard();
        for (int i = 0; i < grids.length; i++) {
            for (int j = 0; j < grids.length; j++) {
                if (curBoard[i][j] == 'W') {
                    grids[i][j].setIcon(whiteIcon);
                } else if (curBoard[i][j] == 'B') {
                    grids[i][j].setIcon(blackIcon);
                } else {
                    grids[i][j].setIcon(nullIcon);
                }
                add(grids[i][j]);
                setVisible(true);
            }
        }

    }

    private void restart(int toRestart){
        if (toRestart == 0){
            StartGUI startGUI = new StartGUI();
            startGUI.setTitle("Konane");
            startGUI.setLocation(getX(), getY());
            startGUI.setSize(400, 400);
            startGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setVisible(false);
            startGUI.setVisible(true);
        } else {
            setVisible(false);
            dispose();
            System.exit(0);
        }
    }
}
