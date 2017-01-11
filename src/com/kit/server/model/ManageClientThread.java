package com.kit.server.model;

import java.util.HashMap;
import java.util.Iterator;

import com.kit.server.model.SerConClientThread;

public class ManageClientThread {
	public static HashMap<String, SerConClientThread> hm = new HashMap<>();

	public static void addClientThread(String uid, SerConClientThread ct) {
		hm.put(uid, ct);
	}

	public static SerConClientThread getClientThread(String uid) {
		return (SerConClientThread) hm.get(uid);
	}

	public static String getAllonLineUserid() {
		Iterator<String> it = hm.keySet().iterator();
		String res = "";
		while (it.hasNext()) {
			res += it.next().toString() + " ";
		}
		return res;
	}
}