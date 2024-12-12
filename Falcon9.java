import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Falcon9 extends Rocket {
	
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
	private double airDensisty;
	private double sectionalArea = 3.7;
	private String spaceLayer;
	private double escapeVel;
	private boolean crash;
	private String phase;
	private int yPos;
	double dragConstant = 0.25;
	double gravConstant = 6.67e-11;
	double massEarth = 5.972e24;
	double radiusEarth = 6.3781e6;

	 
	
	
	public Falcon9(int x, int y, int width, int height, Color color, double mass, double velocity, double altitude) {
		super(x, y, width, height, color);
		this.altitude = altitude;
		this.mass = mass;
		this.initalVel = velocity;
		phase = "";
		
		
	}
	
	public void move(double deltaTime, int frameHeight, double scale) {
		
		if(time <= 162) {
			thrust = 6806000;
			fuelBurned = deltaTime*398900/162;	
			//drag = 0;
			yPos = (int) ((frameHeight * (1 - scale * altitude / 711377) )-50-(50*(.1)));
			setY(yPos);
			phase = "Phase 1";

		}
		
		if(time == 162) {
			mass = 96570;
		}
		
		if(time > 162 && time <= 559) {
			thrust = 934000;
			fuelBurned = (deltaTime*92670)/397;
			yPos = (int) ((frameHeight * (1 - scale * altitude / 711377) )-50-(50*(.1)));
			setY(yPos);
			phase = "Phase 2";
		}
		
		
		
		if(time > 559 && altitude > -5) {
			yPos = (int) ((frameHeight * (1 - scale * altitude / 711377) )-50-(50*(.1)));
			thrust = 0;
			setY(yPos);
			phase = "No Fuel";
		}
		
		if(altitude < -5) {
			deltaTime = 0;
			netForce = 0;
			thrust = 0;
			mass = 0;
			phase = "Crahsed";
		}
		
		
		// Calculating each of the instances
		
		mass = mass - fuelBurned;
		netForce = thrust - gravForce; 
		acceleration = netForce/mass;
		finalVel = finalVel + acceleration*deltaTime;
		altitude = altitude + finalVel*deltaTime;
		calculateGravForce();	
		//calcDrag();
		//calcEscapeVelocity();
		time = time + deltaTime;			
		// Animating it
		
		
		
		
	}	
	
	
	
	public double calculateAcceleration() {
		return 0;
	}
	
	public double calculateGravForce() {
		gravForce = (gravConstant * massEarth * mass) / Math.pow((radiusEarth + altitude), 2);
		return gravForce;
	}
	
	public double calcDrag() {
		drag = (0.5) * airDensisty * sectionalArea * dragConstant * Math.pow(finalVel/1000, 2);
		return drag;
	}
	
	
	public double calcEscapeVelocity() {
		escapeVel = Math.sqrt( (2 * gravConstant * massEarth)/(radiusEarth + altitude) );
		return escapeVel;
	}
	
	public String checkSphere() {
		if(altitude/1000 <= 10) {
			spaceLayer = "Troposphere";
			return "Troposphere";
		}
		if(altitude/1000 <= 50 && altitude/1000 > 10) {
			spaceLayer = "Stratosphere";
			return "Stratosphere";
		}
		if(altitude/1000 <= 85 && altitude/1000 > 50) {
			spaceLayer = "Mesosphere";
			return "Mesosphere";
		}
		if(altitude/1000 <= 1000 && altitude/1000 > 85) {
			spaceLayer = "Thermosphere";
			return "Thermosphere";
		}
		if(altitude/1000 <= 100000 && altitude/1000 > 1000) {
			spaceLayer = "Exosphere";
			return "Exosphere";
		}
		else {
			return "Outer Space!";
		}
	}
	
	
	// Getters
	public double getAltitude() {
		return altitude;
	}
	
	public double getEscapeVelocity() {
		return escapeVel;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public double getMass() {
		return mass;
	}
	
	public double getDrag() {
		return drag;
	}
	
	public double getVelocity() {
		return finalVel;
	}
	
	public double getAirDensisty() {
		return airDensisty;
	}
	
	public double getGrav() {
		return gravForce;
	}
	public double getFuelBurned() {
		return fuelBurned;
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
	
	public String getPhase() {
		return phase;
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