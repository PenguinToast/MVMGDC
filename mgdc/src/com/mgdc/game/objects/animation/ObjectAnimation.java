package com.mgdc.game.objects.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Entries;
import com.badlogic.gdx.utils.ObjectMap.Entry;

public class ObjectAnimation {
	private String currentAnimation;
	private float animationTimer;
	private ObjectMap<String, Animation> animations;
	private Vector2 size;
	public static final float scale = 16;

	public ObjectAnimation() {
	}

	public ObjectAnimation(ObjectAnimation source) {
		animations = new ObjectMap<String, Animation>();
		Entries<String, Animation> entries = source.animations.entries();
		while (entries.hasNext) {
			Entry<String, Animation> entry = entries.next();
			Animation copy = new Animation(entry.value.frameDuration, entry.value.getKeyFrames());
			animations.put(entry.key, copy);
		}
		currentAnimation = animations.keys().next();
		setSize(new Vector2(source.size).scl(scale));
	}

	public void setAnimations(ObjectMap<String, Animation> animations) {
		this.animations = animations;
		currentAnimation = animations.keys().next();
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}

	public Vector2 getSize() {
		return size;
	}

	public void update(float delta) {
		animationTimer += delta;
	}

	public void draw(Batch batch, Actor actor) {
		TextureRegion reg = getRegion();
		batch.draw(reg, actor.getX(), actor.getY(), reg.getRegionWidth() * scale, reg.getRegionHeight() * scale);
	}

	public void setAnimation(String name) {
		animationTimer = 0;
		currentAnimation = name;
	}

	public Array<String> getAnimations() {
		return animations.keys().toArray();
	}

	public TextureRegion getRegion() {
		return animations.get(currentAnimation).getKeyFrame(animationTimer, true);
	}

}
