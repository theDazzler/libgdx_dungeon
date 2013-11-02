package com.devon.dungeon;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.devon.dungeon.tiles.Tile;

public class Dungeon extends TiledMap
{
	private TiledMapTileLayer tileLayer;
	private int width, height;
	private Texture tileLayerTexture;
	private int[][] noiseMap;

	
	/**
	 * Create dungeon of specified size
	 * Width and height are number of tiles. (32, 32 is 32 tiles wide and 32 tiles in height)
	 * @param width
	 * @param height
	 */
	public Dungeon(int width, int height)
	{
		this.width = width;
		this.height = height;
		this.tileLayer = new TiledMapTileLayer(width, height, Tile.WIDTH, Tile.HEIGHT);
		this.noiseMap = new int[width][height];
		this.initDungeon();
		
	}
	
	/**
	 * Create Dungeon from 2D array
	 */
	public Dungeon(int[][] map)
	{
		this.noiseMap = map;
		this.width = map.length;
		this.height = map[0].length;
		this.tileLayer = new TiledMapTileLayer(width, height, Tile.WIDTH, Tile.HEIGHT);
		this.noiseMap[0][0] = 3;
		this.initDungeon();
		
	}
	
	private void initDungeon()
	{
		MapLayers layers = this.getLayers();
		
		for (int x = 0; x < this.width; x++) 
		{
			for (int y = 0; y < this.height; y++) 
			{
				int tileType = this.noiseMap[x][y];
				
				Cell cell = new Cell();
				
				if(tileType == Tile.floor.getId())
				{
					cell.setTile(Tile.floor);
					
				}
				else if(tileType == Tile.grass.getId())
				{
					cell.setTile(Tile.grass);
					
				}
				else if(tileType == Tile.corridor.getId())
				{
					cell.setTile(Tile.corridor);
					
				}
				this.tileLayer.setCell(x, y, cell);
			}
		}
		
		layers.add(tileLayer);

	}

}
