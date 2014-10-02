package eu.javeo.tanks;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Explosion {
    private Animation animation;
    private float animationTime;
    private float posX;
    private float posY;

    public Explosion(Animation explosionAnimation, float x, float y) {
        this.animation = explosionAnimation;
        this.posX = x;
        this.posY = y;
        this.animationTime = 0f;
    }

    public void draw(SpriteBatch batch) {
        animationTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = animation.getKeyFrame(animationTime, true);
        batch.draw(currentFrame, posX, posY);
    }

    public boolean isFinished() {
        return animation.isAnimationFinished(animationTime);
    }
}
