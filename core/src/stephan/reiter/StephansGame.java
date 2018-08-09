package stephan.reiter;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class StephansGame extends ApplicationAdapter {
	
	private SpriteBatch batch;
	private ShapeRenderer drawer;
	private BitmapFont font;
	private int t;
	private FlappyBird player;
	private List<Tube> tubes = new ArrayList<Tube>();
	private String score;
	private boolean game_over = false;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		drawer = new ShapeRenderer();
		player = new FlappyBird();
		font = FontUtils.load("Starlight", 24);
		
		for (int n = 0; n<30;n++) {
			tubes.add(new Tube());
		}
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.6f, 0.85f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (!game_over) t+=2;
		
		drawer.begin(ShapeType.Filled);
		drawer.setColor(Color.RED);
		//drawer.rect(player.getPosition().x, player.getPosition().y, player.getPosition().width, player.getPosition().height);
		for (Tube tube : tubes) tube.update(drawer, t);
		drawer.end();
		batch.begin();
		player.update(batch, t);
		score = "Score: "+(int)((t+(Gdx.graphics.getWidth()*0.15f))/(Gdx.graphics.getWidth()*Tube.SPACE_BETWEEN));
		font.draw(batch, score, Gdx.graphics.getWidth()*0.57f, Gdx.graphics.getHeight()*0.95f);
		batch.end();
		
		if (!game_over && player.isDead(tubes, t)) {
			game_over  = true;
		}
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		drawer.dispose();
		player.dispose();
	}
}
