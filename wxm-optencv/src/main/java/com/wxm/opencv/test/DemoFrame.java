package com.wxm.opencv.test;

import javax.swing.*;
import java.awt.*;

/**
 * TODO
 *
 * @author wangsm
 * @version 1.0.0
 * @date 2023-03-21 9:36:12
 */
public class DemoFrame extends JFrame {
    JPanel jp1, jp2;

    public DemoFrame() {
        jp1 = new JPanel();
        jp1.setBackground(Color.RED);
        jp2 = new JPanel();
        jp2.setBackground(Color.YELLOW);
        JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jp1, jp2);
        add(jsp);
        setTitle("拆分窗口");
        setBounds(300, 200, 500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        jsp.setDividerLocation(0.5);// 在1/2处进行拆分

    }

    public static void main(String[] args) {
        new DemoFrame();
    }
}
