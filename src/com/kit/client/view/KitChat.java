package com.kit.client.view;

/**
 * The interface of chat with friends
 */

import javax.swing.*;
import java.awt.event.*;
import java.io.*;

import com.kit.client.tools.*;
import com.kit.common.*;

@SuppressWarnings("serial")
public class KitChat extends JFrame implements ActionListener {
	JTextArea jta;
	JTextField jtf;
	JButton jb;
	JPanel jp;
	String ownerId;
	String friendId;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 // KitChat qqChat = new KitChat("1", "2");
	}

	public KitChat(String ownerId, String friend) {
		this.ownerId = ownerId;
		this.friendId = friend;
		jta = new JTextArea();
		jtf = new JTextField(15);
		jb = new JButton("Send");
		jb.addActionListener(this);
		jp = new JPanel();
		jp.add(jtf);
		jp.add(jb);

		this.add(jta, "Center");
		this.add(jp, "South");
		this.setTitle(ownerId + " is chatting with " + friend);
		this.setIconImage((new ImageIcon("image/qq.gif")).getImage());
		this.setSize(300, 200);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	// show chat message
	public void showMessage(Message m) {
		String info = m.getSender() + " talk with " + m.getGetter() + ": " + m.getCon() + "\r\n" + "	" + m.getSendTime() + "\r\n";
		this.jta.append(info);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// click "Send" button
		if (arg0.getSource() == jb) {
			Message m = new Message();
			m.setMesType(MessageType.message_comm_mes);
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setCon(jtf.getText());
			m.setSendTime(new java.util.Date().toString());
			// send information to server
			try {
				ObjectOutputStream oos = new ObjectOutputStream(
						ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
				oos.writeObject(m);
			} catch (Exception e) {
				e.printStackTrace();
			}
			jtf.setText("");
		}
	}
}