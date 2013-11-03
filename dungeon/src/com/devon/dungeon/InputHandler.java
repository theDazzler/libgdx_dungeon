package com.devon.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class InputHandler implements InputProcessor
{

	OrthographicCamera camera;
	
	public InputHandler(OrthographicCamera camera) 
	{
		this.camera = camera;
	}

	@Override
	public boolean keyDown(int keycode) {
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            	camera.translate(-64, 0, 0);
    }
    if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            
            	camera.translate(64, 0, 0);
    }
    if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
           
            	camera.translate(0, 64, 0);
    }
    if(Gdx.input.isKeyPressed(Input.Keys.UP))
    {
            
            	camera.translate(0, -64, 0);
    }
    
    return true;
    
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		System.out.println("mouse pressed");
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
