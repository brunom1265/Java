package com.mecathronic.rainShow.level.tile;

import com.mecathronic.rainShow.graphics.Screen;
import com.mecathronic.rainShow.graphics.Sprite;
import com.mecathronic.rainShow.level.tile.spawn_level.SpawnFloorTile;
import com.mecathronic.rainShow.level.tile.spawn_level.SpawnGrassTile;
import com.mecathronic.rainShow.level.tile.spawn_level.SpawnHedgeTile;
import com.mecathronic.rainShow.level.tile.spawn_level.SpawnWallTile;
import com.mecathronic.rainShow.level.tile.spawn_level.SpawnWaterTile;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile flower = new GrassTile(Sprite.flower);
	public static Tile rock = new GrassTile(Sprite.rock);
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	
	public static Tile spawn_grass = new SpawnGrassTile(Sprite.spawn_grass);
	public static Tile spawn_hedge = new SpawnHedgeTile(Sprite.spawn_hedge);
	public static Tile spawn_water = new SpawnWaterTile(Sprite.spawn_water);
	public static Tile spawn_wall1 = new SpawnWallTile(Sprite.spawn_wall1);
	public static Tile spawn_wall2 = new SpawnWallTile(Sprite.spawn_wall2);
	public static Tile spawn_floor = new SpawnFloorTile(Sprite.spawn_floor);
	
	public static final int col_spawn_grass = 0xff267F00;
	public static final int col_spawn_edge = 0; //unused
	public static final int col_spawn_water = 0; //unsused
	public static final int col_spawn_wall1 = 0xff404040;
	public static final int col_spawn_wall2 = 0xff000000;
	public static final int col_spawn_floor = 0xff0800FF;
	
	public Tile(Sprite sprite){
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen){
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid(){
		return false;
	}
	
}
