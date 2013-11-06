package com.devon.pathfinding;

import java.util.ArrayList;

public class DFS
{
	private static ArrayList<Node> reachable_places_list;
	private static AreaMap localMap;
	
	public static ArrayList<Node> findReachableTiles(int[][] map, int startX, int startY, int movementSpeed)
	{
		reachable_places_list = new ArrayList<Node>();
		localMap = new AreaMap(map.length, map[0].length, map);
		
		findReachableTilesHelper(localMap.getNode(startX, startY), 0, movementSpeed);
		
		
			for(int j = 0; j < reachable_places_list.size(); j++)
			{
				System.out.println("x:" + reachable_places_list.get(j).getX() + " y:" + reachable_places_list.get(j).getY());
			}
			System.out.println();
			
		return reachable_places_list;
	}
	

	public static void findReachableTilesHelper(Node currentNode, int distanceTraveled, int movementSpeed)
	{
		for(int i = 0; i < currentNode.neighborList.size(); i++)
		{
			Node neighbor = currentNode.neighborList.get(i);
			if(!reachable_places_list.contains(neighbor))
				reachable_places_list.add(currentNode.neighborList.get(i));
			
			if(distanceTraveled + 1 < movementSpeed)
				findReachableTilesHelper(neighbor, distanceTraveled + 1, movementSpeed);
		}
	}

	/*
	function find_all_reachable_nodes(current_node, distance_travelled):
	    for each node adjacent to current_node that is reachable:
	        add node to reachable_places_list if it's not already there
	        if distance_travelled + 1 < 2:
	            find_all_reachable_nodes(node, distance_travelled + 1)

	find_all_reachable_nodes(player_position, 0)*/

}
