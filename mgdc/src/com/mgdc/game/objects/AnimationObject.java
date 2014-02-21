package com.mgdc.game.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import com.mgdc.game.objects.animation.ObjectAnimation;

public class AnimationObject extends Actor {
	private ObjectAnimation animation;
	private Array<String> animationNames;
	private int animCount;

	public AnimationObject(ObjectAnimation animation) {
		this.animation = animation;
		setSize(animation.getSize().x, animation.getSize().y);
		animationNames = animation.getAnimations();
		addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				cycleAnimation();
				return true;
			}
			
			@Override
			public void touchDragged(InputEvent event, float x, float y, int pointer) {
				setPosition(event.getStageX(), event.getStageY());
				super.touchDragged(event, x, y, pointer);
			}
		});
	}
	
	@Override
	public void act(float delta) {
		animation.update(delta);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		animation.draw(batch, this);
	}
	
	public void cycleAnimation() {
		animCount++;
		if (animCount >= animationNames.size) {
			animCount = 0;
		}
		animation.setAnimation(animationNames.get(animCount));
	}

}
