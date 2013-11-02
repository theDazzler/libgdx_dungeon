package com.devon.pathfinding;

/**
 * A heuristic that uses the tile that is closest to the target
 * as the next best tile.
 */
public class ClosestHeuristic implements AStarHeuristic {

        public float getEstimatedDistanceToGoal(int startX, int startY, int goalX, int goalY) {         
                float dx = goalX - startX;
                float dy = goalY - startY;
                
                int distance = Math.abs(startX-goalX) + Math.abs(startY-goalY);
                
                //float result = (float) (Math.sqrt((dx*dx)+(dy*dy)));
                
                //Optimization! Changed to distance^2 distance: (but looks more "ugly")
                
                //float result = (float) (dx*dx)+(dy*dy);
                //return result
                
                return distance;
        }

}
