package org.echoed.scripts.superglass.strategies;

import org.echoed.methods.InteractionsEX;
import org.echoed.methods.Utils;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class BankStrat extends Strategy implements Runnable, Condition {
    @Override
    public boolean validate() {
        return Players.getLocal().getAnimation() == 4413 && !Bank.isOpen();
    }

    @Override
    public void run() {
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
    }
}
