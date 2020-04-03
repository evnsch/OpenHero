package com.escho.game.entities.debugwand;

import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.*;
import de.gurkenlabs.litiengine.physics.Collision;

@EntityInfo(width = 64, height = 64)
@MovementInfo(velocity = 1)
@CollisionInfo(collisionBoxWidth = 64, collisionBoxHeight = 64, collision = false, collisionType = Collision.NONE)
public class DebugWand extends Creature implements IUpdateable {
    private static DebugWand instance = new DebugWand();
    private DebugWand() {
        super("debugwand");

        // setup movement controller
        this.addController(new DebugWandController(this));
    }
    public static DebugWand instance() {
        return instance;
    }
    @Override
    public void update() {
        //System.out.println("Update");
    }
}