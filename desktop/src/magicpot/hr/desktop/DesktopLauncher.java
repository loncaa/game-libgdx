package magicpot.hr.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import magicpot.hr.GameVariables;
import magicpot.hr.view.MyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GameVariables.WIDTH * GameVariables.SCALE;
		config.height = GameVariables.HEIGHT * GameVariables.SCALE;
		config.resizable = false;
		new LwjglApplication(new MyGame(), config);
	}
}
