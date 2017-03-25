package com.mecathronic.rainShow.level.tile.spawn_level;

import com.mecathronic.rainShow.graphics.Sprite;
import com.mecathronic.rainShow.level.tile.Tile;

public class SpawnHedgeTile extends Tile{

	public SpawnHedgeTile(Sprite sprite) {
		super(sprite);
	}
	
	public boolean solid(){
		return true;
	}
}
