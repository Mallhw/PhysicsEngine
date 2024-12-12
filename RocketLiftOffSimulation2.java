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
public class RocketLiftOffSimulation2 extends JPanel {

	//TODO: set the initial width and height of your image
	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;

	//required global variables
	private BufferedImage image;
	private Graphics g;
	private Timer timer;
	private Random rand;
	private Falcon9 pranav;
	private double deltaTime;
	private double scale;
	private int moveObjectsY;
	private Particles[] stars;
	private SimpleCloud[] clouds;

	
	//Constructor required by BufferedImage
	public RocketLiftOffSimulation2() {
		
		//set up Buffered Image and Graphics objects
		image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();
		
		
		pranav = new Falcon9(WIDTH/2, HEIGHT, 15, 38, Color.red, 541300, 0, 0);
		
		// Clouds generato
		//Stars generator
		stars = new Particles[40];
		for(int i = 0; i < stars.length; i++) {
			stars[i] = new Particles(WIDTH, HEIGHT/2, 0);	
			stars[i].drawStableStars(new Color(225, 225, 30), g);
		}
		
		
		
		
		
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
			
			gradientBackground(g);
			
			
			
			
			
			// Star gen

			for(int i = 0; i < stars.length; i++) {
				stars[i].drawStableStars(new Color(225, 225, 30), g);
			}
			
			//Ground
			
			
			g.setColor(new Color(98, 143, 83));
			g.fillOval(-100, HEIGHT-100+(moveObjectsY), 1000, 300);
			
			
			//Rocket
			
			pranav.drawFancyRocket(8, 16, Color.white, new Color(142, 0, 237), "", g);
			
			
			
			 
			
			// All the string
			float f=50.0f; // font size.
			g.setFont(g.getFont().deriveFont(f));
			g.setColor(Color.red);
		
			
			g.drawString( "" + pranav.getPhase(), 5, 40);
			float a=20.0f; // font size.
			g.setFont(g.getFont().deriveFont(a));
			g.setColor(Color.blue);
			g.drawString( "Time+ " + pranav.getTime(), 5, 50*2);
			g.drawString( "Altitude: " + pranav.getAltitude(), 5, 70*2);
			g.drawString( "Altitude: " + pranav.getAltitude()/1000, 5, 110*2);
			g.drawString( "Velocity: " + pranav.getVelocity(), 5, 90*2);

			setMarkers(70, g);
			
			
			pranav.move(deltaTime, HEIGHT, 0.38);
			
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
		frame.setContentPane(new RocketLiftOffSimulation2()); //TODO: Change this to the name of your class!
		frame.setVisible(true);
	}

	
	public void gradientBackground(Graphics g) {

		Graphics2D g2D = (Graphics2D) g;

		GradientPaint spaceShipBackground = new GradientPaint(0, HEIGHT,  new Color(205, 240, 255), 0, 350, new Color(0, 0, 0));

		g2D.setPaint(spaceShipBackground);
		g2D.fillRect(0, 0, WIDTH, HEIGHT);

	
	}
	
	public void setMarkers(int numDiff, Graphics g) {
		float f=15.0f; // font size.
		g.setFont(g.getFont().deriveFont(f));
		g.setColor(Color.red);
		
		
		for(int i = 0; i < 110; i += 10) {
			g.drawString( i*16 + " km", WIDTH-70, HEIGHT - i * 7 - numDiff);
			g.drawLine(WIDTH-10, HEIGHT - i * 7 - numDiff, WIDTH-15, HEIGHT - i * 7 - numDiff);
		}
		
		
		
		
	}
	
	
	
	public static int randInt(int max, int min)
    {
        Random ran = new Random();
        return ran.nextInt((max-min) + 1) + min;
    }	
	
}