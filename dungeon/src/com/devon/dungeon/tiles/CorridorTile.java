package com.devon.dungeon.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.devon.dungeon.Graphics;

public class CorridorTile extends Tile
{

	//indices are for image location in spritesheet 0,0 is top left sprite, 0,1 is next to the right
	public CorridorTile(int id, int xIndex, int yIndex)
	{
		super(id, new TextureRegion(new Texture(Gdx.files.internal("data/tiles/corridor.png"))));
	}


}
