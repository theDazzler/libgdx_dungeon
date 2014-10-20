package com.devon.dungeon.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.devon.dungeon.MyGdxGame;
import com.devon.dungeon.screens.GameScreen;

public class MainMenuScreen implements Screen
{
	private TextureAtlas atlas;
	private Skin skin;
	//private Table table;
	private TextButton playButton;
	private TextButton exitButton;
	private TextButtonStyle buttonStyle;
	private BitmapFont font;
	private GameScreen gameScreen;
	private Table table;
	private Stage stage;
	private MyGdxGame gdxGame;

	
	public MainMenuScreen(MyGdxGame gdxGame)
	{
		this.gdxGame = gdxGame;
		this.stage = new Stage();
		this.atlas = new TextureAtlas("data/ui/ui.pack");
		this.font = new BitmapFont();
		this.table = new Table();
		
		this.skin = new Skin(this.atlas);		
		table.setSkin(this.skin);
		
		this.buttonStyle = new TextButtonStyle();
		this.buttonStyle.up = this.skin.getDrawable("button_up");
		this.buttonStyle.down = this.skin.getDrawable("button_up");
		this.buttonStyle.font = this.font;
		
		this.skin.add("default", this.buttonStyle);
		
		
		this.playButton = new TextButton("Play", this.skin);
		this.exitButton = new TextButton("Exit", this.skin);

		
	}



	@Override
	public void render(float delta) 
	{
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() 
	{
		playButton.addListener(new ClickListener(){
			@Override
	        public void clicked(InputEvent event, float x, float y) 
	        {
	            ((Game)Gdx.app.getApplicationListener()).setScreen(new GameScreen(gdxGame));
	        }
	    });
	    exitButton.addListener(new ClickListener(){
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	            Gdx.app.exit();
	        }
	    });
			
        table.add(playButton).row();
        table.add(exitButton).row();

        table.setFillParent(true);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		dispose();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
        skin.dispose();
	}
	
	

}
