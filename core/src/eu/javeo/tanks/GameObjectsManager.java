package eu.javeo.tanks;

import com.badlogic.gdx.maps.tiled.TiledMap;

import java.util.Queue;

public class GameObjectsManager {

    private TiledMap map;
    private Queue<Tank> tanks;

    public GameObjectsManager(TiledMap map, Queue<Tank> tanks) {
        this.map = map;
        this.tanks = tanks;
    }

    public TiledMap getMap() {
        return map;
    }

    public Queue<Tank> getTanks() {
        return tanks;
    }

    public boolean collides(GameObject gameObject) {
        for (Tank tank : tanks) {
            if (!tank.equals(gameObject) && objectsCollides(tank, gameObject)) {
                return true;
            }
        }

        return false;
    }

    private boolean objectsCollides(GameObject object1, GameObject object2) {
        return object1.getSprite().getBoundingRectangle().overlaps(object2.getSprite().getBoundingRectangle());
    }
}
