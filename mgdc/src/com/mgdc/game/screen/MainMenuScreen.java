package com.mgdc.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.mgdc.game.Global;

public class MainMenuScreen extends BaseScreen {
	public MainMenuScreen() {
		super();
		
		TextButton startButton = new TextButton("Start", Global.skin);
		startButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.exit();
			}
		});
		table.add(startButton).space(5);
		table.row();
		
		TextButton optionsButton = new TextButton("Options", Global.skin);
		optionsButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.exit();
			}
		});
		table.add(optionsButton).space(5);
		table.row();
		
		TextButton quitButton = new TextButton("Quit", Global.skin);
		quitButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Gdx.app.exit();
			}
		});
		table.add(quitButton);
	}
}
