package com.mgdc.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mgdc.game.screen.LoadingScreen;
import com.mgdc.game.screen.TransitionScreen;

public class MainGame extends Game {

	@Override
	public void create() {
		Global.game = this;
		setScreen(new LoadingScreen());
	}
	
	public void transition(Screen target) {
		setScreen(new TransitionScreen(getScreen(), target)); 
	}
	
}
