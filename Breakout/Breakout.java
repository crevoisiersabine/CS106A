/*
 * File: Breakout.java
 * -------------------
 *
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels.  On some platforms 
  * these may NOT actually be the dimensions of the graphics canvas. */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board.  On some platforms these may NOT actually
  * be the dimensions of the graphics canvas. */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
private static final boolean TRUE = false;
private static final GRect Rect = null;

/** Delay time */
private static final int DELAY = 30;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		
		game_set_up();
		
		Paddle = new GRect(getWidth()/2, getHeight() - PADDLE_Y_OFFSET, PADDLE_WIDTH, PADDLE_HEIGHT); 
		Paddle.setFilled(true); 
		add(Paddle);
		
			addMouseListeners();
		
		play();
			
	}
	
	private void game_set_up(){
		for(int j=0; j < NBRICK_ROWS; j++){
			
			int x = (getWidth() - (NBRICKS_PER_ROW*BRICK_WIDTH + BRICK_SEP * (NBRICKS_PER_ROW - 1)))/2;
			for(int i = 0; i < NBRICKS_PER_ROW; i++){
				Color col = null;
				switch (j){
					case 0: case 1: col = Color.RED;
						break;
					case 2: case 3: col = Color.ORANGE;
						break;
					case 4: case 5: col = Color.YELLOW;
						break;
					case 6: case 7: col = Color.GREEN;
						break;
					case 8: case 9: col = Color.CYAN;
						break;
				}
				
				GRect Rect = new GRect(x, BRICK_Y_OFFSET + j*(BRICK_HEIGHT + BRICK_SEP), BRICK_WIDTH, BRICK_HEIGHT);
				Rect.setFilled(true);
				Rect.setColor(col);
				Rect.setFillColor(col);
				add(Rect);
				x += BRICK_WIDTH + BRICK_SEP;
		
			}
		}
	}

	public void mouseMoved(MouseEvent e) {
	
		if (e.getX() < (getWidth() - PADDLE_WIDTH)) { 
		
			Paddle.setLocation(e.getX(), getHeight() - PADDLE_Y_OFFSET);	
		
		}
	}
	
	private void play(){
		
		Ball = new GOval(getWidth()/2 - BALL_RADIUS, getHeight()/2 - BALL_RADIUS, BALL_RADIUS, BALL_RADIUS);
		Ball.setFilled(true);
		add(Ball);
		
		Move_Ball();
		
	}
	
	private void Move_Ball(){
		
		vx = rgen.nextDouble(1.0, 3.0); 
		if (rgen.nextBoolean(0.5)) vx = -vx;
		vy = 3.0;
		
		while(Ball.getY() < getHeight()) {
		
			if (Ball.getX() + 2 * BALL_RADIUS > getWidth() || Ball.getX() < 0) {vx = -vx;}
			else if (Ball.getY() < 0) {vy = - vy;}
			
			Ball.move(vx, vy);
			pause(DELAY);
			
			GObject collider = getCollidingObject();
			
			if (collider == null) {}
			else if (collider == Paddle) {vy = -vy;}
			else{
				vy = -vy;
				remove(collider);
			}
			
		}
	}	
	
	private GObject getCollidingObject() {

		if (getElementAt(Ball.getX(), Ball.getY()) != null) {return getElementAt(Ball.getX(), Ball.getY());}
		else if (getElementAt(Ball.getX() + 2 * BALL_RADIUS, Ball.getY()) != null) {return getElementAt(Ball.getX() + 2 * BALL_RADIUS, Ball.getY());}
		else if (getElementAt(Ball.getX(), Ball.getY() + 2 * BALL_RADIUS) != null) {return getElementAt(Ball.getX(), Ball.getY() + 2 * BALL_RADIUS);}
		else if (getElementAt(Ball.getX() + 2 * BALL_RADIUS, Ball.getY() + 2 * BALL_RADIUS) != null) {return getElementAt(Ball.getX() + 2 * BALL_RADIUS, Ball.getY() + 2 * BALL_RADIUS);}
		else return null;
		
	}
	
	/* Private instance variable */ 
	private GRect Paddle;
	private GOval Ball;
	private double vx, vy;
	private RandomGenerator rgen = RandomGenerator.getInstance(); 
	
}
