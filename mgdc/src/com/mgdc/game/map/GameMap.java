package com.mgdc.game.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.IntMap.Keys;

public class GameMap {
	private IntMap<IntMap<TiledMap>> sectors;
	public static final int SECTOR_SIZE = 50;

	public GameMap() {
		sectors = new IntMap<IntMap<TiledMap>>();
	}
	
	public void generateSector(int x, int y) {
		if(!sectors.containsKey(x)) {
			sectors.put(x, new IntMap<TiledMap>());
		}
		sectors.get(x).put(y, new TiledMap());
	}
	
	public TiledMap getSector(int x, int y) {
		return sectors.get(x).get(y);
	}
	
	public void save() {
		Keys xs = sectors.keys();
		while(xs.hasNext) {
			int x = xs.next();
			IntMap<TiledMap> sects = sectors.get(x);
			Keys ys = sects.keys();
			while(ys.hasNext) {
				int y = ys.next();
				TiledMap sect = sects.get(y);
				// Save the sector to its own file
			}
		}
	}
}
