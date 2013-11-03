package com.devon.dungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.devon.dungeon.Dungeon;
import com.devon.dungeon.DungeonGenerator;
import com.devon.dungeon.InputHandler;
import com.devon.dungeon.MyGdxGame;
import com.devon.dungeon.Player;

public class GameScreen extends AbstractScreen
{
	private Dungeon dungeon;
	private TiledMapRenderer renderer;
	private OrthographicCamera camera;
	private Player player;
	private InputHandler input;
	
	public GameScreen(MyGdxGame game) 
	{
		super(game);
		
	}

	@Override
	public void render(float delta) 
	{
		super.render(delta);
		camera.update();
		/*
		Gdx.gl.glClearColor(100f / 255f, 100f / 255f, 250f / 255f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();*/
		renderer.setView(camera);
		//render tiles
		renderer.render();
	
		batch.begin();
		font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20); 
		font.draw(batch, "camera: " + camera.position, 10, 50); 
		font.draw(batch, "Stage:" + this.stage.getCamera().position, 10, 80);
		batch.end();
		
		//update and render stage actors
		stage.act(delta);
        stage.draw();
        
        
		
	}

	@Override
	public void resize(int width, int height)
	{
		
		
	}

	@Override
	public void show() 
	{
		//Gdx.graphics.setDisplayMode(1700, 960, false);
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		this.camera = new OrthographicCamera(w, h);
		
		this.camera.setToOrtho(true, w, h);
		this.stage.setCamera(this.camera);
		
		this.input = new InputHandler(this.camera);
		Gdx.input.setInputProcessor(this.input);

		dungeon = new Dungeon(DungeonGenerator.generateDungeonMap());

		renderer = new OrthogonalTiledMapRenderer(dungeon);
		
		this.player = new Player(dungeon);
		this.stage.addActor(this.player);
		
		camera.position.set(player.getX(), player.getY(), 0f);
			
	}

	@Override
	public void hide()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() 
	{
		// TODO Auto-generated method stub
		
	}
	
}
