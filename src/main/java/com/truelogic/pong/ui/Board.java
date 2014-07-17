package com.truelogic.pong.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.truelogic.pong.Ball;
import com.truelogic.pong.Bar;
import com.truelogic.pong.EnemyBar;

public class Board extends JPanel implements ActionListener {

	private static final long serialVersionUID = 592910003380869323L;

	private static final int DELAY = 60;

	public static final int M_WIDTH = 100;

	public static final int M_HEIGHT = 60;

	private static final int B_WIDTH = M_WIDTH * Pixel.SIZE;

	private static final int B_HEIGHT = M_HEIGHT * Pixel.SIZE;

	private static final int MARGIN_LEFT = 0;

	private static final int MARGIN_TOP = 0;

	private Timer timer;

	private Bar bar;
	
	private EnemyBar enemyBar;
	
	private Ball ball;

	private static int enemyPoints;
	
	private static int points;
	

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintBackground(g);
		paintBar(bar, g);
		paintBar(enemyBar, g);
		paintBall(g);
		paintScore(g);
	}

	
	private void paintScore(Graphics g) {
		String msg = Integer.toString(points) + "-" + Integer.toString(enemyPoints) ;
		Font small = new Font("Helvetica", Font.BOLD, 22);
		FontMetrics metr = getFontMetrics(small);
		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(msg, (M_WIDTH - metr.stringWidth(msg)) / 2, 5 * Pixel.SIZE);

	}
	
	private void paintBall(Graphics g) {
		int x = MARGIN_LEFT + ball.getX() * Pixel.SIZE;
		int y = MARGIN_TOP + ball.getY() * Pixel.SIZE;
		g.drawImage(ball.getImage(), x, y, this);
	}

	private void paintBackground(Graphics g) {
		BackgroundPixel bgp = new BackgroundPixel();
		int x = MARGIN_LEFT + M_WIDTH / 2 * Pixel.SIZE;
		for (int i = 0; i < M_HEIGHT; i++) {
			int y = MARGIN_TOP + i * Pixel.SIZE;
			g.drawImage(bgp.getImage(), x, y, this);
		}
		
	}

	private void paintBar(Bar player, Graphics g) {
		for (Pixel bp : player.getBody()) {
			int x = MARGIN_LEFT + bp.getX() * Pixel.SIZE;
			int y = MARGIN_TOP + bp.getY() * Pixel.SIZE;
			g.drawImage(bp.getImage(), x, y, this);
		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		bar.move();
		if (ball.getX() >= M_WIDTH / 2) {
			enemyBar.move(ball);
		}
		if (ball.move(crash(ball, bar, enemyBar))) {
			this.ball = new Ball(M_WIDTH / 2, M_HEIGHT / 2);
		}
		repaint();
	}

	private Integer crash(Ball ball, Bar bar, EnemyBar enemyBar) {
		if (bar.crash(ball)) {
			 return bar.getVelocity();
		} 
		if (ball.crashBorder()) {
			ball.setVy(-ball.getVy());
		}
		if (enemyBar.crash(ball)) {
			 return enemyBar.getVelocity();
		}
		return null;
	}

	public Board() {
		super();
		this.bar = new Bar(true);
		this.enemyBar = new EnemyBar();
		this.ball = new Ball(M_WIDTH / 2, M_HEIGHT / 2);
		setBackground(Color.BLACK);
		timer = new Timer(DELAY, this);
		timer.start();
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		setFocusable(true);
		addKeyListener(new KeyPressListener(this));
	}
	
	private class KeyPressListener extends KeyAdapter {

		private Board board;

		public KeyPressListener(Board board) {
			this.board = board;
		}


		@Override
		public void keyPressed(KeyEvent e) {

			switch(e.getKeyCode()) {
				case KeyEvent.VK_UP: board.getBar().setDirection(Bar.UP); break;
				case KeyEvent.VK_DOWN: board.getBar().setDirection(Bar.DOWN); break;
			}
		}

	}

	public Bar getBar() {
		return bar;
	}

	public void setBar(Bar bar) {
		this.bar = bar;
	}

	public static void takeScore(boolean enemySocre) {
		if (enemySocre) {
			enemyPoints ++;
		} else {
			points ++;
		}
		
	}
	
	

}
