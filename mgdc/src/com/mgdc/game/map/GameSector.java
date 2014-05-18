package com.mgdc.game.map;

import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mgdc.game.Global;

public class GameSector {
	private TiledMap map;
	private int x, y;

	public GameSector() {

		Global.assets.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		Global.assets.load("maps/test.tmx", TiledMap.class);
		Global.assets.finishLoading();
		
		map = Global.assets.get("maps/test.tmx", TiledMap.class);
	}

	public TiledMap getMap() {
		return map;
	}

	public void saveFile() {
		String fileName = "sector_" + x + "" + y + ".tmx";

	}
}
