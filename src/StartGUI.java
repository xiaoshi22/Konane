import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartGUI extends JFrame{
    private JComboBox combo1;
    private JComboBox combo2;
    private JComboBox combo3;
    private JButton startButton;
    private GameGUI gameGUI;


    public StartGUI() {
        setLayout(new GridBagLayout());

        JLabel label = new JLabel("Konane");
        JLabel label1 = new JLabel("Difficulty");
        JLabel label2 = new JLabel("Applied Algorithm");
        JLabel label3 = new JLabel("Choose a color to play");

        startButton = new JButton("Start");

        String[] petStrings = { "Easy", "Medium", "Hard"};
        combo1 = new JComboBox(petStrings);

        String[] petStrings2 = {"Minmax Algorithm", "Alpha-Beta Search"};
        combo2 = new JComboBox(petStrings2);

        String[] petStrings3 = {"Black", "White"};
        combo3 = new JComboBox(petStrings3);


        startButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            GameGUI gameGUI = new GameGUI((combo1.getSelectedIndex()+1)*2, (combo2.getSelectedIndex() == 0), (combo3.getSelectedIndex() == 0));
            gameGUI.setTitle("Konane ("+combo1.getSelectedItem().toString() +", "+combo2.getSelectedItem().toString() + ", "+combo3.getSelectedItem().toString()+")");
            gameGUI.setLocation(getX(), getY());
            gameGUI.setSize(400, 400);
            gameGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setVisible(false);
            gameGUI.setVisible(true);
        }
    });

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
        add(label, c);

        c.gridx = 0;
        c.gridy = 1;
        add(label1, c);

        c.gridx = 1;
        c.gridy = 1;
        add(combo1, c);

        c.gridx = 0;
        c.gridy = 2;
        add(label2, c);

        c.gridx = 1;
        c.gridy = 2;
        add(combo2, c);

        c.gridx = 0;
        c.gridy = 3;
        add(label3, c);

        c.gridx = 1;
        c.gridy = 3;
        add(combo3, c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        add(startButton, c);
    }

    public static void main(String[] args) {
        StartGUI startGUI = new StartGUI();
        startGUI.setTitle("Konane");
        startGUI.setLocation(300, 200);
        startGUI.setSize(400, 400);
        startGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startGUI.setVisible(true);

    }
}