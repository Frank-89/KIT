package com.kit.client.tools;

import java.io.*;
import java.net.*;

import com.kit.client.view.KitChat;
import com.kit.client.view.KitFriendList;
import com.kit.common.*;

public class ClientConServerThread extends Thread {
	private Socket s;

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public ClientConServerThread(Socket s) {
		this.s = s;
	}

	public void run() {
		while (true) {
			// continue reading information from server
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message) ois.readObject();

				if (m.getMesType().equals(MessageType.message_comm_mes)) {
					// show the message from server in the chat window
					KitChat kitChat = ManageKitChat.getKitChat(m.getGetter() + " " + m.getSender());
					kitChat.showMessage(m);
				} else if (m.getMesType().equals(MessageType.message_ret_onLineFriend)) {
					//String con = m.getCon();
					//String friends[] = con.split(" ");
					String getter = m.getGetter();
					KitFriendList kitFriendList = ManageKitFriendList.getKitFriendList(getter);
					if (kitFriendList != null) {
						kitFriendList.upateFriend(m);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
