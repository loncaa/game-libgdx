package magicpot.hr.model.fundamental;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Entity {


    void render(SpriteBatch sb);
    void update();
    void handleInput();

}
