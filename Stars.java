import java.awt.*;
import java.util.Random;

public class Stars {
    

    public Stars() 
    {
        //System.out.println("Particles have been created");
    }
    
    public static void spawnParticles(int posX, int posY, int num, int pWidth, int pHeight, Color particleColor, Graphics graphics)
    {
        graphics.setColor(particleColor);
        for(int i = 0; i<num; i++)
        {
            graphics.drawOval(randInt(posX-1, 0), randInt(posY-1, 0), pWidth, pHeight);
        }
    }
    
    public static void spawnStableParticles(int posX, int posY, int num, int pWidth, int pHeight, Color particleColor, Graphics graphics)
    {
        graphics.setColor(particleColor);
        for(int i = 0; i<num; i++)
        {
            graphics.drawOval(posX, posY, pWidth, pHeight);
        }
    }

    public static int randInt(int max, int min)
    {
        Random ran = new Random();
        return ran.nextInt((max-min) + 1) + min;
    }
    
}