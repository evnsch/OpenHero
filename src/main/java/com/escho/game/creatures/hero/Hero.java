package com.escho.game.creatures.hero;

import com.escho.game.creatures.HEROCreature;
import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.CollisionInfo;
import de.gurkenlabs.litiengine.entities.Creature;
import de.gurkenlabs.litiengine.entities.EntityInfo;
import de.gurkenlabs.litiengine.entities.MovementInfo;
import de.gurkenlabs.litiengine.physics.Collision;

@EntityInfo(width = 64, height = 64)
@MovementInfo(velocity = 1)
@CollisionInfo(collisionBoxWidth = 64, collisionBoxHeight = 64, collision = true, collisionType = Collision.ANY)
public class Hero extends HEROCreature {
    private static Hero instance = new Hero();

    private Hero() {
        super("hero");
        this.addController(new HeroController(this));
    }
    public static Hero instance() {
        return instance;
    }
}