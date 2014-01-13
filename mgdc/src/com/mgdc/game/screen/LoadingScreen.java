package com.mgdc.game.screen;

import com.mgdc.game.Global;

public class LoadingScreen extends BaseScreen {
	public LoadingScreen() {
		super();
		
	}
	
	@Override
	public void show() {
		super.show();

		Global.game.transition(new MainMenuScreen());
	}
}
