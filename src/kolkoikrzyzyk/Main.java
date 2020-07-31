package kolkoikrzyzyk;

import kolkoikrzyzyk.memory.Memory;
import kolkoikrzyzyk.ticToc.TicTacToeBoard;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private JButton ticTocButton = new JButton("Kółko i krzyżyk");
    private JButton memory=new JButton("Memory");
    private JPanel main=new JPanel();

    public Main() {
        initCompononet();
    }


    private void initCompononet() {
        this.setTitle("Gry");
        this.setBounds(400,400,300,300);

        main.setLayout(new GridLayout(4,1));
        ticTocButton.addActionListener(e->{
            new TicTacToeBoard(this);
        });

        memory.addActionListener(e->{
            new Memory(this).setVisible(true);
        });
        main.add(ticTocButton,0);
        main.add(memory,1);

        this.getContentPane().add(main);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    public static void main(String[] args) {

        new Main().setVisible(true);
        //todo
        // repair the logic ...
    }
}
