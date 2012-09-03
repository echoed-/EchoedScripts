package org.echoed.methods;

import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.node.Item;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 8/25/12
 * Time: 12:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class InventoryEX {
    public static boolean contains(int idx) {
        return Inventory.getCount(idx) > 0;

    }

    public static boolean containsAll(int... items) {
        Item[] invItems = Inventory.getItems();

        return Arrays.asList(invItems).contains(items);
    }
}
