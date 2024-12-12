//required import statements
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
//TODO: Change the name of the class from AnimationShell to the name of your class
public class BlackHoleSimulator extends JPanel {

	//TODO: set the initial width and height of your image
	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;

	//required global variables
	private BufferedImage image;
	private Graphics g;
	private Timer timer;
	private JumpingBall[] ballJumper; //TODO: change this to whatever object(s) you are animating
	private Ball blackHole;
	private int counter;
	private int numHits[];
	private Color[] colors;
	private Random rand;
	private double currentSize;
	
	//Constructor required by BufferedImage
	public BlackHoleSimulator() {
		
		rand = new Random(); 
		colors = new Color[] {Color.green, Color.red, Color.magenta, Color.blue, Color.cyan, Color.yellow, Color.orange};
		currentSize = 100;
		//set up Buffered Image and Graphics objects
		image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();
		ballJumper = new JumpingBall[5000];
		
		numHits = new int[5000];
		for(int i = 0; i < ballJumper.length; i++) {
			ballJumper[i] = new JumpingBall(rand.nextInt(WIDTH), rand.nextInt(HEIGHT), rand.nextInt(15)+10, Color.green);	

		}
		blackHole = new Ball(rand.nextInt(WIDTH), rand.nextInt(HEIGHT), currentSize, Color.black);

		
		counter = 0;

		//set up and start the Timer
		timer = new Timer(10, new TimerListener());
		timer.start();

	}
	
	//TimerListener class that is called repeatedly by the timer
	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			g.setColor(Color.white);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			blackHole.draw(g);
			
		
			for(int i = 0; i < ballJumper.length; i++) {
				ballJumper[i].draw(g);
			
				if(ballJumper[i].getX() < blackHole.getX()) {
					ballJumper[i].setXSpeed(1);
				}
				if(ballJumper[i].getX() > blackHole.getX()) {
					ballJumper[i].setXSpeed(-1);
				}
				
				if(ballJumper[i].getY() < blackHole.getY()) {
					ballJumper[i].setYSpeed(1);
				}
				
				if(ballJumper[i].getY() > blackHole.getY()) {
					
					ballJumper[i].setYSpeed(-1);
				}
				ballJumper[i].gravitate(WIDTH, HEIGHT);
				
				if(ballJumper[i].intersectsWith(blackHole)) {
					ballJumper[i].move(WIDTH, HEIGHT);
					ballJumper[i].setColor(colors[rand.nextInt(7)]);
					counter++;
					numHits[i]++;
					currentSize = currentSize + ballJumper[i].getDiameter()/500;
					blackHole.setDiameter(currentSize);

				}

				
				
			}
			
			g.setColor(Color.black);
			float f = 50.0f;
			g.setFont(g.getFont().deriveFont(f));
			g.drawString("Counter: " + counter, 10, 50);
			
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
		frame.setContentPane(new BlackHoleSimulator()); //TODO: Change this to the name of your class!
		frame.setVisible(true);
	}

}