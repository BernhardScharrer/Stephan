package stephan.reiter.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import stephan.reiter.world.Tile;
import stephan.reiter.world.World;

public class Player {
	
	private static final float HIT_BOX_TOLERANCE = 0.001f;
	
	public static final Vector2 SPAWN = new Vector2(6, 6);
	private static final Vector2 GRAVITY = new Vector2(0, -1f);
	
	public Vector2 pos;
	private Vector2 vel;
	private Vector2 acc;
	private Rectangle hit_box_x;
	private Rectangle hit_box_y;
	private Rectangle current_box;
	
	private boolean grounded = false;
	
	private Texture texture;
	
	public Player() {
		this.pos = SPAWN;
		this.vel= new Vector2();
		this.acc= new Vector2(GRAVITY);
		this.texture = new Texture("stephan.png");
		this.hit_box_x = new Rectangle();
		this.hit_box_y = new Rectangle();
		this.current_box = new Rectangle(0,0,1,1);

	}
	
	public void update(World world) {

		acc.x = 0;
		acc.y = GRAVITY.y;
		vel.x *= grounded ? 0.85f : 0.99f;
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) jump();
		if(Gdx.input.isKeyPressed(Input.Keys.A)) acc.x = -100;
		if(Gdx.input.isKeyPressed(Input.Keys.D)) acc.x = 100;
		
		
		vel.x += acc.x * 0.3f;
		vel.y += acc.y * 0.3f;
		
		if (vel.x > 5) vel.x = 5;
		if (vel.y > 5) vel.y = 5;
		if (vel.x < -5) vel.x = -5;
		if (vel.y < -5) vel.y = -5;
		
		
		collides(world);
	}

	public Texture getTexture() {
		return texture;
	}
	
	
	public void jump() {
//		if (!grounded) air_time++;
//		if(air_time <= 15) {
			acc.y = 100;
//		}
	}
	
	private void collidesX(World world) {
		if (vel.x<0) {
			hit_box_x.x = pos.x-HIT_BOX_TOLERANCE+vel.x*0.02f;
			hit_box_x.y = pos.y+0.1f+vel.y*0.02f;
			hit_box_x.width = HIT_BOX_TOLERANCE;
			hit_box_x.height = 1.8f;
		} else {
			hit_box_x.x = pos.x+1+vel.x*0.02f;
			hit_box_x.y = pos.y+0.1f+vel.y*0.02f;
			hit_box_x.width = HIT_BOX_TOLERANCE;
			hit_box_x.height = 1.8f;
		}
	}
	
	private void collidesY(World world) {
		if (vel.y<0) {
			hit_box_y.x = pos.x+0.1f+vel.x*0.02f;
			hit_box_y.y = pos.y-HIT_BOX_TOLERANCE+vel.y*0.02f;
			hit_box_y.width = 0.8f;
			hit_box_y.height = HIT_BOX_TOLERANCE;
		} else {
			hit_box_y.x = pos.x+0.1f+vel.x*0.02f;
			hit_box_y.y = pos.y+2+vel.y*0.02f;
			hit_box_y.width = 0.8f;
			hit_box_y.height = HIT_BOX_TOLERANCE;
		}		
	}
	
	private boolean collides(World world) {
		
		Tile current;
		
		collidesX(world);
		collidesY(world);
		
		for (int y = 0; y < World.WORLD_SIZE_Y; y++) {
			for (int x = 0; x < World.WORLD_SIZE_X; x++) {
				current = world.getTile(x, y);
				if (current != null && current.isSolid()) {
					current_box.x = x;
					current_box.y = y;
					
					if (hit_box_x.overlaps(current_box))  vel.x = 0;
					if (hit_box_y.overlaps(current_box)) {
						if (vel.y < 0) grounded = true;
						vel.y = 0;
					}
				}
			}
		}
		
		if (vel.y != 0) grounded = false;
		pos.x += vel.x*0.02f;
		pos.y += vel.y*0.02f;
		
		return false;
	}
	
}
