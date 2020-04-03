package com.escho.game.entities.debugwand;

import com.escho.game.main.HEROSettingController;
import com.escho.game.util.HEROUtility;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.entities.IMobileEntity;
import de.gurkenlabs.litiengine.input.KeyboardEntityController;

import java.awt.event.KeyEvent;

public class DebugWandController extends KeyboardEntityController {
    public static int KeyPressDelayTime = 120;
    public IMobileEntity _entity = null;
    private long lastTimeKeyWasPressed = System.currentTimeMillis();

    public DebugWandController(IMobileEntity entity) {
        super(entity);
        _entity = entity;
    }

    @Override
    public void handleMovement() {
        super.handleMovement();
    }

    @Override
    public void handlePressedKey(KeyEvent keyCode) {
        int factor = HEROSettingController.worldTileHeight;
        //System.out.println("Sent Key: "+keyCode.getKeyCode());

        // Place all non-time restricted actions here
        if (HEROUtility.debugWandIsActive()) {
            switch (keyCode.getKeyCode()){
                case 61: Game.graphics().setBaseRenderScale(Game.graphics().getBaseRenderScale() + 0.001f); System.out.println("[Bigger]  New Scale: "+Game.graphics().getBaseRenderScale()); break;
                case 45: Game.graphics().setBaseRenderScale(Game.graphics().getBaseRenderScale() - 0.001f); System.out.println("[Smaller] New Scale: "+Game.graphics().getBaseRenderScale()); break;
            }
        }

        // Place all time restricted actions here
        if (canPressTimeRestrictedKey()) {
            if (HEROUtility.debugWandIsActive()){
                switch (keyCode.getKeyCode()) {
                    case 87: timeRestrictedKeyPressed(); _entity.setY(_entity.getY() - HEROSettingController.worldTileWidth); break; //w
                    case 65: timeRestrictedKeyPressed(); _entity.setX(_entity.getX() - HEROSettingController.worldTileWidth); break; //a
                    case 83: timeRestrictedKeyPressed(); _entity.setY(_entity.getY() + HEROSettingController.worldTileHeight); break; //s
                    case 68: timeRestrictedKeyPressed(); _entity.setX(_entity.getX() + HEROSettingController.worldTileHeight); break; //d
                    case 76: timeRestrictedKeyPressed(); HEROUtility.debugWandToggleCameraLock(); break; //l
                }
            }
            switch (keyCode.getKeyCode()) {
                case 192: timeRestrictedKeyPressed(); HEROUtility.debugWandToggleState(); break;
            }
        }
            //super.handlePressedKey(keyCode);
    }

    public void timeRestrictedKeyPressed() {
        //System.out.println("A Key Was Pressed. Time = "+System.currentTimeMillis()+" Can Press? "+canPressTimeRestrictedKey());
        lastTimeKeyWasPressed = System.currentTimeMillis();
    }

    public boolean canPressTimeRestrictedKey() {
        long timeSinceKeyWasLastPressed = System.currentTimeMillis()-lastTimeKeyWasPressed;
        //System.out.println("Time Since Last Key Press: "+timeSinceKeyWasLastPressed);
        if (timeSinceKeyWasLastPressed >= KeyPressDelayTime) {return true;} return false;
    }
}
