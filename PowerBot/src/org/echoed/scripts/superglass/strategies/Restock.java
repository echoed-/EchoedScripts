package org.echoed.scripts.superglass.strategies;


import org.echoed.methods.InteractionsEX;
import org.echoed.methods.MenuEX;
import org.echoed.methods.Utils;
import org.echoed.methods.InventoryEX;
import org.echoed.methods.BankEx;
import org.echoed.scripts.superglass.misc.CounterTask;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.wrappers.widget.WidgetChild;
import org.echoed.scripts.superglass.EchoedSuperGlass;
import org.echoed.scripts.superglass.misc.Constants;

import java.awt.*;

public class Restock extends Strategy implements Runnable,Condition {
    private boolean suicide = EchoedSuperGlass.getSuicide();
    private boolean mouseKeys = EchoedSuperGlass.getMouseKeys();


    private void bankItem(int id) {
        if (!InventoryEX.contains(id)) {
            Bank.withdraw(id, 13);
        } else {
            if (Inventory.getCount(id) != 13) {
                Bank.deposit(id, 0);
            }
        }

    }

    private void mousekeyBankItems() {
        WidgetChild count = Widgets.get(Bank.WIDGET_BANK, BankEx.COMPONENT_P2P_ITEM_COUNT);
        WidgetChild max = Widgets.get(Bank.WIDGET_BANK, BankEx.COMPONENT_P2P_ITEM_MAX);


        if (Inventory.getCount(Constants.BUCKET_OF_SAND) != 0) {
            if (count.validate() && max.validate()) {
                if (count.getText().equals(max.getText())) {
                    Bank.depositInventory();
                } else {
                    Bank.deposit(Constants.BUCKET_OF_SAND, 0);
                }
            }

        }
        if (Inventory.getCount(Constants.SECONDARY_ITEM) != 0) {
            if (count.validate() && max.validate()) {
                if (count.getText().equals(max.getText())) {
                    Bank.depositInventory();
                } else {
                    Bank.deposit(Constants.SECONDARY_ITEM, 0);
                }
            }


        }
        try {
            WidgetChild secondary = Bank.getItem(Constants.SECONDARY_ITEM).getWidgetChild();
            WidgetChild sand = Bank.getItem(Constants.BUCKET_OF_SAND).getWidgetChild();
            if (!InventoryEX.containsAll(Constants.SECONDARY_ITEM, Constants.BUCKET_OF_SAND)
                    && Inventory.getCount(Constants.BUCKET_OF_SAND) == 0
                    && Inventory.getCount(Constants.SECONDARY_ITEM) == 0) {

                if (secondary.validate() && sand.validate()) {
                    Mouse.click(secondary.getNextViewportPoint(), false);
                    if (Menu.isOpen()) {
                        if (Menu.contains("Withdraw-13 " + Constants.getSecondaryItemName(Constants.SECONDARY_ITEM))) {
                            Mouse.move(Mouse.getLocation().x, Mouse.getLocation().y + 72);
                            Mouse.click(true);
                        } else {
                            Bank.withdraw(Constants.SECONDARY_ITEM, 13);
                        }
                    }
                    Mouse.click(sand.getNextViewportPoint(), false);
                    if (Menu.isOpen()) {
                        if (Menu.contains("Withdraw-All Bucket of Sand")) {
                            Mouse.move(Mouse.getLocation().x, Mouse.getLocation().y + 122);
                            Mouse.click(true);
                        } else {
                            Bank.withdraw(Constants.BUCKET_OF_SAND, 13);

                        }
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //Mouse.setSpeed(SyncSuperGlass.getMouseSpeed());

    }

    private void getRunes() {
        if (!InventoryEX.contains(Constants.FIRE_RUNE)) {
            Bank.withdraw(Constants.FIRE_RUNE, 0);
        }
        if (!InventoryEX.contains(Constants.ASTRAL_RUNE)) {
            Bank.withdraw(Constants.ASTRAL_RUNE, 0);
        }
    }

    public int execute() {
        if (Bank.isOpen()) {
            BankEx.depositAllExcept(Constants.BUCKET_OF_SAND, Constants.FIRE_RUNE, Constants.SECONDARY_ITEM, Constants.ASTRAL_RUNE, Constants.MOLTEN_GLASS);
            EchoedSuperGlass.moltenMade = CounterTask.getMoltenMade();
            EchoedSuperGlass.setProfit();

            if (InventoryEX.contains(Constants.MOLTEN_GLASS)) {
                WidgetChild count = Widgets.get(Bank.WIDGET_BANK, BankEx.COMPONENT_P2P_ITEM_COUNT);
                WidgetChild max = Widgets.get(Bank.WIDGET_BANK, BankEx.COMPONENT_P2P_ITEM_MAX);
                if (count.validate() && max.validate()) {
                    if (count.getText().equals(max.getText())) {
                        Bank.depositInventory();
                    } else {
                        Point p = Inventory.getItem(Constants.MOLTEN_GLASS).getWidgetChild().getNextViewportPoint();
                        Mouse.move(p);
                        Mouse.click(false);
                        if (Menu.isOpen() && Menu.contains("Deposit-All")) {
                            MenuEX.click("Deposit-All");
                        }
                    }
                }

            }
            getRunes();

            if (mouseKeys) {
                mousekeyBankItems();
                Utils.sleep(1200, 1300);
            } else {
                switch (Random.nextInt(1, 3)) {
                    case 1:
                        bankItem(Constants.BUCKET_OF_SAND);
                        bankItem(Constants.SECONDARY_ITEM);
                        break;
                    case 2:
                        bankItem(Constants.SECONDARY_ITEM);
                        bankItem(Constants.BUCKET_OF_SAND);
                        break;

                }
            }


        } else {
            SceneObject Chest = SceneEntities.getNearest(Bank.BANK_CHEST_IDS);

            if (Chest != null) {
                InteractionsEX.objectInteract(Chest.getId(), "Use");
                Utils.sleep(1000);
            } else {
                switch (Random.nextInt(0, 3)) {
                    case 1:
                        SceneObject BankBooth = SceneEntities.getNearest(Bank.BANK_BOOTH_IDS);
                        if (BankBooth != null && BankBooth.isOnScreen()) {
                            BankBooth.interact("Use-quickly");
                        }

                        Utils.sleep(1000);
                        break;
                    case 2:
                        NPC Banker = NPCs.getNearest(Bank.BANK_NPC_IDS);
                        if (Banker.isOnScreen() && Banker != null) {
                            Banker.interact("Bank ");
                            Utils.sleep(1000);
                        }
                        break;
                }
            }

            return Random.nextInt(0, 300);
        }
        return 0;
    }




    public String getStatus() {
        return "Restocking items";
    }

    @Override
    public boolean validate() {
        return Inventory.getCount(Constants.BUCKET_OF_SAND) != 13
                || Inventory.getCount(Constants.SECONDARY_ITEM) != 13
                || InventoryEX.contains(Constants.MOLTEN_GLASS)
                || !InventoryEX.containsAll(Constants.FIRE_RUNE, Constants.ASTRAL_RUNE);
    }

    @Override
    public void run() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
