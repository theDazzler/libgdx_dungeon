package com.devon.dungeon;

import java.util.ArrayList;

public class Party 
{
	private ArrayList<Entity> members;
	
	public Party()
	{
		this.members = new ArrayList<Entity>();
	}
	
	public void add(Entity e)
	{
		this.members.add(e);
	}
	
	public ArrayList<Entity> getAll()
	{
		return this.members;
	}

}
