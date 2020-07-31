package kolkoikrzyzyk.ticToc;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ElementListner implements ActionListener {

    private TicTocCell[][] ticTocCells;
    private int[][] ints;
    JPanel board;

    public ElementListner(TicTocCell[][] ticTocCells, int[][] ints, JPanel board) {
        this.ticTocCells = ticTocCells;
        this.ints = ints;
        this.board = board;
    }


    private boolean check(int a, boolean t) {

        if (a == 1 || a == 2) {
            //Pioziom
            if ((ints[0][0] == a && ints[0][1] == a && ints[0][2] == a) ||
                    (ints[1][0] == a && ints[1][1] == a && ints[1][2] == a) ||
                    (ints[2][0] == a && ints[2][1] == a && ints[2][2] == a)) {
                if (t) JOptionPane.showMessageDialog(null, a == 1 ? "You won!" : "Computer won...");
                return true;
            }

            //Pion
            if ((ints[0][0] == a && ints[1][0] == a && ints[2][0] == a) ||
                    (ints[0][1] == a && ints[1][1] == a && ints[2][1] == a) ||
                    (ints[0][2] == a && ints[1][2] == a && ints[2][2] == a)) {
                if (t) JOptionPane.showMessageDialog(null, a == 1 ? "You won!" : "Computer won...");
                return true;
            }
            //Skos
            if ((ints[0][0] == a && ints[1][1] == a && ints[2][2] == a) ||
                    (ints[2][0] == a && ints[1][1] == a && ints[0][2] == a)) {
                if (t) JOptionPane.showMessageDialog(null, a == 1 ? "You won!" : "Computer won...");
                return true;
            }
        }

        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        TicTocCell source = (TicTocCell) e.getSource();
        int x = 0, y = 0;

        boolean isWon = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ticTocCells[i][j] == source) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }

        ticTocCells[x][y].state = 1;
        ints[x][y]=1;
        isWon = check(1, true);

        if (isWon) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    ticTocCells[i][j].state =0;
                    ints[i][j]=0;
                }
            }
            board.repaint();
        }


        Random random=new Random();
        int ranx=0,randy=0;
        int time=0;
        if(!isWon){
            while (true){
                time++;
                ranx=random.nextInt(3);
                randy=random.nextInt(3);
                if(time>70){
                    isWon=true;
                    break;
                }
                if(ticTocCells[ranx][randy].state ==0){
                    ticTocCells[ranx][randy].state =2;
                    ints[ranx][randy]=2;
                    break;
                }
            }
        }

        board.repaint();
        isWon= check(2,true);
        boolean end=true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(ints[i][j]==0){ end=false;}
            }
        }
        if(check(1,false) == false && check(2,false) ==false && end){
            JOptionPane.showMessageDialog(null,"Tie");
            isWon=true;
        }

        if (isWon) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    ticTocCells[i][j].state =0;
                    ints[i][j]=0;
                }
            }
            board.repaint();
            isWon=false;
        }

    }
    }
