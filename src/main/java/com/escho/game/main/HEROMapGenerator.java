package com.escho.game.main;

import com.escho.game.util.HEROUtility;
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
                int tile = 0;
                int roll = HEROUtility.getRandomInteger(HEROUtility.getChaosHash(948398292, (int)x, (int)y), 0, 100); //new DefaultDiceParser().parse("1d100+0", new DiceRoller()).getTotalRoll();
                if (roll >= 70) {
                    tile = 1;
                } else if (roll <= 10) {tile=2;}
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
        return map;
    }
}
