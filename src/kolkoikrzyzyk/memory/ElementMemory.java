package kolkoikrzyzyk.memory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ElementMemory extends JButton {
    int value;
    boolean visible;
    private static List<BufferedImage> imageList = new ArrayList<>(8);


    public ElementMemory(int value, boolean visible) {
        if(imageList.size()==0){
            createImageList();
        }
        this.value = value;
        this.visible = visible;
    }

    private void createImageList(){
        String [] names=new String[]{"one","two","three","four","five","six","seven","eight","nine"};
        File imageFile;
        String filePath;
        for(int i=0;i<8;i++){
            try{

                filePath="./images/"+names[i]+".png";
                imageFile=new File(filePath);
                BufferedImage image=ImageIO.read(imageFile);

                imageList.add(image);
            }catch (IOException e){
                System.out.println("Błąd wczytu obrazka....");
                e.printStackTrace();
            }
        }

        for (Object im: imageList){
            System.out.println(im.toString());
        }

    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(visible) {
            g.drawImage(imageList.get(value-1), 10, 0, this);
        }
        else {
            g.clearRect(5,5,85,80);
        }
    }


}
