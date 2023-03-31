package com.wxm.opencv.demo.test;

import javax.swing.*;

/**
 * TODO
 *
 * @author wangsm
 * @version 1.0.0
 * @date 2023-03-21 9:47:53
 */
public class Test1 {
    public static void main(String[] a) {
        int HORIZSPLIT = JSplitPane.HORIZONTAL_SPLIT;

        int VERTSPLIT = JSplitPane.VERTICAL_SPLIT;

        boolean continuousLayout = true;


        JLabel label1 = new JLabel("a");
        JLabel label2 = new JLabel("b");
        JLabel label3 = new JLabel("c");
        JSplitPane splitPane1 = new JSplitPane(VERTSPLIT, continuousLayout, label1, label2);
        splitPane1.setOneTouchExpandable(true);
        splitPane1.setDividerSize(2);
        splitPane1.setDividerLocation(0.5);

        JSplitPane splitPane2 = new JSplitPane(HORIZSPLIT, splitPane1, label3);//将分隔板和一个label放在第二个分割板中实现嵌套
        splitPane2.setOneTouchExpandable(true);
        splitPane2.setDividerLocation(0.4);
        splitPane2.setDividerSize(2);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(splitPane2);
        frame.pack();
        frame.setVisible(true);
    }
}
