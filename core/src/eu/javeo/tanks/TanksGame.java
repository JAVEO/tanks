package eu.javeo.tanks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class TanksGame extends ApplicationAdapter {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 480;

    private SpriteBatch batch;
    private Stage stage;
    private Missile missile;

    private Texture tankTexture;
    private Tank tank;


    private Texture missileTexture;
	
    private OrthographicCamera camera;
    private OrthoCachedTiledMapRenderer tiledMapRenderer;

    @Override
	public void create () {
        batch = new SpriteBatch();
        loadTextures();
        tank = new Tank(tankTexture);
        fireMissile();
        StretchViewport viewport = new StretchViewport(480, 320);
        stage = new Stage(viewport, batch);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

        TiledMap tiledMap = new TmxMapLoader().load("levels/level1.tmx");
        tiledMapRenderer = new OrthoCachedTiledMapRenderer(tiledMap);
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        batch.begin();
        missile.draw();
        batch.draw(tankTexture, 0, 0);
        batch.end();

	}

    private void fireMissile() {
        missile = new Missile(missileTexture, new Vector2(50f,50f), new Vector2(0f,1f), batch);
    }

    private void loadTextures() {
        tankTexture = new Texture("tank.png");
        missileTexture = new Texture("missile.png");
    }
}
