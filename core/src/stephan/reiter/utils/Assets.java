package stephan.reiter.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by bernh on 06.07.2017.
 */

public class Assets {

    /**
     * textures
     */

    /**
     * sprite sheets
     */
	public static TextureRegion[][] tile_sheet;

    /**
     * animated textures
     */

    /**
     * fonts
     */

    /**
     * chunks
     */

    public static void load() {

        //new_game_prof = new Texture("sprites/newgame/prof.png");

        //dialog_choice_5 = splitSheet("sprites/dialog/choice_5.png", 3, 3);

        //dialog_arrow = loadAnimation("sprites/dialog/arrow.png", 2, 2, 0.125f);

    	tile_sheet = splitSheet("tiles.png", 10, 8);
    	
    }

    /**
     * TODO comment
     *
     * @param sprite_sheet
     * @param rows
     * @param cols
     * @return
     */
    public static TextureRegion[][] splitSheet(String sprite_sheet, float rows, float cols) {
        Texture atlas = new Texture(sprite_sheet);
        return TextureRegion.split(atlas, (int)(atlas.getWidth()/cols), (int)(atlas.getHeight()/rows));
    }

    /**
     * load a freetype font file and returns it as BitmapFont
     *
     * @param name represents the name of the file in the fonts folder without .ttf
     * @param size represents the size the font should have (font size is not dynamic!)
     * @return loaded font as BitmapFont
     */
    private static BitmapFont loadFont(String name, int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/"+name+".ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();
        return font;
    }

    /**
     * TODO comment
     *
     * @param sprite_sheet
     * @param rows
     * @param cols
     * @param speed
     * @return texture
     */
    private static AnimatedTexture loadAnimation(String sprite_sheet, int rows, int cols, float speed) {

        TextureRegion[][] tiles = splitSheet(sprite_sheet, rows, cols);

        TextureRegion[] tile_set = new TextureRegion[rows*cols];

        int index = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                tile_set[index++] = tiles[row][col];
            }
        }

        return new AnimatedTexture(new Animation<TextureRegion>(speed, tile_set));

    }

    /**
     * TODO comment
     *
     * @param speed
     * @return texture
     */
    public static AnimatedTexture loadAnimation(TextureRegion[][] atlas, int x, int y, int width, int height, float speed) {

        TextureRegion[] tile_set = new TextureRegion[width*height];

        int index = 0;
        for (int row = x; row < x+width; row++) {
            for (int col = y; col < y+height; col++) {
                tile_set[index++] = atlas[col][row];
            }
        }

        return new AnimatedTexture(new Animation<TextureRegion>(speed, tile_set));

    }

}
