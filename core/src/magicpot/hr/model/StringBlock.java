package magicpot.hr.model;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import magicpot.hr.GameVariables;
import magicpot.hr.model.fundamental.GameObject;
import magicpot.hr.view.MyGame;

public class StringBlock extends GameObject {
    private static int ID = 0;
    private static float commonYPosition = 0;
    private int blockID;

    private float sizeOfLight = 64;

    BitmapFont font;

    public StringBlock(OrthographicCamera cam, BitmapFont bitmapFont, float x, float y)
    {
        super(cam, x, y, 32, 32);

        font = bitmapFont;
        blockID = ID;

        ID++;
        commonYPosition = y;
    }

    public void setY(){
        boolean isUP = MyGame.getRandomBoolean();

        if(isUP)
        {
            this.y = commonYPosition + this.width >= GameVariables.HEIGHT ? commonYPosition : commonYPosition + width;
        }
        else
        {
            this.y = commonYPosition - width <= 0 ? commonYPosition : commonYPosition - width;
        }

        commonYPosition = y;
    }

    @Override
    public void render(SpriteBatch sb) {

        font.draw(sb, "" + blockID, x, y);

    }

    @Override
    public void update() {
    }

    @Override
    public void handleInput() {

    }

    public void drawFBO(SpriteBatch batch, Texture t)
    {
        batch.draw(t,
                x - sizeOfLight / 2 + font.getXHeight()/2,
                y - sizeOfLight / 2 - font.getXHeight()/2,
                sizeOfLight, sizeOfLight);
    }
}
