package com.escho.app;

import com.escho.game.main.HEROSettingController;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.resources.Resources;

public class Run {
    public static void main(String[] args){
        HEROSettingController.setGameSettings();
        Game.init(args);
        HEROSettingController.loadResources();
        Game.start();
    }
}
