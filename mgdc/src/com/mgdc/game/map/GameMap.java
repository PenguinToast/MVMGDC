package com.mgdc.game.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.IntMap.Keys;
import com.mgdc.game.Global;

public class GameMap {
	private IntMap<IntMap<TiledMap>> sectors;
	public static final int SECTOR_SIZE = 20;
	public static final int TILE_SIZE = 128;
	private OrthogonalTiledMapRenderer mapRenderer;
	private Array<TextureRegion> grass;

	public GameMap() {
		sectors = new IntMap<IntMap<TiledMap>>();
		mapRenderer = new OrthogonalTiledMapRenderer(new TiledMap());
		grass = new Array<TextureRegion>();
		FileHandle dir = Gdx.files.local("data/terrain");
		for (FileHandle file : dir.list()) {
			if (!file.isDirectory()) {
				Global.assets.load(new AssetDescriptor<Texture>(file, Texture.class));
				Global.assets.finishLoading();
				grass.add(new TextureRegion(Global.assets.get(file.path(), Texture.class)));
			}
		}
	}

	public void draw(OrthographicCamera camera) {
		float minX = (float) Math.floor((camera.position.x - camera.viewportWidth / 2) / (SECTOR_SIZE * TILE_SIZE));
		float minY = (float) Math.floor((camera.position.y - camera.viewportHeight / 2) / (SECTOR_SIZE * TILE_SIZE));
		float maxX = (float) Math.ceil((camera.position.x + camera.viewportWidth / 2) / (SECTOR_SIZE * TILE_SIZE));
		float maxY = (float) Math.ceil((camera.position.y + camera.viewportHeight / 2) / (SECTOR_SIZE * TILE_SIZE));

		for (int i = (int) minX - 1; i <= (int) maxY + 1; i++) {
			for (int j = (int) minY - 1; j <= (int) maxY + 1; j++) {
				ensureExists(i, j);
			}
		}



		for (float x = minX; x <= maxX; x++) {
			for (float y = minY; y <= maxY; y++) {
				float width = camera.viewportWidth;
				float height = camera.viewportHeight;
				generateSector((int) x, (int) y);
				// OrthogonalTiledMapRenderer mapRenderer = new OrthogonalTiledMapRenderer(ensureExists((int) Math.floor(x), (int) Math.floor(y)));
				mapRenderer.setMap(getSector((int) x, (int) y));
				float dx = (float) -x * (SECTOR_SIZE * TILE_SIZE);
				float dy = (float) -y * (SECTOR_SIZE * TILE_SIZE);
				camera.translate(dx, dy);
				camera.update();
				mapRenderer.setView(camera);
				// mapRenderer.setView(camera);
				// System.out.println(String.format("%d, %d", (int) Math.floor(x), (int) Math.floor(y)));
				mapRenderer.render();
				camera.translate(-dx, -dy);
				camera.update();
			}
		}
	}

	public void setTile(int x, int y, TiledMapTile tile) {
		int[] sector = sectorFromCoords(x, y);
		int sectorX = sector[0];
		int sectorY = sector[1];
		TiledMap map = ensureExists(sectorX, sectorY);
		int tileX = x - (sectorX * SECTOR_SIZE);
		int tileY = y - (sectorY * SECTOR_SIZE);
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("background");
		Cell cell = new Cell();
		cell.setTile(tile);
		layer.setCell(tileX, tileY, cell);


	}

	public int[] sectorFromCoords(int x, int y) {
		return new int[] {(int) Math.floor((double) x / (double) SECTOR_SIZE), (int) Math.floor((double) y / (double) SECTOR_SIZE)};
	}

	public int[] coordsFromSector(int x, int y) {
		return new int[] {x * SECTOR_SIZE, y * SECTOR_SIZE};
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
			TiledMapTileLayer layer = new TiledMapTileLayer(SECTOR_SIZE, SECTOR_SIZE, TILE_SIZE, TILE_SIZE);
			layer.setName("background");
			/* for (int i = 0; i < SECTOR_SIZE; i++) {
				 for (int j = 0; j < SECTOR_SIZE; j++) {
					 Cell cell = new Cell();
					 cell.setTile(out.getTileSets().getTile(Math.abs(i % 2 + j % 2)));
					 layer.setCell(i, j, cell);
				 }
			} */
			out.getLayers().add(layer);
		}
		return out;
	}

	public void generateSector(int x, int y) {
		TiledMap[][] sectors = new TiledMap[3][3];
		// Generate points
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				TiledMap sector = ensureExists(i, j);
				sectors[i - (x - 1)][j - (y - 1)] = sector;
				if (!sector.getProperties().get("pointsGenerated", false, Boolean.class)) {
					int[] coords = coordsFromSector(i, j);
					Array<GenerationPoint> points = getNearbyPoints(coords[0], coords[1]);
					for (int a = 0; a < SECTOR_SIZE; a++) {
						for (int b = 0; b < SECTOR_SIZE; b++) {
							float baseChance = GenerationConstants.GENERATION_POINT_CHANCE;
							for (GenerationPoint p : points) {
								float dist = (float) (Math.pow(p.getX() - (coords[0] + a), 2) + Math.pow(p.getY() - (coords[1] + b), 2));
								baseChance *= Math.min(1, dist / GenerationConstants.GENERATION_POINT_DIST);
							}
							float roll = (float) Math.random();
							if (roll <= baseChance) { // Make a point
								GenerationPoint point = new GenerationPoint();
								point.setX(coords[0] + a);
								point.setY(coords[1] + b);
								point.setTileType((int) (Math.random() * grass.size));
								sector.getLayers().get(0).getObjects().add(point);
								points.add(point);
							}
						}
					}
					sector.getProperties().put("pointsGenerated", true);
				}
			}
		}
		if (sectors[1][1].getProperties().get("terrainGenerated", false, Boolean.class)) {
			return;
		}
		// Generate terrain
		TiledMapTileLayer layer = (TiledMapTileLayer) sectors[1][1].getLayers().get(0);
		for (int a = 0; a < SECTOR_SIZE; a++) {
			for (int b = 0; b < SECTOR_SIZE; b++) {
				int[] coords = new int[] {x * SECTOR_SIZE + a, y* SECTOR_SIZE + b};
				Array<GenerationPoint> points = getNearbyPoints(coords[0], coords[1]);
				
				/*
				for (GenerationPoint p : points) {
					if (p.getX() == coords[0] && p.getY() == coords[1]) {

						Cell cell = new Cell();
						cell.setTile(sectors[1][1].getTileSets().getTile(p.getTileType()));
						layer.setCell(a, b, cell);
					}
				}
				//*/
				
				//*
				float[] chances = new float[grass.size];
				for (GenerationPoint p : points) {
					float dist = (float) (Math.pow(p.getX() - (coords[0]), 2) + Math.pow(p.getY() - (coords[1]), 2));
					//dist *= dist;
					chances[p.getTileType()] += 1f / dist;
				}
				float[] ranges = new float[grass.size];
				float sum = 0;
				for (int i = 0; i < grass.size; i++) {
					sum += chances[i];
					ranges[i] = sum;
				}
				float roll = (float) (Math.random() * sum);
				int type = -1;
				for (int i = 0; i < grass.size; i++) {
					if (roll <= ranges[i]) {
						type = i;
						break;
					}
				}
				Cell cell = new Cell();
				cell.setTile(sectors[1][1].getTileSets().getTile(type));
				layer.setCell(a, b, cell);
				//*/
				
			}
		}
		sectors[1][1].getProperties().put("terrainGenerated", true);
	}

	public TiledMap getSector(int x, int y) {
		if (!sectors.containsKey(x)) {
			return null;
		}
		if (!sectors.get(x).containsKey(y)) {
			return null;
		}
		return sectors.get(x).get(y);
	}

	public Array<GenerationPoint> getNearbyPoints(int x, int y) {
		int[] sectorCoords = sectorFromCoords(x, y);
		Array<GenerationPoint> out = new Array<GenerationPoint>();
		for (int i = sectorCoords[0] - 1; i <= sectorCoords[0] + 1; i++) {
			for (int j = sectorCoords[1] - 1; j <= sectorCoords[1] + 1; j++) {
				TiledMap sect = getSector(i, j);
				if (sect != null) {
					MapObjects objs = sect.getLayers().get(0).getObjects();
					for (MapObject obj : objs) {
						out.add((GenerationPoint) obj);
					}
				}
			}
		}
		return out;
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
