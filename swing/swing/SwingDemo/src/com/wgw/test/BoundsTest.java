package com.wgw.test;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class BoundsTest {

	private JFrame jFrame = new JFrame();
	
	public static void main(String[] args) {
		new BoundsTest().init();
	}
	
	
	public void init(){
		
		jFrame.setBounds(600, 200, 200, 100);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setLayout(new BorderLayout());
		
		
		Container container = jFrame.getContentPane();
		
		
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout());
		//btnPanel.setLayout(null);
		
		//btnPanel.setBounds(0, 0, 200, 100);
		
		JButton button = new JButton("菜单项");
		//使用setBounds，父容器必须setLayout(null)，其中以父容器setLayout优先
		//button.setBounds(50, 20, 50, 20);
		//button.setPreferredSize(new Dimension(80,  20));
		btnPanel.add(button);
		
		container.add(btnPanel,"Center");
		
		jFrame.setVisible(true);
	}
}
