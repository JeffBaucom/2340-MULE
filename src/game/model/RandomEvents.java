package game.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class to handle the generation of Random Events.
 */
public class RandomEvents implements java.io.Serializable {
    /** A string of random event messages. */
    private List<String> events;
    /** Numbers to help generate random events. */
    private int rand3Num, rand4Num, rand5Num, rand7Num;
    /** The instance of game. */
    private Game game;

    private static final String MONEY = "money";

    /**
     *  Instantiates a random event.
     *  @param game the instance of game
     */
    public RandomEvents(final Game game) {
        this.game = game;
        events = new ArrayList<String>();
        events.add("You just received a package from the GT Alumni "
                + "containing 3 food and 2 energy units!");
        events.add("A wandering Tech student repaid your hospitality by"
                + "leaving two bars of ore!");
        if (game.getRoundCounter() < 3) {
            rand3Num = 8 * 25;
        } else if (game.getRoundCounter() >= 3 && game.getRoundCounter() < 7) {
            rand3Num = 8 * 50;
        } else if (game.getRoundCounter() >= 7 && game.getRoundCounter() < 11) {
            rand3Num = 8 * 75;
        } else {
            rand3Num = 8 * 100;
        }
        events.add("The museum bought your antique personal computer for $"
                + rand3Num + "!");
        if (game.getRoundCounter() < 3) {
            rand4Num = 2 * 25;
        } else if (game.getRoundCounter() >= 3 && game.getRoundCounter() < 7) {
            rand4Num = 2 * 50;
        } else if (game.getRoundCounter() >= 7 && game.getRoundCounter() < 11) {
            rand4Num = 2 * 75;
        } else {
            rand4Num = 2 * 100;
        }
        events.add("You found a dead moose rat and sold the hide for $"
                + rand4Num + "!");
        if (game.getRoundCounter() < 3) {
            rand5Num = 4 * 25;
        } else if (game.getRoundCounter() >= 3 && game.getRoundCounter() < 7) {
            rand5Num = 4 * 50;
        } else if (game.getRoundCounter() >= 7 && game.getRoundCounter() < 11) {
            rand5Num = 4 * 75;
        } else {
            rand5Num = 4 * 100;
        }
        events.add("Flying cat-bugs ate the roof off your house. Repairs cost $"
                + rand5Num + ".");
        events.add("Mischievous U(sic)GA students broke into your storage"
                + "shed and stole half your food.");
        if (game.getRoundCounter() < 3) {
            rand7Num = 6 * 25;
        } else if (game.getRoundCounter() >= 3 && game.getRoundCounter() < 7) {
            rand7Num = 6 * 50;
        } else if (game.getRoundCounter() >= 7 && game.getRoundCounter() < 11) {
            rand7Num = 6 * 75;
        } else {
            rand7Num = 6 * 100;
        }
        events.add("Your space gypsy in-laws made a mess of the town."
                + "It cost you $" + rand7Num + " to clean it up.");
        events.add("A roving band of space pirates stole half your energy.");
        events.add("Wandering mole-bats broke into your ore storage." + "Half of your ore is lost.");
        events.add("Famine has stricken the planet. All players' food is lost.");
        events.add("A solar flare has hit the planet.  All players' energy is lost.");
        events.add("Space invaders demand tribute. All players' smithore is lost.");
    }

    /**
     * Gets the message for a random event.
     * @param p the pplayer the event is occuring for
     * @return the random event message
     */
    public final String getRandomEvent(final Player p) {
        Random rand = new Random();
        int event;
        if (game.getCurrentPlayer() == game.getLosingPlayer()) {
            event = rand.nextInt(4);
        } else {
            event = rand.nextInt(12);
        }
        if (event == 0) {
            p.add("food", 3);
            p.add("energy", 2);
        } else if (event == 1) {
            p.add("smithore", 2);
        } else if (event == 2) {
            p.add(MONEY, rand3Num);
        } else if (event == 3) {
            p.add(MONEY, rand4Num);
        } else if (event == 4) {
            p.add(MONEY, -1 * rand5Num);
        } else if (event == 5) {
            p.set("food", (p.get("food") / 2));
        } else if (event == 6) {
            p.add(MONEY, -1 * rand7Num);
        } else if (event == 7) {
            p.set("energy", (p.get("energy") / 2));
        } else if (event == 8) {
            p.set("smithore", (p.get("smithore") / 2));
        } else if (event == 9) {
            for(Player player: game.getPlayers()) {
                player.set("food", 0);
            }
        } else if (event == 10) {
            for(Player player: game.getPlayers()) {
                player.set("energy", 0);
            }
        } else {
            for(Player player: game.getPlayers()) {
                player.set("smithore", 0);
            }
        }
        return events.get(event);
    }
}
