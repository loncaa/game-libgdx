package magicpot.hr.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {

    protected OrthographicCamera textCam;
    protected StateManager manager;

    public State(StateManager sm)
    {
        manager = sm;

        textCam = new OrthographicCamera();
        textCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public abstract void update();
    public abstract void render(SpriteBatch batch);
}
