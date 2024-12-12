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
import java.util.ArrayList; // import the ArrayList class


@SuppressWarnings("serial")
//TODO: Change the name of the class from AnimationShell to the name of your class
public class PhysicsBallSimulation extends JPanel {

	//TODO: set the initial width and height of your image
	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;

	//required global variables
	private BufferedImage image;
	private Graphics g;
	private Timer timer;
	private int deltaTime;
	private PhysicsBall james[];
	private int clippedAmount[];
	private double totalEnergyX;
	private double totalEnergyY;
	private Color[] colors;
	private Random rand;


	//Constructor required by BufferedImage
	public PhysicsBallSimulation() {
		//set up Buffered Image and Graphics objects
		image =  new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = image.getGraphics();		
		rand = new Random(); 
		int totalBalls = 50;
		
		colors = new Color[] {Color.green, Color.red, Color.magenta, Color.blue, Color.cyan, Color.yellow, Color.orange};

		deltaTime = 10;
		
		clippedAmount = new int[totalBalls];
		james = new PhysicsBall[totalBalls];
		for(int i = 0; i < james.length; i++) {
			
			int nDiameter = rand.nextInt(30)+15;
			james[i] = new PhysicsBall(rand.nextDouble(WIDTH-nDiameter)+nDiameter, rand.nextDouble(HEIGHT-nDiameter)+nDiameter, nDiameter, colors[rand.nextInt(7)]);
			james[i].setMass(nDiameter/2);
			james[i].setAcceleration(0, 9.8);
			james[i].setInitalVelocity(rand.nextInt(5)+5, rand.nextInt(360));
			
		}
		// Tester
		james[1].setColor(Color.red);
		
		
		//set up and start the Timer
		timer = new Timer(deltaTime, new TimerListener());
		timer.start();

	}
	
	//TimerListener class that is called repeatedly by the timer
	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			g.setColor(Color.white);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			
			
			
			for(int i = 0; i < james.length; i++) {
				for(int g = 0; g < james.length; g++) {
					
					if(james[i].intersectsWith(james[g]) && james[g] != james[i]) {
						
						
						
						
						//james[i].setXSpeed(james[i].calculateXMomentum(james[g]));
						//james[i].setYSpeed(james[i].calculateYMomentum(james[g]));
						if(james[i].checkIfClipped(james[g])) {
							
							clippedAmount[i] += 1;
							System.out.println("clipped");
							james[i].setXSpeed(-1*james[i].getXSpeed());
							james[i].setYSpeed(-1*james[i].getYSpeed());
							
							if(james[i].getX() > james[g].getX()) {
								james[i].setX(james[i].getX() + ((james[i].getRadius()+james[g].getRadius()) - james[i].findXDistanceFrom(james[g])));
								
							}
							else if(james[i].getX() < james[g].getX()) {
								james[i].setX(james[i].getX() - ((james[i].getRadius()+james[g].getRadius()) - james[i].findXDistanceFrom(james[g])));
								
							}
							if(james[i].getY() > james[g].getY()) {
								james[i].setY(james[i].getY() + ((james[i].getRadius()+james[g].getRadius()) - james[i].findYDistanceFrom(james[g])));
								
							}
							if(james[i].getY() < james[g].getY()) {
								james[i].setY(james[i].getY() - ((james[i].getRadius()+james[g].getRadius()) - james[i].findYDistanceFrom(james[g])));
								
							}
							
							/*
							if(james[i].getXSpeed() > james[g].getXSpeed()) {
								james[i].setX(james[i].getX() - ((james[i].getRadius()+james[g].getRadius()) - james[i].findXDistanceFrom(james[g])));
								
							}
							else if(james[i].getXSpeed() < james[g].getXSpeed()) {
								james[i].setX(james[i].getX() + ((james[i].getRadius()+james[g].getRadius()) - james[i].findXDistanceFrom(james[g])));
								
							}
							if(james[i].getYSpeed() > james[g].getYSpeed()) {
								james[i].setY(james[i].getY() - ((james[i].getRadius()+james[g].getRadius()) - james[i].findYDistanceFrom(james[g])));
								
							}
							if(james[i].getYSpeed() < james[g].getYSpeed()) {
								james[i].setY(james[i].getY() + ((james[i].getRadius()+james[g].getRadius()) - james[i].findYDistanceFrom(james[g])));
								
							}
							*/
						}
						else {
							james[i].setXSpeed(-0.95*james[i].getXSpeed());
							james[i].setYSpeed(-0.95*james[i].getYSpeed());
						}
							
						}
						
					
				}
				for(int z = 0; z < james.length; z++) {
					if(james[i].intersectsWith(james[z]) && james[z] != james[i]) {
						
					}
					else {
					}
				}
				
				if(clippedAmount[i] <= 0) {
					james[i].launch(WIDTH, HEIGHT, deltaTime, 0.8);
					
				}
				else {
					james[i].launch(WIDTH, HEIGHT, deltaTime, 1);
					
					clippedAmount[i] =- 1;
				}
				

				// Checks if the ball still intersects with the other ball
				for(int z = 0; z < james.length; z++) {
					if(james[i].intersectsWith(james[z]) && james[z] != james[i]) {
						
						//james[i].setX(james[i].getX() + james[i].findXDistanceFrom(james[z])); //
						//james[i].setY(james[i].getY() + james[i].findYDistanceFrom(james[z])); //)
					}
				}
				
				james[i].draw(g);
			}
			
			g.setColor(Color.black);
			int incrementIncrease = 30;
			int incrementDiff = 3;
			int moverString = 0;
			for(int a = 0; a < james.length; a++) {
				float f = 10.0f;
				g.setFont(g.getFont().deriveFont(f));

				totalEnergyX += (int) Math.abs(james[a].getXSpeed());
				totalEnergyY += (int) Math.abs(james[a].getYSpeed());
				g.drawString("" + (int) james[a].getX() + ", ", 15+moverString, incrementIncrease+incrementDiff*100);
				moverString += 20;
			}
			float f = 25.0f;
			g.setFont(g.getFont().deriveFont(f));
			g.drawString("X: " + Math.round(james[1].getX()), 10, incrementIncrease);
			totalEnergyX = 0;
			totalEnergyY = 0;
			
			
			g.drawString("Total energy in System X: " , 10, incrementIncrease+incrementDiff*20);
			g.drawString("Y: " + Math.round(james[1].getY()), 10, incrementIncrease+incrementDiff*40);
			g.drawString("Total energy in System X Speed: " + james[1].getXSpeed(), 10, incrementIncrease+incrementDiff*60);
			g.drawString("Total energy in System Y Speed: " + james[1].getYSpeed(), 10, incrementIncrease+incrementDiff*80);

			
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
		frame.setContentPane(new PhysicsBallSimulation()); //TODO: Change this to the name of your class!
		frame.setVisible(true);
	}

}