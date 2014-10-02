package eu.javeo.tanks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by msuchecki on 02.10.14.
 */
public class Controls extends GameObject {

    private Button upButton;
    private Button downButton;
    private Button rightButton;
    private Button leftButton;

    private Stage stage;

    private float buttonSize = 50f;
    private float buttonOffset = 100f;

    private float centerX = 75f;
    private float centerY = 75f;

    public Controls(Stage stage) {
        super();
        this.stage = stage;
    }

    @Override
    public void init() {
        upButton = createButton(new Texture("fire.png"), centerX, centerY + buttonOffset / 2);
        downButton = createButton(new Texture("fire.png"), centerX, centerY - buttonOffset / 2);
        rightButton = createButton(new Texture("fire.png"), centerX + buttonOffset / 2, centerY);
        leftButton = createButton(new Texture("fire.png"), centerX - buttonOffset / 2, centerY);

        upButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                return false;
            }
        });
        downButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                return false;
            }
        });
        rightButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                return false;
            }
        });
        leftButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                return false;
            }
        });

        stage.addActor(upButton);
        stage.addActor(downButton);
        stage.addActor(rightButton);
        stage.addActor(leftButton);
    }

    private Button createButton(Texture texture, float x, float y) {
        TextureRegionDrawable buttonDrawable = new TextureRegionDrawable(new TextureRegion(texture));
        Button.ButtonStyle btnStyle = new Button.ButtonStyle(buttonDrawable, buttonDrawable, buttonDrawable);
        Button button = new Button(btnStyle);
        button.setBounds(x, y, buttonSize, buttonSize);
        return button;
    }


    public boolean up() {
        return upButton.isPressed();
    }

    public boolean down() {
        return downButton.isPressed();
    }

    public boolean right() {
        return rightButton.isPressed();
    }

    public boolean left() {
        return leftButton.isPressed();
    }

}
