package com.escho.game.creatures.debugwand;

import com.escho.game.main.HEROSettingController;
import com.escho.game.util.HEROUtility;
import de.gurkenlabs.litiengine.Game;
import de.gurkenlabs.litiengine.gui.screens.GameScreen;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DebugWandLayer extends GameScreen {

    private boolean debugWandDoesRenderGrid = true;
    private boolean debugWandDoesRenderCenterLines = true;
    private boolean debugWandDoesRenderWandCoordinates = true;

    public DebugWandLayer() {
        super("random");
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);
        if (HEROUtility.debugWandIsActive()) {
            if (debugWandDoesRenderGrid) renderGrid(g);
        }

        if (HEROUtility.debugWandIsActive()) {
            if (debugWandDoesRenderWandCoordinates) renderDebugWandCoordinates(g);
            if (debugWandDoesRenderCenterLines) renderCenterLines(g);
        }
    }

    public static void renderGrid(Graphics2D g) {
        double xStart = 0;
        double xStop = (Game.world().environment().getMap().getHeight()* HEROSettingController.worldTileHeight)-HEROSettingController.worldTileHeight; //FRTUtility.getCameraBottomRightX();
        double yStart = 0;
        double yStop = (Game.world().environment().getMap().getHeight()*HEROSettingController.worldTileWidth)-HEROSettingController.worldTileWidth;; // FRTUtility.getCameraBottomRightY();

        for (double x = xStart; x <= xStop; x=x)
        {
            for (double y = yStart; y <= yStop; y=y)
            {
                g.setColor(Color.GRAY);
                Rectangle2D r = new Rectangle2D.Double(x, y, HEROSettingController.gridTileWidth, HEROSettingController.gridTileHeight);
                Game.graphics().renderOutline(g, r);
                y = y+HEROSettingController.gridTileHeight;
            }
            x = x+HEROSettingController.gridTileWidth;
        }
    }

    public static void renderDebugWandCoordinates(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        Game.graphics().renderText(g, "Cords: ("+ DebugWand.instance().getX()+", "+DebugWand.instance().getY()+")", HEROUtility.getCameraBottomRightX()-300, HEROUtility.getCameraBottomRightY()-5,true);
    }

    public void renderCenterLines(Graphics2D g) {
        g.setColor(Color.RED);
        Game.graphics().renderShape(g, new Rectangle2D.Double(HEROUtility.getCameraTopCenterX(), HEROUtility.getCameraTopCenterY(),1, Game.world().camera().getViewport().getHeight())); //perp line
        Game.graphics().renderShape(g, new Rectangle2D.Double(HEROUtility.getCameraLeftCenterX(), HEROUtility.getCameraLeftCenterY(),Game.world().camera().getViewport().getWidth(), 1)); //horiz line
    }

}