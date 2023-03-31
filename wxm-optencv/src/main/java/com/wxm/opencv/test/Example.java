package com.wxm.opencv.test;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * TODO
 *
 * @author wangsm
 * @version 1.0.0
 * @date 2023-03-21 9:38:12
 */
public class Example extends JFrame {
    public Example() {

        JPanel panelLeft = new JPanel();
        JPanel panelRight = new JPanel();
        JPanel panelBottom = new JPanel();

        JSplitPane topPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelLeft, panelRight);



        topPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, pce -> {
            topPane.setDividerLocation((int) pce.getNewValue());
        });


        JSplitPane mainPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topPane, panelBottom);
        setContentPane(mainPane);
        //设定窗体关闭后自动退出进程
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设定窗体的默认尺寸
        setSize(1000,800);
        //显示窗体
        setVisible(true);
        //设定分割面板的左右比例(这时候就生效了，如果放在setVisible(true)这据之前就不会有效果。)
        topPane.setDividerLocation(0.8);
        mainPane.setDividerLocation(0.9);
        /*****初始化事件***/
        this.addComponentListener(new ComponentAdapter(){
            public void componentResized(ComponentEvent e) {
                topPane.setDividerLocation(0.8);
                mainPane.setDividerLocation(0.9);
            }
        });

    }

    public static void main(String[] args) {
        new Example();
    }
}
