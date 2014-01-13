package com.mvmgd.pong.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mvmgd.pong.Global;

public class Wall extends Actor {
	private Body body;
	
	public Wall(float y) {
		
		BodyDef bDef = new BodyDef();
		bDef.type = BodyType.StaticBody;
		bDef.position.set(Gdx.graphics.getWidth()/2 * Global.PHYSICS_SCALE, y * Global.PHYSICS_SCALE);
		body = Global.world.createBody(bDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(Gdx.graphics.getWidth() * Global.PHYSICS_SCALE, 20 * Global.PHYSICS_SCALE);
		
		FixtureDef fDef = new FixtureDef();
		fDef.shape = shape;
		fDef.isSensor = true;
		
		body.createFixture(fDef);
		body.setUserData(this);
		
		shape.dispose();
		
	}
	
	@Override
	public void act(float delta) {
	}
}
