package com.mecathronic.rainShow.level.tile.spawn_level;

import com.mecathronic.rainShow.graphics.Sprite;
import com.mecathronic.rainShow.level.tile.Tile;

public class SpawnWallTile extends Tile{

	public SpawnWallTile(Sprite sprite) {
		super(sprite);
	}
	
	public boolean solid(){
		return true;
	}
}
