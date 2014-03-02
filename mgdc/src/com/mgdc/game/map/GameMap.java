package com.mgdc.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.IntMap.Keys;
import com.mgdc.game.Global;

public class GameMap {
	private IntMap<IntMap<TiledMap>> sectors;
	public static final int SECTOR_SIZE = 50;
	private OrthogonalTiledMapRenderer mapRenderer;
	private Array<TextureRegion> grass;

	public GameMap() {
		sectors = new IntMap<IntMap<TiledMap>>();
		mapRenderer = new OrthogonalTiledMapRenderer(new TiledMap());
		grass = new Array<TextureRegion>();
		FileHandle dir = Gdx.files.local("data/terrain");
		for (FileHandle file : dir.list()) {
			Global.assets.load(new AssetDescriptor<Texture>(file, Texture.class));
			Global.assets.finishLoading();
			grass.add(new TextureRegion(Global.assets.get(file.path(), Texture.class)));
		}
	}
	
	public void draw(OrthographicCamera camera) {
		float minX = (camera.position.x - camera.viewportWidth / 2) / SECTOR_SIZE;
		float minY = (camera.position.y - camera.viewportHeight / 2) / SECTOR_SIZE;
		float maxX = (camera.position.x + camera.viewportWidth / 2) / SECTOR_SIZE;
		float maxY = (camera.position.y + camera.viewportHeight / 2) / SECTOR_SIZE;
		
		for (float x = minX; x <= maxX; x++) {
			for (float y = minY; y <= maxY; y++) {
				mapRenderer.setView(camera.combined, camera.position.x, camera.position.y, camera.viewportWidth, camera.viewportHeight);
//				mapRenderer.setView(camera);
				mapRenderer.setMap(ensureExists((int) Math.floor(x), (int) Math.floor(y)));
				System.out.println(String.format("%d, %d", (int) Math.floor(x), (int) Math.floor(y)));
				mapRenderer.render();
			}
		}
	}
	
	public TiledMap ensureExists(int x, int y) {
		if (!sectors.containsKey(x)) {
			sectors.put(x, new IntMap<TiledMap>());
		}
		TiledMap out = sectors.get(x).get(y);
		if (out == null) {
			out = new TiledMap();
			sectors.get(x).put(y, out);
		}
		if (out.getTileSets().getTileSet("background") == null) {
			TiledMapTileSet set = new TiledMapTileSet();
			set.setName("background");
			for (int i = 0; i < grass.size; i++) {
				set.putTile(i, new StaticTiledMapTile(grass.get(i)));
			}
			out.getTileSets().addTileSet(set);
		}
		if (out.getLayers().get("background") == null) {
			TiledMapTileLayer layer = new TiledMapTileLayer(SECTOR_SIZE, SECTOR_SIZE, 32, 32);
			layer.setName("background");
			Cell cell = new Cell();
			cell.setTile(out.getTileSets().getTile(0));
			layer.setCell(0, 0, cell);
			out.getLayers().add(layer);
		}
		return out;
	}
	
	public void generateSector(int x, int y) {
		if(!sectors.containsKey(x)) {
			sectors.put(x, new IntMap<TiledMap>());
		}
		sectors.get(x).put(y, new TiledMap());
	}
	
	public TiledMap getSector(int x, int y) {
		return sectors.get(x).get(y);
	}
	
	public void save() {
		Keys xs = sectors.keys();
		while(xs.hasNext) {
			int x = xs.next();
			IntMap<TiledMap> sects = sectors.get(x);
			Keys ys = sects.keys();
			while(ys.hasNext) {
				int y = ys.next();
				TiledMap sect = sects.get(y);
				// Save the sector to its own file
			}
		}
	}
}
