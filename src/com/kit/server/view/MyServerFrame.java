package com.kit.server.view;

/**
 * This is server control interface, it can starts and stop the servers
 */

import javax.swing.*;

import com.kit.server.model.MyKitServer;

import java.awt.event.*;

@SuppressWarnings("serial")
public class MyServerFrame extends JFrame implements ActionListener {
	JPanel jp1;
	JButton jb1, jb2;

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		MyServerFrame mysf = new MyServerFrame();
	}

	public MyServerFrame() {
		jp1 = new JPanel();
		jb1 = new JButton("Start Servers");
		jb1.addActionListener(this);
		jb2 = new JButton("Stop Servers");
		jb2.addActionListener(this);
		jp1.add(jb1);
		jp1.add(jb2);

		this.add(jp1);
		this.setSize(500, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == jb1) {
			new MyKitServer();
		} else if (arg0.getSource() == jb2) {
			this.dispose();
		}
	}
}
