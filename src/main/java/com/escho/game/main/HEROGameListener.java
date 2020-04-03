package com.escho.game.main;

import com.escho.game.entities.debugwand.DebugWand;
import com.escho.game.entities.debugwand.DebugWandLayer;
import com.escho.game.entities.hero.Hero;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.GameListener;

public class HEROGameListener implements GameListener {

    // Game.graphics().renderText(g, "my text", myEntity.getX(), myEntity.getY());
    // Game.audio().playSound("my-sound.ogg", 50, 50);
    // Game.physics()
    //Game.config()
    //Game.info()
    //Game.metrics()
    //Game.time()
    //Game.loop()
    //Game.renderLoop()
    //Game.inputLoop()
    //Game.world()
    //Game.window()
    //Game.screens()

    @Override
    public void initialized(String... args) {

    }
    @Override
    public void started() {
        Game.screens().add(new DebugWandLayer());
        Game.world().setGravity(0);

        HEROEntityController.spawn(DebugWand.instance(), 0,0);
        HEROEntityController.spawn(Hero.instance(), 0,0);
    }

    @Override
    public void terminated() {
        // do sth when game terminated
    }
}
