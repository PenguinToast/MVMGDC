package com.mvmgd.pong.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mvmgd.pong.Global;

public class Paddle extends Actor {
	private AtlasRegion region;
	private boolean moveUp, moveDown;
	private static final int speed = 5;
	private Body body;
	
	public Paddle(TextureAtlas atlas) {
		region = atlas.findRegion("paddle");
		setSize(region.originalWidth, region.originalHeight);
		
		BodyDef bDef = new BodyDef();
		bDef.type = BodyType.KinematicBody;
		body = Global.world.createBody(bDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(16 * Global.PHYSICS_SCALE, 128 * Global.PHYSICS_SCALE);
		
		FixtureDef fDef = new FixtureDef();
		fDef.shape = shape;
		fDef.isSensor = true;
		
		body.createFixture(fDef);
		body.setUserData(this);
		
		shape.dispose();
		
	}
	
	@Override
	public void translate(float x, float y) {
		super.translate(x, y);
		
		body.setTransform((getX() + x + getWidth()/2) * Global.PHYSICS_SCALE, (getY() + y + getHeight()/2) * Global.PHYSICS_SCALE, 0);
	}
	
	@Override
	public void setX(float x) {
		super.setX(x);
		
		body.setTransform((x + getWidth()/2) * Global.PHYSICS_SCALE, (getY() + getHeight()/2) * Global.PHYSICS_SCALE, 0);
	}
	
	@Override
	public void act(float delta) {
		setPosition(body.getPosition().x / Global.PHYSICS_SCALE, body.getPosition().y / Global.PHYSICS_SCALE);
		float s = 0;
		
		if(moveUp) {
			s += speed;
		}
		if(moveDown) {
			s -= speed;
		}

		body.setLinearVelocity(0, s);
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
