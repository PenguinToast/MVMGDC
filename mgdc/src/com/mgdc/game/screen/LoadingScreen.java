package com.mgdc.game.screen;

import java.io.BufferedReader;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ObjectMap;
import com.mgdc.game.Global;
import com.mgdc.game.objects.animation.AnimationLoader;
import com.mgdc.game.objects.animation.ObjectAnimation;

public class LoadingScreen extends BaseScreen {
	public LoadingScreen() {
		super();

	}

	@Override
	public void show() {
		super.show();
		// Crappy code for testing right now
		Global.assetMap = new ObjectMap<String, String>();
		Global.assets = new AssetManager();
		Global.assets.load("game.atlas", TextureAtlas.class);
		loadFiles();
		Global.assets.finishLoading();
		Global.skin = new Skin(Gdx.files.internal("data/skin.json"), Global.assets.get("game.atlas", TextureAtlas.class));
		Global.game.transition(new MainMenuScreen());
	}

	private void loadFiles() {
		try {
			Global.assets.setLoader(ObjectAnimation.class, ".animation", new AnimationLoader());
			loadDirectory(Gdx.files.internal("."));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void loadDirectory(FileHandle dir) throws IOException {
		BufferedReader br = new BufferedReader(Gdx.files.internal(dir.path() + "/" + "filelist.txt").reader());
		String line = null;
		while((line = br.readLine()) != null) {
			if (line.startsWith("/") || line.endsWith("/")) {
				loadDirectory(Gdx.files.internal(dir.path() + "/" +  line));
			} else {
				if (line.endsWith(".animation")) {
					Global.assets.load(dir.path() + "/" + line, ObjectAnimation.class);
				}
			}
		}
	}
}
