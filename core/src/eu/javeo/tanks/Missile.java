package eu.javeo.tanks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;


public class Missile extends GameObject {
    private final static float SPEED = 7f;
    private Sprite sprite;
    private Vector2 direction;
    private SpriteBatch batch;

    public Missile(Texture texture, Vector2 position, Vector2 direction, SpriteBatch batch, GameObjectsManager gameObjectsManager) {
        this.batch = batch;
        this.direction = direction;
        this.sprite = new Sprite(texture);
        this.sprite.setPosition(position.x, position.y);
    }

    @Override
    public void init() {}

    public void draw() {
        update();
        sprite.draw(batch);
    }

    public boolean isOutOfBounds() {
        return sprite.getX() < 0 || sprite.getX() > TanksGame.SCREEN_WIDTH
            || sprite.getY() < 0 || sprite.getY() > TanksGame.SCREEN_HEIGHT;
    }

    public Sprite getSprite() {
        return sprite;
    }

    private void update() {
        sprite.translate(direction.x * SPEED, direction.y * SPEED);
    }
}
