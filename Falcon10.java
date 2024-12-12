import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Falcon10 extends Rocket {
	
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
	private int startingY;
	double dragConstant = 0.25;
	double gravConstant = 6.67e-11;
	double massEarth = 5.972e24;
	double radiusEarth = 6.3781e6;


	 
	
	
	public Falcon10(int x, int y, int width, int height, Color color, double mass, double velocity, double altitude) {
		super(x, y, width, height, color);
		this.altitude = altitude;
		this.mass = mass;
		this.initalVel = velocity;
		
	}
	
	public void move(double deltaTime, int frameHeight, double scale, Graphics g) {
		
		if(time <= 162) {
			thrust = 6806000;
			fuelBurned = deltaTime*398900/162;
			
			 
			mass = mass - fuelBurned;

			
			
			netForce = thrust - gravForce; 

			acceleration = netForce/mass;
			finalVel = finalVel - drag + acceleration*deltaTime;
			altitude = altitude + finalVel*deltaTime;
			calculateGravForce();

			
			time = time + deltaTime;			
			
			
			// Animating it
			
			yPos = (int) ((frameHeight * (1 - scale * altitude / 105000) ) - 2 * getHeight() - getHeight()*.7);
			
			setY(yPos);
		}
		
		if(time > 162 && time <= 397) {
			thrust = 934000;
			fuelBurned = deltaTime*92670/235;
			
			
			mass = mass - fuelBurned;

			 
			
			netForce = thrust - gravForce; 

			acceleration = netForce/mass;
			finalVel = finalVel - drag + acceleration*deltaTime;
			altitude = altitude + finalVel*deltaTime;
			calculateGravForce();

			
			time = time + deltaTime;			
			
			
			// Animating it
			
			yPos = (int) ((frameHeight * (1 - scale * altitude / 105000) ) - 2 * getHeight() - getHeight()*.5);
			
			setY(yPos);
		}
		
		if(time > 397) {
			
			thrust = 0;
			fuelBurned = deltaTime;
			
			
			mass = mass - fuelBurned;

			
			netForce = thrust - gravForce; 

			acceleration = netForce/mass;
			finalVel = finalVel - drag + acceleration*deltaTime;
			altitude = altitude + finalVel*deltaTime;
			calculateGravForce();

			
			time = time + deltaTime;	
			
			yPos = (int) ((frameHeight * (1 - scale * altitude / 105000) ) - 2 * getHeight() - getHeight()*.5);
			
			setY(yPos);
			
		}
		
		
		
		
		
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