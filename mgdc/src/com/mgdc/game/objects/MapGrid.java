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
		for (float x = 0; x < ex; x++) {
			for (float y = 0; y < ey; y++) {
				Random random = new Random((long) (((x + sx + ox) * (y + sy + oy)) * 100));
				Texture rand = grass.get((int) (random.nextFloat() * grass.size));
				batch.draw(rand, sx + x * GRID_SIZE + ox, sy + y * GRID_SIZE + oy);
			}
		}
		batch.end();
		shape.begin(ShapeType.Line);
		shape.setColor(Color.BLACK);
		for (float x = 0; x < w + GRID_SIZE; x += GRID_SIZE) {
			shape.line(ox + x, oy - GRID_SIZE, ox + x, oy + h + GRID_SIZE);
		}
		for (float y = 0; y < h + GRID_SIZE * 2; y += GRID_SIZE) {
			shape.line(ox - GRID_SIZE, oy + y, ox + w + GRID_SIZE * 2, oy + y);
		}
		shape.end();
		batch.begin();
	}

}
