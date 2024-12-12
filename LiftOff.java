//required import statements
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
//TODO: Change the name of the class from AnimationShell to the name of your class
public class LiftOff extends JPanel {

	//TODO: set the initial width and height of your image
	private static final int WIDTH = 1000;
	private static final int HEIGHT = 1000;

	//required global variables
	private BufferedImage image;
	private Graphics g;
	private Timer timer;
	private Rocket rocket;
	private int speedmult;
	private int sceneNum;

	
	
	//Constructor required by BufferedImage
	public LiftOff() {
		//set up Buffered Image and Graphics objects
		image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();

		
		rocket = new Rocket(WIDTH/2, HEIGHT);
		rocket.drawFancyRocket(WIDTH/2, HEIGHT, 60, 150, Color.white, new Color(142, 0, 237), "Never gonna lift you up", g);
		
		rocket.setYSpeed(-1*(rocket.getY()-1000)+3);

		//set up and start the Timer
		timer = new Timer(1, new TimerListener());
		timer.start();
		sceneNum = 0;

	}
	
	//TimerListener class that is called repeatedly by the timer
	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			/* TODO: Move the objects that need to be animated
			 * 		 Draw your ENTIRE scene
			 * 		 Don't forget to call repaint!
			 */
			
			sceneNum = sceneNum + 1;
			
			int speedifier = (int) Math.sqrt((-1*(rocket.getY()-1000)))/2;
			
			
			speedmult = (int) Math.pow(speedifier, sceneNum);
			gradientBackground(g);
			rocket.setYSpeed(speedifier + 3);
			
			
			System.out.println(rocket.getX());
			rocket.move(HEIGHT);
			rocket.drawFancyRocket(WIDTH/2, rocket.getY(), 60, 150, Color.white, new Color(142, 0, 237), "Never gonna lift you up", g);
			rocket.drawFire(rocket.getX(), rocket.getY(), 60, 150, new Color(230, 83, 50), new Color(255, 224, 156), g);
			repaint(); //leave this alone, it MUST  be the last thing in this method
		}
		
	}

	//do not modify this
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

	//main method with standard graphics code
	public static void main(String[] args) {
		JFrame frame = new JFrame("Lift Off");
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocation(200, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new LiftOff()); //TODO: Change this to the name of your class!
		frame.setVisible(true);
	}

	
	public static void gradientBackground(Graphics g) {

		Graphics2D g2D = (Graphics2D) g;

		GradientPaint aroraBackground = new GradientPaint(0, 0,  new Color(49, 63, 152), WIDTH, HEIGHT, new Color(132, 206, 144));

		g2D.setPaint(aroraBackground);
		g2D.fillRect(0, 0, WIDTH, HEIGHT);
	}
}