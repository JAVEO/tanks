package eu.javeo.tanks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Tank extends GameObject {
    private static final float SPEED = 4f;
    private final Sprite sprite;

    public Tank(Texture tankTexture) {
        sprite = new Sprite(tankTexture);
        sprite.setY(0);
        sprite.setX(0);
    }

    public void init() {

    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void update(float touchpadX, float touchpadY) {
        if (Math.abs(touchpadX) > Math.abs(touchpadY)) {
            touchpadY = 0.0f;
        } else {
            touchpadX = 0.0f;
        }
        move(touchpadX, touchpadY);
        rotate(touchpadX, touchpadY);
    }

    private void move(float touchpadX, float touchpadY) {

        Gdx.app.log("MyTag", touchpadX + " " + touchpadY);
        float x = (sprite.getX() + touchpadX * SPEED + sprite.getBoundingRectangle().getWidth() / 2) % TanksGame.SCREEN_WIDTH;
        float y = (sprite.getY() + touchpadY * SPEED + sprite.getBoundingRectangle().getHeight() / 2) % TanksGame.SCREEN_HEIGHT;
        if (x < 0) x += TanksGame.SCREEN_WIDTH;
        if (y < 0) y += TanksGame.SCREEN_HEIGHT;
        sprite.setX(x - sprite.getBoundingRectangle().getWidth() / 2);
        sprite.setY(y - sprite.getBoundingRectangle().getHeight() / 2);
    }


    private void rotate(float touchpadX, float touchpadY) {
        float rotation = (float) Math.atan2(touchpadX, touchpadY) * MathUtils.radiansToDegrees;
        sprite.setRotation(-rotation);
    }
}
