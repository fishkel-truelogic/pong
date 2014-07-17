package com.truelogic.pong;

import com.truelogic.pong.ui.Board;
import com.truelogic.pong.ui.Pixel;

public class Ball extends Pixel {
	
	private int vy = 0;
	private int vx = -1;
	
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean move(Integer vbar) {
		if (vbar != null) {
			vy -= vbar;
			vx = -vx;
		}
		x += vx;
		y += vy;
		return score();
	}

	private boolean score() {
		boolean score = x < 0 || x > Board.M_WIDTH;
		if (score) {
			Board.takeScore(x < 0);
			return true;
		}
		return false;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}

	public boolean crashBorder() {
		return y - 1 <= 0 || y + 1 >= Board.M_HEIGHT;
	}

	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}
	
	

	
	
}
