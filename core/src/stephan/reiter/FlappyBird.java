package stephan.reiter;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class FlappyBird {
	
	private double y = 0.5f;
	private double velocity = UPPER_CAP;
	private Texture player_image;
	private Rectangle pos;
	private Sprite sprite;
	
	private static final double UPPER_CAP = 1.0;
	private static final double LOWER_CAP = -1.0;
	
	public FlappyBird() {
		player_image = new Texture("flappy.png");
		sprite = new com.badlogic.gdx.graphics.g2d.Sprite(player_image);
	}
	
	public void update(SpriteBatch drawer, int t) {
		
		pos = new Rectangle((float) (Gdx.graphics.getWidth()*0.15),
				(float) (Gdx.graphics.getHeight()*y),
				(float) (Gdx.graphics.getHeight()*0.1),
				(float) (Gdx.graphics.getHeight()*0.1));
		
		sprite.setOrigin(sprite.getWidth()/2,sprite.getHeight()/2);
		sprite.setRotation((float) getRotation());
		sprite.setPosition(pos.x-pos.width*0.1f, pos.y-pos.height*0.1f);
		sprite.setSize(pos.width*1.2f, pos.height*1.2f);
		sprite.draw(drawer);
//		drawer.draw(player_image, pos.x, pos.y, 0, 0, pos.getWidth(), pos.getHeight(), pos.getWidth(), pos.getHeight(), 45f, 0, 0, (int)pos.getWidth(), (int)pos.getHeight(), false, false);
		
        if(velocity < LOWER_CAP/2 && Gdx.input.isKeyPressed(Input.Keys.SPACE)) velocity=UPPER_CAP;
        velocity-=0.04f;
        velocity = (float) Math.max(LOWER_CAP, velocity);
        
        y+=velocity*0.012;
        
        if (y <= 0) y = 0;
	}
	
	private double getRotation() {
		return 120.0 * (velocity / Math.abs(UPPER_CAP-LOWER_CAP));
	}
	
	public boolean isDead(List<Tube> tubes, int t) {
		for (Tube tube : tubes) {
			if (pos.overlaps(tube.getBottomTube()) || pos.overlaps(tube.getTopTube()) || pos.y < 0) {
				return true;
			}
		}
		return false;
	}

	public void dispose() {
		player_image.dispose();
	}
	
	public Rectangle getPosition() {
		return pos;
	}
	
}
