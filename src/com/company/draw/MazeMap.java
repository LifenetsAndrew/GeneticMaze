package com.company.draw;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alif on 13.3.17.
 */
public class MazeMap {

    private Boolean[][] map;

    private static MazeMap instance = null;

    public static MazeMap getMap(){
        if(MazeMap.instance == null){
            MazeMap.instance = new MazeMap();
        }
        return MazeMap.instance;
    }

    //Should be moved to map
    public   Map<Move, Boolean> getWallsForPosition(Dimension position){
        Map<Move, Boolean> walls = new HashMap<>();
        int x = position.width;
        int y = position.height;

        //no need to check array limits, as maze should have walls around, and initial position is 1,1
        walls.put(Move.UP, map[x-1][y]);
        walls.put(Move.DOWN, map[x+1][y]);
        walls.put(Move.LEFT, map[x][y-1]);
        walls.put(Move.RIGHT, map[x][y+1]);
        return walls;
    }
}
