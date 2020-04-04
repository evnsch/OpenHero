package com.escho.game.main;

import com.escho.game.creatures.hero.Hero;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.CollisionBox;
import de.gurkenlabs.litiengine.environment.tilemap.xml.TmxException;
import de.gurkenlabs.litiengine.environment.tilemap.xml.TmxMap;
import de.gurkenlabs.litiengine.resources.Resources;

import java.net.MalformedURLException;
import java.net.URL;

public class HEROSettingController {
    public static int gridTileHeight = 64;
    public static int gridTileWidth = 64;

    public static int worldTileHeight = 64;
    public static int worldTileWidth = 64;

    public static void setGameSettings() {
        Game.addGameListener(new HEROGameListener());
        Game.info().setName("OpenHero");
        Game.info().setSubTitle("");
        Game.info().setVersion("v0.0.1");
        Game.info().setWebsite("https://github.com/evnsch/OpenHero");
        Game.info().setDescription("Open source, just the way it should be");
        //Game.window().setIconImage(Resources.images().get("icon.png"));

        Game.graphics().setBaseRenderScale(1.07298f);
    }

    public static void loadResources() {
        Resources.load("fortress.litidata");
        TmxMap random = HEROMapGenerator.generateMap("random",50,50);
        Game.world().loadEnvironment(random);
        Game.physics().add(Hero.instance());
        Game.physics().update();
        System.out.println(Game.physics().getCollisionBoxes());
    }
}
