package com.devon.dungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.devon.dungeon.MyGdxGame;

public class AbstractScreen implements Screen
{
	protected final MyGdxGame game;
    protected final BitmapFont font;
    protected final SpriteBatch batch;
    protected final Stage stage;
    
    public AbstractScreen(MyGdxGame game)
    {
    	this.game = game;
    	this.font = new BitmapFont();
    	this.batch = new SpriteBatch();
    	this.stage = new Stage();
    }

	@Override
	public void render(float delta)
	{
		// the following code clears the screen with the given RGB color (black)
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // update and draw the stage actors
        //stage.act(delta);
        //stage.draw();
		
	}

	@Override
	public void resize(int width, int height) 
	{
		this.stage.setViewport(width, height, true);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
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
	public void dispose()
	{
		// dispose the collaborators
        this.stage.dispose();
        this.batch.dispose();
        this.font.dispose();
		
	}


}
