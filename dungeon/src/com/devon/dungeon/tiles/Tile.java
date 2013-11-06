package com.devon.dungeon.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTile.BlendMode;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

/** 
 * Super class that all other tiles inherit from
 * @author Devon Guinane
 *
 */
public abstract class Tile extends StaticTiledMapTile
{
	public static final int WIDTH = 64;
	public static final int HEIGHT= 64;
	public static Tile[] tiles = new Tile[256]; //holds all tile types

	//grass tile has id of 1 and its sprite image has indices 0,0 in spritesheet(top left)
	public static Tile floor = new FloorTile(1, 0, 0);
	public static Tile door = new DoorTile(2, 0, 1);
	public static Tile corridor = new CorridorTile(3, 0, 2);
	
	private int id;
	private BlendMode blendMode = BlendMode.ALPHA;   
	private MapProperties properties;   
	private TextureRegion textureRegion; 
	private boolean isReachable;

	public Tile(int id, TextureRegion textureRegion)
	{
		super(textureRegion);
		this.textureRegion = textureRegion;
		this.id = id;
		this.isReachable = false;
		// TODO Auto-generated constructor stub
	}
	
	
	/*
	public static Tile water = new WaterTile(2);
	public static Tile snow = new SnowTile(3);
	public static Tile lava = new LavaTile(4);
	public static Tile cement = new CementTile(5);
	public static Tile dirt = new DirtTile(6);
	public static Tile mountain = new MountainTile(7);
	public static Tile ice = new IceTile(8);
	public static Tile sand = new SandTile(9);*/

	public static Tile getTile(int type)
	{		
		if(type == Tile.door.getId())
			return Tile.door;
		else
			return null;
	}
	
	public void setIsReachable(boolean value)
	{
		this.isReachable = value;
	}
	
	public boolean isReachable()
	{
		return this.isReachable;
	}
	
	@Override
	public int getId() 
	{
		return this.id;
	}

	@Override
	public void setId(int id) 
	{
		this.id = id;
		
	}

	@Override
	public BlendMode getBlendMode() 
	{
		return this.blendMode;
	}

	@Override
	public void setBlendMode(BlendMode blendMode) 
	{
		this.blendMode = blendMode;
		
	}

	@Override
	public TextureRegion getTextureRegion() 
	{
		return this.textureRegion;
	}

	@Override
	public MapProperties getProperties() 
	{
		return this.properties;
	}
	
	

	

	
	
	
	

	
	
}

