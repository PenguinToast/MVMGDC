package com.mgdc.game.objects.exploration;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.mgdc.game.Global;


public class Shrub extends ExplorationObject {
	private AtlasRegion image;

	public Shrub() {
		image = Global.atlas.findRegion("Shrub");
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(image, getX(), getY());
		
	}
}
