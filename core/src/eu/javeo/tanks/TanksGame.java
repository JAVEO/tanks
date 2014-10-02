package eu.javeo.tanks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class TanksGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    Stage stage;
    private Texture tankTexture;
    private Tank tank;

    @Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
        loadTexture();
        tank = new Tank(tankTexture);

        StretchViewport viewport = new StretchViewport(800, 480);
        stage = new Stage(viewport, batch);
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(tankTexture, 0, 0);
		batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
	}

    private void loadTexture() {

        tankTexture = new Texture("tank.png");
    }
}
