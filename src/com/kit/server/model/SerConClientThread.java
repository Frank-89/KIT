package com.kit.server.model;

import java.net.*;
import java.io.*;
import java.util.*;

import com.kit.common.*;

public class SerConClientThread extends Thread {
	Socket s;

	public SerConClientThread(Socket s) {
		this.s = s; // set the connection between server and client to s
	}

	public void notifyOther(String iam) {
		HashMap<String, SerConClientThread> hm = ManageClientThread.hm;
		Iterator<String> it = hm.keySet().iterator();
		while (it.hasNext()) {
			Message m = new Message();
			m.setCon(iam);
			m.setMesType(MessageType.message_ret_onLineFriend);
			String onLineUserId = it.next().toString();
			try {
				ObjectOutputStream oos = new ObjectOutputStream(
						ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
				m.setGetter(onLineUserId);
				oos.writeObject(m);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	public void run() {
		while (true) {
			// thread can receive the information from client
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message) ois.readObject();

				// check the message from client and respond
				if (m.getMesType().equals(MessageType.message_comm_mes)) {
					SerConClientThread sc = ManageClientThread.getClientThread(m.getGetter());
					ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(m);
				} else if (m.getMesType().equals(MessageType.message_get_onLineFriend)) {
					String res = ManageClientThread.getAllonLineUserid();
					Message m2 = new Message();
					m2.setMesType(MessageType.message_ret_onLineFriend);
					m2.setCon(res);
					m2.setGetter(m.getSender());
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m2);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}