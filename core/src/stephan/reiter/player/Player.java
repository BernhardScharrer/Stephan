package stephan.reiter.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import stephan.reiter.world.World;

public class Player {
	
	public static final Vector2 SPAWN = new Vector2(6, 6);
	private static final Vector2 GRAVITY = new Vector2(0, -1);
	
	public Vector2 pos;
	private Vector2 vel;
	private Vector2 acc;
	
	private int jumpCount = 0;
	private boolean lastJumped = false;
	
	private Texture texture;
	
	public Player() {
		this.pos = SPAWN;
		this.vel= new Vector2();
		this.acc= new Vector2(GRAVITY);
		this.texture = new Texture("stephan.png");
	}
	
	public void update(World world) {
	
		vel.mulAdd(acc, 0.3f);
		
		if (vel.len2() < -10f) {
			vel.setLength2(-10f);
		}
		
		if (vel.len2() > 10f) {
			vel.setLength2(10f);
		}
		
		
		pos.mulAdd(vel, 0.02f);
		
		acc.y = GRAVITY.y;
		
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			jump();
			
			lastJumped = true;
		}else {
			if(lastJumped == true) {
				
			}
			lastJumped = false;
		}
		
		lastJumped = isGrounded();
	}

	public Texture getTexture() {
		return texture;
	}
	
	
	public void jump() {
		if(jumpCount <= 30) {
			//jumpCount++;
			System.out.println("hi");
			acc.y = 100;
		}
	}

	private boolean isGrounded() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
