package com.mgdc.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ObjectMap;

public class Global {
	public static final boolean DEBUG = true;
	
	public static MainGame game;
	
	public static AssetManager assets;
	public static ObjectMap<String, String> assetMap;
	public static Skin skin;
	public static TextureAtlas atlas;
}
