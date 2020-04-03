package com.escho.game.main;

import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.graphics.Camera;
import de.gurkenlabs.litiengine.graphics.PositionLockCamera;

public class HEROCameraController {

    public static void LockOn(Creature creature) {
        Game.world().setCamera(new PositionLockCamera(creature));
    }

    public static void LazyOn(Creature creature) {
        Game.world().setCamera(new Camera()); Game.world().camera().setFocus(creature.getX(), creature.getY()); Game.world().camera().pan(creature.getX(), creature.getY(),100);
    }
}
