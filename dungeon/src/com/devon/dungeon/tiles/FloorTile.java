package com.devon.dungeon.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.devon.dungeon.Graphics;

public class FloorTile extends Tile
{

	public FloorTile(int id, int xIndex, int yIndex)
	{
		super(id, new TextureRegion(new Texture(Gdx.files.internal("data/tiles/floor.png"))));
	}

}
