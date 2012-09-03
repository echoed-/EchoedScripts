package org.echoed.methods.paint;


import org.echoed.methods.SkillData;

import java.awt.*;
import java.awt.font.GraphicAttribute;

public class EchoPaint {
    public static void drawStatBar(final Graphics g, final Color barColor, final int idx){
        Graphics2D g1 = (Graphics2D) g;
        g1.setColor(Color.BLACK);

    }
    public static void drawProgBar(final Graphics g, Color progColor, int x, int y, int w, int height, SkillData skill) {
        int pctTL = skill.percentTL();
        int width = (int) ((w - 2) * (double) pctTL / 100);

        final BasicStroke STROKE_1 = new BasicStroke(1);

        // outline
        g.setColor(Color.BLACK);
        ((Graphics2D) g).setStroke(STROKE_1);
        g.drawRect(x, y, w + 1, height);

        //background (black)
        g.setColor(new Color(0, 0, 0));
        g.fillRect(x, y, width, height);

        // progress
        g.setColor(progColor);
        g.fillRect(x + 1, y + 1, w, height - 1);

        // white overlay
        g.setColor(new Color(255, 255, 255, 50));
        g.fillRect(x + 1, y + 1, width, (height / 2));

        g.setColor(new Color(255, 255, 255, 150));
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, height));
        g.drawString("xp p/h: " + skill.xpPH() + " | " + skill.timeToLevel(), x, y - 1);
        g.drawString(skill.curLevel() + "(" + skill.levelsGained() + ") " + skill.getName() + " | " + skill.percentTL() + "%", x + skill.getName().length(), y + height - 1);
    }
}
