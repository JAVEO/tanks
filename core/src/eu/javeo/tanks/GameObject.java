package eu.javeo.tanks;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 * Created by pstepniewski on 02.10.14.
 */
public abstract class GameObject {

    protected Sprite sprite;

    public abstract void init();
    public void draw() {}

    public boolean collidesWith(GameObject another) {
        return sprite.getBoundingRectangle().overlaps(another.sprite.getBoundingRectangle());
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
