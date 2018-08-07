package stephan.reiter;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Tube {
	
	private int index;
	private double gap,y;
	private Rectangle top, bottom;
	
	private static int count = 0;
	private static final Random RANDOM = new Random();
	private static final double TUBE_WIDTH = 0.07;
	public static final double SPACE_BETWEEN = 0.45;
	
	public Tube() {
		index = count++;
		gap = 0.4 - Math.min(index*0.1, 0.1);
		y = 0.1+RANDOM.nextFloat()*0.4;
	}
	
	public void update(ShapeRenderer drawer, int t) {
		drawer.setColor(0, 1, 0, 1);
		
		top = new Rectangle((float) (SPACE_BETWEEN*Gdx.graphics.getWidth()*(index+1)-t), 
				(float) (Gdx.graphics.getHeight()*(y+gap)), 
				(float) (Gdx.graphics.getWidth()*TUBE_WIDTH), 
				(float) Gdx.graphics.getHeight());
		
		bottom = new Rectangle((float) (SPACE_BETWEEN*Gdx.graphics.getWidth()*(index+1)-t), 
				0, 
				(float) (Gdx.graphics.getWidth()*TUBE_WIDTH), 
				(float) (Gdx.graphics.getHeight()*y));
		
		drawer.rect(bottom.x,bottom.y,bottom.width,bottom.height);
		drawer.rect(top.x,top.y,top.width,top.height);
		
		drawer.setColor(0,0.8f,0,1);
		drawer.rect(bottom.x-10, bottom.height-15, bottom.getWidth()+20, 15);
		drawer.rect(top.x-10, top.y, top.width+20, 15);
		
	}

	public int getIndex() {
		return index;
	}

	public double getGap() {
		return gap;
	}

	public double getY() {
		return y;
	}

	public Rectangle getBottomTube() {
		return bottom;
	}
	
	public Rectangle getTopTube() {
		return top;
	}

}
