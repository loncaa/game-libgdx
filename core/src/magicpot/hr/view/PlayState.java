package magicpot.hr.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.ArrayList;
import java.util.List;

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

    private Player img;
    private BoundedCamera playerCamera;

    private GlyphLayout testLayout;
    private BitmapFont font;

    //bolje je koristiti queue!
    private List<StringBlock> blockList;
    private int capacity = 5;
    private float blockWidth;

    public PlayState(StateManager manager)
    {
        super(manager);
        img = new Player(0, 0, 32, 32);

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

        blockList = new ArrayList<StringBlock>(capacity);
        blockWidth = GameVariables.WIDTH / capacity;
        for(int i = 0; i < capacity; i++)
            blockList.add(new StringBlock(playerCamera, font, (blockWidth)*i + blockWidth/2, 25));

    }

    @Override
    public void update() {

        playerCamera.update();

        img.update();
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
        {
            img.startExplosion();
        }

        if(img.isExplosionEnded())
        {
            State ps = manager.getPreviousState();
            if(ps != null)
                manager.pushState(ps);
            else
                manager.pushState(new PauseState(manager));

            img.explosionEnded();
        }

        for(StringBlock b : blockList)
            b.update();
    }

    @Override
    public void render(SpriteBatch batch) {

        batch.setProjectionMatrix(playerCamera.combined);
        batch.begin();
        img.render(batch);
        playerCamera.setPosition(img.getPosx(), img.getPosy());

        for(StringBlock b : blockList)
            b.render(batch);

        batch.end();

        batch.setProjectionMatrix(textCam.combined);
        batch.begin();
        font.draw(batch, testLayout, 10, Gdx.graphics.getHeight() - testLayout.height);
        batch.end();
    }
}
