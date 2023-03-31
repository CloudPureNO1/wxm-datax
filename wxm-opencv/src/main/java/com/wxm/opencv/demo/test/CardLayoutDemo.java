package com.wxm.opencv.demo.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author closewubq
 */
public class CardLayoutDemo {
    public void addComponentToPane(Container pane) {
        final JPanel contentPanel = new JPanel();
        JPanel controlPanel = new JPanel();
        final CardLayout cardLayout=new CardLayout();;
        pane.setLayout(new BorderLayout());
        pane.add(contentPanel, BorderLayout.CENTER);
        pane.add(controlPanel, BorderLayout.PAGE_END);
        controlPanel.setLayout(new FlowLayout());
        JButton[] b = new JButton[10];
        for (int i = 0; i < 10; i++) {
            b[i] = new JButton("No." + i);
            contentPanel.add(b[i]);
        }
        contentPanel.setLayout(cardLayout);
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(contentPanel);
            }});
        controlPanel.add(nextButton);
    }
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("CardLayoutManage");
        frame.setBounds(400, 200, 600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CardLayoutDemo demo = new CardLayoutDemo();
        demo.addComponentToPane(frame.getContentPane());
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