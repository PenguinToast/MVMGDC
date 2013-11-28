package com.mvmgd.pong;

import com.badlogic.gdx.Game;
import com.mvmgd.pong.screen.MenuScreen;

public class PongGame extends Game {
	
	@Override
	public void create() {		
		setScreen(new MenuScreen(this));
	}
}
