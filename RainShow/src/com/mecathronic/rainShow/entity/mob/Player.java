package com.mecathronic.rainShow.entity.mob;

import com.mecathronic.rainShow.graphics.Screen;
import com.mecathronic.rainShow.graphics.Sprite;
import com.mecathronic.rainShow.input.Keyboard;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	int anim = 0;
	private boolean walking = false;

	public Player(Keyboard input) {
		this.input = input;
	}

	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_forward;
	}

	public void update() {
		int xa = 0, ya = 0;
		
		if(anim < 7500)anim++;
		else anim = 0;
		
		if (input.up) ya--;
		if (input.down) ya++;
		if (input.left) xa--;
		if (input.right) xa++;

		if (xa != 0 || ya != 0){
			move(xa, ya);
			walking = true;
		}
		else{
			walking = false;
		}
	}

	public void render(Screen screen) {
		int flip = 0;
		if(dir == 0){
			if(walking){
				if(anim % 20 > 10){
					sprite = Sprite.player_foward_1;
				}
				else{
					sprite = Sprite.player_foward_2;
				}
			}
			else{
				sprite = Sprite.player_forward;
			}
		}
		if(dir == 1){
			if(walking){
				if(anim % 20 > 10){
					sprite = Sprite.player_side_1;
				}
				else{
					sprite = Sprite.player_side_2;
				}
			}
			else{
				sprite = Sprite.player_side;
			}
		}
		if(dir == 2)
			if(walking){
				if(anim % 20 > 10){
					sprite = Sprite.player_backward_1;
				}
				else{
					sprite = Sprite.player_backward_2;
				}
			}
			else{
				sprite = Sprite.player_backward;
			}
		if(dir == 3){
			if(walking){
				if(anim % 20 > 10){
					sprite = Sprite.player_side_1;
				}
				else{
					sprite = Sprite.player_side_2;
				}
			}
			else{
				sprite = Sprite.player_side;
			}
			flip = 1;
		}
		
		screen.renderPlayer(x - 16 , y - 16, sprite, flip);
	}

}
