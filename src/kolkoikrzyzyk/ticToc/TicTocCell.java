package kolkoikrzyzyk.ticToc;

import javax.swing.*;
import java.awt.*;

public class TicTocCell extends JButton {
    int state =0;

    public TicTocCell() {
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(state == 1){
            g.drawLine(0,0,160,160);
            g.drawLine(130,0,0,120);
        } else if(state == 2){
            g.drawOval(10,10,110,110);
        }
    }
}
