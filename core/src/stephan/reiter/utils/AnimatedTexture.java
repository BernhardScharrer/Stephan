package stephan.reiter.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by bernh on 06.07.2017.
 */

public class AnimatedTexture {

    private Animation<TextureRegion> animation;
    private float time;
    private boolean running;

    public AnimatedTexture(Animation<TextureRegion> animation) {
        this.animation = animation;
        this.running = false;
    }

    public void start() {
        time = 0;
        running = true;
    }

    public void stop() {
        running = false;
        time = 0;
    }

    public void resume() {
        running = true;
    }

    public void pause() {
        running = false;
    }

    public void reset() {
        time = 0;
    }

    public void update() {
        if (running) time += Gdx.graphics.getDeltaTime();
    }

    public TextureRegion getTexture() {
        return animation.getKeyFrame(time, true);
    }

}
