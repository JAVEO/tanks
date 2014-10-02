package eu.javeo.tanks;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class WallsCollisionManager {
    private TiledMapTileLayer layer;
    public final static int TILE_SIZE = 100;
    public final static int TILES_NUMBER = 20;
    public final static int SOLID_WALL_ID = 3;
    public final static int WEAK_WALL_ID = 2;

    public WallsCollisionManager(TiledMap map) {
        layer = (TiledMapTileLayer) map.getLayers().get(0);
    }

    public boolean isCollision(float x, float y) {
        int tileIndexX = coordinateToTileIndex(x);
        int tileIndexY = coordinateToTileIndex(y);
        TiledMapTileLayer.Cell cell = layer.getCell(tileIndexX, tileIndexY);
        int tileId = cell.getTile().getId();
        return tileId == SOLID_WALL_ID || tileId == WEAK_WALL_ID;
    }

    private int coordinateToTileIndex(float coordinate) {
        int tileIndex = Math.min((int) coordinate / TILE_SIZE, TILES_NUMBER - 1);
        return tileIndex;
    }
}
