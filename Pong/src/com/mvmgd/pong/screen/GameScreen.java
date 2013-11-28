package com.mvmgd.pong.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mvmgd.pong.actor.Paddle;

public class GameScreen implements Screen {
	private Stage stage;
	
	public GameScreen() {
		Camera camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		stage.setCamera(camera);
		
		TextureAtlas atlas = new TextureAtlas("game.atlas");
		
		final Paddle paddle = new Paddle(atlas);
		stage.addActor(paddle);

		final Paddle paddle2 = new Paddle(atlas);
		paddle2.setX(Gdx.graphics.getWidth()-32);
		stage.addActor(paddle2);
		
		stage.addListener(new ClickListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if(keycode == Keys.W) {
					paddle.moveUp(true);
				}
				if(keycode == Keys.S) {
					paddle.moveDown(true);
				}
				if(keycode == Keys.UP) {
					paddle2.moveUp(true);
				}
				if(keycode == Keys.DOWN) {
					paddle2.moveDown(true);
				}
				return true;
			}
			
			@Override
			public boolean keyUp(InputEvent event, int keycode) {
				if(keycode == Keys.W) {
					paddle.moveUp(false);
				}
				if(keycode == Keys.S) {
					paddle.moveDown(false);
				}
				if(keycode == Keys.UP) {
					paddle2.moveUp(false);
				}
				if(keycode == Keys.DOWN) {
					paddle2.moveDown(false);
				}
				return true;
			}
		});
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
