package stephan.reiter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import stephan.reiter.player.Player;
import stephan.reiter.utils.FontLoader;
import stephan.reiter.world.Tile;
import stephan.reiter.world.World;
import stephan.reiter.world.WorldGenerator;

public class StephansGame extends ApplicationAdapter {

	private static final int TIME_PER_FRAME = 16;
	
	private long ago, delta;
	
	private BitmapFont font;
	private SpriteBatch batch;
	private Texture img;
	
	private float size;
	private Vector2 p_pos;
	private World world;
	private Player p;
	
	@Override
	public void create() {
		
		font = FontLoader.loadFont("f1", 100);
		batch = new SpriteBatch();
		
		img = new Texture("berger.png");
		
		world = WorldGenerator.generateWorld();
		p = new Player();
		
		size = ((float) Gdx.graphics.getHeight())/Tile.TILES_ON_SCREEN;
		
		p_pos = new Vector2();
		p_pos.x = Gdx.graphics.getWidth()/3;
		p_pos.y = Gdx.graphics.getHeight()/2;
	}
	
	@Override
	public void render() {
		
		
		ago = System.currentTimeMillis();
		
		clearScreen();

		p.update(world);
		
		batch.begin();
		
		drawWorld();
		drawPlayer();
		
		batch.end();
		
		delta = System.currentTimeMillis() - ago;
		if (delta < TIME_PER_FRAME) {
			try {
				Thread.sleep(TIME_PER_FRAME-delta);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		
	}
	
	private void clearScreen() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(1, 1, 1, 1);
	}

	private void drawPlayer() {
		System.out.println(Gdx.graphics.getWidth()/3);
		batch.draw(p.getTexture(), p_pos.x, p_pos.y, size, size*2);
	}

	private void drawWorld() {
		
		for (int y = 0; y < World.WORLD_SIZE_Y; y++) {
			for (int x = 0; x < World.WORLD_SIZE_X; x++) {
				if (world.getTile(x, y).isGrounded()) {
					batch.draw(img, (x-p.pos.x)*size+p_pos.x, (y-p.pos.y)*size+p_pos.y, size, size);
				}
			}
		}
		
	}

	@Override
	public void dispose() {
		font.dispose();
		batch.dispose();
	}
	
}
