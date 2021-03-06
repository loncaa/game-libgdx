package magicpot.hr.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import java.util.LinkedList;
import java.util.Queue;

import magicpot.hr.BoundedCamera;
import magicpot.hr.GameVariables;
import magicpot.hr.controller.State;
import magicpot.hr.controller.StateManager;
import magicpot.hr.model.Player;
import magicpot.hr.model.StringBlock;

public class PlayState extends State {

    private Player player;
    private BoundedCamera playerCamera;

    private GlyphLayout testLayout;
    private BitmapFont font;
    private FrameBuffer frameBuffer;

    private TextureRegion fboRegion;

    private Queue<StringBlock> blockQueue;
    private int capacity = 6;
    private float blockWidth;
    private float blockPosition = 0f;

    private ShaderProgram ambientShader;

    public PlayState(StateManager manager)
    {
        super(manager);
        player = new Player(0, 0, 32, 32);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prstart.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 15;
        parameter.borderWidth = 1;
        parameter.borderColor = Color.CORAL;
        font = generator.generateFont(parameter);
        generator.dispose();

        testLayout = new GlyphLayout();
        testLayout.setText(font, "Score");

        playerCamera = new BoundedCamera();
        playerCamera.setToOrtho(false, GameVariables.WIDTH, GameVariables.HEIGHT);
        playerCamera.setBounds(GameVariables.WIDTH / 2, 0, GameVariables.WIDTH * 3 - GameVariables.WIDTH / 2, GameVariables.HEIGHT);

        blockQueue = new LinkedList<StringBlock>();
        blockWidth = GameVariables.WIDTH / capacity;
        blockPosition = blockWidth/2;
        for(int i = 0; i < capacity; i++)
            blockQueue.add(new StringBlock(playerCamera, font, (blockWidth) * i + blockWidth / 2, 25));


        frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);

        ambientShader = MyGame.getRessources().getShader("ambient");
    }

    @Override
    public void update() {

        playerCamera.update();
        player.update();

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
        {
            player.startExplosion();
        }

        if(player.isExplosionEnded())
        {
            State ps = manager.getPreviousState();
            if(ps != null)
                manager.pushState(ps);
            else
                manager.pushState(new PauseState(manager));

            player.explosionEnded();
        }

        if(playerCamera.position.x - GameVariables.WIDTH/2  > blockPosition)
        {
            blockPosition += blockWidth;

            StringBlock sb = blockQueue.poll();
            sb.setX(playerCamera.position.x + GameVariables.WIDTH / 2);
            sb.setY();
            blockQueue.add(sb);
        }
    }

    @Override
    public void render(SpriteBatch batch) {

        batch.setShader(ambientShader);
        batch.setProjectionMatrix(playerCamera.combined);
        batch.begin();
        batch.draw(MyGame.getRessources().getTexture("background"), 0, 0, GameVariables.WIDTH * 3, GameVariables.HEIGHT);
        player.render(batch);
        playerCamera.setPosition(player.getPosx(), player.getPosy());
        playerCamera.setMinWidthBound(playerCamera.position.x);
        for(StringBlock b : blockQueue)
            b.render(batch);
        batch.end();

        frameBuffer.begin();
        batch.setShader(null);
        batch.setProjectionMatrix(playerCamera.combined);
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        float lightsize = (float) (player.getWidth()*5);
        batch.draw(MyGame.getRessources().getTexture("light"),
                player.getPosx() - lightsize / 2 + player.getWidth() / 2.3f,
                player.getPosy() - lightsize / 2 + player.getHeight() / 2.3f,
                lightsize, lightsize);

        for(StringBlock b : blockQueue)
            b.drawFBO(batch, MyGame.getRessources().getTexture("light"));
        
        batch.end();
        frameBuffer.end();
        frameBuffer.getColorBufferTexture().bind(1);

        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);

        batch.setShader(null);
        batch.setProjectionMatrix(textCam.combined);
        batch.begin();
        font.draw(batch, testLayout, 10, Gdx.graphics.getHeight() - testLayout.height);
        batch.end();
    }
}
