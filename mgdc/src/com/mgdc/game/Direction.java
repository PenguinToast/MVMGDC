package com.mgdc.game;

public enum Direction {
	NORTH,
	NORTHEAST,
	EAST,
	SOUTHEAST,
	SOUTH,
	SOUTHWEST,
	WEST,
	NORTHWEST;
	
	public Direction roundToDirection(Integer bearing)
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
