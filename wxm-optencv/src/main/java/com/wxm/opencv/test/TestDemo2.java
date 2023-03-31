package com.wxm.opencv.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * TODO
 *
 * @author wangsm
 * @version 1.0.0
 * @date 2023-03-21 11:23:29
 */
public class TestDemo2 {
    /**
     * Windows 系统 默认路径
     */
    private static final String DEFAULT_PATH_WIN="C:\\Users\\Default\\Pictures\\take-picture";
    /**其他系统默认路径*/
    private static final String DEFAULT_PATH_OTHER="/usr/local";

    private int mouseAtX;
    private int mouseAtY;

    private final JPanel panelCamera = new JPanel();

    private final  JScrollPane  panelImgList = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private final JLabel labelImg=new JLabel();



    public TestDemo2() {
        JFrame frame = new JFrame("GridLayoutManage");


        ImageIcon icon = new ImageIcon("C:\\Users\\wangsm\\Desktop\\11\\1.jpg");

        labelImg.setIcon(icon);


        JTextField textField2 = new JTextField("test2");


        panelCamera.setSize(750, 600);
        panelCamera.setLocation(5, 5);
        panelCamera.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        panelCamera.setBackground(Color.WHITE);
        panelCamera.add(labelImg);



        panelImgList.setBounds(760,5,175,600);
        panelImgList.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        panelImgList.setBackground(Color.WHITE);

        JPanel imgList=new JPanel(new GridLayout(10,1));
        imgList.setBounds(0,0,150,Integer.MAX_VALUE);

        for(int i=1;i<10;i++){
            ImageIcon img=new ImageIcon("C:\\Users\\wangsm\\Desktop\\11\\"+i+".jpg");
            img.setImage(img.getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
            JLabel jlabel=new JLabel(img);
            imgList.add(jlabel);
        }
        panelImgList.setViewportView(imgList);



        JPanel panelBottom = new JPanel();
        panelBottom.setSize(925, 70);
        panelBottom.setLocation(5, 610);
        panelBottom.setBorder(BorderFactory.createLineBorder(Color.lightGray));
        panelBottom.setBackground(Color.WHITE);
        panelBottom.setLayout(new FlowLayout());

        JLabel labelPath=new JLabel("路径");
        String defaultValue=System.getProperty("os.name").toLowerCase().contains("win")?DEFAULT_PATH_WIN:DEFAULT_PATH_OTHER;
        JTextField fieldPath=new JTextField(defaultValue);



        JButton btnTakePicture=new JButton("拍照");
        JButton btnRotate=new JButton("旋转");
        JButton btnQuit=new JButton("确定");
        JButton btnCancel=new JButton("取消");

        btnTakePicture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // do something
                System.out.println("点击了拍照按钮，现在开始拍照！");
            }
        });
        btnRotate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // do something
                System.out.println("点击了旋转按钮，现在开始旋转！");
            }
        });
        btnQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // do something
                System.out.println("点击了确定按钮，现在开始退出！");
                frame.dispose();
                System.exit(0);
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("点击了取消按钮，现在开始退出！");
                frame.dispose();
                System.exit(0);
            }
        });

        panelBottom.add(labelPath);
        panelBottom.add(fieldPath);
        panelBottom.add(btnTakePicture);
        panelBottom.add(btnRotate);
        panelBottom.add(btnQuit);
        panelBottom.add(btnCancel);


        Container container = frame.getContentPane();

        container.add(panelCamera);
        container.add(panelImgList);
        container.add(panelBottom);

        frame.setUndecorated(true);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
//        frame.setBounds(80, 60, 930, 730);
        frame.setBounds(80, 60, 940, 690);
        frame.setResizable(false);


        /**
         * 点击标题栏关闭，和 右键关闭，或者杀进程的时候，都有可能触发，
         * 禁用标题栏 frame.setUndecorated(true);，
         * 采用自定义按年，退出
         * 禁用标题栏后不能拖拽
         */
//        frame.addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                System.out.println(">>>>>>>>>>>>窗口关闭");
//
//
//                frame.dispose();
//                super.windowClosing(e);
//            }
//        });



        frame.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                /*
                 * 获取点击鼠标时的坐标
                 */
                mouseAtX = e.getPoint().x;
                mouseAtY = e.getPoint().y;
            }
        });
        frame.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                frame.setLocation((e.getXOnScreen() - mouseAtX), (e.getYOnScreen() - mouseAtY));//设置拖拽后，窗口的位置
            }
        });

    }

    public static void main(String[] args) {
       System.out.println(System.getProperty("os.name").toLowerCase());
        new TestDemo2();
    }
}


























