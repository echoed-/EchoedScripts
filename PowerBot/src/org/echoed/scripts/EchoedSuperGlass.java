package org.echoed.scripts;


import org.echoed.methods.ImageEX;
import org.echoed.methods.SkillData;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.bot.event.MessageEvent;
import org.powerbot.game.bot.event.listener.MessageListener;
import org.powerbot.game.bot.event.listener.PaintListener;

import java.awt.*;
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
    Manifest m = getClass().getAnnotation(Manifest.class);
    private final double VERSION = m.version();
    private SkillData craftData;
    private SkillData magicData;
    private Image logo = ImageEX.getLogo();
    private Image scriptNmae = ImageEX.getImage(
            "EchoedSuperGlass.png",
            "http://i583.photobucket.com/albums/ss271/echoed/name.png",
            m.name()
    );


    @Override
    protected void setup() {

    }

    @Override
    public void messageReceived(MessageEvent messageEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onRepaint(Graphics graphics) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
