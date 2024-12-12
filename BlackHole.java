import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class BlackHole extends Ball {
	
	public BlackHole(double x, double y, double diameter, Color color) {
		super(x, y, diameter, color);
		
	}
/**
	 * Moves to a random location within the boundaries of the rightEdge
	 * and bottomEdge
	 * @param rightEdge the rightEdge of the movement area
	 * @param bottomEdge the bottomEdge of the movement area
	 */
	public void move(int rightEdge, int bottomEdge) {
		Random rand = new Random();
		setLocation(rand.nextDouble(rightEdge-getRadius())+getRadius(), rand.nextDouble(bottomEdge-getRadius())+getRadius());
	}
		
	/**
	 * Determines if the JumpingBall intersects, or collides with, another Ball object
	 * @param otherBall a Ball object
	 * @return true if JumpingBall intersects with the Ball, false otherwise
	 */
	public boolean intersectsWith(Ball otherBall) {
		if(findDistanceFrom(otherBall.getX(), otherBall.getY()) <= getRadius()+otherBall.getRadius()) {
			return true;
		}
		return false;
	}
	
	
	public void increaseSize(Ball otherBall) {
		
	}
		
	/**
	 * Calculates and returns the distance between the center of the JumpingBall and 
	 * a specific (x, y) location.
	 * @param x the x-coordinate
	 * @param y the y-coordinate
	 * @return the distance between the center of the JumpingBall and (x, y) coordinate
	 */
	public double findDistanceFrom(double x2, double y2) {
		double distance = Math.sqrt(Math.pow((getX()-x2), 2) + Math.pow((getY()-y2), 2));
		return distance;
	}
}

