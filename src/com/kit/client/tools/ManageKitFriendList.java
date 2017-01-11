package com.kit.client.tools;

import java.util.*;

import com.kit.client.view.*;

public class ManageKitFriendList {

	private static HashMap<String, KitFriendList> hm = new HashMap<>();

	public static void addKitFriendList(String kitId, KitFriendList kitFriendList) {
		hm.put(kitId, kitFriendList);
	}

	public static KitFriendList getKitFriendList(String kitId) {
		return (KitFriendList) hm.get(kitId);
	}
}

