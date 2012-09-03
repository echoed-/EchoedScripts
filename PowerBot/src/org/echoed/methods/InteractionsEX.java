package org.echoed.methods;

import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;


import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 8/21/12
 * Time: 11:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class InteractionsEX {
    public static boolean tileContainsObject(Tile tile, int obj_id) {
        for (SceneObject o : SceneEntities.getLoaded(tile)) {
            if (o.getId() == obj_id)
                return true;
        }
        return false;
    }

    public static void objectInteract(Tile tile, String Action) {
        SceneObject object = SceneEntities.getAt(tile.getX(), tile.getY());
        if (object != null) {
            if (object.isOnScreen()) {
                object.interact(Action);
                Utils.sleep(Random.nextInt(200, 800));
            } else {
                if (Calculations.distanceTo(object) > 3) {
                    Walking.findPath(object.getLocation()).traverse();
                }
                Camera.turnTo(object);
            }
        }
    }

    public static void objectInteract(int objectId, String Action) {
        SceneObject obj = SceneEntities.getNearest(objectId);
        if (obj != null) {
            if (obj.isOnScreen()) {
                obj.interact(Action);
                Utils.sleep(Random.nextInt(200, 800));
            } else {
                if (Calculations.distanceTo(obj) > 3) {
                    Walking.findPath(obj.getLocation()).traverse();
                }
                Camera.turnTo(obj);
            }
        }

    }
}
