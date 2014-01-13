package com.mgdc.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mgdc.game.Global;

public class LoadingScreen extends BaseScreen {
	public LoadingScreen() {
		super();
		
	}
	
	@Override
	public void show() {
		super.show();
		// Crappy code for testing right now
		Global.assets = new AssetManager();
		Global.assets.load("game.atlas", TextureAtlas.class);
		Global.assets.finishLoading();
		Global.skin = new Skin(Gdx.files.internal("data/skin.json"), Global.assets.get("game.atlas", TextureAtlas.class));
		Global.game.transition(new MainMenuScreen());
	}
}
