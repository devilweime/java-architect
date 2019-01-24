package com.wgw.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LayoutTest {

	JFrame jFrame = new JFrame();
	JPanel leftJPanel = new JPanel();
	JPanel rightJPanel = new JPanel();

	public static void main(String[] args) {
		new LayoutTest().init();
	}

	public void init() {

		this.jFrame.setLayout(new BorderLayout());
		this.jFrame.setBounds(600, 0, 600, 600);
		this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.jFrame.setVisible(true);

		Container container = this.jFrame.getContentPane();

		// leftJPanel.setBackground(Color.RED);
		this.leftJPanel.setPreferredSize(new Dimension(150, 600));
		initLeftJPanel();

		// rightJPanel.setBackground(Color.BLUE);
		this.rightJPanel.setPreferredSize(new Dimension(450, 600));

		// menuBtn1(rightJPanel);
		initRightJPanel();

		container.add(leftJPanel, "West");
		container.add(rightJPanel, "East");
	}

	public void initLeftJPanel() {

		JPanel menuPanel = new JPanel();
		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));

		JPanel menuBtnPanel1 = new JPanel();
		JButton menuBtn1 = new JButton("驼峰命名法");
		menuBtnPanel1.add(menuBtn1);

		JPanel menuBtnPanel2 = new JPanel();
		JButton menuBtn2 = new JButton("追加前缀");
		menuBtnPanel2.add(menuBtn2);

		menuBtn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				menuBtn1();
			}
		});

		menuBtn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				menuBtn2();
			}
		});

		menuPanel.add(menuBtnPanel1);
		menuPanel.add(menuBtnPanel2);
		this.leftJPanel.add(menuPanel);
	}

	public void initRightJPanel() {
		this.rightJPanel.setBackground(Color.WHITE);
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());
		JLabel label = new JLabel("请选择你需要使用到的功能");
		jPanel.add(label, "Center");
		this.rightJPanel.add(jPanel);
	}

	public void menuBtn1() {

		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());

		JTextArea inputArea = new JTextArea();
		inputArea.setPreferredSize(new Dimension(400, 250));

		JPanel inputAreaPanel = new JPanel();
		inputAreaPanel.setLayout(new FlowLayout());
		inputAreaPanel.add(inputArea);
		JTextArea outputArea = new JTextArea();
		outputArea.setPreferredSize(new Dimension(400, 250));

		JPanel outputAreaPanel = new JPanel();
		outputAreaPanel.setLayout(new FlowLayout());
		outputAreaPanel.add(outputArea);

		JButton executeBtn = new JButton("格式化");
		executeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String input = inputArea.getText();

				String out = format(input);

				outputArea.setText(out);
			}
			
			private String format(String input) {
				
				Pattern pattern = Pattern.compile("(?![\\w]+[\\s]*\\.)([\\w]+)");
				Matcher matcher = pattern.matcher(input);
				StringBuffer sb = new StringBuffer(input);
				
				StringBuffer sb2 = new StringBuffer();
				while (matcher.find()) {
					String group = matcher.group();
					System.out.println(group);
					matcher.appendReplacement(sb2, group +" "+hump(group));
					//matcher.appendTail(sb2);
				}
				matcher.appendTail(sb2);
				
				int length = sb2.length();
				if(length != 0 ){
					sb = sb2;
				}
				
				return sb.toString();
			}

			private String hump(String args1) {

				Pattern pattern = Pattern.compile("_(\\w)");
				Matcher matcher = pattern.matcher(args1);
				StringBuffer sb = new StringBuffer(args1);
				if (matcher.find()) {
					sb = new StringBuffer();
					matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
					matcher.appendTail(sb);
				} else {
					return sb.toString();
				}

				return hump(sb.toString());
			}
		});
		JPanel executeBtnPanel = new JPanel();
		executeBtnPanel.setLayout(new FlowLayout());
		executeBtnPanel.add(executeBtn);

		jPanel.add(inputAreaPanel, "North");
		jPanel.add(executeBtnPanel, "Center");
		jPanel.add(outputAreaPanel, "South");

		this.rightJPanel.removeAll();
		this.rightJPanel.add(jPanel);
		this.rightJPanel.updateUI();
		this.rightJPanel.repaint();
	}

	public void menuBtn2() {

		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());
		
		
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel label = new JLabel("前缀：");
		JTextField textField = new JTextField(20);
		labelPanel.add(label);
		labelPanel.add(textField);
		
		
		JTextArea inputArea = new JTextArea();
		inputArea.setPreferredSize(new Dimension(400, 200));

		JPanel inputAreaPanel = new JPanel();
		inputAreaPanel.setLayout(new FlowLayout());
		inputAreaPanel.add(inputArea);
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.add(labelPanel);
		topPanel.add(inputAreaPanel);
		
		
		JTextArea outputArea = new JTextArea();
		outputArea.setPreferredSize(new Dimension(400, 250));

		JPanel outputAreaPanel = new JPanel();
		outputAreaPanel.setLayout(new FlowLayout());
		outputAreaPanel.add(outputArea);

		JButton executeBtn = new JButton("格式化");
		executeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String prefix = textField.getText();
				
				String input = inputArea.getText();

				String out = appendPrefix(prefix,input);

				outputArea.setText(out);
			}

			private String appendPrefix(String prefix, String input) {
				//Pattern pattern = Pattern.compile("^(?!"+prefix+")");
				//Pattern pattern = Pattern.compile("(?:^|\n)(?!"+prefix+")");
				Pattern pattern = Pattern.compile("(?:^|\\n)(?!"+prefix+")");
				Matcher matcher = pattern.matcher(input);
				StringBuffer sb = new StringBuffer(input);
				if(matcher.find()){
					sb = new StringBuffer();
					System.out.println(matcher.group());
					matcher.appendReplacement(sb,matcher.group(0)+ prefix);
					matcher.appendTail(sb);
					
				}else{
					return sb.toString();
				}
				
				return appendPrefix(prefix, sb.toString());
			}

			
		});
		JPanel executeBtnPanel = new JPanel();
		executeBtnPanel.setLayout(new FlowLayout());
		executeBtnPanel.add(executeBtn);
		
		jPanel.add(topPanel, "North");
		jPanel.add(executeBtnPanel, "Center");
		jPanel.add(outputAreaPanel, "South");

		this.rightJPanel.removeAll();
		this.rightJPanel.add(jPanel);
		this.rightJPanel.updateUI();
		this.rightJPanel.repaint();
	}

}
