import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Satalite extends Rocket {
	
	// All variables
	private double gravForce;
	private double mass;
	private double thrust;
	private double initalVel;
	private double finalVel;
	private double acceleration;
	private double altitude;
	private double time;
	private double netForce;
	private double fuelBurned;
	private double drag;
	private double airDensisty = 1.299;
	private double sectionalArea = 3.7;
	private int yPos;
	double dragConstant = 0.25;
	double gravConstant = 6.67e-11;
	double massEarth = 5.972e24;
	double radiusEarth = 6.3781e6;

	 
	
	
	public Satalite(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);

		
		
	}
	
	public void drawOld(Graphics g) {
		int width =  (int) (1.2 * getWidth());
		int height =  (int)  (1.2 *getHeight());
		g.setColor(getColor());
		g.fillOval( (int) (getX()-getWidth()/2), (int) (getY()-getHeight()/2), (int) getWidth(), (int) getHeight());
		g.drawLine(getX(), getY(), getX()- width, getY()- height);
		g.drawLine(getX(), getY(), getX()-width, getY());
		g.drawLine(getX(), getY(), getX()-width, getY()+height);

		
	}
	
	public void draw(Graphics g) {
		int width =  (int) (1.2 * getWidth());
		int height =  (int)  (1.2 *getHeight());
		g.setColor(Color.cyan);
		g.fillRect(getX()-width/2, getY()-height/2, width, height);
		g.drawLine(getX(), getY(), getX() - width, getY()-height/2);
		g.drawLine(getX(), getY(), getX() + width, getY() - height/2);


	}
	
	
	
	
	public void move(double deltaTime, int frameHeight, double scale, Graphics g) {
	
		
	}	
	
	
	
	public double calculateAcceleration() {
		return 0;
	}
	
	public double calculateGravForce() {
		gravForce = (gravConstant * massEarth * mass) / Math.pow((radiusEarth + altitude), 2);
		return gravForce;
	}
	
	public double calcDrag() {
		drag = (0.5) * airDensisty * sectionalArea * dragConstant * finalVel;
		return drag;
	}
	
	
	// Getters
	public double getAltitude() {
		return altitude;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public double getMass() {
		return mass;
	}
	
	public double getVelocity() {
		return finalVel;
	}
	
	public double getGrav() {
		return gravForce;
	}
	
	public double getAcceleration() {
		return acceleration;
	}
	
	public double getThrust() {
		return thrust;
	}
	
	public double getTime() {
		return time;
	}
	
	public double getNetForce() {
		return netForce;
	}
 	
	// Setters
	
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}
	
	public void setAcceleration(double acceleration) {
		this.acceleration =  acceleration;
	}
	
	public void setMass(double mass) {
		this.mass = mass;
	}
	
	public void setInitalVel(double velocity) {
		this.initalVel = velocity;
	}
	
	public void setFinalVel(double velocity) {
		this.finalVel = velocity;
	}
	
	public void setTime(double time) {
		this.time = time;
	}
	
	
	
	
	
	
	
	
}