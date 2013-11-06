package com.devon.dungeon;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.devon.dungeon.tiles.Tile;
import com.devon.pathfinding.DFS;

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
