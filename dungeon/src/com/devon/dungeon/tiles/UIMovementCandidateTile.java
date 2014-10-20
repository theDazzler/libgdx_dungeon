package com.devon.dungeon.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class UIMovementCandidateTile extends Tile
{
	public UIMovementCandidateTile(int id, int xIndex, int yIndex)
	{
		super(id, new TextureRegion(new Texture(Gdx.files.internal("data/tiles/ui_available_movement.png"))));
	}
}
