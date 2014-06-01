package com.mgdc.game.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.mgdc.game.map.GameMap;
import com.mgdc.game.objects.animation.ObjectAnimation;

public class Player extends Actor {
	private ObjectAnimation animation;
	private Array<String> animationNames;
	private int animCount;
	
	byte moveX = 0;
	byte moveY = 0;
	
	float moveDelay = 0.3f;
	float moveTimer = 0;

	public Player(ObjectAnimation animation) {
		this.animation = animation;
		setSize(animation.getSize().x, animation.getSize().y);
		animationNames = animation.getAnimations();
	}
	
	@Override
	public void act(float delta) {
		animation.update(delta);
		moveTimer -= delta;
		if (moveTimer <= 0) {
			moveTimer = moveDelay;
			translate(moveX * GameMap.TILE_SIZE, moveY * GameMap.TILE_SIZE);
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		animation.draw(batch, this);
	}
	
	public void move(byte x, byte y) {
		moveX = x;
		moveY = y;
	}
	
	public void cycleAnimation() {
		animCount++;
		if (animCount >= animationNames.size) {
			animCount = 0;
		}
		animation.setAnimation(animationNames.get(animCount));
	}

}
