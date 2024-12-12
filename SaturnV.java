import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class SaturnV extends Rocket {
	
	// All variables
	private double gravForce;
	private double mass;
	private double stage2Mass = 96570;
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

	 
	
	
	public SaturnV(int x, int y, int width, int height, Color color, double mass, double velocity, double altitude) {
		super(x, y, width, height, color);
		this.altitude = altitude;
		this.mass = mass;
		this.initalVel = velocity;
		phase = "1";
		
		
	}
	
	public void move(double deltaTime, int frameHeight, double scale) {
		
		
		
		
		if(time <= 161) {
			thrust = 34500000;
			mass = 2965000;
			fuelBurned = deltaTime*18143.7/671;	
			yPos = (int) ((frameHeight * (1 - scale * altitude / 264835000) )-getHeight()-(getHeight()*(.1)));
			setY(yPos);
			phase = "Phase 1";

			
		}
		
		if(time > 161 && time <= 521) {
			mass = 48000;
			thrust = 4535920.37;
			fuelBurned = (deltaTime*13000)/671;
			yPos = (int) ((frameHeight * (1 - scale * altitude / 264835000) )-getHeight()-(getHeight()*(.1)));
			setY(yPos);
			phase = "Phase 2";
		}
		
		if(time > 521 && time <= 671) {
			mass = 11900;
			thrust = 30840.428;
			fuelBurned = (deltaTime*1500)/671;
			yPos = (int) ((frameHeight * (1 - scale * altitude  / 264835000) )-getHeight()-(getHeight()*(.1)));
			setY(yPos);
			phase = "Phase 3";
		}
		
		
		
		if(time > 671 && altitude > -5) {
			thrust = 0;
			fuelBurned = 0;
			phase = "No Fuel";
			yPos = (int) ((frameHeight * (1 - scale * altitude / 264835000) )-getHeight()-(getHeight()*(.1)));
			setY(yPos);
		}
		

		if(altitude < -1) {
			gravForce = 0;
			thrust = 0;
			fuelBurned = 0;
			drag = 0;
			finalVel = 0;
			phase = "Crashed";
		}
		
		if(finalVel > getEscapeVelocity() && time > 671) {
			phase = "Escaped Earth!";
		}
		
		
		
		
		// Calculating each of the instances
		
		
		//.out.println(calcDrag());
		//System.out.println(calcEscapeVelocity());
		
		
		mass = mass - fuelBurned;
		netForce = thrust - gravForce; 
		acceleration = netForce/mass;
		finalVel = finalVel - drag + acceleration*deltaTime;
		altitude = altitude + finalVel*deltaTime;
		calculateGravForce();
		calcAirDensisty();
		//calcDrag();
		calcEscapeVelocity();
		time = time + deltaTime;			
		
		
		
		
	}	
	
	
	// Calculators
	
	
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
	
	public void calcAirDensisty() {
		
		
		// Numbers from https://www.eoas.ubc.ca/courses/atsc113/flying/met_concepts/03-met_concepts/03a-std_atmos/index.html
		
		
		if(altitude/1000 < 11) {
			airDensisty = 1.22;
		}
		if(altitude/1000 >= 11 && altitude/1000 < 20) {
			airDensisty = 0.3639;
		}
		if(altitude/1000 >= 20 && altitude/1000 < 32) {
			airDensisty = 0.088;
		}
		if(altitude/1000 >= 32 && altitude/1000 < 47) {
			airDensisty = 0.0132;
		}
		if(altitude/1000 >= 47 && altitude/1000 < 51) {
			airDensisty = 0.00086;
		}
		if(altitude/1000 >= 51 && altitude/1000 < 71) {
			airDensisty = 0.000064;
		}
		if(altitude/1000 >= 71 && altitude/1000 < 84.9) {
			airDensisty = 0.0000007;
		}
		if(altitude/1000 > 100) {
			airDensisty = 0;
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