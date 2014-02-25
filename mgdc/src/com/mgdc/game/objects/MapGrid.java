package com.mgdc.game.objects;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.mgdc.game.Global;

public class MapGrid extends Actor {
	public static float GRID_SIZE = 32;
	private ShapeRenderer shape;
	private Array<Texture> grass;

	public MapGrid() {
		shape = new ShapeRenderer();
		grass = new Array<Texture>();
		FileHandle dir = Gdx.files.local("data/terrain");
		for (FileHandle file : dir.list()) {
			Global.assets.load(new AssetDescriptor<Texture>(file, Texture.class));
			Global.assets.finishLoading();
			grass.add(Global.assets.get(file.path(), Texture.class));
		}
	}

	@Override
	public void act(float delta) {
		Camera cam = getStage().getCamera();
		setPosition(cam.position.x, cam.position.y);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		float w = getStage().getWidth();
		float h = getStage().getHeight();
		float ox = -getX() % GRID_SIZE;
		float oy = -getY() % GRID_SIZE;
		float ex = ((w) / GRID_SIZE);
		float ey = ((h) / GRID_SIZE);
		float sx = getX() - w/2;
		float sy = getY() - h/2;
		for (float x = -1; x < ex + 1; x++) {
			for (float y = -1; y < ey + 1; y++) {
				float xv = (x + (sx + ox) / GRID_SIZE);
				float yv = (y + (sy + oy) / GRID_SIZE);
				Random random = new Random((long) ((xv * yv)));
				Texture rand = grass.get((int) (random.nextFloat() * grass.size));
				batch.draw(rand, sx + x * GRID_SIZE + ox, sy + y * GRID_SIZE + oy);
			}
		}
		batch.end();
		shape.setProjectionMatrix(batch.getProjectionMatrix());
		shape.begin(ShapeType.Line);
		shape.setColor(Color.BLACK);
		for (float x = 0; x < ex + 1; x++) {
//			shape.line(sx + x * GRID_SIZE + ox, sy + oy - GRID_SIZE, sx + x * GRID_SIZE + ox, sy + oy + h + GRID_SIZE);
		}
		for (float y = 0; y < ey + 1; y++) {
//			shape.line(sx + ox - GRID_SIZE, sy + y * GRID_SIZE + oy, sx + ox + w + GRID_SIZE * 2, sy + y * GRID_SIZE + oy);
		}
		shape.end();
		batch.begin();
	}

}
