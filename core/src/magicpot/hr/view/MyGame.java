package magicpot.hr.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import magicpot.hr.GameVariables;
import magicpot.hr.controller.Resources;
import magicpot.hr.controller.StateManager;

public class MyGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private StateManager manager;
	private static ShaderProgram program;
	public static TextureAtlas textureAtlas;

	private static Resources res;

	@Override
	public void create () {

		ShaderProgram.pedantic = false;
		program = new ShaderProgram(
				Gdx.files.internal("shaders/vertex.glsl").readString() ,
				Gdx.files.internal("shaders/fragment.glsl").readString());
		System.out.print(program.isCompiled() ? "Compiled!" : program.getLog());

		//initShaderProgramVariables();


		batch = new SpriteBatch();
		//batch.setShader(program);

		res = new Resources();
		res.addTexture(new Texture(Gdx.files.internal("textures/light.png")), "light");
		textureAtlas = new TextureAtlas(Gdx.files.internal("pack/gamepack.pack"));

		manager = new StateManager();
		manager.pushState(new PlayState(manager));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(131 / 255f, 142 / 255f, 102 / 255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		manager.update();
		manager.render(batch);
	}

	public static ShaderProgram getShaderProgram()
	{
		return program;
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

		program.dispose();
		textureAtlas.dispose();
		batch.dispose();
	}

	private void initShaderProgramVariables()
	{
		program.begin();
		program.setUniformf("u_res", GameVariables.WIDTH, GameVariables.HEIGHT);
		program.end();
	}
}
