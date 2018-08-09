package stephan.reiter.world;

public class WorldGenerator {
	
	public static World generateWorld() {
		Tile[][] tiles = new Tile[World.WORLD_SIZE_X][World.WORLD_SIZE_Y];
		
		for (int y = 0; y < World.WORLD_SIZE_Y; y++) {
			for (int x = 0; x < World.WORLD_SIZE_X; x++) {
				
				boolean ground;
				
				if (y <= 4) ground = true;
				else ground = false;
				
				tiles[x][y] = new Tile(ground);
				
			}
		}
		
		return new World(tiles);
		
	}
	
}
