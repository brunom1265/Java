package com.mecathronic.rainShow.entity.mob;

import com.mecathronic.rainShow.entity.Entity;
import com.mecathronic.rainShow.graphics.Screen;
import com.mecathronic.rainShow.graphics.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;

	public void move(int xa, int ya) {

		if(xa != 0 && ya != 0){
			move(xa, 0);
			move(0, ya);
			return;
		}
		if (xa > 0) dir = 1;
		if (xa < 0) dir = 3;
		if (ya > 0) dir = 2;
		if (ya < 0) dir = 0;

		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}

	}

	public void update() {
	}

	private boolean collision(int xa, int ya) {
		for(int c = 0; c < 4; c++){
			int xt = ((x + xa) + c % 2 * 15 - 8) >> 4; //i don't fucking know what calcules are this
			int yt = ((y + ya) + c % 2 * 3 + 12) >> 4; //i don't fucking know what calcules are this

			if(level.getTile(xt, yt).solid())
				return true;
		}
		return false;
	}

	public void render(Screen screen) {
	}

}
