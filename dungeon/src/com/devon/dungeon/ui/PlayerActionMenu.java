package com.devon.dungeon.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.devon.dungeon.MyGdxGame;
import com.devon.dungeon.screens.GameScreen;

public class PlayerActionMenu extends Table
{
	private TextureAtlas atlas;
	private Skin skin;
	//private Table table;
	private TextButton moveButton;
	private TextButtonStyle moveButtonStyle;
	private BitmapFont font;
	private GameScreen gameScreen;

	
	public PlayerActionMenu(GameScreen gameScreen)
	{
		this.gameScreen = gameScreen;
		this.setBounds(0, 0, 200, 200);
		this.atlas = new TextureAtlas("data/ui/ui.pack");
		this.font = new BitmapFont();
		
		this.skin = new Skin(this.atlas);		
		this.setSkin(this.skin);
		
		this.moveButtonStyle = new TextButtonStyle();
		this.moveButtonStyle.up = this.skin.getDrawable("button_up");
		this.moveButtonStyle.down = this.skin.getDrawable("button_up");
		this.moveButtonStyle.font = this.font;
		
		this.skin.add("default", this.moveButtonStyle);
		
		this.moveButton = new TextButton("Move", this.skin);
		this.moveButton.addListener(new ChangeListener()
		{

			@Override
			public void changed(ChangeEvent event, Actor actor)
			{
				System.out.println(" clickec@@@");
				onMoveButtonClicked();
			}
			
		});
		
		this.add(this.moveButton);
		
	}
	
	public void onMoveButtonClicked()
	{
		this.gameScreen.showAvailableMoves();
	}
	
	@Override
	public void act(float delta)
	{
		Stage stage = this.getStage();
		this.setPosition(stage.getCamera().position.x + ((stage.getWidth() / 2) - (this.getWidth())), stage.getCamera().position.y + 25);
	}
	
	

}
