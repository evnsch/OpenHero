package com.escho.game.entities.hero;

import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.MovementInfo;
import de.gurkenlabs.litiengine.physics.Collision;

@EntityInfo(width = 64, height = 64)
@MovementInfo(velocity = 1)
@CollisionInfo(collisionBoxWidth = 64, collisionBoxHeight = 64, collision = true, collisionType = Collision.ANY)
public class Hero extends Creature implements IUpdateable {
    private static Hero instance = new Hero();
    private Hero() {
        super("hero");

        System.out.println("Created Hero");

        // setup movement controller
        this.addController(new HeroController(this));
    }
    public static Hero instance() {
        return instance;
    }
    @Override
    public void update() {
        //System.out.println("Update");
    }
}