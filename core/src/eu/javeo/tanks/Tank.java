package eu.javeo.tanks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tank extends GameObject {
    private Animation explosionAnimation;
    private final Sprite sprite;

    public Tank(Texture tankTexture, Animation explosionAnimation, SpriteBatch batch) {
        this.batch = batch;
        this.explosionAnimation = explosionAnimation;
        sprite = new Sprite(tankTexture);
    }

    @Override
    public void init() {

    }

    @Override
    public void draw() {
        explode();
    }

    private void explode() {
        Explosion explosion = new Explosion(explosionAnimation, sprite.getX(), sprite.getY());
        explosion.draw(batch);
    }
}
