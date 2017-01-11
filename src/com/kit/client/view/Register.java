package com.kit.client.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Register extends JFrame{
	JPanel jpr1;
	JLabel jpr1_jbl1;
	JLabel jpr1_jbl2;
	JTextField jpr1_jtf;
	JPasswordField jpr1_jpf;
	JButton jpr1_jb1;
	
	public Register() {
		jpr1 = new JPanel(new GridLayout(3, 3));
		jpr1_jbl1 = new JLabel("KIT Number", JLabel.LEFT);
		jpr1_jbl2 = new JLabel("Password", JLabel.LEFT);
		jpr1_jtf = new JTextField();
		jpr1_jpf = new JPasswordField();
		jpr1_jb1 = new JButton("Register");
		jpr1.add(jpr1_jbl1);
		jpr1.add(jpr1_jtf);
		jpr1.add(jpr1_jbl2);
		jpr1.add(jpr1_jpf);
		jpr1.add(jpr1_jb1);
		
		this.add(jpr1);
		this.setTitle("Register");
		this.setSize(300, 100);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
