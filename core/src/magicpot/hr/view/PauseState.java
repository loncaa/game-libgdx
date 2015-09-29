package magicpot.hr.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import magicpot.hr.controller.State;
import magicpot.hr.controller.StateManager;

public class PauseState extends State {
    private BitmapFont font;
    private GlyphLayout layout;

    public PauseState(StateManager manager) {
        super(manager);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/prstart.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 20;
        parameter.borderWidth = 2;
        parameter.borderColor = Color.CORAL;
        font = generator.generateFont(parameter);

        generator.dispose();

        layout = new GlyphLayout();
        layout.setText(font, "Pause");
    }

    @Override
    public void update() {

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            manager.popState();
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        manager.renderPrevious(batch);

        batch.setProjectionMatrix(textCam.combined);
        batch.begin();
        font.draw(batch, layout, (Gdx.graphics.getWidth() - layout.width )/ 2, (Gdx.graphics.getHeight() + layout.height)/2);
        batch.end();
    }
}
