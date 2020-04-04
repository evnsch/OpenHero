package com.escho.game.creatures.hero;

import com.escho.game.creatures.HEROCreature;
import com.escho.game.main.HEROSettingController;
import com.escho.game.util.HEROUtility;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.IMobileEntity;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;

import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class HeroController extends KeyboardEntityController {
    public static int KeyPressDelayTime = 120;
    public HEROCreature _entity = null;
    private long lastTimeKeyWasPressed = System.currentTimeMillis();

    public HeroController(HEROCreature entity) {
        super(entity);
        _entity = entity;
    }

    @Override
    public void handleMovement() {
        super.handleMovement();
    }

    @Override
    public void handlePressedKey(KeyEvent keyCode) {
        //System.out.println("Sent Key: "+keyCode.getKeyCode());

        // Place all time restricted actions here
        if (canPressTimeRestrictedKey()) {
            if (!HEROUtility.debugWandIsActive()){
                Point2D oldPosition = _entity.getPosition();
                Point2D newPositionCollisonInverse = _entity.getPosition();
                Point2D newPosition = _entity.getPosition();
                int stepY = HEROSettingController.worldTileWidth;
                int stepX = HEROSettingController.worldTileHeight;
                boolean movement = false;
                switch (keyCode.getKeyCode()) {
                    case 87: timeRestrictedKeyPressed(); newPosition.setLocation(newPosition.getX(), newPosition.getY() + stepY); newPositionCollisonInverse.setLocation(oldPosition.getX(), oldPosition.getY() - stepY); movement = true; break; //w
                    case 65: timeRestrictedKeyPressed(); newPosition.setLocation(newPosition.getX() + stepX, newPosition.getY()); newPositionCollisonInverse.setLocation(oldPosition.getX() - stepX, oldPosition.getY()); movement = true; break; //a
                    case 83: timeRestrictedKeyPressed(); newPosition.setLocation(newPosition.getX(), newPosition.getY() - stepY); newPositionCollisonInverse.setLocation(oldPosition.getX(), oldPosition.getY() + stepY); movement = true; break; //s
                    case 68: timeRestrictedKeyPressed(); newPosition.setLocation(newPosition.getX() - stepX, newPosition.getY()); newPositionCollisonInverse.setLocation(oldPosition.getX() + stepX, oldPosition.getY()); movement = true; break;  //d
                }
                boolean collides = Game.physics().collides(new Point2D.Double(newPositionCollisonInverse.getX(), newPositionCollisonInverse.getY())) || newPositionCollisonInverse.getX() < stepY || newPositionCollisonInverse.getY() < stepX;
                if (!collides && movement) {super.handlePressedKey(keyCode); HEROUtility.moveCreatureTransition(_entity, (int)newPosition.getX(), (int)newPosition.getY(), 10, 10);}//else
                    //System.out.println("Collision: can't change position from (old: "+_entity.getX()+", "+_entity.getY()+") to  (new: "+newPosition.getX()+", "+newPosition.getY()+" AKA inverse: "+newPositionCollisonInverse.getX()+", "+newPositionCollisonInverse.getY()+")");
            }
            switch (keyCode.getKeyCode()) {
                case 192: timeRestrictedKeyPressed(); break;
            }
        }
            //super.handlePressedKey(keyCode);
    }

    public void timeRestrictedKeyPressed() {
        lastTimeKeyWasPressed = System.currentTimeMillis();
    }

    public boolean canPressTimeRestrictedKey() {
        long timeSinceKeyWasLastPressed = System.currentTimeMillis()-lastTimeKeyWasPressed;
        if (timeSinceKeyWasLastPressed >= KeyPressDelayTime) {return true;} return false;
    }
}
