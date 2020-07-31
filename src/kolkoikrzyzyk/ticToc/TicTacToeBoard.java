package kolkoikrzyzyk.ticToc;

import javax.swing.*;
import java.awt.*;

public class TicTacToeBoard extends JFrame {
    JFrame ticTacToeBoard =this;
    JPanel board =new JPanel();

    public TicTacToeBoard(JFrame parent) {
        init();
    }

    private void init(){

        this.setMinimumSize(new Dimension(400,400));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Tic Tac Toe Game");

        board.setLayout(new GridLayout(3,3));
        TicTocCell[][] a=new TicTocCell[3][3];
        int result [][] =new int[3][3];

        for (int i=0;i<3;i++){
            for(int j=0; j<3;j++){
                a[i][j]=new TicTocCell();
                result[i][j]=0;
                a[i][j].addActionListener(new ElementListner(a,result, board));
                board.add(a[i][j]);
            }
        }
        this.add(board);
        this.setVisible(true);
    }
}
