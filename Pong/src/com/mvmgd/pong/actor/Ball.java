package com.mvmgd.pong.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mvmgd.pong.Global;

public class Ball extends Actor {
	private AtlasRegion region;
	private float speed = 5;
	private Body body;
	
	public Ball(TextureAtlas atlas) {
		region = atlas.findRegion("ball");
		BodyDef bDef = new BodyDef();
		bDef.type = BodyType.DynamicBody;
		body = Global.world.createBody(bDef);
		
		CircleShape shape = new CircleShape();
		shape.setRadius(32 * Global.PHYSICS_SCALE);
		
		FixtureDef fDef = new FixtureDef();
		fDef.shape = shape;
		fDef.restitution = 2;
		fDef.density = 1;
		
		body.createFixture(fDef);
		body.setUserData(this);
		
		shape.dispose();
		
	}
	
	@Override
	public void translate(float x, float y) {
		super.translate(x, y);
		
		body.setTransform((getX() + x) * Global.PHYSICS_SCALE, (getY() + y) * Global.PHYSICS_SCALE, 0);
	}
	
	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		body.setTransform((x) * Global.PHYSICS_SCALE, (y) * Global.PHYSICS_SCALE, 0);
	}
	
	@Override
	public void setX(float x) {
		super.setX(x);
		
		body.setTransform((x) * Global.PHYSICS_SCALE, (getY()) * Global.PHYSICS_SCALE, 0);
	}
	
	@Override
	public void act(float delta) {
		
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(region, getX(), getY());
	}
}
