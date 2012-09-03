package org.echoed.scripts.superglass.strategies;

import org.echoed.methods.Utils;
import org.powerbot.concurrent.strategy.Condition;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.interactive.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 8/24/12
 * Time: 4:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class Antiban extends Strategy implements Runnable, Condition {
    @Override
    public boolean validate() {
        return Random.nextInt(0, 25) == 20;
    }

    @Override
    public void run() {
        int rand = Random.nextInt(0, 5);

        switch (rand) {

            case 0:

                Mouse.move(Random.nextInt(20, 500), Random.nextInt(20, 500));
                break;
            case 1:

                Mouse.move(20, 202, 20, 20);
                break;
            case 2:
                if (Bank.isOpen()) {
                    Tabs.STATS.open();
                    Skills.getWidgetChild(Skills.WIDGET_MAGIC).hover();
                    Utils.sleep(2000, 2300);
                }

                break;
            case 3:
                if (!Bank.isOpen()) {
                    Tabs.STATS.open();
                    Skills.getWidgetChild(Skills.WIDGET_CRAFTING).hover();
                    Utils.sleep(1500, 3000);
                }
                break;
            case 4:

                Camera.setAngle(Random.nextInt(200, 1000));
                Camera.setPitch(Random.nextInt(200, 1000));
                break;
            case 5:
                if (!Bank.isOpen()) {
                    Player[] randomPlayer = Players.getLoaded(new Filter<Player>() {

                        public boolean accept(Player Player) {
                            return Player.isOnScreen();
                        }
                    });
                    if (randomPlayer != null && randomPlayer.length > 0) {

                        switch (Random.nextInt(0, 3)) {
                            case 0:
                                Mouse.move(randomPlayer[Random.nextInt(0,
                                        randomPlayer.length)].getNextViewportPoint());
                                break;
                            case 1:
                                Mouse.move(randomPlayer[Random.nextInt(0,
                                        randomPlayer.length)].getNextViewportPoint());
                                Mouse.click(false);
                                Mouse.move(Random.nextInt(200, 500), Random.nextInt(200, 500));
                                Utils.sleep(1030, 2234);
                                break;
                            case 2:
                                Utils.sleep(2000, 2300);
                        }
                    }
                    break;
                }
        }
    }
}
