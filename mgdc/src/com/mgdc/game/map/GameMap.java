package com.mgdc.game.map;

import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.IntMap.Keys;

public class GameMap {
	private IntMap<IntMap<GameSector>> sectors;

	public GameMap() {
		sectors = new IntMap<IntMap<GameSector>>();
	}
	
	public void generateSector(int x, int y) {
		if(!sectors.containsKey(x)) {
			sectors.put(x, new IntMap<GameSector>());
		}
		sectors.get(x).put(y, new GameSector());
	}
	
	public GameSector getSector(int x, int y) {
		return sectors.get(x).get(y);
	}
	
	public void save() {
		Keys xs = sectors.keys();
		while(xs.hasNext) {
			int x = xs.next();
			IntMap<GameSector> sects = sectors.get(x);
			Keys ys = sects.keys();
			while(ys.hasNext) {
				int y = ys.next();
				GameSector sect = sects.get(y);
				
			}
		}
	}
}
