package stephan.reiter.world;

public class World {
	
	public static final int WORLD_SIZE_X = 100;
	public static final int WORLD_SIZE_Y = 20;
	
	private Tile[][] tiles = new Tile[WORLD_SIZE_X][WORLD_SIZE_Y];
	
	public World(Tile[][] tiles) {
		this.tiles = tiles;
	}
	
	public Tile getTile(int x, int y) {
		return tiles[x][y];
	}
	
	public Tile[][] getTiles() {
		return tiles;
	}
	
}
