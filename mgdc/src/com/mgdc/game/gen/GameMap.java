package com.mgdc.game.gen;

import java.util.*;
public class GameMap
{
	protected ArrayList<ArrayList<Point>> map = new ArrayList<ArrayList<Point>>();
	protected Integer startx = 0, starty = 0;
	public GameMap()
	{
		for(int i = -50; i < 50; i++)
		{
			map.add(new ArrayList<Point>());
			for(int j = -50; j < 51; j++)
			{
				map.get(i).add(new Point(this, i,j));
			}
		}
	}
		
	public static void main(String [] args)
	{
		
	}
	
	public void refreshPosition(Point p)
	{
		
	}
}
