package eu.javeo.tanks;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TanksGame extends ApplicationAdapter {
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 480;

    private SpriteBatch batch;
    private Stage stage;

    private Texture explosionTexture;
    private Texture tankTexture;
    private Queue<Tank> tanks = new ConcurrentLinkedQueue<Tank>();
    private Tank tank;
    private Touchpad touchpad;
    private TiledMap tiledMap;

    private Texture missileTexture;

    private Button fireButton;
    private Texture fireButtonTexture;
    private Queue<Missile> missiles = new ConcurrentLinkedQueue<Missile>();
	
    private OrthographicCamera camera;
    private OrthoCachedTiledMapRenderer tiledMapRenderer;
    private WallsCollisionManager wallsCollisionManager;
    private GameObjectsManager gameObjectsManager;

	@Override
	public void create () {
		batch = new SpriteBatch();
        loadTextures();
		tanks.add(new Tank(tankTexture, batch, Tank.ControlType.HUMAN, createExplosionAnimation(), gameObjectsManager));
        tanks.add(new Tank(tankTexture, batch, Tank.ControlType.COMPUTER, createExplosionAnimation(), gameObjectsManager));

		touchpad = createTouchpad();
        StretchViewport viewport = new StretchViewport(SCREEN_WIDTH, SCREEN_HEIGHT);
        stage = new Stage(viewport, batch);
        fireButton = createFireButton();
        stage.addActor(fireButton);
        stage.addActor(touchpad);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();
        tank = humanTank();

        tiledMap = new TmxMapLoader().load("levels/level.tmx");
        wallsCollisionManager = new WallsCollisionManager(tiledMap);
        tiledMapRenderer = new OrthoCachedTiledMapRenderer(tiledMap);
        Gdx.input.setInputProcessor(stage);

        gameObjectsManager = new GameObjectsManager(tiledMap, tanks);
    }

	@Override
	public void render () {

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        for (Tank tank : tanks) {
            tank.update(touchpad.getKnobPercentX(), touchpad.getKnobPercentY());
        }

		batch.begin();
        fireMissile();
        drawMissiles();

        for (Tank tank : tanks) {
            tank.draw();
        }

		batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
	}

    private void drawMissiles() {
        Iterator<Missile> iterator = missiles.iterator();
        while (iterator.hasNext()) {
            Missile missile = iterator.next();
            missile.draw();
            if (missile.isOutOfBounds()) {
                iterator.remove();
            }
        }
    }
    private Button createFireButton() {
        TextureRegionDrawable buttonDrawable = new TextureRegionDrawable(new TextureRegion(fireButtonTexture));
        Button.ButtonStyle btnStyle = new Button.ButtonStyle(buttonDrawable, buttonDrawable, buttonDrawable);
        Button fireButton = new Button(btnStyle);
        fireButton.setBounds(690, 10, 100, 100);
        return fireButton;
    }

    private void fireMissile() {
        if (fireButton.isPressed() && tank.canFire()) {
            Missile missile = new Missile(missileTexture, tank.getPosition(), tank.getDirection(), batch, gameObjectsManager);
            missiles.add(missile);
        }
    }

    private void loadTextures() {
        tankTexture = new Texture("tank.png");
        missileTexture = new Texture("missile.png");
        explosionTexture = new Texture("explosion.png");
        fireButtonTexture = new Texture("fire.png");
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

    private Animation createExplosionAnimation() {
        TextureRegion[][] explosionFrames = TextureRegion.split(explosionTexture, 134, 134);
        return new Animation(0.1f, explosionFrames[0]);
    }

    private Tank humanTank() {
        for(Tank tank: tanks) {
            if(tank.isHuman()) return tank;
        }
        return new Tank(tankTexture, batch, Tank.ControlType.HUMAN, createExplosionAnimation(), gameObjectsManager);
    }
}
