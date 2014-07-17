package com.truelogic.pong;

import java.util.ArrayList;
import java.util.List;

import com.truelogic.pong.ui.BarPixel;
import com.truelogic.pong.ui.Board;
import com.truelogic.pong.ui.Pixel;

public class Bar {

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	protected static final int MAX_BAR_PIXEL = 6;

	protected int direction;

	private boolean left;
	protected List<Pixel> body;

	public Bar(boolean left) {
		this.left = left;
		this.body = new ArrayList<Pixel>();
		int x = left ? 1 : Board.M_WIDTH - 2;
		for (int i = 0; i < MAX_BAR_PIXEL + 1; i++) {
			BarPixel bp = new BarPixel(x, Board.M_HEIGHT / 2 + i);
			body.add(bp);
		}
	}

	public void move() {
		switch (this.direction) {
		case UP:
			if (!(this.body.get(0).getY() - 1 == 0)) {

				BarPixel newTop = new BarPixel(body.get(0).getX(), body.get(0)
						.getY() - 1);
				List<Pixel> newBody = new ArrayList<Pixel>();
				newBody.add(newTop);
				newBody.addAll(body);
				newBody.remove(newBody.size() - 1);
				body = newBody;
			} else {
				this.direction = 0;
			}
			break;
		case DOWN:
			if (!(this.body.get(MAX_BAR_PIXEL).getY() + 1 == Board.M_HEIGHT)) {
				BarPixel newBottom = new BarPixel(body.get(body.size() - 1)
						.getX(), body.get(body.size() - 1).getY() + 1);
				List<Pixel> newBody2 = new ArrayList<Pixel>();
				body.remove(0);
				newBody2.addAll(body);
				newBody2.add(newBottom);
				body = newBody2;
			} else {
				this.direction = 0;
			}
			break;
		default:
			break;
		}

	}


	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public List<Pixel> getBody() {
		return body;
	}

	public void setBody(List<Pixel> body) {
		this.body = body;
	}

	public boolean crash(Ball ball) {
		for (Pixel p : body) {
			Ball newBall = new Ball(ball.getX() - 1, ball.getY());
			if (p.inSameSpot(newBall)) {
				return true;
			}
		}
		return false;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		if (this.direction != 0 && direction != this.direction) {
			this.direction = 0;
		} else {
			this.direction = direction;
		}
	}

	public Integer getVelocity() {
		switch (this.direction) {
		case UP:
			return 1;
		case DOWN:
			return -1;
		case 0:
			return 0;
		}
		return null;
	}

}
