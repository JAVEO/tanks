package eu.javeo.tanks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class TanksGame extends ApplicationAdapter {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 480;

	SpriteBatch batch;
	Texture img;
    Stage stage;
    private Texture tankTexture;
    private Tank tank;
    private Touchpad touchpad;

    @Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
        loadTexture();
        tank = new Tank(tankTexture);
        touchpad = createTouchpad();

        StretchViewport viewport = new StretchViewport(SCREEN_WIDTH, SCREEN_HEIGHT);
        stage = new Stage(viewport, batch);
        stage.addActor(touchpad);
        Gdx.input.setInputProcessor(stage);
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        tank.update(touchpad.getKnobPercentX(), touchpad.getKnobPercentY());
        batch.begin();
        tank.draw(batch);
		batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
	}

    private void loadTexture() {
        tankTexture = new Texture("tank.png");
    }

    private Touchpad createTouchpad() {
        Skin touchpadSkin = new Skin();
        touchpadSkin.add("touchBackground", new Texture("analog_stick_bg.png"));
        touchpadSkin.add("touchKnob", new Texture("analog_stick_knob.png"));
        Touchpad.TouchpadStyle touchpadStyle = new Touchpad.TouchpadStyle();
        touchpadStyle.background = touchpadSkin.getDrawable("touchBackground");
        touchpadStyle.knob = touchpadSkin.getDrawable("touchKnob");
        touchpad = new Touchpad(10, touchpadStyle);
        touchpad.setBounds(15, 15, 120, 120);
        return touchpad;
    }
}
