package kolkoikrzyzyk.memory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ElementMemoryListner implements ActionListener {
    static private boolean[][] visible;
    static private ElementMemory[][] elementMemories;
    static private int[][] values;
    JPanel memoryPlansza;
    static ElementMemory[] previous = new ElementMemory[2];
    static private int countShowed = 0;
    static Map<Integer, Integer> previousXY = new HashMap<>();

    public ElementMemoryListner(boolean[][] visible, ElementMemory[][] elementMemories, int[][] values, JPanel memoryPlansza) {
        this.visible = visible;
        this.elementMemories = elementMemories;
        this.values = values;
        this.memoryPlansza = memoryPlansza;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ElementMemory currentElement = (ElementMemory) e.getSource();
        int x = 0, y = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (elementMemories[i][j]==e.getSource()) {
                    if(visible[i][j]==true)
                        break;
                    x = i;
                    y = j;
                    System.out.println(x + " "+ y);
                }
            }
        }
        elementMemories[x][y].visible = true;
        elementMemories[x][y].setAction(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentElement.repaint();
            }
        });

        if (countShowed < 2) {
            previous[countShowed] = elementMemories[x][y];
            previousXY.put(x, y);
            countShowed++;
        }


        if (countShowed == 2) {
            if (previous[0].value == previous[1].value) {
                for (Map.Entry<Integer, Integer> secik : previousXY.entrySet()) {
                    visible[secik.getKey()][secik.getValue()]=true;
                }
                previousXY.clear();
                countShowed = 0;
            } else {
                for (Map.Entry<Integer, Integer> secik : previousXY.entrySet()) {
                    elementMemories[secik.getKey()][secik.getValue()].visible = false;
                }
                previousXY.clear();
                countShowed = 0;

            }
        }
        check();

        memoryPlansza.repaint();
        int d=0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(d+" = "+ visible[i][j]+" ");
                System.out.print(d+ " = "+elementMemories[i][j].visible);
                System.out.println();
                d++;
            }
        }
        check();
    }



    private boolean check() {
        int count = 0;
        for (int i = 0; i < visible.length; i++) {
            for (int j = 0; j < visible.length; j++) {
                if (!visible[i][j]) {
                    count++;
                }
            }
        }
        if (count == 0) {
            JOptionPane.showMessageDialog(null, "You won!");
            return true;
        }
        return false;
    }
}
