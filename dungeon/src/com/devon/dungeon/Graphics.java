package com.devon.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.devon.dungeon.tiles.Tile;

public class Graphics 
{
	public static final String TILES_GFX_LOCATION = "data/tiles/tiles.png";
	public static final String SPRITES_GFX_LOCATION = "data/sprites/sprites.png";
	public static final Texture TILES_TEXTURE = new Texture(Gdx.files.internal(TILES_GFX_LOCATION));
	
	//holds tile images ([0][0] is top left image of spritesheet)
	public static TextureRegion[][] tiles = TextureRegion.split(TILES_TEXTURE, Tile.WIDTH, Tile.HEIGHT);


}
