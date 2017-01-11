package com.kit.client.tools;

import java.util.HashMap;

import com.kit.client.tools.ClientConServerThread;

public class ManageClientConServerThread {
	private static HashMap<String, ClientConServerThread> hm = new HashMap<>();

	public static void addClientConServerThread(String qqId, ClientConServerThread ccst) {
		hm.put(qqId, ccst);
	}

	public static ClientConServerThread getClientConServerThread(String qqId) {
		return (ClientConServerThread) hm.get(qqId);
	}
}