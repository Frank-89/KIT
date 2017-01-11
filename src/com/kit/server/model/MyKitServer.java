package com.kit.server.model;

/**
 * This is KIT server, it's listening and waiting for any KitClient to connect
 */

import java.net.*;

import com.kit.common.*;

import java.io.*;

public class MyKitServer {
	@SuppressWarnings("resource")
	public MyKitServer() {
		try {
			System.out.println("I'm server and listening at port 9999");
			ServerSocket ss = new ServerSocket(9999);	// listen at port 9999
			while (true) {
				Socket s = ss.accept();
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				User u = (User) ois.readObject();
				System.out.println("server has received user's id: " + u.getUserId() + ", password: " + u.getPasswd());
				Message m = new Message();
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				if (u.getPasswd().equals("123456")) {
					m.setMesType("1");
					oos.writeObject(m);
					SerConClientThread scct = new SerConClientThread(s);		// create a thread and keep connect with client
					ManageClientThread.addClientThread(u.getUserId(), scct);
					scct.start();		// start thread
					scct.notifyOther(u.getUserId());
				} else {
					m.setMesType("2");
					oos.writeObject(m);
					s.close();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

		}
	}
}