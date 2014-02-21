package com.mgdc.game.objects.animation;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectMap.Entries;
import com.badlogic.gdx.utils.ObjectMap.Entry;
import com.mgdc.game.Global;

public class AnimationLoader extends SynchronousAssetLoader<ObjectAnimation, AnimationLoader.AnimationLoaderParameter> {
	private JsonReader reader;

	public AnimationLoader() {
		this(new InternalFileHandleResolver());
	}

	public AnimationLoader(FileHandleResolver resolver) {
		super(resolver);
		reader = new JsonReader();
	}

	@Override
	public ObjectAnimation load(AssetManager assetManager, String fileName, FileHandle file, AnimationLoaderParameter parameter) {
		JsonValue root = reader.parse(file);
		JsonValue frameSize = root.get("frameSize");
		int frameWidth = frameSize.getInt(0);
		int frameHeight = frameSize.getInt(1);
		float frameDuration = root.getFloat("frameDuration");
		JsonValue frames = root.get("frames");
		Texture texture = assetManager.get(file.pathWithoutExtension() + ".png", Texture.class);
		TextureRegion[][] regions = TextureRegion.split(texture, frameWidth, frameHeight);

		ObjectMap<String, IntMap<TextureRegion>> textures = new ObjectMap<String, IntMap<TextureRegion>>();
		for (int i = 0; i < frames.size; i++) {
			JsonValue row = frames.get(i);
			for (int j = 0; j < row.size; j++) {
				String data = row.getString(j);
				if (data != null) {
					int split = data.lastIndexOf('.');
					String name = data.substring(0, split);
					int index = Integer.parseInt(data.substring(split + 1));
					if (!textures.containsKey(name)) {
						textures.put(name, new IntMap<TextureRegion>());
					}
					textures.get(name).put(index, regions[i][j]);
				}
			}
		}

		ObjectMap<String, Animation> animations = new ObjectMap<String, Animation>();
		Entries<String, IntMap<TextureRegion>> entries = textures.entries();
		while(entries.hasNext) {
			Entry<String, IntMap<TextureRegion>> entry = entries.next();
			String name = entry.key;
			Array<TextureRegion> animRegions = new Array<TextureRegion>();
			int i = 0;
			while(entry.value.containsKey(i)) {
				animRegions.add(entry.value.get(i));
				i++;
			}
			Animation anim = new Animation(frameDuration, animRegions);
			animations.put(name, anim);
		}
		
		ObjectAnimation out = new ObjectAnimation();
		out.setAnimations(animations);
		out.setSize(new Vector2(frameWidth, frameHeight));
		Global.assetMap.put(file.nameWithoutExtension(), file.path());
		return out;
	}

	@Override
	public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, AnimationLoaderParameter parameter) {
		Array<AssetDescriptor> out = new Array<AssetDescriptor>();
		out.add(new AssetDescriptor<Texture>(file.pathWithoutExtension() + ".png", Texture.class));
		return out;
	}

	public static class AnimationLoaderParameter extends AssetLoaderParameters<ObjectAnimation> {

	}

}
