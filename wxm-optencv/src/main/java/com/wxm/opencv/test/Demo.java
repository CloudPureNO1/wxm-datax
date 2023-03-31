package com.wxm.opencv.test;

import javax.swing.*;
import java.awt.*;

/**
 * TODO
 *
 * @author wangsm
 * @version 1.0.0
 * @date 2023-03-21 9:39:09
 */
public class Demo extends JFrame {
    int screenwidth=760,screenheigth=550;

    JSplitPane top_sp,bottom_sp,main_sp;

    JButton b1,b2,b3,b4,b5,b6;
    JButton b7,b8,b9,b10;

    Demo(){
        setSize(screenwidth,screenheigth);
        setLayout(new BorderLayout());
        setTitle("Demo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        b1=new JButton("B1");
        b2=new JButton("B2");
        b3=new JButton("B3");
        b4=new JButton("B4");
        b5=new JButton("B5");
        b6=new JButton("B6");
        b7=new JButton("B7");
        b8=new JButton("B8");
        b9=new JButton("B9");
        b10=new JButton("B10");

        JPanel topleft=new JPanel(new FlowLayout());
        topleft.add(b1);
        topleft.add(b2);

        JPanel topright=new JPanel(new FlowLayout(FlowLayout.CENTER));
        topright.add(b3);
        topright.add(b4);
        topright.add(b5);
        topright.add(b6);

        JPanel bottomleft=new JPanel(new FlowLayout(FlowLayout.CENTER));

        bottomleft.add(b7);
        bottomleft.add(b8);
        bottomleft.add(b9);
        bottomleft.add(b10);

        JPanel bottomright=new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomright.add(new JLabel("TABLE"));

        top_sp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,topleft,topright);
        bottom_sp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,bottomleft,bottomright);

        main_sp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,top_sp,bottom_sp);

        add(main_sp,"Center");

        setVisible(true);
    }

    public static void main(String args[]){
        Demo demo=new Demo();

    }
}
