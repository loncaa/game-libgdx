package magicpot.hr.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import magicpot.hr.controller.Resources;
import magicpot.hr.controller.StateManager;

public class MyGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private StateManager manager;
	public static TextureAtlas textureAtlas;
	private static Resources res;

	@Override
	public void create () {
		batch = new SpriteBatch();
		res = new Resources();

		ShaderProgram.pedantic = false;
		res.addShader(new ShaderProgram(
				Gdx.files.internal("shaders/vertex.glsl").readString() ,
				Gdx.files.internal("shaders/fragment.glsl").readString()), "ambient");
		res.addShader(new ShaderProgram(
				Gdx.files.internal("shaders/vertex.glsl").readString() ,
				Gdx.files.internal("shaders/fragment.glsl").readString()), "blur");

		res.addTexture(new Texture(Gdx.files.internal("textures/light2.png")), "light");
		res.addTexture(new Texture(Gdx.files.internal("textures/background.png")), "background");
		textureAtlas = new TextureAtlas(Gdx.files.internal("pack/gamepack.pack"));

		manager = new StateManager();
		manager.pushState(new PlayState(manager));

		initShaderProgramVariables();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		manager.update();
		manager.render(batch);
	}

	public static Resources getRessources()
	{
		return res;
	}

	public static TextureAtlas getTextureAtlas()
	{
		return textureAtlas;
	}

	@Override
	public void dispose() {
		super.dispose();

		res.dispose();
		textureAtlas.dispose();
		batch.dispose();
	}

	private void initShaderProgramVariables()
	{
		res.getShader("ambient").begin();
		res.getShader("ambient").setUniformf("u_ambient", 0f, 0f, 0f, 0.4f);

		res.getShader("ambient").setUniformf("u_resolution", Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		res.getShader("ambient").setUniformi("u_lightmap", 1);
		res.getShader("ambient").end();
	}
}
