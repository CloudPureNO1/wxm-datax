package com.wxm.opencv.demo.test;

import javax.swing.*;
import java.awt.*;

/**
 * TODO
 *
 * @author wangsm
 * @version 1.0.0
 * @date 2023-03-21 15:30:52
 */
public class Picture {
    public Picture(){
        JFrame frame = new JFrame("Picture");

        JPanel imgList=new JPanel(new FlowLayout(FlowLayout.CENTER));
        imgList.setBounds(0,0,150,Integer.MAX_VALUE);

        for(int i=1;i<10;i++){
            ImageIcon img=new ImageIcon("C:\\Users\\wangsm\\Desktop\\11\\"+i+".jpg");
            img.setImage(img.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
            JLabel jlabel=new JLabel(img);
            imgList.add(jlabel);
        }

        JScrollPane  scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(760,5,175,600);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setViewportView(imgList);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(scrollPane);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setBounds(80, 60, 915, 690);
    }

    public static void main(String[] args) {
        new Picture();
    }
}
