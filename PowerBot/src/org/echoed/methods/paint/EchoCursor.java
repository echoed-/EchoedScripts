package org.echoed.methods.paint;

import org.powerbot.game.api.methods.input.Mouse;

import java.awt.*;
/**
 * Created with IntelliJ IDEA.
 * User: Andrew
 * Date: 8/22/12
 * Time: 12:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class EchoCursor {

    private final static Color MOUSE_BORDER = new Color(28, 105, 144);
    private final static Color MOUSE_COLOR = new Color(172, 211, 215);
    private final static Color MOUSE_CENTER = new Color(220, 235, 241);

    public static void drawMouse(Graphics g) {
        ((Graphics2D) g).setRenderingHints(new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON));
        Point p = Mouse.getLocation();
        Graphics2D spinG = (Graphics2D) g.create();
        Graphics2D spinGRev = (Graphics2D) g.create();
        Graphics2D spinG2 = (Graphics2D) g.create();
        spinG.setColor(MOUSE_BORDER);
        spinGRev.setColor(MOUSE_COLOR);
        spinG.rotate(System.currentTimeMillis() % 2000d / 2000d * (360d) * 2
                * Math.PI / 180.0, p.x, p.y);
        spinGRev.rotate(System.currentTimeMillis() % 2000d / 2000d * (-360d)
                * 2 * Math.PI / 180.0, p.x, p.y);
        final int outerSize = 25;
        final int innerSize = 10;
        spinG.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND));
        spinGRev.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND));
        spinG.drawArc(p.x - (outerSize / 2), p.y - (outerSize / 2), outerSize,
                outerSize, 100, 75);
        spinG.drawArc(p.x - (outerSize / 2), p.y - (outerSize / 2), outerSize,
                outerSize, -100, 75);
        spinGRev.drawArc(p.x - (innerSize / 2), p.y - (innerSize / 2),
                innerSize, innerSize, 100, 75);
        spinGRev.drawArc(p.x - (innerSize / 2), p.y - (innerSize / 2),
                innerSize, innerSize, -100, 75);
        //g.setColor(MOUSE_CENTER);
        //g.fillOval(p.x, p.y, 2, 2);
        spinG2.setColor(MOUSE_CENTER);
        spinG2.rotate(System.currentTimeMillis() % 2000d / 2000d * 360d
                * Math.PI / 180.0, p.x, p.y);
        spinG2.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND));
        //spinG2.drawLine(p.x - 4, p.y, p.x + 4, p.y);
        //spinG2.drawLine(p.x, p.y - 4, p.x, p.y + 4);

    }
}
