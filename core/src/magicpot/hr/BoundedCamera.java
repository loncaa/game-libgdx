package magicpot.hr;

import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Created by XXX on 28.9.2015..
 */
public class BoundedCamera extends OrthographicCamera {
    private float minWidthBound;
    private float maxWidthBound;
    private float minHeightBound;
    private float maxHeightBound;

    private float padding = 10;

    public BoundedCamera(){
        super();
    }

    public void setBounds(float minwb, float minhb, float maxwb, float maxhb)
    {
        minWidthBound = minwb;
        maxWidthBound = maxwb;

        minHeightBound = minhb;
        maxHeightBound = maxhb;
    }

    public void setPosition(float x, float y){
        if(x > minWidthBound && x < maxWidthBound)
            position.x = x;
        else
            if(x <= minWidthBound)
                position.x = minWidthBound;
            else if(x >= maxWidthBound)
                position.x = maxWidthBound;

        /*
        if(y > 0 && y < frameHeight - padding)
            position.y = y;
        else
            if(y < 0)
                position.y = 0 + padding;
            else if(y > frameHeight - padding)
                position.y = frameHeight;
                */
    }
}
