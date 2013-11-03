package com.devon.dungeon.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.devon.dungeon.Graphics;

public class DoorTile extends Tile
{

	public DoorTile(int id, int xIndex, int yIndex)
	{
		super(id, new TextureRegion(new Texture(Gdx.files.internal("data/tiles/door.png"))));
	}

	

}
