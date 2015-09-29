package magicpot.hr.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import magicpot.hr.view.MyGame;

/**
 * Created by XXX on 24.9.2015..
 */
public class Player extends BareObject {

    private float time = 0;
    private int maxJump = 80;
    private boolean peek =false;
    private boolean inTheAir = false;
    private float grav = 2.5f;

    private TextureRegion jump;
    private Animation run, idle, explosion;
    private TextureRegion currentFrame;

    private boolean isExplosionStarted = false;
    private boolean isExplosionEnded = false;
    private float explosionTime = 0;

    public Player(float x, float y, int w, int h) {
        super(x, y, w, h);

        jump = new TextureRegion();
        jump.setRegion(MyGame.textureAtlas.findRegion("n_jump"));

        setIdleTextureRegions(1 / 10f,
                MyGame.textureAtlas.findRegion("n_run1"),
                MyGame.textureAtlas.findRegion("n_run2"));

        setRunTextureRegions(1 / 10f,
                MyGame.textureAtlas.findRegion("n_run3"),
                MyGame.textureAtlas.findRegion("n_run4"),
                MyGame.textureAtlas.findRegion("n_run5"));

        setExplosionTextureRegions(1/10f,
                MyGame.textureAtlas.findRegion("spr_smoke_0"),
                MyGame.textureAtlas.findRegion("spr_smoke_1"),
                MyGame.textureAtlas.findRegion("spr_smoke_2"),
                MyGame.textureAtlas.findRegion("spr_smoke_3"),
                MyGame.textureAtlas.findRegion("spr_smoke_4"));

        setFacingRight(true);

        currentFrame = new TextureRegion();
        currentFrame.setRegion(MyGame.textureAtlas.findRegion("n_run1"));
    }

    private void setRunTextureRegions(float speed, TextureRegion... txr) {
        run = new Animation(speed, txr);
        run.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    private void setIdleTextureRegions(float speed, TextureRegion... txr) {
        idle = new Animation(speed, txr);
        idle.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    private void setExplosionTextureRegions(float speed, TextureRegion... txr) {
        explosion = new Animation(speed, txr);
        explosion.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    }

    public void startExplosion(){
        isExplosionStarted = true;}

    public boolean isExplosionEnded(){return isExplosionEnded;}
    public void explosionEnded(){isExplosionEnded = false;}

    private void handleInput(){
        if(!isExplosionStarted)
        {
            if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            {
                setFacingRight(false);
                currentFrame.setRegion(run.getKeyFrame(time));
                setPosx(getPosx()-3);
            }else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            {
                setFacingRight(true);
                currentFrame.setRegion(run.getKeyFrame(time));
                setPosx(getPosx()+3);
            }else
            {
                currentFrame.setRegion(idle.getKeyFrame(time));
            }

            if(Gdx.input.isKeyPressed(Input.Keys.UP))
            {
                inTheAir = true;
            }

            if(inTheAir)
            {
                currentFrame.setRegion(jump);

                if(getPosy() < maxJump && !peek)
                    setPosy(getPosy() + grav);
                else if(getPosy() > 0 && peek)
                    setPosy(getPosy() - grav);
                else if(getPosy() >= maxJump)
                    peek = true;
                else if(getPosy() <= 0)
                {
                    peek = false;
                    inTheAir = false;
                }
            }
        }
        else {
            explosionTime += Gdx.graphics.getDeltaTime();
            currentFrame.setRegion(explosion.getKeyFrame(explosionTime));

            int index = explosion.getKeyFrameIndex(explosionTime);
            if(index == 4)
            {
                currentFrame.setRegionWidth(0);
                currentFrame.setRegionHeight(0);

                isExplosionEnded = true;
                isExplosionStarted = false;

                explosionTime = 0;
            }
        }
    }

    @Override
    public void update() {
        time += Gdx.graphics.getDeltaTime();
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        if(getFacingRight())
            sb.draw(currentFrame, getPosx(), getPosy(), getWidth(), getHeight());
        else
            sb.draw(currentFrame, getPosx() + getWidth(), getPosy(), -getWidth(), getHeight());

    }
}
