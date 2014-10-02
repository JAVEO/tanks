package eu.javeo.tanks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class TanksGame extends ApplicationAdapter {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 480;

    private SpriteBatch batch;
    private Stage stage;
    private Missile missile;

    private Texture explosionTexture;
    private Texture tankTexture;
    private Texture missileTexture;

    private Tank tank;

	@Override
	public void create () {
		batch = new SpriteBatch();
        loadTextures();
        tank = new Tank(tankTexture, createExplosionAnimation(), batch);
        fireMissile();
        StretchViewport viewport = new StretchViewport(480, 320);
        stage = new Stage(viewport, batch);
    }

	@Override
	public void render () {
		batch.begin();
        missile.draw();
        tank.draw();
		batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
	}

    private void fireMissile() {
        missile = new Missile(missileTexture, new Vector2(50f,50f), new Vector2(0f,1f), batch);
    }

    private void loadTextures() {
        tankTexture = new Texture("tank.png");
        missileTexture = new Texture("missile.png");
        explosionTexture = new Texture("explosion.png");
    }

    private Animation createExplosionAnimation() {
        TextureRegion[][] explosionFrames = TextureRegion.split(explosionTexture, 134, 134);
        return new Animation(0.1f, explosionFrames[0]);
    }
}
