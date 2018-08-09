package stephan.reiter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import stephan.reiter.StephansGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1920;
		config.height = 1080;
		config.fullscreen = true;
		config.vSyncEnabled = true;
		config.title = "Platformer Testt Engine (c) Bernhard Scharrer";
		new LwjglApplication(new StephansGame(), config);
	}
}
