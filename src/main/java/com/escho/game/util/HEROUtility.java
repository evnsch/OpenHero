package com.escho.game.util;

import com.bernardomg.tabletop.dice.interpreter.DiceRoller;
import com.bernardomg.tabletop.dice.parser.DefaultDiceParser;
import com.escho.game.creatures.HEROCreature;
import com.escho.game.creatures.debugwand.DebugWand;
import com.escho.game.creatures.hero.Hero;
import com.escho.game.main.HEROCameraController;
import com.escho.game.main.HEROSettingController;
import de.gurkenlabs.litiengine.Game;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    /*----------------------------------------------------------------- */
    /*                                                                  */
    /*                                                                  */
    /*                               OTHER                              */
    /*                                                                  */
    /*                                                                  */
    /*----------------------------------------------------------------- */


    public static int getRandomInteger(long seed, int min, int max){
        Random rand = new Random(seed);
        return (rand.nextInt((max - min) + 1) + min);
    }

    public static int getRandomChaosHash(long seed, int x, int y, int min, int max) {
        return getRandomInteger(getChaosHash(seed, x, y), min, max);
    }

    public static long getChaosHash(long seed, int x, int y){
        long h = seed + x*374761393 + y*668265263; //all constants are prime
        h = (h^(h >> 13))*1274126177;
        return h^(h >> 16);
    }

    public static int getRandomRoll(String roll) {
        return new DefaultDiceParser().parse("1d100+0", new DiceRoller()).getTotalRoll();
    }

    public static double getNeareastNumberFromMultiple(double number, int multiple) {
        return multiple*(Math.round(Math.abs(number/multiple)));
    }

    public static Integer findMin(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return Integer.MAX_VALUE;
        }
        List<Integer> sortedlist = new ArrayList<>(list);
        Collections.sort(sortedlist);
        return sortedlist.get(0);
    }

    public static Integer findMax(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return Integer.MIN_VALUE;
        }
        List<Integer> sortedlist = new ArrayList<>(list);
        Collections.sort(sortedlist);
        return sortedlist.get(sortedlist.size() - 1);
    }

    public static void moveCreatureTransition(HEROCreature creature, double xNew, double yNew, int steps, int sleep) {
        if (creature.isMovingASync) return;

        double xDiff = creature.getX() - xNew;
        double yDiff = creature.getY() - yNew;
        double xIncrement = xDiff / steps;
        double yIncrement = yDiff / steps;

        new Thread(new Runnable() {
            public void run(){
                creature.isMovingASync = true;
                for (int i = 0; i < steps; ++i) {
                    creature.setPosition(creature.getX() + xIncrement, creature.getY() + yIncrement);
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                creature.setLocation((int)HEROUtility.getNeareastNumberFromMultiple(creature.getX(), HEROSettingController.worldTileWidth), creature.getY());
                creature.setLocation(creature.getX(), (int)HEROUtility.getNeareastNumberFromMultiple(creature.getY(), HEROSettingController.worldTileHeight));
                //if (creature.getLocation().getX() % 64 != 0 && !creature.isMovingASync) {}
                //if (creature.getLocation().getY() % 64 != 0 && !creature.isMovingASync) {}
                creature.isMovingASync = false;
            }
        }).start();
    }

}
