package magicpot.hr.model;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import magicpot.hr.GameVariables;
import magicpot.hr.model.fundamental.Entity;

public class StringBlock implements Entity {
    private OrthographicCamera playerCam;
    private float x, y;
    public static int ID = 0;

    private int blockID;

    BitmapFont font;

    public StringBlock(OrthographicCamera cam, BitmapFont bitmapFont, float x, float y)
    {
        playerCam = cam;
        font = bitmapFont;
        this.x = x;
        this.y = y;

        blockID = ID;
        ID++;
    }

    private void setXPosition(float x)
    {
        this.x = x;
    }

    @Override
    public void render(SpriteBatch sb) {

        font.draw(sb, ""+blockID, x, y);
    }

    @Override
    public void update() {

        if( (playerCam.position.x - GameVariables.WIDTH/2) > x)
        {
            setXPosition(playerCam.position.x + GameVariables.WIDTH/2);
        }

    }

    @Override
    public void handleInput() {

    }
}
