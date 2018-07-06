//P371

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SimpleGui3C implements ActionListener {

    JFrame frame;

    public static void main(String[] args) {
        SimpleGui3C gui = new SimpleGui3C();
        gui.go();
    }

    public void go() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton("Change colors");
        button.addActionListener(this); /*向按钮注册*/

        MyDrawPanel drawPanel = new MyDrawPanel();
        frame.getContentPane().add(BorderLayout.SOUTH, button);
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel); /*依照指定区域把widget放上去*/
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        frame.repaint(); /*当用户按下按钮时就要求frame重新绘制*/
    }
}

class MyDrawPanel extends JPanel {
    public void paintComponent(Graphics g) {
        // Cast it so we can access G2D methods
        Graphics2D g2d = (Graphics2D) g;
        // Starting point, color, gradient point, color.

        Color startColor = MyDrawPanel.makeRandomColor();
        Color endColor = MyDrawPanel.makeRandomColor();
        GradientPaint gradient = new GradientPaint(70,70,startColor,150,150,endColor);
        // Set virtual paint brush to a gradient instead of solid color.
        g2d.setPaint(gradient);
        // Fill oval with whatever is loaded on to the paintbrush
        g2d.fillOval(70,70,100,100);
    }
    public static Color makeRandomColor() {
        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);

        return new Color(red, green, blue);
    }
}
