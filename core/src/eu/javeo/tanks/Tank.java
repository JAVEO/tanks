package eu.javeo.tanks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tank extends GameObject {

    private final Sprite sprite;

    public Tank(Texture tankTexture) {
        sprite = new Sprite(tankTexture);
        sprite.setY(0);
        sprite.setX(0);
    }

    public void init() {

    }

    public void draw(SpriteBatch batch) {
        sprite.setY(sprite.getY() + 0.1f);
        sprite.draw(batch);
    }
}
