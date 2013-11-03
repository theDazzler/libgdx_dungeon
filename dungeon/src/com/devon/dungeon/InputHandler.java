package com.devon.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class InputHandler implements InputProcessor
{

	OrthographicCamera camera;
	private int mouseMoveCameraMargin = 50;//if user's mouse is within this many pixels from edge of screen, move screen in that direction 
	private int cameraMovementSpeed = 51;
	
	boolean mouseInEdge = false;
	
	public InputHandler(OrthographicCamera camera) 
	{
		this.camera = camera;
	}

	@Override
	public boolean keyDown(int keycode) 
	{
		return false;
    
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
		//scroll right edge
		if(screenX > Gdx.graphics.getWidth() - this.mouseMoveCameraMargin)
		{
			mouseInEdge = true;
			this.scrollScreenRight(screenX, screenY);		
		}
		//scroll left edge
		else if(screenX < this.mouseMoveCameraMargin)
		{
			mouseInEdge = true;
			this.scrollScreenLeft(screenX, screenY);		
		}
		
		//scroll bottom edge
		else if(screenY > Gdx.graphics.getHeight() - this.mouseMoveCameraMargin)
		{
			mouseInEdge = true;
			this.scrollScreenBottom(screenX, screenY);		
		}
		
		//scroll top edge
		else if(screenY < this.mouseMoveCameraMargin)
		{
			mouseInEdge = true;
			this.scrollScreenTop(screenX, screenY);		
		}
		
		else
			mouseInEdge = false;
		return true;
	}

	private void scrollScreenTop(int screenX, int screenY)
	{
		int pixelsFromEdge = screenY;
		camera.translate(0, (this.cameraMovementSpeed - pixelsFromEdge) * -1, 0);
	}

	private void scrollScreenBottom(int screenX, int screenY)
	{
		int pixelsFromEdge = Gdx.graphics.getHeight() - screenY;
		camera.translate(0, this.cameraMovementSpeed - pixelsFromEdge, 0);
	}

	private void scrollScreenLeft(int screenX, int screenY) 
	{
		
		int pixelsFromEdge = Gdx.input.getX();
		System.out.println("EDGE:" + pixelsFromEdge);
		camera.translate((this.cameraMovementSpeed - pixelsFromEdge) * -1, 0, 0);
		
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	
	//xDirection = 1 for right and -1 for left. 0 for no movement
	private void scrollScreenRight(int screenX, int screenY)
	{		
		int pixelsFromEdge = Gdx.graphics.getWidth() - Gdx.input.getX();

		camera.translate(this.cameraMovementSpeed - pixelsFromEdge, 0, 0);

	}

	

}
