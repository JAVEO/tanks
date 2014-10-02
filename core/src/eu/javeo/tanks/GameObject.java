package eu.javeo.tanks;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by pstepniewski on 02.10.14.
 */
public abstract class GameObject {

    protected SpriteBatch batch;

    public abstract void init();
    public abstract void draw();
}
