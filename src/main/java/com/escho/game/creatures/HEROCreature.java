package com.escho.game.creatures;

import de.gurkenlabs.litiengine.IUpdateable;
import de.gurkenlabs.litiengine.entities.Creature;

import java.awt.geom.Point2D;

public class HEROCreature extends Creature implements IUpdateable {
    public boolean isMovingASync = false;
    public HEROCreature(String name) {
        super(name);
    }

    @Override
    public void update() {
        //if (getLocation().getX() % 64 != 0) setLocation(getX()+0.1, getY());
        //if (getLocation().getY() % 64 != 0) setLocation(getX(), getY()+0.1);
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
