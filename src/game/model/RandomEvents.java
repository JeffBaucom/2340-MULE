package game.model;

import java.util.ArrayList;
import java.util.Random;

public class RandomEvents {
    ArrayList<String> events;
    int rand3Num, rand4Num, rand5Num, rand7Num;
    Game game;

    public RandomEvents(Game game) {
        this.game = game;
        events = new ArrayList<String>();
        events.add("You just received a package from the GT Alumni containing 3 food and 2 energy units!");
        events.add("A wandering Tech student repaid your hospitality by leaving two bars of ore!");
        if (game.getRoundCounter() < 3) {
            rand3Num = 8*25;
        } else if (game.getRoundCounter() >= 3 && game.getRoundCounter() < 7) {
            rand3Num = 8*50;
        } else if (game.getRoundCounter() >= 7 && game.getRoundCounter() < 11) {
            rand3Num = 8*75;
        } else {
            rand3Num = 8*100;
        }
        events.add("The museum bought your antique personal computer for $" + rand3Num + "!");
        if (game.getRoundCounter() < 3) {
            rand4Num = 2*25;
        } else if (game.getRoundCounter() >= 3 && game.getRoundCounter() < 7) {
            rand4Num = 2*50;
        } else if (game.getRoundCounter() >= 7 && game.getRoundCounter() < 11) {
            rand4Num = 2*75;
        } else {
            rand4Num = 2*100;
        }
        events.add("You found a dead moose rat and sold the hide for $" + rand4Num + "!");
        if (game.getRoundCounter() < 3) {
            rand5Num = 4*25;
        } else if (game.getRoundCounter() >= 3 && game.getRoundCounter() < 7) {
            rand5Num = 4*50;
        } else if (game.getRoundCounter() >= 7 && game.getRoundCounter() < 11) {
            rand5Num = 4*75;
        } else {
            rand5Num = 4*100;
        }
        events.add("Flying cat-bugs ate the roof off your house. Repairs cost $" + rand5Num + ".");
        events.add("Mischievous U(sic)GA students broke into your storage shed and stole half your food.");
        if (game.getRoundCounter() < 3) {
            rand7Num = 6*25;
        } else if (game.getRoundCounter() >= 3 && game.getRoundCounter() < 7) {
            rand7Num = 6*50;
        } else if (game.getRoundCounter() >= 7 && game.getRoundCounter() < 11) {
            rand7Num = 6*75;
        } else {
            rand7Num = 6*100;
        }
        events.add("Your space gypsy in-laws made a mess of the town. It cost you $" + rand7Num + " to clean it up.");
    }

    public String getRandomEvent(Player p) {
        Random rand = new Random();
        int event;
        if (game.getCurrentPlayer() == game.getLowestScore()) {
            event = rand.nextInt(4);
        } else {
            event = rand.nextInt(7);
        }
        if (event == 0) {
            p.add("food", 3);
            p.add("energy", 2);
        } else if (event == 1) {
            p.add("smithore", 2);
        } else if (event == 2) {
            p.add("money", rand3Num);
        } else if (event == 3) {
            p.add("money", rand4Num);
        } else if (event == 4) {
            p.add("money", -1*rand5Num);
        } else if (event == 5) {
            p.set("food", (p.get("food")/2));
        } else {
            p.add("money", -1*rand7Num);
        }
        return events.get(event);
    }
}