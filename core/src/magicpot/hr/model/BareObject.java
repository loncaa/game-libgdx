package magicpot.hr.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class BareObject {

    private boolean facingRight = true;
    private float posx;
    private float posy;
    private float width;
    private float height;

    public BareObject(float x, float y, int w, int h){
        posx = x;
        posy = y;
        width = w;
        height = h;
    }
    public BareObject(int w, int h){
        width = w;
        height = h;
    }

    protected void setPosition(float x, float y){
        posx = x;
        posy = y;
    }
    public void setPosx(float x){posx = x;}
    public void setPosy(float y){posy = y;}
    public float getPosx(){return posx;}
    public float getPosy(){return posy;}

    public void setWidth(float w){ width = w;}
    public void setHeight(float h){height = h;}
    public float getWidth(){return width;}
    public float getHeight(){return height;}

    public void setFacingRight(boolean facingRight){this.facingRight = facingRight;}
    protected boolean getFacingRight(){return facingRight;}

    public abstract void update();
    public abstract void render(SpriteBatch sb);
}
