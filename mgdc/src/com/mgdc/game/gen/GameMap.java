package com.mgdc.game.gen;

import java.lang.IndexOutOfBoundsException;
import com.mgdc.game.*;
import java.util.*;
public class GameMap
{
	protected ArrayList<ArrayList<Point>> map = new ArrayList<ArrayList<Point>>();
	protected Integer originx = 51, originy = 51;
	public GameMap()
	{
		for(int i = -50; i < 50; i++)	//50 is an arbitrary starting value
		{
			map.add(new ArrayList<Point>());
			for(int j = -50; j < 50; j++)
			{
				map.get(i).add(new Point(this, i,j));	//now we have a 100x100 square grid, with the origin at (51,51).
			}
		}
	}
	
	public void addInDirection(int d)		//Will do later when my brain is working.
	{
		if(d == Direction.NORTH || d == Direction.NORTHEAST || d == Direction.NORTHWEST)
		{
			map.add(new ArrayList<Point>(map.get(0).size()));
			originy++;	//I know this needs to be here though.
		}
		if(d == Direction.SOUTH || d == Direction.SOUTHEAST || d == Direction.SOUTHWEST)
		{
			map.add(new ArrayList<Point>(map.get(0).size()));
			originy--;
		}
		if(d == Direction.EAST || d == Direction.SOUTHEAST || d == Direction.NORTHEAST)
		{
			for(ArrayList<Point> a: map)
				a.add(0, new Point());
			originx--;
		}
		if(d == Direction.WEST || d == Direction.NORTHWEST || d == Direction.SOUTHWEST)
		{
			for(ArrayList<Point> a : map)
				a.add(new Point());
			originx++;
		}
	}
	
	public void put(Point p, int x, int y) throws IndexOutOfBoundsException
	{
		
	}
	
	public void refreshPosition(Point p) //Should put the Point p in the correct position in the ArrayList
	{
		
	}
}
