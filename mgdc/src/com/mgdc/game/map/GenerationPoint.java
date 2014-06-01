package com.mgdc.game.map;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.utils.Array;

public class GenerationPoint extends MapObject {
	private int x, y;
	private Array<GenerationPoint> connections;
	private int tileType;
	
	public GenerationPoint() {
		connections = new Array<GenerationPoint>();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public Array<GenerationPoint> getConnections() {
		return connections;
	}
	
	public int getTileType() {
		return tileType;
	}
	
	public void setTileType(int tileType) {
		this.tileType = tileType;
	}
}
