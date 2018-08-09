package stephan.reiter.world;

public class Tile {
	
	public static final int TILES_ON_SCREEN = 15;
	
	private boolean ground;
	
	public Tile(boolean ground) {
		this.ground = ground;
	}

	public boolean isGrounded() {
		return ground;
	}
	
}
