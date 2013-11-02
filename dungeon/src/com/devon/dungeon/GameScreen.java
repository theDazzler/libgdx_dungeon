package com.devon.dungeon;

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

public class GameScreen implements Screen
{
	private MyGdxGame game;
	private TiledMap dungeon;
	private TiledMapRenderer renderer;
	private OrthographicCamera camera;
	private AssetManager assetManager;
	private BitmapFont font;
	private SpriteBatch batch;
	
	public GameScreen(MyGdxGame game)
	{
		this.game = game;
	}

	@Override
	public void render(float delta) 
	{
		Gdx.gl.glClearColor(100f / 255f, 100f / 255f, 250f / 255f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();
		renderer.setView(camera);
		renderer.render();
		batch.begin();
		font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20); 
		font.draw(batch, "camera: " + camera.position, 10, 50); 
		batch.end();
		
	}

	@Override
	public void resize(int width, int height)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() 
	{
		Gdx.graphics.setDisplayMode(1700, 960, false);
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		System.out.println(w);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(true, w, h);
		camera.update();
		
	
		font = new BitmapFont();
		batch = new SpriteBatch();
		
		dungeon = new Dungeon(DungeonGenerator.generateDungeonMap());

		renderer = new OrthogonalTiledMapRenderer(dungeon);
		
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
