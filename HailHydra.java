//required import statements
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
//TODO: Change the name of the class from AnimationShell to the name of your class
public class HailHydra extends JPanel {

	//TODO: set the initial width and height of your image
	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;

	//required global variables
	private BufferedImage image;
	private Graphics g;
	private Timer timer;
	private ArrayList<JumpingBall> ballJumper; //TODO: change this to whatever object(s) you are animating
	private Ball ball;
	private int counter;
	private int numHits[];
	private Color[] colors;
	private Random rand;

	//Constructor required by BufferedImage
	public HailHydra() {
		//set up Buffered Image and Graphics objects
		rand = new Random(); 
		colors = new Color[] {Color.green, Color.red, Color.magenta, Color.blue, Color.cyan, Color.yellow, Color.orange};
		image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();
		
		
		
		
		
		ballJumper = new ArrayList<JumpingBall>();
		for(int i = 0; i < 5; i++) {
			ballJumper.add(new JumpingBall(rand.nextInt(WIDTH-30), rand.nextInt(HEIGHT-30), rand.nextInt(20)+30, colors[rand.nextInt(7)]));
		}
		ball = new Ball(rand.nextInt(WIDTH), rand.nextInt(HEIGHT), 100, Color.red);
		ball.setXSpeed(rand.nextInt(4)+5);
		ball.setYSpeed(rand.nextInt(4)+5);
		
		
		counter = 0;
		
		
		
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
			ball.move(WIDTH, HEIGHT);

			
			for(int i = 0; i < ballJumper.size(); i++) {
				ballJumper.get(i).draw(g);
				if(ballJumper.get(i).intersectsWith(ball)) {
					if(ballJumper.get(i).getDiameter() > 10) {
						ballJumper.add(new JumpingBall(rand.nextInt(WIDTH), rand.nextInt(HEIGHT), ballJumper.get(i).getDiameter()/2, ballJumper.get(i).getColor()));
						ballJumper.get(i).setDiameter(ballJumper.get(i).getDiameter() / 2);
						ballJumper.get(i).setX(rand.nextInt(WIDTH));
						ballJumper.get(i).setX(rand.nextInt(HEIGHT));	
					}
					else {
						ballJumper.get(i).setX(-100);
						ballJumper.get(i).setX(-100);
					}
					
					counter++;
				}
			
			}
			
			ball.draw(g);
			
			
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
		frame.setContentPane(new HailHydra()); //TODO: Change this to the name of your class!
		frame.setVisible(true);
	}

}