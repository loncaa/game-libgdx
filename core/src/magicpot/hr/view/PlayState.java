package magicpot.hr.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.LinkedList;
import java.util.Queue;

import magicpot.hr.BoundedCamera;
import magicpot.hr.GameVariables;
import magicpot.hr.controller.State;
import magicpot.hr.controller.StateManager;
import magicpot.hr.model.Player;
import magicpot.hr.model.StringBlock;

/**
 * Created by XXX on 27.9.2015..
 */
public class PlayState extends State {

    private Player player;
    private BoundedCamera playerCamera;

    private GlyphLayout testLayout;
    private BitmapFont font;

    private Queue<StringBlock> blockQueue;
    private int capacity = 5;
    private float blockWidth;
    private float blockPosition = 0f;

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
            sb.setXPosition(playerCamera.position.x + GameVariables.WIDTH/2);
            blockQueue.add(sb);

            playerCamera.setMinWidthBound(playerCamera.position.x);

        }
    }

    @Override
    public void render(SpriteBatch batch) {

        batch.setProjectionMatrix(playerCamera.combined);
        batch.begin();
        player.render(batch);
        playerCamera.setPosition(player.getPosx(), player.getPosy());

        for(StringBlock b : blockQueue)
            b.render(batch);

        batch.end();

        batch.setProjectionMatrix(textCam.combined);
        batch.begin();
        font.draw(batch, testLayout, 10, Gdx.graphics.getHeight() - testLayout.height);
        batch.end();
    }
}
