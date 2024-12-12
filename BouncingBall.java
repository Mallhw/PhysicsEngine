//required import statements
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Random;



@SuppressWarnings("serial")
//TODO: Change the name of the class from AnimationShell to the name of your class
public class BouncingBall extends JPanel {

	//TODO: set the initial width and height of your image
	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;

	//required global variables
	private BufferedImage image;
	private Graphics g;
	private Timer timer;
	private Ball[] ball; //TODO: change this to whatever object(s) you are animating

	//Constructor required by BufferedImage
	public BouncingBall() {
		//set up Buffered Image and Graphics objects
		image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();

				
		/*TODO: Code to setup the objects you will animate goes here
		        All objects declared above should be initialized here
			    ex. cloud = new Cloud(100, 100, 250, 70, Color.WHITE);
			        cloud.setXSpeed(2);
		*/
		Random rand = new Random(); 
		
		Color[] colors = new Color[] {Color.green, Color.red, Color.magenta, Color.blue, Color.cyan, Color.yellow, Color.orange};

		ball = new Ball[20];
		for(int a = 0; a < 20; a++) {
			ball[a] = new Ball(rand.nextInt(WIDTH), rand.nextInt(HEIGHT), 25, colors[rand.nextInt(7)]);		
			ball[a].setXSpeed(rand.nextInt(5));
			ball[a].setYSpeed(rand.nextInt(5));
			ball[a].draw(g);
		}
				
		

		
		
		

		//set up and start the Timer
		timer = new Timer(10, new TimerListener());
		timer.start();

	}
	
	//TimerListener class that is called repeatedly by the timer
	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			/* TODO: Move the objects that need to be animated
			 * 		 Draw your ENTIRE scene
			 * 		 Don't forget to call repaint!
			 */
			g.setColor(Color.white);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			for(int i = 0; i < 20; i++) {
				ball[i].move(WIDTH, HEIGHT);
				ball[i].draw(g);
			}
			
			
			
			
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
		frame.setContentPane(new BouncingBall()); //TODO: Change this to the name of your class!
		frame.setVisible(true);
	}

}