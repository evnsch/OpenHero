package com.escho.game.creatures.hero;

import com.escho.game.creatures.HEROCreature;
import com.escho.game.main.HEROSettingController;
import com.escho.game.util.HEROUtility;
import de.gurkenlabs.litiengine.entities.IMobileEntity;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;

import java.awt.event.KeyEvent;

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
                switch (keyCode.getKeyCode()) {
                    case 87: timeRestrictedKeyPressed(); HEROUtility.moveCreatureTransition(_entity, _entity.getX(), _entity.getY() + HEROSettingController.worldTileHeight, 10, 10); super.handlePressedKey(keyCode); break; //w
                    case 65: timeRestrictedKeyPressed(); HEROUtility.moveCreatureTransition(_entity, _entity.getX() + HEROSettingController.worldTileWidth, _entity.getY(), 10, 10); super.handlePressedKey(keyCode); break; //a
                    case 83: timeRestrictedKeyPressed(); HEROUtility.moveCreatureTransition(_entity, _entity.getX(), _entity.getY() - HEROSettingController.worldTileWidth, 10, 10); super.handlePressedKey(keyCode); break; //s
                    case 68: timeRestrictedKeyPressed(); HEROUtility.moveCreatureTransition(_entity, _entity.getX() - HEROSettingController.worldTileWidth, _entity.getY(), 10, 10); super.handlePressedKey(keyCode); break; //d
                }
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
