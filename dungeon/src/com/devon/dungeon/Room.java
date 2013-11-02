package com.devon.dungeon;

import java.util.ArrayList;

/**
 * Class representing a room in a dungeon
 * @author Devon
 *
 */
public class Room 
{
	private int width;//tiles wide
	private int height;//tiles in height
	private int x;//top left index location of the room in the world map
	private int y;//top left index location of the room in the world map
	//private ArrayList<Door> doors;
	private int[][] floor;//room floor starts at 0,0 (Local coord system)
	
	public Room(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.buildDefaultFloor();
		//this.doors = new ArrayList<Door>();
	}
	
	public Room(int x, int y, int[][] floorTiles)
	{
		this.x = x;
		this.y = y;
		this.width = floorTiles.length;
		this.height = floorTiles[0].length;
		this.floor = floorTiles;
		this.buildDefaultFloor();
	}
	
	public Room(int width, int height)
	{
		this.width = width;
		this.height = height;
		this.buildDefaultFloor();
	}
	
	//default the floor to all FLOOR tiles
	private void buildDefaultFloor() 
	{
		for(int i = 0; i < this.width; i++)
		{
			for(int j = 0; j < this.height; j++)
			{
				this.floor[i][j] = DungeonGenerator.FLOOR;
			}
		}
		
	}

	public int getTileValue(int x, int y)
	{
		return this.floor[x][y];
	}
	
	public void setTileValue(int value, int x, int y)
	{
		this.floor[x][y] = value;
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	public int[][] getFloorTiles()
	{
		return this.floor;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	

}
