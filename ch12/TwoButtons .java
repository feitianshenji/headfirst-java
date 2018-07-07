//p379

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TwoButtons {

    JFrame frame;
    JLabel label;

    public static void main(String[] args) {
        TwoButtons gui = new TwoButtons();
        gui.go();
    }

    public void go() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton labelButton = new JButton("Change Label");
        labelButton.addActionListener(new LabelListener()); /*相对于将this传给监听的注册方法，现在传的是对应的实例*/

        JButton colorButton = new JButton("Change Circle");
        colorButton.addActionListener(new ColorListener());  /*相对于将this传给监听的注册方法，现在传的是对应的实例*/

        label = new JLabel("I`m a label");
        MyDrawPanel drawPanel = new MyDrawPanel();

        frame.getContentPane().add(BorderLayout.SOUTH, colorButton);
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
        frame.getContentPane().add(BorderLayout.EAST, labelButton);
        frame.getContentPane().add(BorderLayout.WEST, label);

        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    class LabelListener implements ActionListener { /*内部类：终于可以在单一的类中做出不同的ActionListener*/
        public void actionPerformed(ActionEvent event) {
            label.setText("Ouch!");
        }
    }

    class ColorListener implements ActionListener { /*内部类：终于可以在单一的类中做出不同的ActionListener*/
        public void actionPerformed(ActionEvent event) {
            frame.repaint();
        }
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
