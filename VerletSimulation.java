//required import statements
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import java.util.ArrayList; // import the ArrayList class
import java.util.HashSet;


@SuppressWarnings("serial")
//TODO: Change the name of the class from AnimationShell to the name of your class
public class VerletSimulation extends JPanel {

	//TODO: set the initial width and height of your image
	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;

	//required global variables
	private BufferedImage image;
	private Graphics g;
	private Timer timer;
	private int deltaTime;
	private double dt;
	private VerletObject bob;
	private ArrayList<Double> gravity =  new ArrayList<Double>();

                    




	//Constructor required by BufferedImage
	public VerletSimulation() {
		//set up Buffered Image and Graphics objects
		image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();		
		deltaTime = 5;
		dt = 0;
		
		gravity.add(0.0);
		gravity.add(9.8);
		
		
		bob = new VerletObject();
				
		//set up and start the Timer
		timer = new Timer(deltaTime, new TimerListener());
		timer.start();
	}

	
	//TimerListener class that is called repeatedly by the timer
	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			g.setColor(Color.gray);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.white);

			g.fillOval(0, 0, WIDTH, HEIGHT);
			
			bob.draw(g);
			bob.calculateAcceleration(gravity);
			bob.applyConstraints(WIDTH, HEIGHT);
			bob.updatePosition(dt);
			
			dt += 0.001;
			repaint(); //leave this alone, it MUST  be the last thing in this method
		}
		
	}

	//do not modify this
	public void paintComponent(Graphics g) {
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

	//main method with standard graphics code
	public static void main(String[] args) {
		JFrame frame = new JFrame("Verlet");
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocation(0, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(new VerletSimulation()); //TODO: Change this to the name of your class!
		frame.setVisible(true);
	}

}