package com.escho.game.main;

import com.escho.game.util.HEROUtility;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.CollisionBox;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.environment.tilemap.MapOrientations;
import de.gurkenlabs.litiengine.environment.tilemap.RenderOrder;
import de.gurkenlabs.litiengine.environment.tilemap.xml.*;
import de.gurkenlabs.litiengine.graphics.RenderType;
import de.gurkenlabs.litiengine.resources.Resources;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

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

        LinkedHashMap<Point2D, String> skeletonMap = new LinkedHashMap<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width ; x++) {
                Point2D here = new Point2D.Double(x,y);
                String type = "floor";
                int roll = HEROUtility.getRandomChaosHash(948398292, x, y, 0, 100);
                if (roll >= 70) {type="floor";} else
                if (roll <= 10) {type="wall";}
                if (x == 0 || y == 0 || x == width-1 || y == width-1) {type="wall";}
                skeletonMap.put(here, type);
            }
        }

        ArrayList<Tile> tiles = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width ; x++) {
                String thisType = skeletonMap.get(new Point2D.Double(x,y)); boolean thisIsWall = Objects.equals(thisType, "wall");
                String upType = skeletonMap.get(new Point2D.Double(x,y-1)); boolean upIsWall = Objects.equals(upType, "wall");
                String downType = skeletonMap.get(new Point2D.Double(x,y+1)); boolean downIsWall = Objects.equals(downType, "wall");
                String leftType = skeletonMap.get(new Point2D.Double(x-1,y)); boolean leftIsWall = Objects.equals(leftType, "wall");
                String rightType = skeletonMap.get(new Point2D.Double(x+1,y)); boolean rightIsWall = Objects.equals(rightType, "wall");

                int tile = 2;

                if (x == 9 && y == 0) {
                    System.out.println("north east corner");
                }
                if (x == 9 && y == 9) {
                    System.out.println("south east corner");
                }
                if (x == 0 && y == 9) {
                    System.out.println("south west corner");
                }
                if (x == 0 && y == 0) {
                    System.out.println("north west corner");
                }

                if (x==2 && y == 0) {
                    System.out.println("here!");
                    //tile=3;
                }
                if (thisIsWall) {tile = 0;}
                if (thisIsWall && (leftIsWall && rightIsWall)) tile = 0;
                if (thisIsWall && (downIsWall && leftIsWall)) tile = 1; //should be northeast corners
                if (thisIsWall && (downIsWall && rightIsWall)) tile = 1; //northwest corners
                if (thisIsWall && (upIsWall && rightIsWall)) tile = 0; //should be southwest corners
                if (thisIsWall && (upIsWall && leftIsWall)) tile = 0; //southeast corners
                if (thisIsWall && (upIsWall && downIsWall)) tile = 1; // middles

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
        ground.setRenderType(RenderType.NORMAL);
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
