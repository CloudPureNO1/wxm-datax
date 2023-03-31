package com.wxm.opencv.demo.test;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * TODO
 *
 * @author wangsm
 * @version 1.0.0
 * @date 2023-03-21 9:57:02
 */
public class MainFrame extends JFrame {
    JSplitPane jSplitPane1 = new JSplitPane();
    JSplitPane mainPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    JPanel jPanel1 = new JPanel();
    JPanel jPanel2 = new JPanel();
    JPanel jPanel3 = new JPanel();


    private void myInit(){
        //设定窗体关闭后自动退出进程
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设定窗体的默认尺寸
        setSize(800,600);
        //显示窗体
        setVisible(true);
        //设定分割面板的左右比例(这时候就生效了，如果放在setVisible(true)这据之前就不会有效果。)
        jSplitPane1.setDividerLocation(0.7);
        mainPane.setDividerLocation(0.7);
        /*****初始化事件***/
        this.addComponentListener(new ComponentAdapter(){
            public void componentResized(ComponentEvent e) {
                jSplitPane1.setDividerLocation(0.7);
                mainPane.setDividerLocation(0.7);
            }
        });


    }
    public MainFrame() {
        try {
            jbInit();
            myInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);
        jSplitPane1.add(jPanel1, JSplitPane.LEFT);
        jSplitPane1.add(jPanel2, JSplitPane.RIGHT);

        mainPane.add(jSplitPane1,JSplitPane.TOP);
        mainPane.add(jPanel3,JSplitPane.BOTTOM);
    }


    public static void main(String[] args){
        MainFrame f=new MainFrame();
    }
}
