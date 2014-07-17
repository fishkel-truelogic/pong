package com.truelogic.pong;

import java.util.ArrayList;
import java.util.List;

import com.truelogic.pong.ui.BarPixel;
import com.truelogic.pong.ui.Board;
import com.truelogic.pong.ui.Pixel;

public class EnemyBar extends Bar {

	public EnemyBar() {
		super(false);
	}

	public void move(Ball ball) {
		if (ball.getVx() < 0) {
			this.direction = goToCenter();
		} else {
			this.direction = followTheBall(ball);
		}

		switch (this.direction) {
		case UP:
			for (int i = 0; i < 2; i++) {
				if (!(this.body.get(0).getY() - 1 == 0)) {
					BarPixel newTop = new BarPixel(body.get(0).getX(), body
							.get(0).getY() - 1);
					List<Pixel> newBody = new ArrayList<Pixel>();
					newBody.add(newTop);
					newBody.addAll(body);
					newBody.remove(newBody.size() - 1);
					body = newBody;
				} else {
					this.direction = 0;
				}
			}
			break;
		case DOWN:
			for (int i = 0; i < 2; i++) {
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
			}
			break;
		default:
			break;
		}
	}

	private int followTheBall(Ball ball) {
		if (ball.getY() > this.getBody().get(MAX_BAR_PIXEL / 2).getY()) {
			return DOWN;
		} else if (ball.getY() < this.getBody().get(MAX_BAR_PIXEL / 2).getY()) {
			return UP;
		} else {
			return 0;
		}
	}

	private int goToCenter() {
		if (this.getBody().get(MAX_BAR_PIXEL / 2).getY() < Board.M_HEIGHT / 2) {
			return DOWN;
		} else if (this.getBody().get(MAX_BAR_PIXEL / 2).getY() > Board.M_HEIGHT / 2) {
			return UP;
		} else {
			return 0;
		}

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
