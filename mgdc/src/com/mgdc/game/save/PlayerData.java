package com.mgdc.game.save;

public class PlayerData {

	private static PlayerData INSTANCE;
	
	private PlayerData() {
	}
	
	public static PlayerData getInstance() {
		return INSTANCE;
	}

}
