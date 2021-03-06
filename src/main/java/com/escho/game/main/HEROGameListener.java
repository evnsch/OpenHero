package com.escho.game.main;

import com.escho.game.creatures.debugwand.DebugWand;
import com.escho.game.creatures.debugwand.DebugWandLayer;
import com.escho.game.creatures.hero.Hero;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.GameListener;

public class HEROGameListener implements GameListener {
    @Override
    public void initialized(String... args) {

    }
    @Override
    public void started() {
        Game.screens().add(new DebugWandLayer());
        Game.world().setGravity(0);

        HEROEntityController.spawn(Hero.instance());
        HEROEntityController.spawn(DebugWand.instance());
    }

    @Override
    public void terminated() {

    }
}
