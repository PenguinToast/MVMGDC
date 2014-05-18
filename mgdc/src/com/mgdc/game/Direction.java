package com.mgdc.game;

public class Direction {
	public static int NORTH = 0;
	public static int NORTHEAST = 45;
	public static int EAST = 90;
	public static int SOUTHEAST = 135;
	public static int SOUTH = 180;
	public static int SOUTHWEST = 225;
	public static int WEST = 270;
	public static int NORTHWEST = 315;
	
	public int roundToDirection(int bearing)
	{
		int a = bearing;
		a %= 360;
		if(btwn(a, 337, 22)) return NORTH;
		if(btwn(a, 22, 67)) return NORTHEAST;
		if(btwn(a, 67, 112)) return EAST;
		if(btwn(a, 112, 157)) return SOUTHEAST;
		if(btwn(a, 157, 202)) return SOUTH;
		if(btwn(a, 202, 247)) return SOUTHWEST;
		if(btwn(a, 247, 292)) return WEST;
		return NORTHWEST;
		
	}
	
	public boolean btwn(int x, int min, int max)
	{
		return (x >= min && x < max);
	}
}
