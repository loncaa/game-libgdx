package magicpot.hr.model.fundamental;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameObject implements Entity {

    protected OrthographicCamera camera;
    protected float x,y;
    protected float width, height;

    public GameObject(OrthographicCamera c, float x, float y, float width, float height)
    {
        camera = c;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void render(SpriteBatch sb) {

    }

    @Override
    public void update() {

    }

    @Override
    public void handleInput() {

    }

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y)
    {
        this.y = y;
    }
}
