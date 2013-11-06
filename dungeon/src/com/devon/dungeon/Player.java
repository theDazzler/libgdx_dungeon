package com.devon.dungeon;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.devon.dungeon.tiles.Tile;

public class Player extends Actor
{
	private TextureRegion texture;
	private Dungeon dungeon;
	private int speed = 5;
	
	public Player(Dungeon dungeon)
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
		
	}
}
