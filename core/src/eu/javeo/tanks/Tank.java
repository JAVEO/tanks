package eu.javeo.tanks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Tank extends GameObject {

    private final Sprite sprite;

    public Tank(Texture tankTexture) {
        sprite = new Sprite(tankTexture);
    }

    public void init() {

    }
}
