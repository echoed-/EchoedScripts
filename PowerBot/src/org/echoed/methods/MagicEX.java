package org.echoed.methods;

import java.awt.*;


import org.powerbot.beta.core.concurrent.Task;
import org.powerbot.game.api.methods.Settings;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.wrappers.node.SceneObjectDefinition;
import org.powerbot.game.api.wrappers.widget.Widget;
import org.powerbot.game.api.wrappers.widget.WidgetChild;


/**
 * MagicEX tab related operations.
 */
public class MagicEX {

      /**
        * MagicEX spell books.
        */
              public static enum Book {
            MODERN(192, 94),
            ANCIENT(193, 51),
            LUNAR(430, 62),
            DUNGEONEERING(950, 70);

            private final int scrollId;
            private final int widgetId;

            Book(int widgetId, int scrollId) {
                  this.scrollId = scrollId;
                  this.widgetId = widgetId;
                }

            public int getScrollBarId() {
                  return scrollId;
                }

            public int getWidgetId() {
                  return widgetId;
                }
          }

      /**
        * MagicEX spellbook buttons.
        *
        * @author kiko
        */
              public static enum Button {
            CAST_DEFENSIVE(2, 18, 20),
            FILTER_COMBAT(7, 5, 5),
            FILTER_TELEPORT(9, 7, 7),
            FILTER_MISC(11, -1, 9),
            FILTER_SKILLS(13, -1, 9),
            SORT_LEVEL(15, 9, 11),
            SORT_COMBAT(16, 10, 12),
            SORT_TELEPORT(17, 11, 13);

            private final int modernId;
            private final int ancientId;
            private final int lunarId;

            Button(int modernId, int ancientId, int lunarId) {
                  this.modernId = modernId;
                  this.ancientId = modernId;
                  this.lunarId = lunarId;
                }

            public int getComponentId() {
                  switch (getCurrentBook()) {
                        case ANCIENT:
                              return ancientId;
                        case LUNAR:
                              return lunarId;
                        default:
                              return modernId;
                      }
                }
          }

      /**
        * MagicEX spells.
        *
        * @author kiko
        */
              public static interface Spell {

            /**
              * Returns the amounts of each rune required to cast this spell.
              *
              * @return the amount of each rune required to cast this spell.
              */
                    public int[] getAmounts();

            /**
              * Returns the MagicEX Book that contains this spell.
              *
              * @return the MagicEX Book that contains this spell.
              */
                    public Book getBook();

            /**
              * Returns the button that filters this spell.
              *
              * @return the button that filters this spell.
              */
                    public Button getButton();

            /**
              * Returns the spell's component id within the magic tab.
              *
              * @return the spell's component id within the magic tab.
              */
                    public int getComponentId();

            /**
              * Returns the spell's texture id within the magic tab.
              *
              * @return the spell's texture id within the magic tab.
              */
                    public int getGreyTextureId();

            /**
              * Returns the magic level requirement to cast this spell.
              *
              * @return the magic level requirement to cast this spell.
              */
                    public int getLevel();

            /**
              * Returns the formatted name of this spell.
              *
              * @return the formatted name of this spell.
              */
                    public String getName();

            /**
              * Returns the runes required to cast this spell. Each array contains all possible rune ids.
              *
              * @return the runes required to cast this spell. Each array contains all possible rune ids.
              */
                    public int[][] getRunes();
          }

      public static enum Modern implements Spell {
            WIND_RUSH(98, 1, -1, Button.FILTER_COMBAT, new int[][]{RUNES_AIR}, 2),
                    WIND_STRIKE(25, 1, 65, Button.FILTER_COMBAT, new int[][]{RUNES_AIR, RUNES_MIND}, 1, 1),
                    CONFUSE(26, 3, 66, Button.FILTER_COMBAT, new int[][]{RUNES_WATER, RUNES_EARTH, RUNES_BODY}, 3, 2, 1),
                    WATER_STRIKE(28, 5, 67, Button.FILTER_COMBAT, new int[][]{RUNES_WATER, RUNES_AIR, RUNES_MIND}, 1, 1, 1),
                    EARTH_STRIKE(30, 9, 69, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_AIR, RUNES_MIND}, 2, 1, 1),
                    WEAKEN(31, 11, 70, Button.FILTER_COMBAT, new int[][]{RUNES_WATER, RUNES_EARTH, RUNES_BODY}, 3, 2, 1),
                    FIRE_STRIKE(32, 13, 71, Button.FILTER_COMBAT, new int[][]{RUNES_FIRE, RUNES_AIR, RUNES_MIND}, 3, 2, 1),
                    WIND_BOLT(34, 17, 73, Button.FILTER_COMBAT, new int[][]{RUNES_AIR, RUNES_CHAOS}, 2, 1),
                    CURSE(35, 19, 74, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_WATER, RUNES_BODY}, 3, 2, 1),
                    BIND(36, 20, 369, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_WATER, RUNES_NATURE}, 3, 3, 2),
                    WATER_BOLT(39, 23, 76, Button.FILTER_COMBAT, new int[][]{RUNES_AIR, RUNES_WATER, RUNES_CHAOS}, 2, 2, 1),
                    EARTH_BOLT(42, 29, 79, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_AIR, RUNES_CHAOS}, 3, 2, 1),
                    FIRE_BOLT(45, 35, 82, Button.FILTER_COMBAT, new int[][]{RUNES_FIRE, RUNES_AIR, RUNES_CHAOS}, 4, 3, 1),
                    CRUMBLE_UNDEAD(47, 39, 84, Button.FILTER_COMBAT, new int[][]{RUNES_AIR, RUNES_EARTH, RUNES_CHAOS}, 2, 2, 1),
                    WIND_BLAST(49, 41, 85, Button.FILTER_COMBAT, new int[][]{RUNES_AIR, RUNES_DEATH}, 3, 1),
                    WATER_BLAST(52, 47, 88, Button.FILTER_COMBAT, new int[][]{RUNES_AIR, RUNES_WATER, RUNES_DEATH}, 3, 3, 1),
                    IBAN_BLAST(54, 50, 103, Button.FILTER_COMBAT, new int[][]{RUNES_FIRE, RUNES_DEATH}, 5, 1),
                    SNARE(55, 50, 370, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_WATER, RUNES_NATURE}, 4, 4, 3),
                    MAGIC_DART(56, 50, 374, Button.FILTER_COMBAT, new int[][]{RUNES_MIND, RUNES_DEATH}, 4, 1),
                    EARTH_BLAST(58, 53, 90, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_AIR, RUNES_DEATH}, 4, 3, 1),
                    FIRE_BLAST(63, 59, 94, Button.FILTER_COMBAT, new int[][]{RUNES_FIRE, RUNES_AIR, RUNES_DEATH}, 5, 4, 1),
                    CLAWS_OF_GUTHIX(67, 60, 110, Button.FILTER_COMBAT, new int[][]{RUNES_AIR, RUNES_BLOOD, RUNES_FIRE}, 4, 2, 1),
                    FLAMES_OF_ZAMORAK(68, 60, 109, Button.FILTER_COMBAT, new int[][]{RUNES_FIRE, RUNES_BLOOD, RUNES_AIR}, 4, 2,
                        1),
                    SARADOMIN_STRIKE(66, 60, 111, Button.FILTER_COMBAT, new int[][]{RUNES_AIR, RUNES_BLOOD, RUNES_FIRE}, 4, 2,
                        2),
                    WIND_WAVE(70, 62, 96, Button.FILTER_COMBAT, new int[][]{RUNES_AIR, RUNES_BLOOD}, 5, 1),
                    WATER_WAVE(73, 65, 98, Button.FILTER_COMBAT, new int[][]{RUNES_WATER, RUNES_AIR, RUNES_BLOOD}, 7, 5, 1),
                    VULNERABILITY(75, 66, 106, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_WATER, RUNES_COSMIC}, 5, 5,
                        1),
                    EARTH_WAVE(77, 70, 101, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_AIR, RUNES_BLOOD}, 7, 5, 1),
                    ENFEEBLE(78, 73, 107, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_WATER, RUNES_SOUL}, 8, 8, 1),
                    FIRE_WAVE(80, 75, 102, Button.FILTER_COMBAT, new int[][]{RUNES_FIRE, RUNES_AIR, RUNES_BLOOD}, 7, 5, 1),
                    ENTANGLE(81, 79, 371, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_WATER, RUNES_NATURE}, 5, 5, 4),
                    STUN(82, 80, 108, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_WATER, RUNES_SOUL}, 12, 12, 1),
                    CHARGE(83, 80, 372, Button.FILTER_COMBAT, new int[][]{RUNES_AIR, RUNES_BLOOD, RUNES_FIRE}, 3, 3, 3),
                    WIND_SURGE(84, 81, 815, Button.FILTER_COMBAT, new int[][]{RUNES_AIR, RUNES_BLOOD, RUNES_DEATH}, 7, 1, 1),
                    TELEPORT_BLOCK(86, 85, 1571, Button.FILTER_COMBAT, new int[][]{RUNES_CHAOS, RUNES_DEATH, RUNES_LAW}, 1, 1,
                        1),
                    WATER_SURGE(87, 85, 816, Button.FILTER_COMBAT, new int[][]{RUNES_WATER, RUNES_AIR, RUNES_BLOOD,
                    RUNES_DEATH}, 10, 7, 1, 1),
                    EARTH_SURGE(90, 90, 817, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_AIR, RUNES_BLOOD,
                    RUNES_DEATH}, 10, 7, 1, 1),
                    FIRE_SURGE(91, 95, 818, Button.FILTER_COMBAT, new int[][]{RUNES_FIRE, RUNES_AIR, RUNES_BLOOD,
                    RUNES_DEATH}, 10, 7, 1, 1),

                    HOME_TELEPORT(24, 0, -1, Button.FILTER_TELEPORT, null),
            MOBILISING_ARMIES_TELEPORT(37, 10, 1570, Button.FILTER_TELEPORT, new int[][]{RUNES_LAW, RUNES_WATER,
                    RUNES_AIR}, 1, 1, 1),
                    VARROCK_TELEPORT(40, 25, 77, Button.FILTER_TELEPORT, new int[][]{RUNES_AIR, RUNES_FIRE, RUNES_LAW}, 3, 1, 1),
                    LUMBRIDGE_TELEPORT(43, 31, 80, Button.FILTER_TELEPORT, new int[][]{RUNES_AIR, RUNES_EARTH, RUNES_LAW}, 3, 1,
                        1),
                    FALADOR_TELEPORT(46, 37, 83, Button.FILTER_TELEPORT, new int[][]{RUNES_AIR, RUNES_WATER, RUNES_LAW}, 3, 1,
                        1),
                    TELEPORT_TO_HOUSE(48, 40, 405, Button.FILTER_TELEPORT, new int[][]{RUNES_AIR, RUNES_EARTH, RUNES_LAW}, 1, 1,
                        1),
                    CAMELOT_TELEPORT(51, 45, 87, Button.FILTER_TELEPORT, new int[][]{RUNES_AIR, RUNES_LAW}, 5, 1),
                    ARDOUGNE_TELEPORT(57, 51, 104, Button.FILTER_TELEPORT, new int[][]{RUNES_WATER, RUNES_LAW}, 2, 2),
                    WATCHTOWER_TELEPORT(62, 58, 105, Button.FILTER_TELEPORT, new int[][]{RUNES_EARTH, RUNES_LAW}, 2, 2),
                    TROLLHEIM_TELEPORT(69, 61, 373, Button.FILTER_TELEPORT, new int[][]{RUNES_FIRE, RUNES_LAW}, 2, 2),
                    APE_ATOL_TELEPORT(72, 64, 407, Button.FILTER_TELEPORT, new int[][]{RUNES_FIRE, RUNES_LAW, RUNES_WATER,
                    BANANAS}, 2, 2, 2, 1),
                    TELEOTHER_LUMBRIDGE(79, 74, 399, Button.FILTER_TELEPORT, new int[][]{RUNES_EARTH, RUNES_LAW,
                    RUNES_SOUL}, 1, 1, 1),
                    TELEOTHER_FALADOR(85, 82, 400, Button.FILTER_TELEPORT, new int[][]{RUNES_LAW, RUNES_SOUL,
                    RUNES_WATER}, 1, 1, 1),
                    TELEOTHER_CAMELOT(89, 90, 401, Button.FILTER_TELEPORT, new int[][]{RUNES_SOUL, RUNES_LAW}, 2, 1),

                    BONES_TO_BANANAS(33, 15, 72, Button.FILTER_MISC, new int[][]{RUNES_EARTH, RUNES_WATER, RUNES_NATURE}, 2, 2,
                        1),
                    LOW_LEVEL_ALCHEMY(38, 21, 75, Button.FILTER_MISC, new int[][]{RUNES_FIRE, RUNES_NATURE}, 3, 1),
                    TELEKINETIC_GRAB(44, 33, 81, Button.FILTER_MISC, new int[][]{RUNES_AIR, RUNES_LAW}, 1, 1),
                    HIGH_LEVEL_ALCHEMY(59, 55, 91, Button.FILTER_MISC, new int[][]{RUNES_FIRE, RUNES_NATURE}, 5, 1),
                    BONES_TO_PEACHES(65, 60, 404, Button.FILTER_MISC, new int[][]{RUNES_EARTH, RUNES_WATER, RUNES_NATURE}, 4, 4,
                        2),

                    ENCHANT_CROSSBOW_BOLT(27, 4, -1, Button.FILTER_SKILLS, null),
            LVL1_ENCHANT(29, 7, 68, Button.FILTER_SKILLS, new int[][]{RUNES_WATER, RUNES_COSMIC}, 1, 1),
                    LVL2_ENCHANT(41, 27, 78, Button.FILTER_SKILLS, new int[][]{RUNES_AIR, RUNES_COSMIC}, 3, 1),
                    SUPERHEAT_ITEM(50, 43, 86, Button.FILTER_SKILLS, new int[][]{RUNES_FIRE, RUNES_NATURE}, 4, 1),
                    LVL3_ENCHANT(53, 49, 89, Button.FILTER_SKILLS, new int[][]{RUNES_FIRE, RUNES_COSMIC}, 5, 5, 1),
                    CHARGE_WATER_ORB(60, 56, 92, Button.FILTER_SKILLS, new int[][]{RUNES_WATER, RUNES_COSMIC,
                    UNPOWERED_ORB}, 30, 3, 1),
                    LVL4_ENCHANT(61, 57, 93, Button.FILTER_SKILLS, new int[][]{RUNES_EARTH, RUNES_COSMIC}, 10, 1),
                    CHARGE_EARTH_ORB(64, 60, 95, Button.FILTER_SKILLS, new int[][]{RUNES_EARTH, RUNES_COSMIC,
                    UNPOWERED_ORB}, 30, 3, 1),
                    CHARGE_FIRE_ORB(71, 63, 97, Button.FILTER_SKILLS, new int[][]{RUNES_FIRE, RUNES_COSMIC,
                    UNPOWERED_ORB}, 30, 3, 1),
                    CHARGE_AIR_ORB(74, 66, 99, Button.FILTER_SKILLS, new int[][]{RUNES_AIR, RUNES_COSMIC, UNPOWERED_ORB}, 30, 3,
                        1),
                    LVL5_ENCHANT(76, 68, 100, Button.FILTER_SKILLS, new int[][]{RUNES_EARTH, RUNES_WATER, RUNES_COSMIC}, 15, 15,
                        1),
                    LVL6_ENCHANT(88, 87, 403, Button.FILTER_SKILLS, new int[][]{RUNES_EARTH, RUNES_FIRE, RUNES_COSMIC}, 20, 20,
                        1);

            private final int[][] runes;
            private final int[] amounts;
            private final Button button;
            private final int componentId;
            private final int level;
            private final int textureId;

            private Modern(int componentId, int level, int textureId, Button button, int[][] runes, int... amounts) {
                  this.button = button;
                  this.componentId = componentId;
                  this.textureId = textureId;
                  this.level = level;
                  this.runes = runes;
                  this.amounts = amounts;
                }

            public int[] getAmounts() {
                  return amounts;
                }

            public Book getBook() {
                  return Book.MODERN;
                }

            public Button getButton() {
                  return button;
                }

            public int getComponentId() {
                  return componentId;
                }

            public int getGreyTextureId() {
                  return textureId;
                }

            public int getLevel() {
                  return level;
                }

            public String getName() {
                  StringBuilder name = new StringBuilder();
                  for (String s : this.getName().split("_")) {
                        name.append(s.substring(0, 1) + s.substring(1).toLowerCase() + " ");
                      }
                  return name.toString().trim();
                }

            public int[][] getRunes() {
                  return runes;
                }
          }

      public static enum Ancient implements Spell {
            ICE_RUSH(20, 58, 375, Button.FILTER_COMBAT, new int[][]{RUNES_CHAOS, RUNES_DEATH, RUNES_WATER}, 2, 2, 2),
                    ICE_BLITZ(21, 82, 377, Button.FILTER_COMBAT, new int[][]{RUNES_WATER, RUNES_BLOOD, RUNES_DEATH}, 3, 2, 2),
                    ICE_BURST(22, 70, 376, Button.FILTER_COMBAT, new int[][]{RUNES_CHAOS, RUNES_WATER, RUNES_DEATH}, 4, 4, 2),
                    ICE_BARRAGE(23, 94, 378, Button.FILTER_COMBAT, new int[][]{RUNES_WATER, RUNES_DEATH, RUNES_BLOOD}, 6, 4, 2),
                    BLOOD_RUSH(24, 56, 383, Button.FILTER_COMBAT, new int[][]{RUNES_CHAOS, RUNES_DEATH, RUNES_BLOOD}, 2, 2, 1),
                    BLOOD_BLITZ(25, 80, 385, Button.FILTER_COMBAT, new int[][]{RUNES_BLOOD, RUNES_DEATH}, 4, 2),
                    BLOOD_BURST(26, 68, 384, Button.FILTER_COMBAT, new int[][]{RUNES_CHAOS, RUNES_BLOOD, RUNES_DEATH}, 4, 2, 2),
                    BLOOD_BARRAGE(27, 92, 386, Button.FILTER_COMBAT, new int[][]{RUNES_BLOOD, RUNES_DEATH, RUNES_SOUL}, 4, 4, 1),
                    SMOKE_RUSH(28, 50, 379, Button.FILTER_COMBAT, new int[][]{RUNES_CHAOS, RUNES_DEATH, RUNES_AIR,
                    RUNES_FIRE}, 2, 2, 1, 1),
                    SMOKE_BLITZ(29, 74, 381, Button.FILTER_COMBAT, new int[][]{RUNES_AIR, RUNES_BLOOD, RUNES_DEATH,
                    RUNES_FIRE}, 2, 2, 2, 2),
                    SMOKE_BURST(30, 62, 380, Button.FILTER_COMBAT, new int[][]{RUNES_CHAOS, RUNES_AIR, RUNES_DEATH,
                    RUNES_FIRE}, 4, 2, 2, 2),
                    SMOKE_BARRAGE(31, 86, 382, Button.FILTER_COMBAT, new int[][]{RUNES_AIR, RUNES_DEATH, RUNES_FIRE,
                    RUNES_BLOOD}, 4, 4, 4, 2),
                    SHADOW_RUSH(32, 52, 387, Button.FILTER_COMBAT, new int[][]{RUNES_CHAOS, RUNES_DEATH, RUNES_AIR,
                    RUNES_SOUL}, 2, 2, 1, 1),
                    SHADOW_BLITZ(33, 76, 389, Button.FILTER_COMBAT, new int[][]{RUNES_AIR, RUNES_BLOOD, RUNES_DEATH,
                    RUNES_SOUL}, 2, 2, 2, 2),
                    SHADOW_BURST(34, 64, 388, Button.FILTER_COMBAT, new int[][]{RUNES_CHAOS, RUNES_AIR, RUNES_DEATH,
                    RUNES_SOUL}, 4, 2, 2, 2),
                    SHADOW_BARRAGE(35, 88, 390, Button.FILTER_COMBAT, new int[][]{RUNES_AIR, RUNES_DEATH, RUNES_SOUL,
                    RUNES_BLOOD}, 4, 4, 3, 2),
                    MIASMIC_RUSH(36, 62, 1574, Button.FILTER_COMBAT, new int[][]{RUNES_CHAOS, RUNES_EARTH, RUNES_SOUL}, 2, 1, 1),
                    MIASMIC_BLITZ(37, 85, 1573, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_SOUL, RUNES_BLOOD}, 3, 3,
                        2),
                    MIASMIC_BURST(38, 73, 1575, Button.FILTER_COMBAT, new int[][]{RUNES_CHAOS, RUNES_EARTH, RUNES_SOUL}, 4, 2,
                        2),
                    MIASMIC_BARRAGE(39, 97, 1572, Button.FILTER_COMBAT, new int[][]{RUNES_BLOOD, RUNES_EARTH, RUNES_SOUL}, 4, 4,
                        4),

                    HOME_TELEPORT(48, 0, -1, Button.FILTER_TELEPORT, null),
            PADDEWWA_TELEPORT(40, 54, 391, Button.FILTER_TELEPORT, new int[][]{RUNES_LAW, RUNES_AIR, RUNES_FIRE}, 2, 1,
                        1),
                    SENNTISTEN_TELEPORT(41, 60, 392, Button.FILTER_TELEPORT, new int[][]{RUNES_LAW, RUNES_SOUL}, 2, 1),
                    KHARYRLL_TELEPRT(42, 66, 393, Button.FILTER_TELEPORT, new int[][]{RUNES_LAW, RUNES_BLOOD}, 2, 1),
                    LASSAR_TELEPORT(43, 72, 394, Button.FILTER_TELEPORT, new int[][]{RUNES_WATER, RUNES_LAW}, 4, 2),
                    DAREEYAK_TELEPORT(44, 78, 395, Button.FILTER_TELEPORT, new int[][]{RUNES_FIRE, RUNES_LAW, RUNES_AIR}, 4, 2,
                        2),
                    CARRALLANGER_TELEPORT(45, 84, 396, Button.FILTER_TELEPORT, new int[][]{RUNES_LAW, RUNES_SOUL}, 2, 2),
                    ANNAKARL_TELEPORT(46, 90, 397, Button.FILTER_TELEPORT, new int[][]{RUNES_LAW, RUNES_BLOOD}, 2, 2),
                    GHORROCK_TELEPORT(47, 96, 398, Button.FILTER_TELEPORT, new int[][]{RUNES_WATER, RUNES_LAW}, 8, 2);

            private final int[][] runes;
            private final int[] amounts;
            private final Button button;
            private final int componentId;
            private final int level;
            private final int textureId;

            private Ancient(int componentId, int level, int textureId, Button button, int[][] runes, int... amounts) {
                  this.button = button;
                  this.componentId = componentId;
                  this.textureId = textureId;
                  this.level = level;
                  this.runes = runes;
                  this.amounts = amounts;
                }

            public int[] getAmounts() {
                  return amounts;
                }

            public Book getBook() {
                  return Book.ANCIENT;
                }

            public Button getButton() {
                  return button;
                }

            public int getComponentId() {
                  return componentId;
                }

            public int getGreyTextureId() {
                  return textureId;
                }

            public int getLevel() {
                  return level;
                }

            public String getName() {
                  StringBuilder name = new StringBuilder();
                  for (String s : this.getName().split("_")) {
                        name.append(s.substring(0, 1) + s.substring(1).toLowerCase() + " ");
                      }
                  return name.toString().trim();
                }

            public int[][] getRunes() {
                  return runes;
                }
          }

      public static enum Lunar implements Spell {
            MONSTERS_EXAMINE(28, 66, 627, Button.FILTER_COMBAT, new int[][]{RUNES_ASTRAL, RUNES_COSMIC,
                    RUNES_MIND}, 1, 1, 1),
                    CURE_OTHER(23, 68, 609, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_ASTRAL}, 10, 1),
                    CURE_ME(46, 69, 612, Button.FILTER_COMBAT, new int[][]{RUNES_ASTRAL, RUNES_COSMIC}, 2, 2),
                    CURE_GROUP(25, 74, 615, Button.FILTER_COMBAT, new int[][]{RUNES_ASTRAL, RUNES_COSMIC}, 2, 2),
                    SPIRITUALIZE_FOOD(71, 80, 5645, Button.FILTER_COMBAT, new int[][]{RUNES_BODY, RUNES_COSMIC,
                    RUNES_ASTRAL}, 5, 3, 2),
                    STAT_RESTORE_POT_SHARE(50, 81, 604, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_WATER,
                    RUNES_ASTRAL}, 10, 10, 2),
                    BOOST_POTION_SHARE(49, 84, 601, Button.FILTER_COMBAT, new int[][]{RUNES_WATER, RUNES_ASTRAL,
                    RUNES_LAW}, 10, 3, 3),
                    DISRUPTION_SHIELD(73, 90, 5646, Button.FILTER_COMBAT, new int[][]{RUNES_BODY, RUNES_ASTRAL,
                    RUNES_BLOOD}, 10, 3, 3),
                    HEAL_OTHER(52, 92, 610, Button.FILTER_COMBAT, new int[][]{RUNES_ASTRAL, RUNES_LAW, RUNES_BLOOD}, 3, 3, 1),
                    VENGEANCE_OTHER(42, 93, 611, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_ASTRAL,
                    RUNES_DEATH}, 10, 3, 2),
                    VENGEANCE(37, 94, 614, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_ASTRAL, RUNES_DEATH}, 10, 4, 2),
                    VENGEANCE_GROUP(74, 95, 5647, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_ASTRAL,
                    RUNES_DEATH}, 11, 4, 3),
                    HEAL_GROUP(53, 95, 616, Button.FILTER_COMBAT, new int[][]{RUNES_LAW, RUNES_ASTRAL, RUNES_BLOOD}, 6, 4, 3),
                    HOME_TELEPORT_LUNAR(39, 0, -1, Button.FILTER_TELEPORT, null),
            MOONCLAN_TELEPORT(43, 69, 594, Button.FILTER_TELEPORT, new int[][]{RUNES_ASTRAL, RUNES_EARTH,
                    RUNES_LAW}, 2, 2, 1),
                    TELE_GROUP_MOONCLAN(56, 70, 619, Button.FILTER_TELEPORT, new int[][]{RUNES_EARTH, RUNES_ASTRAL,
                    RUNES_LAW}, 4, 2, 1),
                    OURANIA_TELEPORT(54, 71, 633, Button.FILTER_TELEPORT, new int[][]{RUNES_EARTH, RUNES_ASTRAL,
                    RUNES_LAW}, 6, 2, 1),
                    SOUTH_FALADOR_TELEPORT(67, 72, 5640, Button.FILTER_TELEPORT, new int[][]{RUNES_AIR, RUNES_ASTRAL,
                    RUNES_LAW}, 2, 2, 1),
                    WATERBIRTH_TELEPORT(47, 72, 595, Button.FILTER_TELEPORT, new int[][]{RUNES_ASTRAL, RUNES_LAW,
                    RUNES_WATER}, 2, 1, 1),
                    TELE_GROUP_WATERBIRTH(57, 73, 620, Button.FILTER_TELEPORT, new int[][]{RUNES_ASTRAL, RUNES_LAW,
                    RUNES_WATER}, 2, 1, 1),
                    BARBARIAN_TELEPORT(22, 75, 597, Button.FILTER_TELEPORT, new int[][]{RUNES_FIRE, RUNES_ASTRAL,
                    RUNES_LAW}, 3, 2, 2),
                    TELE_GROUP_BARBARIAN(58, 76, 621, Button.FILTER_TELEPORT, new int[][]{RUNES_FIRE, RUNES_ASTRAL,
                    RUNES_LAW}, 6, 2, 2),
                    NORTH_ARDOUGNE_TELEPORT(69, 76, 5642, Button.FILTER_TELEPORT, new int[][]{RUNES_WATER, RUNES_ASTRAL,
                    RUNES_LAW}, 5, 2, 1),
                    KHAZARD_TELEPORT(41, 78, 599, Button.FILTER_TELEPORT, new int[][]{RUNES_WATER, RUNES_ASTRAL,
                    RUNES_LAW}, 4, 2, 2),
                    TELE_GROUP_KHAZARD(59, 79, 622, Button.FILTER_TELEPORT, new int[][]{RUNES_WATER, RUNES_ASTRAL,
                    RUNES_LAW}, 8, 2, 2),
                    FISHING_GUILD_TELEPORT(40, 85, 605, Button.FILTER_TELEPORT, new int[][]{RUNES_WATER, RUNES_ASTRAL,
                    RUNES_LAW}, 10, 3, 3),
                    TELE_GROUP_FISHING_GUILD(60, 86, 623, Button.FILTER_TELEPORT, new int[][]{RUNES_WATER, RUNES_ASTRAL,
                    RUNES_LAW}, 14, 3, 3),
                    CATHERBY_TELEPORT(44, 87, 606, Button.FILTER_TELEPORT, new int[][]{RUNES_WATER, RUNES_ASTRAL,
                    RUNES_LAW}, 10, 3, 3),
                    TUNE_BANE_ORE(0, 87, 7684, Button.FILTER_MISC, new int[][]{RUNES_ASTRAL, RUNES_EARTH, BANE_ORE}, 2, 4, 1),
                    TELE_GROUP_CATHERBY(61, 88, 624, Button.FILTER_TELEPORT, new int[][]{RUNES_WATER, RUNES_ASTRAL,
                    RUNES_LAW}, 10, 3, 3),
                    ICE_PLATEAU_TELEPORT(51, 89, 607, Button.FILTER_TELEPORT, new int[][]{RUNES_WATER, RUNES_ASTRAL,
                    RUNES_LAW}, 8, 3, 3),
                    TELE_GROUP_ICE_PLATEAU(62, 90, 625, Button.FILTER_TELEPORT, new int[][]{RUNES_WATER, RUNES_ASTRAL,
                    RUNES_LAW}, 8, 3, 3),

                    BAKE_PIE(38, 65, 593, Button.FILTER_MISC, new int[][]{RUNES_FIRE, RUNES_WATER, RUNES_ASTRAL}, 5, 4, 1),
                    CURE_PLANT(55, 66, 617, Button.FILTER_MISC, new int[][]{RUNES_EARTH, RUNES_ASTRAL}, 8, 1),
                    NPC_CONTACT(26, 67, 618, Button.FILTER_MISC, new int[][]{RUNES_AIR, RUNES_ASTRAL, RUNES_COSMIC}, 2, 1, 1),
                    HUMIDIFY(29, 68, 628, Button.FILTER_MISC, new int[][]{RUNES_WATER, RUNES_ASTRAL, RUNES_FIRE}, 3, 1, 1),
                    HUNTER_KIT(30, 71, 629, Button.FILTER_MISC, new int[][]{RUNES_ASTRAL, RUNES_EARTH}, 2, 2),
                    REPAIR_RUNES_POUCH(68, 75, 5641, Button.FILTER_MISC, new int[][]{RUNES_ASTRAL, RUNES_COSMIC,
                    RUNES_LAW}, 2, 1, 1),
                    STAT_SPY(31, 75, 626, Button.FILTER_MISC, new int[][]{RUNES_BODY, RUNES_ASTRAL, RUNES_COSMIC}, 5, 2, 2),
                    SUPERGLASS_MAKE(48, 77, 598, Button.FILTER_MISC, new int[][]{RUNES_AIR, RUNES_FIRE, RUNES_ASTRAL}, 10, 6, 2),
                    REMOTE_FARMING(70, 78, 5643, Button.FILTER_MISC, new int[][]{RUNES_NATURE, RUNES_ASTRAL, RUNES_EARTH}, 3, 2,
                        2),
                    DREAM(32, 79, 630, Button.FILTER_MISC, new int[][]{RUNES_BODY, RUNES_ASTRAL, RUNES_COSMIC}, 5, 2, 1),
                    STRING_JEWELLERY(45, 80, 600, Button.FILTER_MISC, new int[][]{RUNES_EARTH, RUNES_WATER,
                    RUNES_ASTRAL}, 10, 5, 2),
                    MAGIC_IMBUE(36, 82, 602, Button.FILTER_MISC, new int[][]{RUNES_FIRE, RUNES_WATER, RUNES_ASTRAL}, 7, 7, 2),
                    MAKE_LEATHER(72, 83, 5644, Button.FILTER_MISC, new int[][]{RUNES_ASTRAL, RUNES_BODY, RUNES_FIRE}, 2, 2, 2),
                    FERTILE_SOIL(24, 83, 603, Button.FILTER_MISC, new int[][]{RUNES_EARTH, RUNES_ASTRAL, RUNES_NATURE}, 15, 3,
                        2),
                    PLANK_MAKE(33, 86, 631, Button.FILTER_MISC, new int[][]{RUNES_EARTH, RUNES_ASTRAL, RUNES_NATURE}, 15, 2, 1),
                    ENERGY_TRANSFER(27, 91, 608, Button.FILTER_MISC, new int[][]{RUNES_ASTRAL, RUNES_LAW, RUNES_NATURE}, 3, 2,
                        1),
                    SPELLBOOK_SWAP(34, 96, 632, Button.FILTER_MISC, new int[][]{RUNES_ASTRAL, RUNES_COSMIC, RUNES_LAW}, 3, 2, 1);

            private final int[][] runes;
            private final int[] amounts;
            private final Button button;
            private final int componentId;
            private final int level;
            private final int textureId;

            private Lunar(int componentId, int level, int textureId, Button button, int[][] runes, int... amounts) {
                  this.button = button;
                  this.componentId = componentId;
                  this.textureId = textureId;
                  this.level = level;
                  this.runes = runes;
                  this.amounts = amounts;
                }

            public int[] getAmounts() {
                  return amounts;
                }

            public Book getBook() {
                  return Book.LUNAR;
                }

            public Button getButton() {
                  return button;
                }

            public int getComponentId() {
                  return componentId;
                }

            public int getGreyTextureId() {
                  return textureId;
                }

            public int getLevel() {
                  return level;
                }

            public String getName() {
                  StringBuilder name = new StringBuilder();
                  for (String s : this.getName().split("_")) {
                        name.append(s.substring(0, 1) + s.substring(1).toLowerCase() + " ");
                      }
                  return name.toString().trim();
                }

            public int[][] getRunes() {
                  return runes;
                }
          }

      public static enum Dungeoneering implements Spell {
            WIND_STRIKE(25, 1, 65, Button.FILTER_COMBAT, new int[][]{RUNES_AIR, RUNES_MIND}, 1, 1),
                    CONFUSE(26, 3, 66, Button.FILTER_COMBAT, new int[][]{RUNES_WATER, RUNES_EARTH, RUNES_BODY}, 3, 2, 1),
                    WATER_STRIKE(27, 5, 67, Button.FILTER_COMBAT, new int[][]{RUNES_AIR, RUNES_MIND, RUNES_WATER}, 1, 1, 1),
                    EARTH_STRIKE(28, 9, 69, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_AIR, RUNES_MIND}, 2, 1, 1),
                    WEAKEN(29, 11, 70, Button.FILTER_COMBAT, new int[][]{RUNES_WATER, RUNES_EARTH, RUNES_BODY}, 3, 2, 1),
                    FIRE_STRIKE(30, 13, 71, Button.FILTER_COMBAT, new int[][]{RUNES_FIRE, RUNES_AIR, RUNES_MIND}, 3, 2, 1),
                    WIND_BOLT(32, 17, 73, Button.FILTER_COMBAT, new int[][]{RUNES_AIR, RUNES_CHAOS}, 2, 1),
                    CURSE(33, 19, 74, Button.FILTER_COMBAT, new int[][]{RUNES_WATER, RUNES_EARTH, RUNES_BODY}, 3, 2, 1),
                    BIND(34, 20, 369, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_WATER, RUNES_NATURE}, 3, 3, 2),
                    WATER_BOLT(36, 23, 76, Button.FILTER_COMBAT, new int[][]{RUNES_AIR, RUNES_WATER, RUNES_CHAOS}, 2, 2, 1),
                    EARTH_BOLT(37, 29, 79, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_AIR, RUNES_CHAOS}, 3, 2, 1),
                    FIRE_BOLT(41, 35, 82, Button.FILTER_COMBAT, new int[][]{RUNES_FIRE, RUNES_AIR, RUNES_CHAOS}, 4, 3, 1),
                    WIND_BLAST(42, 41, 85, Button.FILTER_COMBAT, new int[][]{RUNES_AIR, RUNES_DEATH}, 3, 1),
                    WATER_BLAST(43, 47, 88, Button.FILTER_COMBAT, new int[][]{RUNES_AIR, RUNES_WATER, RUNES_DEATH}, 3, 3, 1),
                    SNARE(44, 50, 370, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_WATER, RUNES_NATURE}, 4, 4, 3),
                    EARTH_BLAST(45, 53, 90, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_AIR, RUNES_DEATH}, 4, 3, 1),
                    FIRE_BLAST(47, 59, 94, Button.FILTER_COMBAT, new int[][]{RUNES_FIRE, RUNES_AIR, RUNES_DEATH}, 5, 4, 1),
                    WIND_WAVE(48, 62, 96, Button.FILTER_COMBAT, new int[][]{RUNES_AIR, RUNES_BLOOD}, 5, 1),
                    WATER_WAVE(49, 65, 98, Button.FILTER_COMBAT, new int[][]{RUNES_WATER, RUNES_AIR, RUNES_BLOOD}, 7, 5, 1),
                    VULNERABILITY(50, 66, 106, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_WATER, RUNES_SOUL}, 5, 5, 1),
                    MONSTER_EXAMINE(51, 66, 627, Button.FILTER_COMBAT, new int[][]{RUNES_ASTRAL, RUNES_COSMIC,
                    RUNES_MIND}, 1, 1, 1),
                    CURE_OTHER(52, 68, 609, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_ASTRAL, RUNES_LAW}, 10, 1, 1),
                    EARTH_WAVE(54, 70, 101, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_AIR, RUNES_BLOOD}, 7, 5, 1),
                    CURE_ME(55, 71, 612, Button.FILTER_COMBAT, new int[][]{RUNES_ASTRAL, RUNES_COSMIC, RUNES_LAW}, 2, 2, 1),
                    ENFEEBLE(56, 73, 107, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_WATER, RUNES_ASTRAL}, 8, 8, 1),
                    CURE_GROUP(57, 74, 615, Button.FILTER_COMBAT, new int[][]{RUNES_ASTRAL, RUNES_COSMIC, RUNES_LAW}, 2, 2, 2),
                    FIRE_WAVE(58, 75, 102, Button.FILTER_COMBAT, new int[][]{RUNES_FIRE, RUNES_AIR, RUNES_BLOOD}, 7, 5, 1),
                    ENTANGLE(59, 79, 371, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_WATER, RUNES_NATURE}, 5, 5, 4),
                    STUN(60, 80, 108, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_WATER, RUNES_SOUL}, 12, 12, 1),
                    WIND_SURGE(61, 81, 815, Button.FILTER_COMBAT, new int[][]{RUNES_AIR, RUNES_BLOOD, RUNES_DEATH}, 7, 1, 1),
                    WATER_SURGE(62, 85, 816, Button.FILTER_COMBAT, new int[][]{RUNES_WATER, RUNES_AIR, RUNES_BLOOD,
                    RUNES_DEATH}, 10, 7, 1, 1),
                    EARTH_SURGE(63, 90, 817, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_AIR, RUNES_BLOOD,
                    RUNES_DEATH}, 10, 7, 1, 1),
                    VENGENCE_OTHER(64, 93, 611, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_ASTRAL,
                    RUNES_DEATH}, 10, 3, 2),
                    VENGENCE(65, 94, 614, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_ASTRAL, RUNES_DEATH}, 10, 4, 2),
                    VENGENCE_GROUP(66, 95, 5647, Button.FILTER_COMBAT, new int[][]{RUNES_EARTH, RUNES_ASTRAL,
                    RUNES_DEATH}, 11, 4, 3),
                    FIRE_SURGE(67, 95, 818, Button.FILTER_COMBAT, new int[][]{RUNES_FIRE, RUNES_AIR, RUNES_BLOOD,
                    RUNES_DEATH}, 10, 7, 1, 1),

                    BONES_TO_BANANAS(31, 15, 72, Button.FILTER_MISC, new int[][]{RUNES_EARTH, RUNES_WATER, RUNES_NATURE}, 2, 2,
                        1),
                    LOW_LEVEL_ALCHEMY(35, 21, 75, Button.FILTER_MISC, new int[][]{RUNES_FIRE, RUNES_NATURE}, 3, 1),
                    HIGH_LEVEL_ALCHEMY(46, 55, 91, Button.FILTER_MISC, new int[][]{RUNES_FIRE, RUNES_NATURE}, 5, 1),

                    HUMIDIFY(53, 68, 628, Button.FILTER_SKILLS, new int[][]{RUNES_WATER, RUNES_ASTRAL, RUNES_FIRE}, 3, 1, 1),

                    HOME_TELEPORT(24, 0, -1, Button.FILTER_TELEPORT, null),
            CREATE_GATESTONE(38, 32, 3059, Button.FILTER_TELEPORT, new int[][]{RUNES_COSMIC}, 3),
                    GATESTONE_TELEPORT(39, 32, 3058, Button.FILTER_TELEPORT, null),
            GROUP_GATESTONE_TELEPORT(40, 64, 3058, Button.FILTER_TELEPORT, new int[][]{RUNES_LAW}, 3);

            private final int[][] runes;
            private final int[] amounts;
            private final Button button;
            private final int componentId;
            private final int level;
            private final int textureId;

            private Dungeoneering(int componentId, int level, int textureId, Button button, int[][] runes,
                                                    int... amounts) {
                  this.button = button;
                  this.componentId = componentId;
                  this.textureId = textureId;
                  this.level = level;
                  this.runes = runes;
                  this.amounts = amounts;
                }

            public int[] getAmounts() {
                  return amounts;
                }

            public Book getBook() {
                  return Book.DUNGEONEERING;
                }

            public Button getButton() {
                  return button;
                }

            public int getComponentId() {
                  return componentId;
                }

            public int getGreyTextureId() {
                  return textureId;
                }

            public int getLevel() {
                  return level;
                }

            public String getName() {
                  StringBuilder name = new StringBuilder();
                  for (String s : this.getName().split("_")) {
                        name.append(s.substring(0, 1) + s.substring(1).toLowerCase() + " ");
                      }
                  return name.toString().trim();
                }

            public int[][] getRunes() {
                  return runes;
                }
          }

      // Buttons
              @Deprecated
      public static final int BUTTON_DEFENSIVE_STANCE = 2, BUTTON_SHOW_COMBAT_SPELLS = 7,
          BUTTON_SHOW_TELEPORT_SPELLS = 9, BUTTON_SHOW_MISC_SPELLS = 11, BUTTON_SHOW_SKILL_SPELLS = 13,
                  BUTTON_SORT_BY_LEVEL = 15, BUTTON_SORT_BY_COMBAT = 16, BUTTON_SORT_BY_TELEPORTS = 17;

      // Equipment - Provide infinite amounts of the corresponding rune
              public static final int[] EQUIPMENT_AIR = new int[]{1381, 1397, 1405, 16169, 16170, 17009, 17011, 21777};
      public static final int[] EQUIPMENT_WATER = new int[]{1383, 1395, 1403, 6562, 6723, 16163, 16164, 16997, 16999, 11736, 11738,
          18346 };
  public static final int[] EQUIPMENT_EARTH = new int[]{1385, 1399, 1407, 3053, 3054, 6562, 6723, 16165, 16166, 17001, 17003};
  public static final int[] EQUIPMENT_FIRE = new int[]{1387, 1393, 1401, 3053, 3054, 16167, 16168, 17005, 17007, 11736, 11738};

  // Equipment that provides infinite amounts of two corresponding runes.
          public static final int[] EQUIPMENT_SPECIAL = new int[]{3053, 3054, 11736, 11738, 6562, 6563};

  // Runes - Main id, combination runes, activities, last 2 are Dungeoneering
          public static final int[] RUNES_AIR = new int[]{556, 4695, 4696, 4697, 6422, 7558, 16091, 17780};
  public static final int[] RUNES_ASTRAL = new int[]{9075, 16101, 17790};
  public static final int[] RUNES_BLOOD = new int[]{565, 16098, 17787};
  public static final int[] RUNES_BODY = new int[]{559, 6438, 16099, 17788};
  public static final int[] RUNES_CHAOS = new int[]{562, 6430, 7560, 16096, 17785};
  public static final int[] RUNES_COSMIC = new int[]{564, 16100, 17789};
  public static final int[] RUNES_DEATH = new int[]{560, 6432, 16097, 17786};
  public static final int[] RUNES_EARTH = new int[]{557, 4696, 4698, 4699, 6426, 16093, 17782};
  public static final int[] RUNES_FIRE = new int[]{554, 4694, 4697, 4699, 6428, 7554, 16094, 17783};
  public static final int[] RUNES_LAW = new int[]{563, 6434, 16103, 17792};
  public static final int[] RUNES_MIND = new int[]{558, 6436, 16095, 17784};
  public static final int[] RUNES_NATURE = new int[]{561, 16102, 17791};
  public static final int[] RUNES_SOUL = new int[]{566, 16104, 17793};
  public static final int[] RUNES_WATER = new int[]{555, 4694, 4695, 4698, 6424, 7556, 16092, 17781};

  // Other Spell ingredients
          public static final int[] BANE_ORE = new int[]{21778};
  public static final int[] BANANAS = new int[]{1963};
  public static final int[] UNPOWERED_ORB = new int[]{567};

  // Settings
          public static final int SETTING_DEFENSIVE_CASTING = 439;

  // Normal spells
          @Deprecated
  public static final int SPELL_HOME_TELEPORT = 24, SPELL_WIND_RUSH = 98, SPELL_WIND_STRIKE = 25, SPELL_CONFUSE = 26,
              SPELL_ENCHANT_CROSSBOW_BOLT = 27, SPELL_WATER_STRIKE = 28, SPELL_LVL1_ENCHANT = 29,
              SPELL_EARTH_STRIKE = 30, SPELL_WEAKEN = 31, SPELL_FIRE_STRIKE = 32, SPELL_BONES_TO_BANANAS = 33,
              SPELL_WIND_BOLT = 34, SPELL_CURSE = 35, SPELL_BIND = 36, SPELL_MOBILISING_ARMIES_TELEPORT = 37,
              SPELL_LOW_LEVEL_ALCHEMY = 38, SPELL_WATER_BOLT = 39, SPELL_VARROCK_TELEPORT = 40, SPELL_LVL2_ENCHANT = 41,
              SPELL_EARTH_BOLT = 42, SPELL_LUMBRIDGE_TELEPORT = 43, SPELL_TELEKINETIC_GRAB = 44, SPELL_FIRE_BOLT = 45,
              SPELL_FALADOR_TELEPORT = 46, SPELL_CRUMBLE_UNDEAD = 47, SPELL_TELEPORT_TO_HOUSE = 48,
              SPELL_WIND_BLAST = 49, SPELL_SUPERHEAT_ITEM = 50, SPELL_CAMELOT_TELEPORT = 51, SPELL_WATER_BLAST = 52,
              SPELL_LVL3_ENCHANT = 53, SPELL_IBAN_BLAST = 54, SPELL_SNARE = 55, SPELL_MAGIC_DART = 56,
              SPELL_ARDOUGNE_TELEPORT = 57, SPELL_EARTH_BLAST = 58, SPELL_HIGH_LEVEL_ALCHEMY = 59,
              SPELL_CHARGE_WATER_ORB = 60, SPELL_LVL4_ENCHANT = 61, SPELL_WATCHTOWER_TELEPORT = 62,
              SPELL_FIRE_BLAST = 63, SPELL_CHARGE_EARTH_ORB = 64, SPELL_BONES_TO_PEACHES = 65,
              SPELL_SARADOMIN_STRIKE = 66, SPELL_CLAWS_OF_GUTHIX = 67, SPELL_FLAMES_OF_ZAMORAK = 68,
              SPELL_TROLLHEIM_TELEPORT = 69, SPELL_WIND_WAVE = 70, SPELL_CHARGE_FIRE_ORB = 71,
              SPELL_APE_ATOL_TELEPORT = 72, SPELL_WATER_WAVE = 73, SPELL_CHARGE_AIR_ORB = 74, SPELL_VULNERABILITY = 75,
              SPELL_LVL5_ENCHANT = 76, SPELL_EARTH_WAVE = 77, SPELL_ENFEEBLE = 78, SPELL_TELEOTHER_LUMBRIDGE = 79,
              SPELL_FIRE_WAVE = 80, SPELL_ENTANGLE = 81, SPELL_STUN = 82, SPELL_CHARGE = 83, SPELL_WIND_SURGE = 84,
              SPELL_TELEOTHER_FALADOR = 85, SPELL_TELEPORT_BLOCK = 86, SPELL_WATER_SURGE = 87, SPELL_LVL6_ENCHANT = 88,
              SPELL_TELEOTHER_CAMELOT = 89, SPELL_EARTH_SURGE = 90, SPELL_FIRE_SURGE = 91;

  // Ancient spells
          @Deprecated
  public static final int SPELL_ICE_RUSH = 20, SPELL_ICE_BLITZ = 21, SPELL_ICE_BURST = 22, SPELL_ICE_BARRAGE = 23,
              SPELL_BLOOD_RUSH = 24, SPELL_BLOOD_BLITZ = 25, SPELL_BLOOD_BURST = 26, SPELL_BLOOD_BARRAGE = 27,
              SPELL_SMOKE_RUSH = 28, SPELL_SMOKE_BLITZ = 29, SPELL_SMOKE_BURST = 30, SPELL_SMOKE_BARRAGE = 31,
              SPELL_SHADOW_RUSH = 32, SPELL_SHADOW_BLITZ = 33, SPELL_SHADOW_BURST = 34, SPELL_SHADOW_BARRAGE = 35,
              SPELL_MIASMIC_RUSH = 36, SPELL_MIASMIC_BLITZ = 37, SPELL_MIASMIC_BURST = 38, SPELL_MIASMIC_BARRAGE = 39,
              SPELL_PADDEWWA_TELEPORT = 40, SPELL_SENNTISTEN_TELEPORT = 41, SPELL_KHARYRLL_TELEPRT = 42,
              SPELL_LASSER_TELEPORT = 43, SPELL_DAREEYAK_TELEPORT = 44, SPELL_CARRALLANGER_TELEPORT = 45,
              SPELL_ANNAKARL_TELEPORT = 46, SPELL_GHORROCK_TELEPORT = 47, SPELL_ANCIENT_HOME_TELEPORT = 48;

  // Lunar spells
          @Deprecated
  public static final int SPELL_BARBARIAN_TELEPORT = 22, SPELL_CURE_OTHER = 23, SPELL_FERTILE_SOIL = 24,
              SPELL_CURE_GROUP = 25, SPELL_NPC_CONTACT = 26, SPELL_ENERGY_TRANSFER = 27, SPELL_MONSTERS_EXAMINE = 28,
              SPELL_HUMIDIFY = 29, SPELL_HUNTER_KIT = 30, SPELL_STATE_SPY = 31, SPELL_DREAM = 32, SPELL_PLANK_MAKE = 33,
              SPELL_SPELLBOOK_SWAP = 34, SPELL_MAGIC_IMBUE = 35, SPELL_VENGEANCE = 36, SPELL_BAKE_PIE = 37,
              SPELL_HOME_TELEPORT_LUNAR = 38, SPELL_FISHING_GUILD_TELEPORT = 39, SPELL_KHAZARD_TELEPORT = 40,
              SPELL_VENGEANCE_OTHER = 41, SPELL_MOONCLAN_TELEPORT = 42, SPELL_CATHERBY_TELEPORT = 43,
              SPELL_STRING_JEWELLERY = 44, SPELL_CURE_ME = 45, SPELL_WATERBIRTH_TELEPORT = 46,
              SPELL_SUPERGLASS_MAKE = 47, SPELL_BOOTS_POTION_SHARE = 48, SPELL_STAT_RESTORE_POT_SHARE = 49,
              SPELL_ICE_PLATEAU_TELEPORT = 50, SPELL_HEAL_OTHER = 51, SPELL_HEAL_GROUP = 52, SPELL_OURANIA_TELEPORT = 53,
              SPELL_CURE_PLANT = 54, SPELL_TELE_GROUP_MOONCLAN = 55, SPELL_TELE_GROUP_WATERBIRTH = 56,
              SPELL_TELE_GROUP_BARBARIAN = 57, SPELL_TELE_GROUP_KHAZARD = 58, SPELL_TELE_GROUP_FISHING_GUILD = 59,
              SPELL_TELE_GROUP_CATHERBY = 60;

  // Dungeoneering spells
          @Deprecated
  public static final int SPELL_WIND_STRIKE_DUNGEONEERING = 25, SPELL_CONFUSE_DUNGEONEERING = 26,
              SPELL_WATER_STRIKE_DUNGEONEERING = 27, SPELL_EARTH_STRIKE_DUNGEONEERING = 28,
              SPELL_WEAKEN_DUNGEONEERING = 29, SPELL_FIRE_STRIKE_DUNGEONEERING = 30,
              SPELL_BONES_TO_BANANAS_DUNGEONEERING = 31, SPELL_WIND_BOLT_DUNGEONEERING = 32,
              SPELL_CURSE_DUNGEONEERING = 33, SPELL_BIND_DUNGEONEERING = 34, SPELL_LOW_LEVEL_ALCHEMY_DUNGEONEERING = 35,
              SPELL_WATER_BOLT_DUNGEONEERING = 36, SPELL_EARTH_BOLT_DUNGEONEERING = 37,
              SPELL_CREATE_GATESTONE_DUNGEONEERING = 38, SPELL_GATESTONE_TELEPORT_DUNGEONEERING = 39,
              SPELL_GROUP_GATESTONE_TELEPORT_DUNGEONEERING = 40, SPELL_FIRE_BOLT_DUNGEONEERING = 41,
              SPELL_WIND_BLAST_DUNGEONEERING = 42, SPELL_WATER_BLAST_DUNGEONEERING = 43, SPELL_SNARE_DUNGEONEERING = 44,
              SPELL_EARTH_BLAST_DUNGEONEERING = 45, SPELL_HIGH_LEVEL_ALCHEMY_DUNGEONEERING = 46,
              SPELL_FIRE_BLAST_DUNGEONEERING = 47, SPELL_WIND_WAVE_DUNGEONEERING = 48,
              SPELL_WATER_WAVE_DUNGEONEERING = 49, SPELL_VULNERABILITY_DUNGEONEERING = 50,
              SPELL_MONSTER_EXAMINE_DUNGEONEERING = 51, SPELL_CURE_OTHER_DUNGEONEERING = 52,
              SPELL_HUMIDIFY_DUNGEONEERING = 53, SPELL_EARTH_WAVE_DUNGEONEERING = 54, SPELL_CURE_ME_DUNGEONEERING = 55,
              SPELL_ENFEEBLE_DUNGEONEERING = 56, SPELL_CURE_GROUP_DUNGEONEERING = 57, SPELL_FIRE_WAVE_DUNGEONEERING = 58,
              SPELL_ENTANGLE_DUNGEONEERING = 59, SPELL_STUN_DUNGEONEERING = 60, SPELL_WINDSURGE_DUNGEONEERING = 61,
              SPELL_WATER_SURGE_DUNGEONEERING = 62, SPELL_EARTH_SURGE_DUNGEONEERING = 63,
              SPELL_VENGENCE_OTHER_DUNGEONEERING = 64, SPELL_VENGENCE_DUNGEONEERING = 65,
              SPELL_VENGENCE_GROUP_DUNGEONEERING = 66, SPELL_FIRE_SURGE_DUNGEONEERING = 67;

  /**
    * Clicks on the "Auto-cast" option for the specified spell.
    *
    * @param spell the spell to auto-cast
    * @return <tt>true</tt> if the "Auto-cast" option was clicked; otherwise <tt>false</tt>
    */
          @Deprecated
  public static boolean autoCastSpell(int spell) {
            return castSpell(getSpell(spell), true);
  }

          /**
    * Sets the specified <code>Spell</code> to autocast.
    *
    * @param spell the Spell to auto-cast
    * @return <tt>true</tt> if the "Autocast" option was selected; otherwise <tt>false</tt>
    */
          public static boolean autoCastSpell(Spell spell) {
            return castSpell(spell, true);
  }

          /**
    * Clicks on the specified spell.
    *
    * @param spell the spell to cast
    * @return <tt>true</tt> if the spell was clicked; otherwise <tt>false</tt>
    */
          @Deprecated
  public static boolean castSpell(int spell) {
            return castSpell(getSpell(spell), false);
  }

          /**
    * Clicks on the specified <code>Spell</code>.
    *
    * @param spell the spell to cast
    * @return <tt>true</tt> if the spell was clicked; otherwise <tt>false</tt>
    */
          public static boolean castSpell(Spell spell) {
            return castSpell(spell, false);
  }

          /**
    * Casts the <code>Spell</code> on the specified inventory <code>Item</code>.
    *
    * @param spell the spell to cast
    * @param item  the inventory item to cast on
    * @return <tt>true</tt> if the <code>Spell</code> was cast on the <code>Item</code>;
    *         otherwise <tt>false</tt>
    */
          public static boolean castSpellOn(Spell spell, Item item) {
            if (!castSpell(spell, false)) {
              return false;
    }
            for (int i = 0; i < 5; i++) {
              if (Tabs.getCurrent() == Tabs.INVENTORY) break;
      if (i == 4) return false;
      Task.sleep(Random.nextInt(200, 300));
    }
            return item.getWidgetChild().interact("Cast ");
          }

          /**
    * Casts the <code>Spell</code> on the specified <code>SceneObject</code>.
    *
    * @param spell the spell to cast
    * @param obj   the object to cast on
    * @return <tt>true</tt> if the <code>Spell</code> was cast on the <code>SceneObject</code>;
    *         otherwise <tt>false</tt>
    */
          public static boolean castSpellOn(Spell spell, SceneObject obj) {
            if (!castSpell(spell, false)) {
              return false;
    }
              SceneObjectDefinition def = obj.getDefinition();
    String objName = "";
    if (def != null) {
              objName = def.getName() != null ? def.getName() : "";
    }
            return obj.interact("Cast " + spell.getName() + " -> " + objName);
  }

          /**
    * Casts the <code>Spell</code> on the specified <code>GroundItem</code>.
    *
    * @param spell the spell to cast
    * @param item  the item to cast on
    * @return <tt>true</tt> if the <code>Spell</code> was cast on the <code>GroundItem</code>;
    *         otherwise <tt>false</tt>
    */
          public static boolean castSpellOn(Spell spell, GroundItem item) {
            if (!castSpell(spell, false)) {
              return false;
    }
            String itemName = item.getGroundItem().getName() != null ? item.getGroundItem().getName() : "";
    return item.interact("Cast " + spell.getName() + " -> " + itemName);
  }

          /**
    * Casts the <code>Spell</code> on the specified <code>Npc</code>.
    *
    * @param spell the spell to cast
    * @param npc   the npc to cast on
    * @return <tt>true</tt> if the <code>Spell</code> was cast on the <code>Npc</code>; otherwise
    *         <tt>false</tt>
    */
          public static boolean castSpellOn(Spell spell, NPC npc) {
            return castSpell(spell, false) && npc.interact("Cast " + spell.getName() + " -> " + npc.getName());
  }

          /**
    * Sets all four <code>Spell</code> filter Buttons. The provided Buttons are activated, any
    * others deactivated.
    *
    * @param filters the <code>Spell</code> filter Buttons that should be enabled
    */
          public static void filterSpells(Button... filters) {
            Button[] buttons = Button.values();
    for (int I = 1; I < 5; I++) {
              boolean select = false;
      Button b = buttons[I];
      for (Button f : filters) {
                if (f == b) {
                  select = true;
          break;
        }
              }
              setSpellButton(b, select);
    }
          }

          /**
    * Gets the <code>Spell</code> currently set to autocast.
    *
    * @return the currently autocasted <code>Spell</code> or <code>null</code> if undefined
    */
          public static Spell getAutocastedSpell() {
            if (isAutoCasting()) {
              Widget widget = Widgets.get(getCurrentBook().getWidgetId());
      if (widget.validate()) {
                WidgetChild auto = widget.getChild(68);
                WidgetChild menu = widget.getChild(0);
        if (auto != null && menu != null && auto.validate() && menu.validate()) {
                  Point aP = auto.getCentralPoint();
          Point mP = menu.getAbsoluteLocation();
          if (aP.x > mP.x && aP.y > mP.y) {
                    for (Spell s : getCurrentSpells()) {
                      WidgetChild spell = widget.getChild(s.getComponentId());
              if (spell.getBoundingRectangle().contains(aP)) {
                        return s;
              }
                    }
                  }
                }
              }
            }
            return null;
  }

          /**
    * Gets the player's current magic <code>Book</code> (from the cache if possible).
    *
    * @return the current magic <code>Book</code> or <code>null</code> if unavailable
    */
          public static Book getCurrentBook() {
            for (int i = 0; i < 2; i++) {
              for (Book book : Book.values()) {
                Widget widget = Widgets.get(book.getWidgetId());
        if (widget.validate()) {
                  return book;
        }
              }
              openTab();
    }
            return Book.MODERN;
  }

          /**
    * Retrieves the Spells associated with the active magic Book.
    *
    * @return the <code>Spell</code>s that correspond to the current magic Book.
    */
          public static Spell[] getCurrentSpells() {
            switch (getCurrentBook()) {
              case ANCIENT:
                return Ancient.values();
      case LUNAR:
                return Lunar.values();
      case DUNGEONEERING:
                return Dungeoneering.values();
      default:
                return Modern.values();
    }
          }

          /**
    * Gets the currently selected <code>Spell</code>.
    *
    * @return the currently selected <code>Spell</code> or <code>null</code> if none selected
    */
          public static Spell getSelectedSpell() {
            Widget widget = Widgets.get(getCurrentBook().getWidgetId());
    if (widget.validate()) {
              for (Spell s : getCurrentSpells()) {
                WidgetChild spell = widget.getChild(s.getComponentId());
        if (spell != null && spell.getBorderThickness() == 2) {
                  return s;
        }
              }
            }
            return null;
  }

          /**
    * Gets the name of this <code>Spell</code> from the book Widgets.
    *
    * @return the <code>Spell</code>'s component name or "" if unavailable
    */
          @Deprecated
  public static String getSpellName(Spell spell) {
            Widget widget = getWidget();
    if (widget != null) {
              WidgetChild spellIcon = widget.getChild(spell.getComponentId());
      if (spellIcon != null) {
                String name = spellIcon.getText();
        return name.substring(name.indexOf(">") + 1);
      }
            }
            return "";
  }

          /**
    * Gets the current magic book widget.
    *
    * @return the current magic book <code>Widget</code> or <code>null</code> if invalid
    */
          public static Widget getWidget() {
            openTab();
    for (Book book : Book.values()) {
              Widget widget = Widgets.get(book.getWidgetId());
      if (widget.validate()) {
                return widget;
      }
            }
            return null;
  }

          /**
    * Determines if the current magic level is adequate to cast this <code>Spell</code>.
    *
    * @param spell the <code>Spell</code> to check
    * @return <tt>true</tt> if the player's current magic level is greater than or equal to the
    *         required level; otherwise <tt>false</tt>
    */
          public static boolean hasLevel(Spell spell) {
            return Skills.getLevel(Skills.MAGIC) >= spell.getLevel();
  }

          /**
    * Determines whether the player currently has the required runes and ingredients to
    * cast the desired <code>Spell</code>. This does not yet account for equipped items (e.g. staves).
    *
    * @return <tt>true</tt> if the inventory contains all the required spell ingredients; otherwise
    *         <tt>false</tt>
    */
          public static boolean hasRunes(Spell spell) {
            return hasRunes(spell, 1);
  }

          /**
    * Determines whether the player currently enough runes and ingredients to cast the desired
    * Spell a provided number of times. This does not yet account for equipped items (e.g. staves).
    *
    * @return <tt>true</tt> if the inventory contains all the required spell ingredients;
    *         otherwise <tt>false</tt>
    */
          public static boolean hasRunes(Spell spell, int casts) {
            int[][] runes = spell.getRunes();
    if (runes != null) {
              int[] amounts = spell.getAmounts();
      for (int I = 0; I < runes.length; I++) {
                if (Inventory.getCount(true, runes[I]) < amounts[I] * casts) return false;
      }
            }
            return true;
  }

          /**
    * Checks whether the player is currently autocasting a spell.
    *
    * @return <tt>true</tt> if a spell is set to autocast; otherwise <tt>false</tt>
    */
          public static boolean isAutoCasting() {
            return Settings.get(Settings.SETTING_COMBAT_STYLE) == 4;
  }

          /**
    * Determines whether this spell Button is currently selected.
    *
    * @param button the Button to check
    * @return <tt>true</tt> if the button is currently selected; otherwise <tt>false</tt>
    */
          public static boolean isButtonSelected(Button button) {
            if (button.getComponentId() == -1) {
              return false;
    }
            WidgetChild b = Widgets.get(getCurrentBook().getWidgetId(), button.getComponentId());
    return b != null && b.getTextureId() == 1703;
  }

          /**
    * Determines whether the player is currently capable of casting the desired spell. Do not use
    * this before casting a spell (it is checked automatically).
    *
    * @param spell the Spell to check
    * @return <tt>true</tt> if the level requirement is met and the spell book matches; otherwise
    *         <tt>false</tt>
    */
          public static boolean isCastable(Spell spell) {
            return spell != null && hasLevel(spell) && getCurrentBook().equals(spell.getBook());
  }

          /**
    * Checks whether the player is currently casting defensively.
    *
    * @return <tt>true</tt> if defensive casting is enabled; otherwise <tt>false</tt>
    */
          public static boolean isDefensiveCasting() {
            switch (Settings.get(SETTING_DEFENSIVE_CASTING)) {
              case 256:  // Modern
              case 259:  // Dungeoneering
              case 3328: // Modern
              case 3329: // Ancient
              case 3330: // Lunar
                return true;
    }
            return false;
  }

          /**
    * Opens the magic tab if not already opened.
    */
          public static void openTab() {
            int tab = Tabs.MAGIC.getIndex();
    if (Tabs.getCurrent().getIndex() != tab) {
              Tabs.MAGIC.open();
    }
          }

          /**
    * Scrolls the magic menu so that the desired spell is selectable.
    *
    * @param spell the Spell to scroll to
    * @return <tt>true</tt> if the spell Button is made visible on the magic menu; otherwise
    *         <tt>false</tt>
    */
          public static boolean scrollToSpell(Spell spell) {
            Widget widget = getWidget();
    if (widget != null) {
              WidgetChild scrollBar = widget.getChild(spell.getBook().getScrollBarId());
      WidgetChild spellIcon = widget.getChild(spell.getComponentId());
      if (scrollBar != null && spellIcon != null) {
                if (scrollBar.getChildren().length > 0) {
                  int minY = spellIcon.getAbsoluteY();
          int maxY = minY + spellIcon.getHeight();
          int scrollTop = scrollBar.getAbsoluteY();
          int scrollBottom = scrollTop + scrollBar.getHeight();
          if (minY < scrollTop || maxY > scrollBottom) {
                    WidgetChild area = scrollBar.getChild(0);
            WidgetChild bar = scrollBar.getChild(1);
            Point aP = area.getAbsoluteLocation();
            Point bP = bar.getAbsoluteLocation();
            Point min, max;
            if (minY < scrollTop) {
                      min = new Point(bP.x + 1, aP.y + 1);
              max = new Point(bP.x + bar.getWidth() - 1, bP.y - 1);
            } else {
                      min = new Point(bP.x + 1, bP.y + bar.getHeight() + 1);
              max = new Point(bP.x + bar.getWidth() - 1, aP.y + area.getBoundingRectangle().height - 1);
            }
                    Mouse.drag(min.x, min.y, max.x - min.x, max.y - min.y);
          }
                }
                return true;
      }
            }
            return false;
  }

          /**
    * Sets one of the spell Buttons to the desired visibility.
    *
    * @param button the Button to set
    * @param select <tt>true</tt> to activate the Button, <tt>false</tt> to disable
    * @return <tt>true</tt> if the spell Button was set to the desired visibility; otherwise
    *         <tt>false</tt>
    */
          public static boolean setSpellButton(Button button, boolean select) {
            if (button.getComponentId() != -1) {
              if (isButtonSelected(button) == select) {
                return true;
      }
              Widget widget = getWidget();
      if (widget != null) {
                WidgetChild b = widget.getChild(button.getComponentId());
        if (b != null && b.validate() && b.click(true)) {
                  for (int i = 0; i < 10; i++) {
                    if (isButtonSelected(button) == select) {
                      return true;
            }
                    Task.sleep(Random.nextInt(80, 150));
          }
                }
              }
            }
            return false;
  }

          /**
    * Casts the specified Spell to the desired mode.
    *
    * @param spell  the Spell to cast
    * @param autocast false to cast, true to autocast
    * @return <tt>true</tt> if the spell was clicked; otherwise <tt>false</tt>
    */
          private static boolean castSpell(Spell spell, boolean autocast) {
            if (spell != null && (!autocast || spell != getAutocastedSpell()) && isCastable(spell)) {
              Widget widget = getWidget();
      if (widget != null && setSpellButton(spell.getButton(), true) && scrollToSpell(spell)) {
                WidgetChild c = widget.getChild(spell.getComponentId());
        if (c != null && c.validate() && c.getTextureId() != spell.getGreyTextureId()) {
                  return !autocast && c.getBorderThickness() == 2 || c.interact(autocast ? "Autocast" : "Cast");
        }
              }
            }
            return false;
  }

          /**
    * Gets the Spell that corresponds to this component id. For internal use.
    *
    * @return the <code>Spell</code> that corresponds to this component id or <code>null</code> if
    *         unavailable
    */
          private static Spell getSpell(int spellId) {
            for (Spell spell : getCurrentSpells()) {
              if (spell.getComponentId() == spellId)
                return spell;
    }
            return null;
  }
        }
