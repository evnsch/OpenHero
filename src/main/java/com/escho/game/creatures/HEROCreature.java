package com.escho.game.creatures;

import com.escho.game.main.HEROSettingController;
import com.escho.game.util.HEROUtility;
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
        if (getLocation().getX() % 64 != 0 && !isMovingASync) {setLocation(HEROUtility.getNeareastNumberFromMultiple(getX(), HEROSettingController.worldTileWidth), getY());}
        if (getLocation().getY() % 64 != 0 && !isMovingASync) {setLocation(getX(), HEROUtility.getNeareastNumberFromMultiple(getY(), HEROSettingController.worldTileHeight));}
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
