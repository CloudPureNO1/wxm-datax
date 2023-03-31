package com.wxm.opencv.demo.test;

import javax.swing.*;
import java.awt.*;
/**
 * @author closewubq
 */
public class BorderLayoutDemo {

    public static void addComponentsToPane(Container pane) {
        JButton button = new JButton("[PAGE_START]");
        pane.add(button, BorderLayout.PAGE_START);
        button = new JButton("[CENTER]");
        button.setPreferredSize(new Dimension(200, 100));
        pane.add(button, BorderLayout.CENTER);
        button = new JButton("[LINE_START]");
        pane.add(button, BorderLayout.LINE_START);
        button = new JButton("Long Name Button [PAGE_END]");
        pane.add(button, BorderLayout.PAGE_END);
        button = new JButton("[LINE_END]");
        pane.add(button, BorderLayout.LINE_END);
    }
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("BorderLayoutManage");
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