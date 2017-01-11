package com.kit.client.view;

/**
 * My friend list
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import com.kit.client.tools.*;
import com.kit.common.Message;

@SuppressWarnings("serial")
public class KitFriendList extends JFrame implements ActionListener, MouseListener {
	// define the first card
	JPanel jphy1, jphy2, jphy3;
	JButton jphy_jb1, jphy_jb2, jphy_jb3;
	JScrollPane jsp1;
	String owner;
	JLabel[] jbls;
	
	// define the second card 
	JPanel jpmsr1, jpmsr2, jpmsr3;
	JButton jpmsr_jb1, jpmsr_jb2, jpmsr_jb3;
	JScrollPane jsp2;

	// set the JFrame to CardLayout
	CardLayout cl;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// KitFriendList kitFriendList=new KitFriendList("1");
	}

	// update friend list
	public void upateFriend(Message m) {
		String onLineFriend[] = m.getCon().split(" ");

		for (int i = 0; i < onLineFriend.length; i++) {
			jbls[Integer.parseInt(onLineFriend[i]) - 1].setEnabled(true);
		}
	}

	public KitFriendList(String ownerId) {
		// deal with the first card
		this.owner = ownerId;
		jphy1 = new JPanel(new BorderLayout());		// show friend list
		jphy_jb1 = new JButton("Myfriends");
		jphy_jb2 = new JButton("Strangers");
		jphy_jb2.addActionListener(this);
		jphy_jb3 = new JButton("Blacklist");
		jphy2 = new JPanel(new GridLayout(30, 1, 4, 4));	// assume there are 30 friends
		jbls = new JLabel[30];		// initialize 50 friends for jphy2
		for (int i = 0; i < jbls.length; i++) {
			jbls[i] = new JLabel(i + 1 + "", new ImageIcon("image/friend.gif"), JLabel.LEFT);
			jbls[i].setEnabled(false);
			if (jbls[i].getText().equals(ownerId)) {
				jbls[i].setEnabled(true);
			}
			jbls[i].addMouseListener(this);
			jphy2.add(jbls[i]);
		}
		jphy3 = new JPanel(new GridLayout(2, 1));
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		jsp1 = new JScrollPane(jphy2);
		jphy1.add(jphy_jb1, "North");		// initialize the jphy1
		jphy1.add(jsp1, "Center");
		jphy1.add(jphy3, "South");

		// deal with the second card
		jpmsr_jb1 = new JButton("Myfriends");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2 = new JButton("Strangers");
		jpmsr_jb3 = new JButton("Blacklist");
		jpmsr1 = new JPanel(new BorderLayout());
		jpmsr2 = new JPanel(new GridLayout(20, 1, 4, 4));		// assume there are 20 strangers
		JLabel[] jbls2 = new JLabel[20];		// initialize 20 strangers for jpmsr2
		for (int i = 0; i < jbls2.length; i++) {
			jbls2[i] = new JLabel(i + 1 + "", new ImageIcon("image/stranger.gif"), JLabel.LEFT);
			jbls2[i].setEnabled(false);
			if (jbls2[i].getText().equals(ownerId)) {
				jbls2[i].setEnabled(true);
			}
			jbls2[i].addMouseListener(this);
			jpmsr2.add(jbls2[i]);
		}
		jpmsr3 = new JPanel(new GridLayout(2, 1));
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
		jsp2 = new JScrollPane(jpmsr2);
		jpmsr1.add(jpmsr3, "North");
		jpmsr1.add(jsp2, "Center");
		jpmsr1.add(jpmsr_jb3, "South");

		cl = new CardLayout();
		this.setLayout(cl);
		this.add(jphy1, "1");
		this.add(jpmsr1, "2");
		this.setTitle(ownerId);
		this.setSize(250, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == jphy_jb2) {
			cl.show(this.getContentPane(), "2");
		} else if (arg0.getSource() == jpmsr_jb1) {
			cl.show(this.getContentPane(), "1");
		}
	}
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getClickCount() == 2) {
			String friendNo = ((JLabel) arg0.getSource()).getText();
			KitChat kitChat = new KitChat(this.owner, friendNo);
			ManageKitChat.addChat(this.owner + " " + friendNo, kitChat);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		JLabel jl = (JLabel) arg0.getSource();
		jl.setForeground(Color.BLACK);
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		JLabel jl = (JLabel) arg0.getSource();
		jl.setForeground(Color.RED);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
}