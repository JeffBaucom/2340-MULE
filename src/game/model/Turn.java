package game.model;

import java.util.Random;

public class Turn {

    Player player;
    private Game game;

    public Turn(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

    public void placeMule(int row, int col) {
    }

    public boolean buyTile(int row, int col) {
        boolean boughtTile = false;
        game.getMap().getTile(row, col).setOwner(player.getId());
        if (game.getPhase() == 1 && player.get("money") >= 300) {
            game.getCurrentPlayer().set("money", game.getCurrentPlayer()
                    .get("money") - 300);
            boughtTile = true;
        }

        player.addProperty();
        game.getMap().removeTile();
        game.endTurn();

        return boughtTile;
    }

    public void gamble() {
        int timeLeft = game.getTimeLeft();
        Random rand = new Random();
        int roundBonus, timeBonus, round = game.getRoundCounter() + 1;

        if (round >= 1 && round < 3) {
            roundBonus = 50;
        } else if (round >= 4 && round < 7) {
            roundBonus = 100;
        } else if (round >= 8 && round < 11) {
            roundBonus = 150;
        } else {
            roundBonus = 200;
        }

        if (timeLeft >= 37) {
            timeBonus = 200;
        } else if (timeLeft >= 25 && timeLeft < 37) {
            timeBonus = 150;
        } else if (timeLeft >= 12 && timeLeft < 25) {
            timeBonus = 100;
        } else {
            timeBonus = 50;
        }

        int winnings = roundBonus + rand.nextInt
                (timeBonus);
        if (winnings > 250) {
            winnings = 250;
        }

        player.set("money", player.get("money") + winnings);
        game.logEvent(player.getName() + " won " + winnings
                + " gold gambling.");
        game.endTurn();
    }

    public boolean buyStore(String resource, int amount) {
        if (game.store.getCost(resource) * amount > player.get("money")) {
            return false; // not enough money
        } else {
            game.store.buy(resource, amount, player);
            return true;
        }
    }

    public boolean sellStore(String resource, int amount) {
        if (amount > player.get(resource)) {
            return false;
        } else {
            game.store.sell(resource, amount, player);
            return true;
        }
    }

    public boolean buyMuleStore(int muleType) {
        if (game.store.getCost("mule") > player.get("money")) {
            return false;
        } else {
            game.store.buyMule(muleType, player);
            return true;
        }
    }
}
