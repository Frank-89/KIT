package com.kit.client.model;

import com.kit.client.model.KitClientConServer;
import com.kit.common.User;

public class KitClientUser {
	public boolean checkUser(User u) {
		return new KitClientConServer().sendLoginInfoToServer(u);
	}
}
