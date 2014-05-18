package com.mgdc.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mgdc.game.Global;
import com.mgdc.game.map.GameMap;
import com.mgdc.game.objects.AnimationObject;
import com.mgdc.game.objects.MapGrid;
import com.mgdc.game.objects.animation.ObjectAnimation;

public class ExplorationScreen extends BaseScreen {
	private GameMap map;
	private OrthographicCamera camera;
	private Vector2 touchLoc;
	private Vector2 touchOffset;
	private Stage gameStage;

	public ExplorationScreen() {
		map = new GameMap();

		gameStage = new Stage();

		camera = new OrthographicCamera(500, 300);
		gameStage.setCamera(camera);


		InputMultiplexer multi = new InputMultiplexer(stage, gameStage, new GameInputProcessor());
		Gdx.input.setInputProcessor(multi);

		touchOffset = new Vector2();
		
		// gameStage.addActor(new MapGrid());

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		float mult = 1;
		float dx = delta * touchOffset.x * mult;
		float dy = delta * touchOffset.y * -mult;
		camera.translate(dx, dy);

		camera.update();
		
		map.draw(camera);

		gameStage.act(delta);
		gameStage.draw();

		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		gameStage.setViewport(width, height, true);
	}

	public class GameInputProcessor implements InputProcessor {
		@Override
		public boolean keyDown(int keycode) {
			if (keycode == Keys.SPACE) {
				Gdx.input.getTextInput(new TextInputListener() {

					@Override
					public void input(String text) {
						AnimationObject actor = new AnimationObject(new ObjectAnimation(Global.assets.get(Global.assetMap.get(text),
								ObjectAnimation.class)));
						actor.setPosition(camera.position.x, camera.position.y);
						gameStage.addActor(actor);
					}

					@Override
					public void canceled() {

					}
				}, "Enter an animation to spawn: ", "");
			}
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean keyTyped(char character) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			touchLoc = new Vector2(screenX, screenY);
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			touchOffset = new Vector2();
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			touchOffset = new Vector2(screenX, screenY).sub(touchLoc);
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			// TODO Auto-generated method stub
			return false;
		}
	}
}
