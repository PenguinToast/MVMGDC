package com.mgdc.game.screen;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
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
		Global.assets.finishLoading();
		loadFiles();
		Global.assets.finishLoading();
		Global.skin = new Skin(Gdx.files.internal("data/skin.json"), Global.assets.get("game.atlas", TextureAtlas.class));
		Global.game.transition(new MainMenuScreen());
	}

	private void loadFiles() {
		try {
			Global.assets.setLoader(Texture.class, ".png", new TextureLoader(new LocalFileHandleResolver()));
			Global.assets.setLoader(ObjectAnimation.class, ".animation", new AnimationLoader(new LocalFileHandleResolver()));
			loadDirectory(Gdx.files.local("./data/"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void loadDirectory(FileHandle dir) throws IOException {
		FileHandle[] files = dir.list();
		for (FileHandle file : files) {
			if (file.isDirectory()) {
				loadDirectory(file);
			} else {
				if (file.extension().equalsIgnoreCase("animation")) {
					Global.assets.load(new AssetDescriptor<ObjectAnimation>(file, ObjectAnimation.class));
				}
			}
		}
		/* BufferedReader br = new BufferedReader(Gdx.files.internal(dir.path() + "/" + "filelist.txt").reader());
		String line = null;
		while((line = br.readLine()) != null) {
			if (line.startsWith("/") || line.endsWith("/")) {
				loadDirectory(Gdx.files.internal(dir.path() + "/" +  line));
			} else {
				if (line.endsWith(".animation")) {
					Global.assets.load(dir.path() + "/" + line, ObjectAnimation.class);
				}
			}
		} */
	}

	private class LocalFileHandleResolver implements FileHandleResolver {
		@Override
		public FileHandle resolve(String fileName) {
			return Gdx.files.local(fileName);
		}
	}
}
