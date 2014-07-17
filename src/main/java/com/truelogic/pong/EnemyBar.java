package com.truelogic.pong;

import com.truelogic.pong.ui.Pixel;

public class EnemyBar extends Bar {

	public EnemyBar() {
		super(false);
	}
	
	public void move(Ball ball) {
		if (ball.getY() > this.getBody().get(MAX_BAR_PIXEL / 2).getY()) {
			this.direction = DOWN;
		} else if (ball.getY() < this.getBody().get(MAX_BAR_PIXEL / 2).getY()) {
			this.direction = UP;
		} else {
			this.direction = 0;
		}
		super.move();
	}
	
	public boolean crash(Ball ball) {
		for (Pixel p : this.body) {
			Ball newBall = new Ball(ball.getX() + 1, ball.getY());
			if (p.inSameSpot(newBall)) {
				return true;
			}
		}
		return false;
	}


}
