package magicpot.hr.controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import java.util.HashMap;

/**
 * Created by XXX on 13.10.2015..
 */
public class Resources {
    private HashMap<String, Texture> textures;
    private HashMap<String, ShaderProgram> shaders;

    public Resources()
    {
        textures = new HashMap<String, Texture> ();
        shaders = new HashMap<String, ShaderProgram>();
    }

    public Texture getTexture(String str)
    {
        return textures.get(str);
    }
    public ShaderProgram getShader(String key){return shaders.get(key);}

    public void addTexture(Texture t, String key)
    {
        textures.put(key, t);
    }
    public void addShader(ShaderProgram s, String key){
        String str = s.isCompiled() ? ""+key + " compiled!" : s.getLog();
        System.out.println(str);

        shaders.put(key, s);
    }

    public void dispose()
    {
        for(HashMap.Entry<String, Texture> t : textures.entrySet())
            t.getValue().dispose();
        textures.clear();

        for(HashMap.Entry<String, ShaderProgram> s: shaders.entrySet())
            s.getValue().dispose();
        shaders.clear();
    }
}
