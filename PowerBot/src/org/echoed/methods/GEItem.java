package org.echoed.methods;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GEItem {
    private int ID;
    private String Name;
    private String Description;
    private String Type;
    private int Price;
    private String ImageSmall;
    private String ImageBig;
    private boolean MembersOnly;

    /*
      * Returns the integer value of a string in RS price notation (e.g. 171.7k -> 171700)
      */
    public static int priceToInt(String price) {
        return Integer.parseInt(price.toUpperCase().replaceAll("\\.([0-9])", "$1").replace("K", "00").replace("M", "00000").replace("B", "00000000").replaceAll(",", "").trim());
    }

    /*
      * Returns string with RS price notation, when given an integer (e.g. 171700 -> 171.7k)
      */
    public static String priceToString(int normalCoins) {
        return priceToString(Integer.toString(normalCoins));
    }

    /*
      * Returns string with RS price notation, when given an integer (e.g. 171700 -> 171.7k)
      */
    public static String priceToString(String normalCoins) {
        if (normalCoins.length() < 6)
            return normalCoins;

        return normalCoins.replaceAll("([0-9])[0-9]{" + ((normalCoins.length() < 8) ? 2 : ((normalCoins.length() < 10) ? 5 : 8)) + "}$", ".$1" + ((normalCoins.length() < 8) ? "K" : ((normalCoins.length() < 10) ? "M" : "B")));
    }

    public Image downloadImageSmall() {
        try {
            return ImageIO.read(new URL(this.ImageSmall));
        } catch (IOException e) {
            return null;
        }
    }

    public Image downloadImageBig() {
        try {
            return ImageIO.read(new URL(this.ImageBig));
        } catch (IOException e) {
            return null;
        }
    }

    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getImageSmall() {
        return ImageSmall;
    }

    public void setImageSmall(String imageSmall) {
        ImageSmall = imageSmall;
    }

    public String getImageBig() {
        return ImageBig;
    }

    public void setImageBig(String imageBig) {
        ImageBig = imageBig;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public boolean isMembersOnly() {
        return MembersOnly;
    }

    public void setMembersOnly(boolean membersOnly) {
        MembersOnly = membersOnly;
    }
}