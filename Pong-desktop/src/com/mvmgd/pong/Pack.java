package com.mvmgd.pong;

import com.badlogic.gdx.tools.imagepacker.TexturePacker2;

public class Pack {
	public static void main(String[] args) {
		TexturePacker2.process("../pack", "../Pong-android/assets/", "game");
	}
}
