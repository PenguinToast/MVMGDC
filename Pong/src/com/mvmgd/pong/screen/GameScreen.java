package com.mvmgd.pong.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mvmgd.pong.CollisionListener;
import com.mvmgd.pong.Global;
import com.mvmgd.pong.actor.Ball;
import com.mvmgd.pong.actor.Paddle;
import com.mvmgd.pong.actor.Wall;

public class GameScreen implements Screen {
	private Stage stage;
	Box2DDebugRenderer debug;
	
	public GameScreen() {
		Global.world = new World(new Vector2(), true);
		Global.world.setContactListener(new CollisionListener());
		debug = new Box2DDebugRenderer();	
		
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
		
		Ball ball = new Ball(atlas);
		stage.addActor(ball);
		
		Wall bWall = new Wall(0);
		Wall tWall = new Wall(Gdx.graphics.getHeight());
		
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

		Global.world.step(delta, 6, 2);
		
		stage.act(delta);
		stage.draw();
		
		debug.render(Global.world, stage.getCamera().combined.scl(1/Global.PHYSICS_SCALE));
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
