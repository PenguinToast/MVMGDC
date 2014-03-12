package com.mgdc.game.gen.map;

import com.mgdc.game.gen.Point;
public class Structure {
	protected Point[][] layout;
	protected int height, width;
	
	public Structure(int h, int w){
		layout = new Point[h][w];
		height = h;
		width = w;
	}
	
	public void setPoint(int x, int y, Point p) throws IllegalArgumentException{
		if(x >= width || y >= height)
			throw new IllegalArgumentException(String.format("The given coordinates are not inside this Structure's boundaries. Max x = %d; max y = %d.", width-1, height-1));
		else layout[height-y][width-x] = p;
	}
	
	public Point getPoint(int x, int y, Point p) throws IllegalArgumentException{
		if(x >= width || y >= height)
			throw new IllegalArgumentException(String.format("The given coordinates are not inside this Structure's boundaries. Max x = %d; max y = %d.", width-1, height-1));
		else return layout[height-y][width-x];
	}
	
	public void generate(int x, int y){
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++)
				layout[i][j] = pic
		}
	}
}
