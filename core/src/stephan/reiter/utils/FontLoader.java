package stephan.reiter.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class FontLoader {
	
	public static BitmapFont loadFont(String font, int size) {
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/"+font+".ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = size;
		BitmapFont fnt = generator.generateFont(parameter);
		generator.dispose();
		
		return fnt;
		
	}
	
	public static BitmapFont loadFont(String font, FreeTypeFontParameter param) {
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/"+font+".ttf"));
		BitmapFont fnt = generator.generateFont(param);
		generator.dispose();
		
		return fnt;
		
	}
	
}
