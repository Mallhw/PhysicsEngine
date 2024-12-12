import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Rocket {
	
	private double x;
	private double y;
	private Color color;
	private double diameter;
	private double width;
	private double height = 0;
	private double ySpeed;
	
	public Rocket(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;

		ySpeed = 0;
		
	}
	
	
	public void drawSpaceJam1(Graphics g) {
		
		int x = (int) this.x;
		int y = (int) this.y;
		int width = (int) this.width;
		int height = (int) this.height;
		int ySpeed = (int) this.ySpeed;
		
		
		g.setColor(color);
		int xPoints[] = {x, x-40, x, 110, 100};
		int yPoints[] = {y+height, y+height, y+height/2-50, y-650, y-550};
		g.fillRect(x, y, width, height);
		g.setColor(color.red);
		g.drawRect(x, y, width, height);
		int xTrianglePoints[] = {x, x+width/2, x+width};
		int yTrianglePoints[] = {y, y-50, y};
		g.fillPolygon(xTrianglePoints, yTrianglePoints, 3);
		g.fillPolygon(xPoints, yPoints, 3);
		
		
		for(int i = 0; i < xPoints.length; i++) {
			xPoints[i] = xPoints[i]+width;
		}
		
		
		// 
		xPoints[1] = xPoints[0]+40;
		g.fillPolygon(xPoints, yPoints, 3);
		
		String rocketText = "SpaceJam1";
		verticalString(rocketText, x+width/2, y+height/3, 15f, 13, g);
		g.setColor(color.white);
		g.fillOval(x+width/2 -5,y-25, 10, 10);
		g.setColor(color.gray);
		g.drawOval(x+width/2 -5,y-25, 10, 10);


		
	}
	
	public void drawFancyRocket(int nwidth, int nheight, Color bodyColor, Color colorAesthetic, String rocketName, Graphics g) {
		
		
		int x = (int) this.x;
		int y = (int) this.y;

		
		width = nwidth;
		height = nheight;
		
		int width = (int) this.width;
		int height = (int) this.height;
		Color mainColor = bodyColor;
		Color colorScheme = colorAesthetic; 
		
		String rocketText = rocketName;
		
		
		g.setColor(colorScheme);
		g.fillOval(x, y-height/2, width, nheight);
		
		g.fillArc(x, y-height/2, width, height, 90, 180);
		
		g.setColor(mainColor);
		g.fillRect(x, y, width, height);
		
		g.setColor(colorScheme);
		g.fillRect(x, y+height*2/3, width, 3);
		
		g.setColor(getContrastColor(bodyColor));
		g.fillOval(x+width/2-width/5, y+height/10, height/5, height/5);
		g.setColor(getContrastColor(bodyColor));

		g.fillArc(x-width/2, y+height/2, width, height/2, 90, 180);
		g.fillArc(x-width/2+width, y+height/2, width, height/2, 270, 180);
		g.fillRect(x-width/2, y+height-height/4, width/2, height/4);
		g.fillRect(x-width/2+width+width/2, y+height-height/4, width/2, height/4);
		g.fillRect(x+width/2-width/12, y+height/2, width/6, height/2);
		
		
		
		int polygonXPoints[] = {x, x+width/5, x+(4*width)/5, x+width};
		int polygonYPoints[] = {y+height, y+height+height/10, y+height+height/10, y+height};
		
		g.fillPolygon(polygonXPoints, polygonYPoints, 4);
		
		/*
		float f=5.5f; // font size.
		g.setFont(g.getFont().deriveFont(f));
		g.setColor(colorScheme);
		g.drawString(rocketText, x, y+height/2);
		*/
		
		
		
		//\
	}
	
	
	public void drawRegularRocket(Color color, Graphics g) {
		int x = (int) this.x;
		int y = (int) this.y;
		int width = (int) this.width;
		int height = (int) this.height;
		int ySpeed = (int) this.ySpeed;
		
		
		
		
	}
	
	
	
	public void verticalString(String name, int x, int y, float fontSize, int spacing, Graphics g) {
		g.setFont(g.getFont().deriveFont(fontSize));
		x = x-3;
		for(int i = 0; i < name.length(); i++) {
			g.drawString(Character.toString(name.charAt(i)), x, y+(i*spacing));
		}
	}
	
	public static Color getContrastColor(Color color) {
		  double y = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;
		  return y >= 128 ? Color.black : Color.white;
		}
	 
	
	// Animation Methods
	public void setYSpeed(int amount) {
		ySpeed = amount;
	}

	public int getX() {
		return (int) x;
	}
	
	public int getY() {
		return (int) y;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	public void setX(int y) {
		this.x = y;
	}
	public void setWidth(int y) {
		this.width = y;
	}
	public void setHeight(int y) {
		this.height = y;
	}
	
	
/*
	public void move(int edge) {

		
		y = (int) y - ySpeed;
		
		if(y+(height*2) < 0 ) {
			y = (int) edge + (height*2);
		}
		
		

	}
	*/
	public void drawFire(double nExhaust, Color primaryColor, Color sparkly, Graphics g) {
		Random rand = new Random();
		
		int exhaust = (int) nExhaust;
		
		
		int x = (int) this.x;
		int y = (int) this.y;
		int width = (int) this.width;
		int height = (int) this.height;
		

		
		g.setColor(primaryColor);
		g.fillRect(10, 10, 5, 5);

		int exhaustDrawingNum = rand.nextInt(exhaust);
		
		if(exhaustDrawingNum > 30) {
			int xFirePoints1[] = {x, x+ (width/7), x+ (2*width/7)};
			int xFirePoints2[] = {x + (width/14), x + (width/2), x + (13*width/14)};
			int xFirePoints3[] = {x + (5*width/7), x + (6*width/7), x + width};
			int yFirePoints1[] = {y + height, y + height + (int) ((0.2)*height), y + height};
			int yFirePoints2[] = {y + height, y + height + (int) (0.5 * height), y + height};
			int yFirePoints3[] = {y + height, y + height + (int) (0.2*height), y + height};
			g.fillPolygon(xFirePoints1, yFirePoints1, 3);
			g.fillPolygon(xFirePoints2, yFirePoints2, 3);	
			g.fillPolygon(xFirePoints3, yFirePoints3, 3);		
		}
		
		if(exhaustDrawingNum < 29 && exhaustDrawingNum > 1) {
			int xFirePoints1[] = {x, x+ (width/7), x+ (2*width/7)};
			int xFirePoints2[] = {x + (width/14), x + (width/2), x + (13*width/14)};
			int xFirePoints3[] = {x + (5*width/7), x + (6*width/7), x + width};
			int yFirePoints1[] = {y + height, y + height + (int) ((0.4)*height), y + height};
			int yFirePoints2[] = {y + height, y + height + (int) (1 * height), y + height};
			int yFirePoints3[] = {y + height, y + height + (int) (0.4*height), y + height};
			g.fillPolygon(xFirePoints1, yFirePoints1, 3);
			g.fillPolygon(xFirePoints2, yFirePoints2, 3);	
			g.fillPolygon(xFirePoints3, yFirePoints3, 3);
		}

		

	}
}