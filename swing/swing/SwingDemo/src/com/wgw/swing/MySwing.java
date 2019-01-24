package com.wgw.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MySwing {

	private JFrame jFrame = new JFrame("我的工具");
	private Container container = jFrame.getContentPane();
	
	public void init(){
		jFrame.setBounds(600, 200, 600, 600);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
		
		container.setLayout(new BorderLayout());
		
		//增加左侧
			
		JButton button1 = new JButton("驼峰命名法");
		button1.setBounds(50, 20, 50, 20);
		JButton button2 = new JButton("加前缀");
		button1.setBounds(50, 60, 50, 20);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setPreferredSize(new Dimension(100, 600));
		leftPanel.setBackground(Color.RED);
		leftPanel.add(button1);
		leftPanel.add(button2);
		container.add(leftPanel,"West");
		//增加右侧
		
		JTextArea inputArea = new JTextArea();
		inputArea.setBounds(0, 0, 500, 250);
		
		JPanel inputAreaPanel = new JPanel();
		inputAreaPanel.setLayout(null);
		inputAreaPanel.add(inputArea);
		
		
		JButton executeBtn = new JButton("格式化");
		JPanel executeBtnPanel = new JPanel();
		executeBtnPanel.setLayout(new FlowLayout());
		executeBtnPanel.add(executeBtn,"Center");
		
		JTextArea outputArea = new JTextArea();
		outputArea.setBounds(0, 350, 500, 250);
		
		JPanel outputAreaPanel = new JPanel();
		outputAreaPanel.setLayout(null);
		outputAreaPanel.add(outputArea);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.setBounds(0,0,500,600);
		rightPanel.setBackground(Color.BLUE);
		rightPanel.add(inputAreaPanel);
		rightPanel.add(executeBtnPanel);
		rightPanel.add(outputAreaPanel);
		
		container.add(rightPanel,"East");
	}
	
	public static void main(String[] args) {
		new MySwing().init();
	}
}
