package com.escho.game.creatures.debugwand;

import com.escho.game.creatures.HEROCreature;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.*;
import de.gurkenlabs.litiengine.physics.Collision;

@EntityInfo(width = 64, height = 64)
@MovementInfo(velocity = 1)
@CollisionInfo(collisionBoxWidth = 64, collisionBoxHeight = 64, collision = false, collisionType = Collision.NONE)
public class DebugWand extends HEROCreature implements IUpdateable {
    private static DebugWand instance = new DebugWand();
    private DebugWand() {
        super("debugwand");
        this.addController(new DebugWandController(this));
    }
    public static DebugWand instance() {
        return instance;
    }
}