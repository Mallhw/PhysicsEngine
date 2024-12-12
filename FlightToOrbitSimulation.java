//required import statements
import java.awt.Color;
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
public class FlightToOrbitSimulation extends JPanel {

	//TODO: set the initial width and height of your image
	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;

	//required global variables
	private BufferedImage image;
	private Graphics g;
	private Timer timer;
	private Random rand;
	private Falcon10 pranav;
	private double deltaTime;
	private double scale;
	private int moveObjectsY;
	private int moveObjectsYVel;
	private Particles[] stars;
	private SimpleCloud[] clouds;
	private Satalite satalite;
	private double moveObjectsYAcceleration;
	private int groundX;

	
	//Constructor required by BufferedImage
	public FlightToOrbitSimulation() {
		rand = new Random(); 

		//set up Buffered Image and Graphics objects
		image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();
		
		
		pranav = new Falcon10(WIDTH/2, HEIGHT, 15, 38, Color.red, 541300, 0, 0);
		satalite = new Satalite(WIDTH/2, HEIGHT, 15, 15, Color.gray);
		//Stars generator
		stars = new Particles[100];
		for(int i = 0; i < stars.length; i++) {
			stars[i] = new Particles(WIDTH, HEIGHT, HEIGHT);	
			stars[i].spawnStableStars(new Color(225, 225, 30), g);
		}

		deltaTime = 0;
		//set up and start the Timer
		timer = new Timer(10, new TimerListener());
		timer.start();
		int groundX = -100;

		

	}
	
	//TimerListener class that is called repeatedly by the timer
	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int phaseNumber = 1;
			
			gradientBackground(g);
			// Phase 1: Getting rocket to space
			if(pranav.getTime() < 397) {
				moveObjectsY = (int) pranav.getAltitude()/500;
				moveObjectsYVel = (int) ((pranav.getAltitude())/pranav.getTime());
				moveObjectsYAcceleration = moveObjectsYVel/pranav.getTime();
				
				// Star gen
				for(int i = 0; i < stars.length; i++) {
					stars[i].setY( (int) (stars[i].getOriginalYPos() + (moveObjectsY)/5));
				}
				
				
				//Ground
				
				
				g.setColor(new Color(98, 143, 83));
				g.fillOval(-100, HEIGHT-100+(moveObjectsY), 2000, 300);
				
				
				//Rocket
				for(int i = 0; i < stars.length; i++) {
					stars[i].spawnStableStars(new Color(225, 225, 30), g);
					stars[i].setX(stars[i].getX()-4);
					if(stars[i].getX() < 0) {
						stars[i].setX(rand.nextInt(500)+WIDTH);
						stars[i].setY(rand.nextInt(HEIGHT/2));
					}
				}
			
			}
			
			if(pranav.getTime() > 397 && pranav.getTime() < 400) {
				satalite.setY(pranav.getY()- 20);
				satalite.setX(pranav.getX() + 30);
			}
			
			if(pranav.getTime() > 400 && pranav.getTime() < 600) {
				satalite.setY( (int) (satalite.getY()- 2) );
				satalite.setX(satalite.getX());
			}
			
			if(pranav.getTime() > 600) {
				pranav.setX(pranav.getX()-1);

			}
			
			
			if(pranav.getTime() > 397) {
				satalite.draw(g);
				for(int i = 0; i < stars.length; i++) {
					stars[i].spawnStableStars(new Color(225, 225, 30), g);
					stars[i].setX(stars[i].getX()-5);
					if(stars[i].getX() < 0) {
						stars[i].setX(rand.nextInt(500)+WIDTH);
						stars[i].setY(rand.nextInt(HEIGHT/2));
					}
				}
				
			}
			pranav.drawFancyRocket(50, 100, Color.white, new Color(142, 0, 237), "", g);

			
			// All the string
			float f=50.0f; // font size.
			g.setFont(g.getFont().deriveFont(f));
			g.setColor(Color.red);
			if(pranav.getTime() > 162) {
				phaseNumber = 2;
			}
			
			g.drawString( "Phase " + phaseNumber, 5, 40);
			float a=20.0f; // font size.
			g.setFont(g.getFont().deriveFont(a));
			g.setColor(Color.blue);

				g.drawString( "Time+ " + pranav.getTime(), 5, 50*2);
				g.drawString( "Altitude: " + pranav.getAltitude(), 5, 70*2);
				g.drawString( "Velocity: " + pranav.getVelocity(), 5, 90*2);


			
			
			
			
			pranav.move(deltaTime, HEIGHT, 0.05, g);
			
			deltaTime += 0.001;
			
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
		frame.setContentPane(new FlightToOrbitSimulation()); //TODO: Change this to the name of your class!
		frame.setVisible(true);
	}

	
	public void gradientBackground(Graphics g) {

		Graphics2D g2D = (Graphics2D) g;

		GradientPaint spaceShipBackground = new GradientPaint(0, HEIGHT,  new Color(205, 240, 255), 0, -8000+(moveObjectsY*7), new Color(0, 0, 0));
		
		if(moveObjectsY*5 -8000 > 0) {
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
		}
		else {
			g2D.setPaint(spaceShipBackground);
			g2D.fillRect(0, 0, WIDTH, HEIGHT);
		}

	
	}
	
	
	
	
	
}