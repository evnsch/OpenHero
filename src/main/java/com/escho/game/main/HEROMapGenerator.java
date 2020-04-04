package com.escho.game.main;

import com.escho.game.util.HEROUtility;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.environment.tilemap.MapOrientations;
import de.gurkenlabs.litiengine.environment.tilemap.RenderOrder;
import de.gurkenlabs.litiengine.environment.tilemap.xml.*;
import de.gurkenlabs.litiengine.graphics.RenderType;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class HEROMapGenerator {

    public static TmxMap generateMap(String name, int width, int height) {
        TmxMap map = new TmxMap(MapOrientations.ORTHOGONAL);
        map.setRenderOrder(RenderOrder.RIGHT_DOWN);
        map.setTileWidth(HEROSettingController.worldTileWidth);
        map.setTileHeight(HEROSettingController.worldTileHeight);
        map.setWidth(width);
        map.setHeight(height);
        map.setTiledVersion("1.1.5");
        map.setName(name);

        Tileset tileset = Resources.tilesets().get("tilesheet.tsx");
        map.getTilesets().add(tileset);

        ArrayList<Tile> tiles = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int tile = 2;
                int roll = HEROUtility.getRandomChaosHash(948398292, x, y, 0, 100);
                if (roll >= 70) { tile = 2; } else
                     if (roll <= 10) {tile = 2;}
                if (x == 0 || y == 0 || x == width-1 || y == width-1) {tile=0;}
                Tile t = new Tile(tile);
                t.setTileCoordinate(new Point(x,y));
                tiles.add(t);
            }
        }

        TileData data;
        try {
            data = new TileData(tiles, width, height, TileData.Encoding.CSV, TileData.Compression.NONE);
            data.setValue(TileData.encode(data));
        } catch (IOException e) {
            data = null;
            e.printStackTrace();
        }

        TileLayer ground = new TileLayer(data);
        ground.setVisible(true);
        ground.setRenderType(RenderType.BACKGROUND);
        ground.setWidth(width);
        ground.setHeight(height);
        map.addLayer(ground);

        // Workaround, map never does finish()
        try {
            map.finish(Game.class.getClassLoader().getResource("tilesheet.tsx"));
        } catch (TmxException e) {
            e.printStackTrace();
        }

        return map;
    }
}
