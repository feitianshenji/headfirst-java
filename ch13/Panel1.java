//p408

import javax.swing.*;
import java.awt.*;



public class Panell {
    public static void main(String[] args) {
        Panell gui = new Panell();
        gui.go();
    }

    public void go() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
        frame.getContentPane().add(BorderLayout.EAST, panel);
        frame.setSize(200, 200);
        frame.setVisible(true);
    }
}
