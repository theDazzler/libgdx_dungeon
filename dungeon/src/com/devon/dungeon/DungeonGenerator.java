package com.devon.dungeon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import com.devon.pathfinding.AStar;
import com.devon.pathfinding.AStarHeuristic;
import com.devon.pathfinding.AreaMap;
import com.devon.pathfinding.ClosestHeuristic;
import com.devon.pathfinding.Path;

public class DungeonGenerator extends JPanel
{

	public static final int MAX_WIDTH = 1024 ; //width of city in pixels
	public static final int MAX_HEIGHT = 1024 ;//height of city in pixels
	public static final int TILE_WIDTH = 8;//pixels wide
	public static final int TILE_HEIGHT= 8;
	public static final int NUM_TILES_WIDTH = MAX_WIDTH / TILE_WIDTH;
	public static final int NUM_TILES_HEIGHT = MAX_HEIGHT / TILE_HEIGHT;
	
	public static final int MIN_ROOM_SIZE = 6;
	public static final int MAX_ROOM_SIZE = 25;

	public static final int FLOOR = 1;  //used for cellular automata
	public static final int DOOR = 2;
	public static final int CORRIDOR = 3;


	private static int[][] map;
	private static int numCols;
	private static int numRows;
	private static ArrayList<Point> doorLocations;
	private static ArrayList<Room> rooms;

	private static Random rand = new Random();

	private static final long serialVersionUID = 8111554517572474109L;

	public DungeonGenerator()
	{
		generateDungeonMap();
		

	}
	
	public static int[][] generateDungeonMap()
	{
		numCols = MAX_WIDTH / TILE_WIDTH;
		numRows = MAX_HEIGHT / TILE_HEIGHT;
		map = new int[numRows][numCols];
		doorLocations = new ArrayList<Point>();
		rooms = new ArrayList<Room>();
		
		placeRooms();
		connectRooms();
		
		return map;
	}
	
	public static ArrayList<Room> getRooms()
	{
		return rooms;
	}
	

	private static void connectRooms() 
	{
		int mapWith = NUM_TILES_WIDTH;
        int mapHeight = NUM_TILES_HEIGHT;
        
        ArrayList<Point> doorsCopy = new ArrayList<Point>(doorLocations);
        
        //while there are unconnected doors
        while(doorsCopy.size() > 0)
        {
        	int rand1, rand2;
        	Point door1, door2;
        	
        	if(doorsCopy.size() == 1)
        	{
        		rand1 = 0;
        		rand2 = rand.nextInt(doorLocations.size());
        		
        		door1 = doorsCopy.get(rand1);
        		doorsCopy.remove(rand1);
        		door2 = doorLocations.get(rand2);

        	}
        	else
        	{
	        	//choose 2 random doors
	        	rand1 = rand.nextInt(doorsCopy.size());
	        	door1 = doorsCopy.get(rand1);
	        	doorsCopy.remove(rand1);
	        	
	        	rand2 = rand.nextInt(doorsCopy.size());
	        	door2 = doorsCopy.get(rand2);
	        	doorsCopy.remove(rand2);
        	}

        	
        	int startX = (int) door1.getX();
            int startY = (int) door1.getY();
            int goalX = (int) door2.getX();
            int goalY = (int) door2.getY();
            
            
            
            AreaMap aMap = new AreaMap(mapWith, mapHeight, map);

            AStarHeuristic heuristic = new ClosestHeuristic();

            AStar pathFinder = new AStar(aMap, heuristic);

            Path shortest = pathFinder.calcShortestPath(startX, startY, goalX, goalY);
            

            //pathFinder.printPath();
            pathFinder.addToMap(map);

            //convert a star map to regular map to get corridors

            /*
	            System.out.println("SSSSS" + shortest.getLength());
	            for(int i = 0; i < shortest.getLength(); i++)
	            {
	            	Node n = shortest.getWayPoint(i);
	            	int xIndex = n.getX();
	            	int yIndex = n.getY();
	            	this.map[xIndex][yIndex] = FLOOR;
	            }*/

        	
        }

	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.black);
		for(int i = 0; i < this.numRows; i++)
		{
			for(int j = 0; j < this.numCols; j++)
			{
				int tileValue = this.map[i][j];

				if(tileValue == FLOOR)
				{
					g.setColor(Color.GREEN);

				}
				else if(tileValue == CORRIDOR)
				{
					g.setColor(Color.orange);

				}

				else if(tileValue == DOOR)
				{
					g.setColor(Color.PINK);

				}


				//x y width height
				g.fillRect(i * TILE_WIDTH, j * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
				g.setColor(Color.black);
			}
		}
	}
	

	public static void placeRooms()
	{
		int numRooms = calculateTotalRooms();
		System.out.println("numRooms:" + numRooms);
		
		//for each room
		for(int i = 0; i < numRooms; i++)
		{
			int[][] room = generateRoom();
			System.out.println("roomWidth:" + room.length);
			System.out.println("roomHeight: " + room[0].length);
			
			placeRoom(room);
			
		}
	}
	
	


	private static void placeRoom(int[][] roomTiles) 
	{
		int startX = rand.nextInt(numCols);
		int startY = rand.nextInt(numRows);
		int endX = startX + roomTiles.length;
		int endY = startY + roomTiles[0].length;
		
		if(canPlaceRoom(roomTiles, startX, startY, endX, endY))
		{			
			Room room = new Room(startX, startY, roomTiles);
			room = addDoors(room);
			
			System.out.println("ROOOOOM WIDTH: " + room.getWidth());
			System.out.println("ROOOOOM HEIGTH: " + room.getHeight());

			rooms.add(room);
			
			for(int i = startX; i < endX; i++)
			{
				for(int j = startY; j < endY; j++)
				{
					map[i][j] = FLOOR;
				}
			}
			
			for(int i = 0; i < room.getWidth(); i++)
			{
				for(int j = 0; j < room.getHeight(); j++)
				{
					if(room.getTileValue(i, j) == DOOR)
					{
						doorsOnPerimeter(i + startX, j + startY);
						//room.setTileValue(FLOOR, i, j);
					}
				}
			}	
		}	
	}



	private static void doorsOnPerimeter(int mapX, int mapY) 
	{
		
			if(mapX + 1 < numRows)
			{
				//if area to the bottom of room is clear
				if(map[mapX + 1][mapY] == 0)
				{
					System.out.println("numRows" + " " + numRows);
					System.out.println("mapX: " + mapX);
					System.out.println("mapY: " + mapY);
					System.out.println("bottom clear");
					map[mapX + 1][mapY] = DOOR;
					doorLocations.add(new Point(mapX + 1, mapY));
					return;
	
				}
				
				
			}
	
			if(mapX - 1 >= 0)
			{
				//if area to the top of room is clear
				if(map[mapX - 1][mapY] == 0)
				{
					System.out.println("top clear");
					map[mapX - 1][mapY] = DOOR;
					doorLocations.add(new Point(mapX - 1, mapY));
					return;
				}
				
			}
		
			
			if(mapY - 1 >= 0)
			{
				//if area to the left of room is clear
				if(map[mapX][mapY - 1] == 0)
				{
					System.out.println("left clear");
					map[mapX][mapY - 1] = DOOR;
					doorLocations.add(new Point(mapX, mapY - 1));
					return;
				}
			}
		
			if(mapY + 1 < numCols)
			{
				//if area to the right of room is clear
				if(map[mapX][mapY + 1] == 0)
				{
					System.out.println("right clear");
					map[mapX][mapY + 1] = DOOR;
					doorLocations.add(new Point(mapX, mapY + 1));
					return;
	
				}
			}
		}

	private static Room addDoors(Room room) 
	{
		int doors;
		int roomWidth = room.getWidth();
		int roomHeight = room.getHeight();
		int sqFeet = roomWidth * roomHeight;
		
		int number = rand.nextInt(sqFeet + sqFeet / 2);
		
		if(number > 500)
		{
			doors = 3;
		}
		else if(number < 500 && number > 350)
		{
			doors = 2;
		}
		else
			doors = 1;

		
		ArrayList<Integer> roomSides = new ArrayList<Integer>();
		roomSides.add(0);//top
		roomSides.add(1);//right
		roomSides.add(2);//bottom
		roomSides.add(3);//left
		
		ArrayList<Integer> copy = new ArrayList<Integer>();
		copy.add(0);//top
		copy.add(1);//right
		copy.add(2);//bottom
		copy.add(3);//left
		
		for(int i = 0; i < doors; i++)
		{
			int num = rand.nextInt(roomSides.size());

				
			for(int j = 0; j < copy.size(); j++)
			{
				if(copy.get(j) == num)
				{
					room = placeDoor(room, num);
					copy.remove(j);
				}
					
			}
		}
		
		return room;
		
	}

	//num is which side of room to place door(0 = top, 1= right, 2 = bottom, 3 = left)
	private static Room placeDoor(Room room, int num) 
	{
		int roomHeight = room.getWidth();
		int roomWidth = room.getHeight();
		
		System.out.println("TEST width:" + roomWidth);
		System.out.println("TEST height:" + roomHeight);
		
		int rowIndex = 0;
		int colIndex = 0;
		
		//top
		if(num == 0)
		{
			rowIndex = 0;
			colIndex = rand.nextInt(roomWidth);
		}
		//right
		else if(num == 1)
		{
			rowIndex = rand.nextInt(roomHeight);
			colIndex = roomWidth - 1;
			
			System.out.println("placed right door at:" + rowIndex + " " + colIndex + " with width " + roomWidth);
		}
		//bottom
		else if(num == 2)
		{
			rowIndex = roomHeight - 1;
			colIndex = rand.nextInt(roomWidth);
		}
		//left
		else if(num == 3)
		{
			rowIndex = rand.nextInt(roomHeight);
			colIndex = 0;
		}
		
		System.out.println("row: " + rowIndex);
		System.out.println("col: " + colIndex);
		room.setTileValue(DOOR, rowIndex, colIndex);
		
		return room;
	}


	private static boolean canPlaceRoom(int[][] room, int startX, int startY, int endX, int endY) 
	{
		System.out.println("startX: " + startX);
		System.out.println("startY: " + startY);
		System.out.println("endX: " + endX);
		System.out.println("endY: " + endY);
		
		if(endX >= numCols)
			return false;
		if(endY >= numRows)
			return false;
		
		//if room doesnt collide with other rooms
		for(int i = startX; i <= endX; i++)
		{
			for(int j = startY; j <= endY; j++)
			{
				if(map[i][j] != 0)
					return false;
			}
		}
		return true;
	}


	private static int[][] generateRoom() 
	{
		int roomWidth = rand.nextInt((MAX_ROOM_SIZE - MIN_ROOM_SIZE) + 1) + MIN_ROOM_SIZE;
		int roomHeight = rand.nextInt((MAX_ROOM_SIZE - MIN_ROOM_SIZE) + 1) + MIN_ROOM_SIZE;
		
		return new int[roomWidth][roomHeight];
		
	}


	public static int calculateTotalRooms()
	{	
		int avgRoomSize = (int) Math.floor((MAX_ROOM_SIZE + MIN_ROOM_SIZE) / 2);
		int numRooms = (int) Math.floor(numCols / avgRoomSize) - 1;
		
		System.out.println("avg room size: " + avgRoomSize);
		int result = (numRooms * numRooms) + MAX_ROOM_SIZE / 2;
		
		return result;
		
	}
	

	

	

	


	
	


	


	



	


}