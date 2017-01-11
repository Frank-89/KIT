package com.kit.client.view;

/**
 *  KIT client login interface
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;

import com.kit.client.model.KitClientUser;
import com.kit.client.tools.*;
import com.kit.common.*;

@SuppressWarnings("serial")
public class KitClientLogin extends JFrame implements ActionListener {
	// define the components in the north
	JLabel jbl1;

	// define the components in the middle
	JTabbedPane jtp;
	JPanel jp2, jp3, jp4;
	JLabel jp2_jbl1, jp2_jbl2, jp2_jbl3, jp2_jbl4;
	JButton jp2_jb1;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1, jp2_jcb2;

	// define the components in the south
	JPanel jp1;
	JButton jp1_jb1, jp1_jb2, jp1_jb3;

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		KitClientLogin kitclientLogin = new KitClientLogin();
	}

	public KitClientLogin() {
		// deal with northern components
		jbl1 = new JLabel(new ImageIcon("image/head.gif"));

		// deal with middle components
		jp2 = new JPanel(new GridLayout(3, 3));
		jp2_jbl1 = new JLabel("KIT Number", JLabel.LEFT);
		jp2_jbl2 = new JLabel("KIT Password", JLabel.LEFT);
		jp2_jbl3 = new JLabel("Forget Password", JLabel.CENTER);
		jp2_jbl3.setForeground(Color.BLUE);
		jp2_jbl4 = new JLabel("PWD Protection", JLabel.CENTER);
		jp2_jb1 = new JButton(new ImageIcon("image/clear.gif"));
		jp2_jb1.addActionListener(this);
		jp2_jtf = new JTextField();
		jp2_jpf = new JPasswordField();
		jp2_jcb1 = new JCheckBox("Show pwd");
		jp2_jcb1.addActionListener(this);
		jp2_jcb2 = new JCheckBox("Save PWD");

		// put all components on the jp2
		jp2.add(jp2_jbl1);
		jp2.add(jp2_jtf);
		jp2.add(jp2_jb1);
		jp2.add(jp2_jbl2);
		jp2.add(jp2_jpf);
		jp2.add(jp2_jbl3);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		jp2.add(jp2_jbl4);

		// create the chosen window
		jtp = new JTabbedPane();
		jtp.add("KIT Number", jp2);
		jp3 = new JPanel();
		jtp.add("Mobile Number", jp3);
		jp4 = new JPanel();
		jtp.add("E-mail", jp4);

		// deal with southern components
		jp1 = new JPanel();
		jp1_jb1 = new JButton(new ImageIcon("image/login.gif"));
		jp1_jb1.addActionListener(this); // response the user's login click
		jp1_jb2 = new JButton(new ImageIcon("image/cancel.gif"));
		jp1_jb2.addActionListener(this);
		jp1_jb3 = new JButton(new ImageIcon("image/register.gif"));
		jp1_jb3.addActionListener(this);

		// put the three JButton on the jp1
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);

		this.add(jtp, "Center");
		this.add(jbl1, "North");
		this.add(jp1, "South");
		this.setSize(350, 240);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == jp1_jb1) { // if click login button
			KitClientUser kitClientUser = new KitClientUser();
			User u = new User();
			u.setUserId(jp2_jtf.getText().trim());
			u.setPasswd(new String(jp2_jpf.getPassword()));

			if (kitClientUser.checkUser(u)) {
				try {
					KitFriendList kitList = new KitFriendList(u.getUserId());
					ManageKitFriendList.addKitFriendList(u.getUserId(), kitList);

					// send a request to receive the online friend list
					ObjectOutputStream oos = new ObjectOutputStream(ManageClientConServerThread
							.getClientConServerThread(u.getUserId()).getS().getOutputStream());
					Message m = new Message();
					m.setMesType(MessageType.message_get_onLineFriend);
					m.setSender(u.getUserId());
					oos.writeObject(m);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "username or password is wrong!");
			}
		} else if (arg0.getSource() == jp1_jb2) {
			jp2_jtf.setText("");
			jp2_jpf.setText("");
		} else if (arg0.getSource() == jp1_jb3) {
			new Register();
		} else if (arg0.getSource() == jp2_jb1) {
			jp2_jtf.setText("");
		} else if (arg0.getSource() == jp2_jcb1) {
			jp2_jpf.setEchoChar((char) 0);
		}
	}
}
