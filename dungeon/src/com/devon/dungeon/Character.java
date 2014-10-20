package com.devon.dungeon;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.devon.dungeon.tiles.Tile;
import com.devon.pathfinding.DFS;
import com.devon.pathfinding.Node;

public class Character extends Entity
{
	private TextureRegion texture;
	private Dungeon dungeon;
	private int speed = 5;
	
	public Character(Dungeon dungeon)
	{
		this.dungeon = dungeon;
		this.texture = new TextureRegion(new Texture(Graphics.SPRITES_GFX_LOCATION), 0, 0, Tile.WIDTH, Tile.HEIGHT * 2);
		this.findStartLocation();
		this.setBounds(this.getX(), this.getY(), Tile.WIDTH, Tile.HEIGHT);
	}
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha)
	{
		batch.draw(this.texture, this.getX(), this.getY());
	}
	
	//place player in random room
	public void findStartLocation()
	{
		Random rand = new Random();
		int xIndex, yIndex;
		
		do{
			 xIndex = rand.nextInt(this.dungeon.getWidth());
			 yIndex = rand.nextInt(this.dungeon.getHeight());
		}
		
		
		
		while(dungeon.getMap()[xIndex][yIndex] != Tile.floor.getId());
		
		this.setPosition(xIndex * Tile.HEIGHT, yIndex * Tile.WIDTH);
		
	}
	
	public void showAvailableMoves() 
	{
		System.out.println("showing available moves");

		//int[][] tempMap = new int[this.dungeon.getWidth()][this.dungeon.getHeight()];

		//get reachable tiles
		ArrayList<Node> reachableTiles = DFS.findReachableTiles(this.dungeon.getMap(), this.getXIndex(), this.getYIndex(), this.getSpeed());
		MapLayers layers = this.dungeon.getLayers();
		TiledMapTileLayer UILayer = new TiledMapTileLayer(this.dungeon.getWidth(), this.dungeon.getHeight(), Tile.WIDTH, Tile.HEIGHT);
		
		//for each reachable tile
		for(int i = 0; i < reachableTiles.size(); i++)
		{		
			System.out.println("setting reachable tiles...");
			Node tile = reachableTiles.get(i);
			
			//make sure tile is walkable
			if(this.dungeon.getMap()[tile.getX()][tile.getY()] == Tile.floor.getId())
			{
				Cell cell = new Cell();
				cell.setTile(Tile.UIMovementCandiate);
				UILayer.setCell(tile.getX(), tile.getY(), cell);
			}			
		}
		
		layers.add(UILayer);
		
	}
	
	public int getXIndex()
	{
		return (int) (this.getX() / Tile.WIDTH);
	}
	
	public int getYIndex()
	{
		return (int) (this.getY() / Tile.HEIGHT);
	}

	public int getSpeed() 
	{
		return this.speed;
	}

	
	
}
