package com.escho.game.creatures;

import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.Creature;

import java.awt.geom.Point2D;

public class HEROCreature extends Creature implements IUpdateable {
    public HEROCreature(String name) {
        super(name);
    }

    @Override
    public void update() {
    }

    public Point2D getPosition() {
        return new Point2D.Double(getX(),getY());
    }

    public void setPosition(Point2D point) {
        setX(point.getX());
        setY(point.getY());
    }

    public void setPosition(double x, double y) {
        setPosition(new Point2D.Double(x,y));
    }
}
