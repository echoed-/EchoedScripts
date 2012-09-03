package org.echoed.scripts.superglass.misc;


public class Constants {


    public static final int BUCKET_OF_SAND = 1783;
    public static final int MOLTEN_GLASS = 1775;
    public static final int FIRE_RUNE = 554;
    public static final int ASTRAL_RUNE = 9075;
    public static final int[] AIR_STAFFS = {1381, 1397, 1405};
    public static int SECONDARY_ITEM;

    public enum SecondaryItem {
        SEAWEED(401, "Seaweed"),
        SODA_ASH(1781, "Soda Ash"),
        SWAMP_WEED(10978, "Swamp Weed");

        private int id;
        private String name;

        SecondaryItem(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }
    }

    public static String getSecondaryItemName(int id) {
        for (SecondaryItem i : SecondaryItem.values()) {
            if (i.getId() == id) {
                return i.getName();
            }
        }
        return null;
    }

    public static void setSecondaryItem(String name) {
        for (SecondaryItem i : SecondaryItem.values()) {
            if (i.getName().contains(name)) {
                SECONDARY_ITEM = i.getId();
            }
        }
    }
}


