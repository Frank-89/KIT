package com.kit.client.model;

import java.net.*;
import java.io.*;

import com.kit.client.tools.*;
import com.kit.common.*;

public class KitClientConServer {
	public Socket s;

	// send the first request
	public boolean sendLoginInfoToServer(Object o) {
		boolean b = false;
		try {
			s = new Socket("127.0.0.1", 9999);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message ms = (Message) ois.readObject();
			// check user login 
			if (ms.getMesType().equals("1")) {
				// create the thread connect between QQ and server
				ClientConServerThread ccst = new ClientConServerThread(s);
				ccst.start();
				ManageClientConServerThread.addClientConServerThread(((User) o).getUserId(), ccst);
				b = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

		}
		return b;
	}
}
