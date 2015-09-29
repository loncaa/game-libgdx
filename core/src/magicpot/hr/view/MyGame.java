package magicpot.hr.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.logging.FileHandler;

import magicpot.hr.controller.StateManager;

public class MyGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private StateManager manager;
	public static TextureAtlas textureAtlas;


	@Override
	public void create () {
		batch = new SpriteBatch();

		textureAtlas = new TextureAtlas(Gdx.files.internal("pack/gamepack.pack"));

		manager = new StateManager();
		manager.pushState(new PlayState(manager));

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(131/255f, 142/255f, 102/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		manager.update();
		manager.render(batch);
	}
}
