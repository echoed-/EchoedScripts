package org.echoed.scripts.superglass;


import org.echoed.methods.*;
import org.echoed.methods.paint.EchoCursor;
import org.echoed.methods.paint.EchoPaint;
import org.echoed.scripts.superglass.misc.Constants;
import org.echoed.scripts.superglass.misc.CounterTask;
import org.echoed.scripts.superglass.misc.SuperGlassGUI;
import org.echoed.scripts.superglass.strategies.Antiban;
import org.echoed.scripts.superglass.strategies.BankStrat;
import org.echoed.scripts.superglass.strategies.Cast;
import org.echoed.scripts.superglass.strategies.Restock;
import org.powerbot.beta.core.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Equipment;
import org.powerbot.game.api.methods.tab.Magic;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.bot.event.MessageEvent;
import org.powerbot.game.bot.event.listener.MessageListener;
import org.powerbot.game.bot.event.listener.PaintListener;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.text.NumberFormat;
import java.util.LinkedList;
//testing
/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 8/21/12
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */
@Manifest(authors = { "Echoed" }, name = "EchoedSuperGlass", description = "Make's Molten Glass Using Super Glass Make", version = 1.0)
public class EchoedSuperGlass extends ActiveScript implements PaintListener,MessageListener {

    //paint&stats
    private String status = "initializing";
    private static long startTime;
    private SkillData CraftData;
    private static SkillData MageData;
    Manifest m = getClass().getAnnotation(Manifest.class);
    private final double VERSION = m.version();
    private Image logo = ImageEX.getLogo();
    private Image scriptName = ImageEX.getImage("Name.png",
            "http://i583.photobucket.com/albums/ss271/echoed/name.png",
            m.name());


    //item counts and prices
    public static double moltenMade = CounterTask.getMoltenMade();
    private static int moltenPrice;
    private static int[] ExpenseCosts = new int[4];
    private static final int[] backupCost = {4, 107, 82, 306};
    private static int[] ExpenseQty = new int[]{6, 2, 13, 13};
    public static double Profit;
    public static double ProfitPerHour;


    //gui and misc
    private static LinkedList<Strategy> strategies = new LinkedList<Strategy>();
    private SuperGlassGUI GUI;
    private static int mouseSpeed;
    MagicEX.Spell superglass = MagicEX.Lunar.SUPERGLASS_MAKE;
    private static boolean mouseKeys = false;
    private static boolean suicide = false;


    /*
      * Accessors Methods
      */
    public static long getStartTime() {
        return startTime;
    }

    public static SkillData getMagicData() {
        return MageData;
    }

    public static boolean getSuicide() {

        return suicide;
    }

    public static int getCast(){
        return getMagicData().xpGained() / 78;
    }

    public static boolean getMouseKeys() {
        // TODO Auto-generated method stub
        return mouseKeys;
    }

    public static int getMouseSpeed() {

        return mouseSpeed;
    }

    /*
      * Mutator Methods
      */
    public static void setProfit( ) {
        double TotalExpenses = 0;
        for (int i = 0; i < ExpenseCosts.length; i++) {
            TotalExpenses += ExpenseCosts[i] * ExpenseQty[i] * getCast();
        }
        Profit = (moltenMade * moltenPrice) - TotalExpenses;
    }

    public static void setMouseSpeed(final int speed) {
        mouseSpeed = speed;
    }

    public static void setMouseKeys(final boolean b) {
        mouseKeys = b;
    }



    @Override
    protected void setup() {

        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    GUI = new SuperGlassGUI();
                    GUI.setVisible(true);
                }
            });
        } catch (InterruptedException e) {
            e.getStackTrace();
        } catch (InvocationTargetException e) {
            e.getStackTrace();
        }

        while (GUI.isVisible()) {
            Task.sleep(50);
        }

        GEItem[] expenses = new GEItem[]{
                GrandExchange.getGEItemByID(Constants.FIRE_RUNE),
                GrandExchange.getGEItemByID(Constants.ASTRAL_RUNE),
                GrandExchange.getGEItemByID(Constants.SECONDARY_ITEM),
                GrandExchange.getGEItemByID(Constants.BUCKET_OF_SAND)};
        GEItem molten = GrandExchange.getGEItemByID(Constants.MOLTEN_GLASS);
        if (molten != null) {
            moltenPrice = molten.getPrice();
            System.out.println(molten.getName() + ":" + molten.getPrice());
        } else {
            moltenPrice = 375;
        }
        for (int i = 0; i < expenses.length; i++) {
            if (expenses[i] != null) {
                ExpenseCosts[i] = expenses[i].getPrice();
                System.out.println(expenses[i].getName() + ":" + ExpenseCosts[i]);
            } else {
                ExpenseCosts[i] = backupCost[i];
            }
        }
        //log(getMouseKeys() ? Color.GREEN : Color.red, getMouseKeys() ? "MouseKeys: Enabled" : "MouseKeys: Disabled");
        //log(Color.GREEN, "Mouse Speed(1-10): " + getMouseSpeed() + " (lower is faster)");
        //log(Color.GREEN, "Secondary Item: " + Constants.getSecondaryItemName(Constants.SECONDARY_ITEM));
        if (Game.isLoggedIn()) {
            //Mouse.setSpeed(mouseSpeed);
            if (Skills.getLevel(Skills.MAGIC) < 77) {
                //log(Color.red, "Must have 77 magic");
                kill();
            }
            if (MagicEX.getCurrentBook() != MagicEX.Book.LUNAR) {
                //log(Color.red, "Must be on Lunar Magic");
                kill();

            }
            if (!Equipment.containsOneOf(Constants.AIR_STAFFS) || !Equipment.appearanceContainsOneOf(Constants.AIR_STAFFS)) {
                //log(Color.red, "Start with a air staff equipped");
               kill();
            }

            startTime = System.currentTimeMillis();
            submit(new CounterTask());
            provide(new Restock());
            provide(new Cast());
            provide(new Antiban());
            provide(new BankStrat());

            CraftData = new SkillData(Skills.CRAFTING);
            MageData = new SkillData(Skills.MAGIC);

        } else {
            //log(Color.red, "Please log in before using script");
           kill();
        }

    }



    @Override
    public void messageReceived(MessageEvent messageEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onRepaint(Graphics g) {
        String TimeElapsed = CalculationsEX.parseTime(System.currentTimeMillis() - getStartTime());
        ProfitPerHour = CalculationsEX.perHour(Profit, getStartTime());
        double MoltenPerHour = CalculationsEX.perHour(moltenMade, getStartTime());
        double CastPerHour = CalculationsEX.perHour(getCast(), getStartTime());
        g.drawImage(logo, 0, 0, null);
        g.drawImage(scriptName, 10, 310, null);
        g.setColor(new Color(251, 73, 91, 100));
        g.fillRect(212, 270 - 10, 300, 64 + 10);
        g.setColor(Color.BLACK);
        g.drawRect(212, 270 - 10, 300, 64 + 10);
        EchoPaint.drawProgBar(g, new Color(106, 86, 69, 180), 215, 320, 140, 10, CraftData);
        EchoPaint.drawProgBar(g, new Color(39, 43, 190, 180), 215, 320 - 35 - 10, 140, 10, MageData);

        int col2baseY = 270;
        int col2baseX = 370;
        g.drawString("Status:" + status, col2baseX, col2baseY);
        g.drawString("Elapsed: " + TimeElapsed, col2baseX, col2baseY + 12);
        g.drawString("Profit p/h: " + NumberFormat.getIntegerInstance().format(ProfitPerHour), col2baseX, col2baseY + 24);
        g.drawString("Cast p/h: " + NumberFormat.getIntegerInstance().format(CastPerHour), col2baseX, col2baseY + 36);


        g.drawString("Molten p/h: " + NumberFormat.getIntegerInstance().format(MoltenPerHour), col2baseX, col2baseY + 48);
        g.drawString("Molten Made: " + NumberFormat.getIntegerInstance().format(moltenMade), col2baseX, col2baseY + 60);
        g.drawString("V" + VERSION, 480, col2baseY + 60);
        EchoCursor.drawMouse(g);
    }


}
