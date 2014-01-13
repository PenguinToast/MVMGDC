package com.mgdc.game;

import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

public class Pack {
	public static void main(String[] args) {
		TexturePacker2.process("../pack", "../mgdc-android/assets/", "game");
		System.out.println("Pack finished");
	}
}

