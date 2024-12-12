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
public class JumpingBallAnimation extends JPanel {

	//TODO: set the initial width and height of your image
	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;

	//required global variables
	private BufferedImage image;
	private Graphics g;
	private Timer timer;
	private JumpingBall[] ballJumper; //TODO: change this to whatever object(s) you are animating
	private Ball ball;
	private int counter;
	private int numHits[];
	private Color[] colors;
	private Random rand;
	
	//Constructor required by BufferedImage
	public JumpingBallAnimation() {
		
		rand = new Random(); 
		colors = new Color[] {Color.green, Color.red, Color.magenta, Color.blue, Color.cyan, Color.yellow, Color.orange};

		//set up Buffered Image and Graphics objects
		image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();
		ballJumper = new JumpingBall[50];
		
		numHits = new int[50];
		for(int i = 0; i < ballJumper.length; i++) {
			ballJumper[i] = new JumpingBall(rand.nextInt(WIDTH), rand.nextInt(HEIGHT), rand.nextInt(15)+10, Color.green);	

		}
		ball = new Ball(rand.nextInt(WIDTH), rand.nextInt(HEIGHT), 100, Color.red);
		ball.setXSpeed(rand.nextInt(1)+1);
		ball.setYSpeed(rand.nextInt(1)+1);
		
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
			
			ball.move(WIDTH, HEIGHT);
			ball.draw(g);
			
		
			for(int i = 0; i < ballJumper.length; i++) {
				ballJumper[i].draw(g);
				
				if(numHits[i] > 5) {
					ballJumper[i].setLocation(-100, -100);
				}
				
				if(!(ballJumper[i].intersectsWith(ball) && numHits[i] < 6)) {
					continue;
				}
				
				ballJumper[i].move(WIDTH, HEIGHT);
				
				counter++;
				numHits[i]++;
				
				ballJumper[i].setColor(colors[rand.nextInt(7)]);
				
				if(ball.getXSpeed() <= 20 && ball.getXSpeed() > 0) {
					ball.setXSpeed(ball.getXSpeed()+1);
				}
				
				else if(ball.getXSpeed() >= -20 && ball.getXSpeed() < 0) {
					ball.setXSpeed(ball.getXSpeed()-1);
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
		frame.setContentPane(new JumpingBallAnimation()); //TODO: Change this to the name of your class!
		frame.setVisible(true);
	}

}