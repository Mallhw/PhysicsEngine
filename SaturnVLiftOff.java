//required import statements
import java.awt.Color;
import java.text.DecimalFormat;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
//TODO: Change the name of the class from AnimationShell to the name of your class
public class SaturnVLiftOff extends JPanel {

	//TODO: set the initial width and height of your image
	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;

	//required global variables
	private BufferedImage image;
	private Graphics g;
	private Timer timer;
	private Random rand;
	private SaturnV pranav;
	private DecimalFormat pattern;
	private double deltaTime;
	private int moveObjectsY;
	private int moveObjectsYVel;
	private Particles[] stars;
	private SimpleCloud[] clouds;
	private int scaler;

	
	//Constructor required by BufferedImage
	public SaturnVLiftOff() {
		rand = new Random();
		
		//set up Buffered Image and Graphics objects
		image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();
	   	pattern = new DecimalFormat("#.00");
		pranav = new SaturnV(WIDTH/2, HEIGHT, 15, 38, Color.red, 541300, 0, 0);
		//pranav = new FalconHeavy(WIDTH/2, HEIGHT, 30, 76, Color.red, 541300, 0, 0);
		
		// Clouds generator
		
	
		
		//Stars generator
		stars = new Particles[100];
		for(int i = 0; i < stars.length; i++) {
			stars[i] = new Particles(WIDTH, HEIGHT, rand.nextInt(500) + HEIGHT);	
			stars[i].drawStableStars(new Color(225, 225, 30), g);
			
		}
		
		
		scaler = 10000;
		moveObjectsY = (int) (scaler * pranav.getAltitude()/2818111);
		int colorChanger = moveObjectsY/100;
		
		deltaTime = 0;
		//set up and start the Timer
		timer = new Timer(10, new TimerListener());
		timer.start();
		

	}
	
	//TimerListener class that is called repeatedly by the timer
	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
					
			
			
			//Background
			g.setColor(new Color( Math.max(0, 255 - ( (int) Math.pow(moveObjectsY/100, 1.1))), Math.max(0, 255 - ( (int) Math.pow(moveObjectsY/100, 1.1))), Math.max(0, 255 - ( (int) Math.pow(moveObjectsY/100, 1.1)))));
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			//Ground
			g.setColor(new Color(98, 143, 83));
			g.fillOval(-100, HEIGHT-100+(moveObjectsY), 1000, 300);
			
			// Stars placement
						if(pranav.getAltitude()/1000 > 3500) {
							for(int i = 0; i < stars.length; i++) {
								stars[i].drawStableStars(new Color(225, 225, 30), g);
								stars[i].setY( (int) (stars[i].getY() + pranav.getVelocity()*0.0005));
								if(stars[i].getY() > HEIGHT) {
									stars[i].setY( (int) (stars[i].getY() - rand.nextInt(500) - HEIGHT));
									stars[i].setX(rand.nextInt(WIDTH));

								}
								else if(stars[i].getY() < 10 && pranav.getVelocity() < 0) {
									stars[i].setY( (int) (stars[i].getY() + rand.nextInt(500) + HEIGHT + HEIGHT));
									stars[i].setX(rand.nextInt(WIDTH));

								}
								
							}
						}
			
			//Draw markers on the side
			setMarkers(0, (int) (moveObjectsY/1.8), g);
			
			
			//Rocket
			if(pranav.getVelocity() > 0) {
				pranav.drawFire( (int) pranav.getFuelBurned()/10 + 1, Color.red, Color.yellow, g);
			}
			//pranav.drawFancyRocket(8, 16, Color.white, new Color(142, 0, 237), "", g);
			pranav.drawFancyRocket(30, 60, Color.white, new Color(142, 0, 237), "", g);
			
			
		
			
			 
			
			// All the string
			float f=50.0f; // font size.
			g.setFont(g.getFont().deriveFont(f));
			g.setColor(Color.red);
			
			g.drawString(pranav.getPhase(), 5, 40);
			float a=20.0f; // font size.
			g.setFont(g.getFont().deriveFont(a));
			g.setColor(Color.blue);
			g.drawString( "Time+ " + pattern.format(pranav.getTime()), 5, 50*2);
			g.drawString( "Altitude: " + pattern.format(pranav.getAltitude()), 5, 70*2);
			g.drawString( "Altitude: " + pattern.format(pranav.getAltitude()/1000), 5, 110*2);
			g.drawString( "Velocity km: " + pattern.format(pranav.getVelocity()), 5, 90*2);
			g.drawString( "Acceleration: " + pattern.format(pranav.getAcceleration()), 5, 130*2);
			g.drawString( "FuelBurned: " + pattern.format((pranav.getThrust())), 5, 150*2);
			g.drawString( "EscapeVelocity: " + pattern.format((pranav.getEscapeVelocity())), 5, 170*2);
			g.drawString( "Current Sphere: " + pranav.checkSphere(), 5, 190*2);
			g.drawString( "Drag: " + pranav.getDrag(), 5, 210*2);
			g.drawString( "Air Densisty: " + pranav.getAirDensisty(), 5, 230*2);


			

			


			


			
			
			
			
			
			
			
			
			
			// Math and Logic
			
			pranav.move(deltaTime, HEIGHT, 0.02);
			scaler = 10000;
			moveObjectsY = (int) (scaler * pranav.getAltitude()/2818111);
				
			
			deltaTime += 0.001;
			//deltaTime += 0.0005;
			repaint(); //leave this alone, it MUST  be the last thing in this method

			
		}
		
	}

	//do not modify this
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
	}

	//main method with standard graphics code
	public static void main(String[] args) {
		JFrame frame = new JFrame("Animation Shell");
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocation(0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new SaturnVLiftOff()); //TODO: Change this to the name of your class!
		frame.setVisible(true);
	}

	
	public void setMarkers(int numDiff, int yIncrement, Graphics g) {
		float f=15.0f; // font size.
		g.setFont(g.getFont().deriveFont(f));
		g.setColor(Color.red);
		
		
		
		for(int i = 0; i < 110000; i += 10) {
			g.drawString( i*10 + " km", WIDTH-70, HEIGHT - i * 20  - numDiff + yIncrement);
			g.drawLine(WIDTH-10, HEIGHT - i * 20 - numDiff + yIncrement, WIDTH-15, HEIGHT - i * 20 + yIncrement - numDiff);
		}
	
	}
	
	public static int randInt(int max, int min)
    {
        Random ran = new Random();
        return ran.nextInt((max-min) + 1) + min;
    }	
	
}