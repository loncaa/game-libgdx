package magicpot.hr.controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;

/**
 * Created by XXX on 13.10.2015..
 */
public class Resources {
    private HashMap<String, Texture> textures;

    public Resources()
    {
        textures = new HashMap<String, Texture> ();
    }

    public Texture getTexture(String str)
    {
        return textures.get(str);
    }

    public void addTexture(Texture t, String key)
    {
        textures.put(key, t);
    }

    public void dispose()
    {
        for(HashMap.Entry<String, Texture> t : textures.entrySet())
            t.getValue().dispose();
        textures.clear();

    }
}
