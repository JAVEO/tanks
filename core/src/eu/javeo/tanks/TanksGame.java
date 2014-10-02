package eu.javeo.tanks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class TanksGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    Stage stage;
    OrthographicCamera camera;
    OrthoCachedTiledMapRenderer tiledMapRenderer;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");


        StretchViewport viewport = new StretchViewport(480, 320);
        stage = new Stage(viewport, batch);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        TiledMap tiledMap = new TmxMapLoader().load("levels/level1.tmx");
        OrthoCachedTiledMapRenderer tiledMapRenderer = new OrthoCachedTiledMapRenderer(tiledMap);
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();

        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
	}
}
