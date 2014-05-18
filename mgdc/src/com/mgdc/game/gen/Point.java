package com.mgdc.game.gen;

public class Point extends Renderer{
	protected Integer x, y;
	protected GameMap map;
	
	public Point(GameMap inmap, Integer newx, Integer newy)
	{
		x = newx;
		y = newy;
		map = inmap;
	}
	
	public Point(GameMap inmap, int newx, int newy)
	{
		x = new Integer(newx);
		y = new Integer(newy);
		map = inmap;
	}
	
	public void putSelfInMap(GameMap inmap, int newx, int newy)
	{
		x = newx;
		y = newy;
		map = inmap;
		map.put(this, x, y);
	}
	
	public Point()
	{
		x = null; y = null; map = null;
	}
	
	public Integer getX(){ return x; }
	public Integer getY(){ return y; }
	
	public void setX(Integer newx){ 
		x = newx;
		map.refreshPosition(this);
	}
	public void setY(Integer newy){ 
		y = newy;
		map.refreshPosition(this);
	}	
}
