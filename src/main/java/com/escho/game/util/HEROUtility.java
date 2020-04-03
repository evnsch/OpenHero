package com.escho.game.util;

import com.escho.game.creatures.debugwand.DebugWand;
import com.escho.game.creatures.hero.Hero;
import com.escho.game.main.HEROCameraController;
import de.gurkenlabs.litiengine.Game;

import java.awt.geom.Point2D;
import java.util.Random;

public class HEROUtility {

    private static boolean cameraLock = false;
    private static Point2D lastLocation = new Point2D.Double(DebugWand.instance().getX(),DebugWand.instance().getY());

    /*----------------------------------------------------------------- */
    /*                                                                  */
    /*                                                                  */
    /*                             CAMERA                               */
    /*                                                                  */
    /*                                                                  */
    /*----------------------------------------------------------------- */

    public static double getCameraBottomRightX() {
        return Game.world().camera().getViewport().getMaxX();
    }

    public static double getCameraBottomRightY() {
        return Game.world().camera().getViewport().getMaxY();
    }

    public static double getCameraTopLeftX() {
        return Game.world().camera().getViewport().getX();
    }

    public static double getCameraTopLeftY() {
        return Game.world().camera().getViewport().getY();
    }

    public static double getCameraTopCenterX() {
        return Game.world().camera().getViewport().getCenterX();
    }

    public static double getCameraTopCenterY() {
        return getCameraTopLeftY();
    }

    public static double getCameraLeftCenterX() {
        return getCameraTopLeftX();
    }

    public static double getCameraLeftCenterY() {
        return Game.world().camera().getViewport().getCenterY();
    }

    public static boolean getCameraIsLocked() {
        return cameraLock;
    }

    /*----------------------------------------------------------------- */
    /*                                                                  */
    /*                                                                  */
    /*                            ENVIRONMENT                           */
    /*                                                                  */
    /*                                                                  */
    /*----------------------------------------------------------------- */

    public static double getEnviornmentBottomRightX() {
        return Game.world().camera().getViewport().getMaxX();
    }

    public static double getEnviornmenBottomRightY() {
        return Game.world().camera().getViewport().getMaxY();
    }

    public static double getEnviornmenTopLeftX()
    {
        return Game.world().camera().getViewport().getMinX();
    }

    public static double getEnviornmenTopLeftY() {
        return Game.world().camera().getViewport().getMinY();
    }

    /*----------------------------------------------------------------- */
    /*                                                                  */
    /*                                                                  */
    /*                               DEBUG                              */
    /*                                                                  */
    /*                                                                  */
    /*----------------------------------------------------------------- */

    public static void debugWandToggleState() {
        if (debugWandIsActive()) {debugWandSetState(false);}
        else {debugWandSetState(true);}
    }

    public static void debugWandSetState(boolean active) {
        DebugWand.instance().setVisible(active);
        if (!active) {HEROCameraController.LockOn(Hero.instance());; cameraLock = true;} else {
            DebugWand.instance().setPosition(Hero.instance().getPosition());
            HEROCameraController.LockOn(DebugWand.instance());
        }
    }

    public static boolean debugWandIsActive() {
        return DebugWand.instance().isVisible();
    }

    public static void debugWandToggleCameraLock() {
        if (cameraLock) { HEROCameraController.LazyOn(DebugWand.instance()); cameraLock = false;}
        else {HEROCameraController.LockOn(DebugWand.instance()); cameraLock = true;}
    }

    public static void debugLogCameraCoordinates() {
        System.out.println("------------------------------------------------------");
        System.out.println("Top Left X: " + HEROUtility.getCameraTopLeftX());
        System.out.println("Top Left Y: " + HEROUtility.getCameraTopLeftY());
        System.out.println("Bottom Right X: " + HEROUtility.getCameraBottomRightX());
        System.out.println("Bottom Right Y: " + HEROUtility.getCameraBottomRightY());
        System.out.println("Top Center X: " + HEROUtility.getCameraTopCenterX());
        System.out.println("Top Center Y: " + HEROUtility.getCameraTopCenterY());
        System.out.println("------------------------------------------------------");
    }

        // Other

    public static int getRandomInteger(long seed, int min, int max){
        Random rand = new Random(seed);
        return rand.nextInt((max - min) + 1) + min;
    }

    public static long getChaosHash(long seed, int x, int y){
        long h = seed + x*374761393 + y*668265263; //all constants are prime
        h = (h^(h >> 13))*1274126177;
        return h^(h >> 16);
    }

}
