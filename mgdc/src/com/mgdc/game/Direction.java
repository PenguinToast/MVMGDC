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
	
	public int roundToDirection(Integer bearing)
	{
		int a = bearing.intValue();
		while(a < 361 && a > -1)
		{
			if(a < 0) a += 360;
			if(a > 360) a -= 360;
		}
		if(isBetween(a, 337, 22)) return NORTH;
		if(isBetween(a, 22, 67)) return NORTHEAST;
		if(isBetween(a, 67, 112)) return EAST;
		if(isBetween(a, 112, 157)) return SOUTHEAST;
		if(isBetween(a, 157, 202)) return SOUTH;
		if(isBetween(a, 202, 247)) return SOUTHWEST;
		if(isBetween(a, 247, 292)) return WEST;
		return NORTHWEST;
		
	}
	
	public boolean isBetween(int x, int min, int max)
	{
		return (x > min && x <= max);
	}
}
