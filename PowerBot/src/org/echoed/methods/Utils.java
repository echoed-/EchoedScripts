package org.echoed.methods;

import org.powerbot.beta.core.concurrent.Task;
import org.powerbot.game.api.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 8/22/12
 * Time: 12:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class Utils {
    public static void sleep(int min, int max) {
        int time = Random.nextInt(min,max);
        Task.sleep(time);

    }

    public static void sleep(int time) {
        Task.sleep(time);
    }
}
