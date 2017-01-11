package com.kit.client.tools;

import java.util.*;

import com.kit.client.view.*;;

public class ManageKitChat {

	private static HashMap<String, KitChat> hm = new HashMap<>();

	public static void addChat(String loginIdAnFriendId, KitChat KitChat) {
		hm.put(loginIdAnFriendId, KitChat);
	}

	public static KitChat getKitChat(String loginIdAnFriendId) {
		return (KitChat) hm.get(loginIdAnFriendId);
	}
}