package stephan.reiter.world;

public class Tile {
	
	public static final int TILES_ON_SCREEN = 15;
	
	private int tile_x;
	private int tile_y;
	private boolean solid;
	
	public Tile(int tile_x, int tile_y, boolean solid) {
		this.tile_x = tile_x;
		this.tile_y = tile_y;
		this.solid = solid;
	}
	public int getTile_x() {
		return tile_x;
	}
	public int getTile_y() {
		return tile_y;
	}
	public boolean isSolid() {
		return solid;
	}
	
}
