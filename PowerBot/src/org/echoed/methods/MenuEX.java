package org.echoed.methods;
import org.powerbot.game.api.methods.node.Menu;

/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 8/25/12
 * Time: 1:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class MenuEX {

    public static boolean click(String action){
       return Menu.clickIndex(Menu.getIndex(action));
    }
}
