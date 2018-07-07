//P384

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SimpleAnimation {

    int x = 70; /*在主要的GUI中创建两个实例变量用来代表图形的坐标*/
    int y = 70;

    public static void main(String[] args) {
        SimpleAnimation gui = new SimpleAnimation();
        gui.go();
    }

    public void go() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyDrawPanel drawPanel = new MyDrawPanel();
        frame.getContentPane().add(drawPanel);
        frame.setSize(300, 300);
        frame.setVisible(true);

        for (int i=0; i<130; i++) {

            x++;
            y++;
            drawPanel.repaint(); /*要求重新绘制面板*/
            try {
                Thread.sleep(50); /*加上延迟刻意放慢，不然一下就会跑完*/
            } catch (Exception ex) {}
        }
    }

    class MyDrawPanel extends JPanel { /*内部类*/

        public void paintComponent(Graphics g) { /*paintComponent这个函数只能由系统来调用*/
            g.setColor(Color.white);
            g.fillRect(0, 0, this.getWidth(), this.getHeight()); /*先把面板颜色设定为白色，然后填满整个方块区域*/
            g.setColor(Color.green);
            g.fillOval(x, y ,40, 40);
        }
    }
}
