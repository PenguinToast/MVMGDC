package com.mgdc.game.gen;

import java.lang.IllegalArgumentException;

public class Renderer {
	public int[][] window = new int[2][2];
	public void setWindow (int[][] in) throws IllegalArgumentException
	{
		if(in.length != 2) throw new IllegalArgumentException("The window passed has the wrong dimensions.");
		if(in[0].length != 2) throw new IllegalArgumentException("The window passed has the wrong dimensions.");
		if(in[1].length != 2) throw new IllegalArgumentException("The window passed has the wrong dimensions.");
		window = in;
	}
	
	
	
	public void render(int x, int y){
		//@William Implement pls
	}
}
