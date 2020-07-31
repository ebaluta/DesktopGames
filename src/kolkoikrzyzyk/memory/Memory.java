package kolkoikrzyzyk.memory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Memory extends JFrame implements ActionListener {
    private static int MATCHED_PAIRS=0;
    private JPanel memory = new JPanel();
    private final int capacity = 8;
    private List<Integer> listaNumerow = new ArrayList<>(capacity);
    private List<Integer> listaNumerow2 = new ArrayList<>(capacity);

    private boolean[][] visible;
    private ElementMemory[][] elementMemories;
    private int[][] values;

    private void fillSet(int capacity) {
        Random random = new Random();
        Set<Integer> setInts = new HashSet<>(capacity);
        while (listaNumerow.size() < 8) {
            int randomInt = 1 + random.nextInt(8);
            if (setInts.add(randomInt)) {
                listaNumerow.add(randomInt);
            }
        }
        listaNumerow2 = List.copyOf(listaNumerow);
        Collections.shuffle(listaNumerow);
    }

    public Memory(JFrame parent) {
        fillSet(capacity);
        init();
    }



    private void init() {
        this.setTitle("Memory");
        this.setMinimumSize(new Dimension(400, 400));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        memory.setLayout(new GridLayout(4, 4));
        elementMemories = new ElementMemory[4][4];
        visible = new boolean[4][4];
        values = new int[4][4];
        int help = 0;
        int help2 = 0;
        int place = 0;

        //  ElementMemoryListner elementMemoryListner=new ElementMemoryListner()
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value = 0;
                if ((i + j) % 2 == 0) {
                    value = listaNumerow.get(help);
                    help++;
                }
                if ((i + j) % 2 == 1) {
                    value = listaNumerow2.get(help2);
                    help2++;
                }
                visible[i][j] = false;
                elementMemories[i][j] = new ElementMemory(value, visible[i][j]);
                values[i][j] = value;
                memory.add(elementMemories[i][j], place);
                 elementMemories[i][j].addActionListener(new ElementMemoryListner(visible, elementMemories, values, memory));
               // elementMemories[i][j].addActionListener(this);
            }
            place++;
        }
        memory.repaint();
       this.setContentPane(memory);
    }

    int count = 0;
    ElementMemory[] previous = new ElementMemory[2];
    Map<Integer, Integer> previousXY = new HashMap<>();

    private static int selectedCard=0;

    @Override
    public void actionPerformed(ActionEvent e) {
        ElementMemory currentElement = (ElementMemory) e.getSource();
        int x = 0, y = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (elementMemories[i][j] == e.getSource()) {
                    if (visible[i][j] == true)
                        break;
                    x = i;
                    y = j;
                }
            }
        }
        elementMemories[x][y].visible=true;
//        elementMemories[x][y].addActionListener(a->{
//            currentElement.repaint();
//        });
//        if (count == 0) {
//            previous[0] = elementMemories[x][y];
//            previousXY.put(x, y);
//        }
//        if (count == 1) {
//            previous[1] = elementMemories[x][y];
//            previousXY.put(x, y);
//        }

        if(count < 2 ){
            previous[count] = elementMemories[x][y];
            previousXY.put(x,y);
        }


        if (count == 2) {
            if (previous[0].value == previous[1].value) {
                for (Map.Entry<Integer, Integer> secik : previousXY.entrySet()) {
                    visible[secik.getKey()][secik.getValue()] = true;
                }
                ++MATCHED_PAIRS;
                check();
                previous[0] = null;
                previous[1] = null;
                previousXY.clear();
                elementMemories[x][y].visible = true;
                previous[0] = elementMemories[x][y];
                previousXY.put(x, y);
                count = 0;
            } else {
                for (Map.Entry<Integer, Integer> secik : previousXY.entrySet()) {
                  //  visible[secik.getKey()][secik.getValue()] = false;
                    elementMemories[secik.getKey()][secik.getValue()].visible = false;
                }
                previousXY.clear();
                elementMemories[x][y].visible = true;
                previous[0] = elementMemories[x][y];
                previousXY.put(x, y);
                count = 0;
            }

        }
        memory.repaint();
//        System.out.println(" count: "+count);
//        for (int i =0; i<4; i++){
//            for (int j=0;j<4;j++) {
//
//                System.out.print(elementMemories[i][j].value+" " + elementMemories[i][j].visible);
//
//            }
//            System.out.println(" ");}
//        System.out.println(((ElementMemory) e.getSource()).getX() + " "+ ((ElementMemory) e.getSource()).getY());

        if(check()){
            JOptionPane.showMessageDialog(null, "You won!");
        }
        if(count==0 || count==1){
            count++;
        }

        System.out.println(MATCHED_PAIRS);
    }

    private boolean check() {
        if(MATCHED_PAIRS==capacity){
            JOptionPane.showMessageDialog(this,"You won!");
            JOptionPane.showMessageDialog(this,"Restart");
            restart();
            return true;
        }
        return false;
    }

    private void restart() {
        new Memory(this);
    }


}
