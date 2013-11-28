package com.mvmgd.pong.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Paddle extends Actor {
	private AtlasRegion region;
	private boolean moveUp, moveDown;
	private static final int speed = 5;
	
	public Paddle(TextureAtlas atlas) {
		region = atlas.findRegion("paddle");
	}
	
	@Override
	public void act(float delta) {
		if(moveUp) {
			translate(0, speed);
		}
		if(moveDown) {
			translate(0, -speed);
		}
	}
	
	public void moveUp(boolean move) {
		this.moveUp = move;
	}
	
	public void moveDown(boolean move) {
		this.moveDown = move;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(region, getX(), getY());
	}
}
