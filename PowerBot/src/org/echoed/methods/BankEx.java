package org.echoed.methods;

import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.node.Item;

import java.util.Arrays;


/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 8/28/12
 * Time: 4:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankEx {

    public final static int COMPONENT_P2P_ITEM_COUNT = 31;
    public final static int COMPONENT_P2P_ITEM_MAX = 32;
    public static void depositAllExcept(int... items){
        Item[] invItems = Inventory.getAllItems(true);
        for(Item item: invItems){
            if(!Arrays.asList(items).contains(item.getId())){
               Bank.deposit(item.getId(),0);
            }
        }

    }
}
