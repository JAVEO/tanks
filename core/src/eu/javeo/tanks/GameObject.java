package eu.javeo.tanks;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;

public abstract class GameObject {

    protected Sprite sprite;
    protected TiledMap map;

    public abstract void init();
    public void draw(SpriteBatch batch) {}

    public boolean collidesWith(GameObject another) {
        return sprite.getBoundingRectangle().overlaps(another.sprite.getBoundingRectangle());
    }
}
