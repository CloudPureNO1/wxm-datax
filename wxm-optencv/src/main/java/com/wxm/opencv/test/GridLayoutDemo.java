package com.wxm.opencv.test;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * @author closewubq
 */
public class GridLayoutDemo {
    public static void addComponentsToPane(Container pane) {
        JButton[] buttons = new JButton[9];
        pane.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(i + "");
            pane.add(buttons[i]);
        }
    }
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("GridLayoutManage");
        frame.setBounds(80, 60, 600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addComponentsToPane(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}