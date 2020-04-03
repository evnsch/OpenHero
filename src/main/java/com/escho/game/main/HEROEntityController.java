package com.escho.game.main;

import com.escho.game.entities.debugwand.DebugWand;
import com.escho.game.util.HEROUtility;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.IEntity;
import de.gurkenlabs.litiengine.entities.Spawnpoint;

import java.util.Collection;

public class HEROEntityController {

    public static Collection<IEntity> getEnviornmentEntities() {
        return Game.world().environment().getEntities();
    }

    public static void spawn(IEntity entity, double x, double y) {
        Spawnpoint enter = new Spawnpoint(x,y);
        enter.spawn(entity);
        Game.world().camera().setFocus(DebugWand.instance().getX(), DebugWand.instance().getY());
    }

    public static void spawn(IEntity entity) {
        spawn(entity, HEROUtility.getCameraTopLeftX(), HEROUtility.getCameraTopLeftY());
    }
}
