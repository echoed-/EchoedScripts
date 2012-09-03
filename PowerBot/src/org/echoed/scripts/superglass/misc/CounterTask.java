package org.echoed.scripts.superglass.misc;


import org.powerbot.beta.core.script.job.LoopTask;
import org.powerbot.game.api.methods.widget.Bank;


public class CounterTask extends LoopTask implements Runnable {

    static boolean startup = true;
    private static int startMolten = -1;
    private static int curMolten;
    private static int startSecondary = -1;
    private static int startSand = -1;
    private static int curSecondary;
    private static int curSand;



    public static int getStartMolten() {

        return startMolten;
    }

    public static int getStartSecondary() {
        return startSecondary;
    }

    public static int getStartSand() {
        return startSand;
    }

    public static int getCurrentMolten() {
        return curMolten;
    }

    public static int getCurrentSand() {
        return curSand;
    }

    public static int getCurrentSecondary() {
        return curSecondary;
    }

    public static int getMoltenMade() {
        return curMolten - startMolten;
    }

    public static int getSecondaryUsed() {
        return startSecondary - curSecondary;
    }

    public static int getSandUsed() {
        return startSand - curSand;
    }




    @Override
    public int loop() {
        startup = startMolten == -1 || startMolten == 0;


        if (Bank.isOpen()) {
            if (startup) {

                startMolten = Bank.getItemCount(Constants.MOLTEN_GLASS);
                startSecondary = Bank.getItemCount(Constants.SECONDARY_ITEM);
                startSand = Bank.getItemCount(Constants.BUCKET_OF_SAND);
                startup = false;
            } else {

                curMolten = Bank.getItemCount(Constants.MOLTEN_GLASS);
                curSecondary = Bank.getItemCount(Constants.SECONDARY_ITEM);
                curSand = Bank.getItemCount(Constants.BUCKET_OF_SAND);

            }
        }
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void run() {
        loop();
    }
}
