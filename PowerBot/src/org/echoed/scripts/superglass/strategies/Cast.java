package org.echoed.scripts.superglass.strategies;

import org.echoed.methods.MagicEX;
import org.echoed.methods.Utils;
import org.echoed.methods.InventoryEX;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.widget.Bank;
import org.echoed.scripts.superglass.misc.Constants;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 8/24/12
 * Time: 4:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class Cast extends Strategy implements Runnable, Condition {
    MagicEX.Spell superglass = MagicEX.Lunar.SUPERGLASS_MAKE;

    @Override
    public boolean validate() {
        return InventoryEX.contains(Constants.BUCKET_OF_SAND) && InventoryEX.contains(Constants.SECONDARY_ITEM);
    }

    @Override
    public void run() {
        if (Bank.isOpen()) {
            Bank.close();
        }
        MagicEX.castSpell(superglass);
        Utils.sleep(2100);

    }
}
