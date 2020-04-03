package com.escho.game.entities.hero;

import com.escho.game.main.HEROSettingController;
import com.escho.game.util.HEROUtility;
import de.gurkenlabs.litiengine.entities.IMobileEntity;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;

import java.awt.event.KeyEvent;

public class HeroController extends KeyboardEntityController {
    public static int KeyPressDelayTime = 120;
    public IMobileEntity _entity = null;
    private long lastTimeKeyWasPressed = System.currentTimeMillis();

    public HeroController(IMobileEntity entity) {
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
                    case 87: timeRestrictedKeyPressed(); _entity.setY(_entity.getY() - HEROSettingController.worldTileWidth); super.handlePressedKey(keyCode); break; //w
                    case 65: timeRestrictedKeyPressed(); _entity.setX(_entity.getX() - HEROSettingController.worldTileWidth); super.handlePressedKey(keyCode); break; //a
                    case 83: timeRestrictedKeyPressed(); _entity.setY(_entity.getY() + HEROSettingController.worldTileHeight); super.handlePressedKey(keyCode); break; //s
                    case 68: timeRestrictedKeyPressed(); _entity.setX(_entity.getX() + HEROSettingController.worldTileHeight); super.handlePressedKey(keyCode); break; //d
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
