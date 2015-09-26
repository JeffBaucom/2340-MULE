package game.model;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Turn {

    private Player player;
    Game game;
    int timeLeft;
    // private Timer timer;

    public Turn() {

    }

    public Turn(Player player, Game game) {
        this.player = player;
        this.game = game;

        timeLeft = 0;
//        this.timer = new Timer();
//        getTimerTask();
    }

//    public void getTimerTask() {
//        int round = game.getRoundCounter();
//        int food = player.getFood();
//        if (round < 4) {
//            if (food == 0) {
//                timer.schedule(new TurnEnder(), 5*1000);
//            } else if (food >= 3) {
//                timer.schedule(new TurnEnder(), 50*1000);
//            } else {
//                timer.schedule(new TurnEnder(), 30*1000);
//            }
//        } else if (round > 7) {
//            if (food == 0) {
//                timer.schedule(new TurnEnder(), 5*1000);
//            } else if (food >= 5) {
//                timer.schedule(new TurnEnder(), 50*1000);
//            } else {
//                timer.schedule(new TurnEnder(), 30*1000);
//            }
//        } else {
//            if (food == 0) {
//                timer.schedule(new TurnEnder(), 5*1000);
//            } else if (food >= 4) {
//                timer.schedule(new TurnEnder(), 50*1000);
//            } else {
//                timer.schedule(new TurnEnder(), 30*1000);
//            }
//        }
//    }
//
//    private class TurnEnder extends TimerTask {
//        public void run() {
//            game.endTurn();
//            timer.cancel(); //Terminate the timer thread
//        }
//    }

    public void placeMule(int row, int col) {
    }

    public boolean buyTile(int row, int col) {
        boolean boughtTile = false;
        game.getMap().getTile(row, col).setOwner(player.getId());
        if (game.getPhase() == 1 && player.getMoney() >= 300) {
            game.getCurrentPlayer().setMoney(game.getCurrentPlayer()
                    .getMoney() - 300);
            boughtTile = true;
        }

        player.addProperty();
        game.getMap().removeTile();
        game.endTurn();

        return boughtTile;
    }

    public void gamble() {
//        Random rand = new Random();
//        int roundBonus, timeBonus, round = game.getRoundCounter() + 1;
//
//        if (round >= 1 && round < 3) {
//            roundBonus = 50;
//        } else if (round >= 4 && round < 7) {
//            roundBonus = 100;
//        } else if (round >= 8 && round < 11) {
//            roundBonus = 150;
//        } else {
//            roundBonus = 200;
//        }
//
//        if (timeLeft >= 37) {
//            timeBonus = 200;
//        } else if (timeLeft >= 25 && timeLeft < 37) {
//            timeBonus = 150;
//        } else if (timeLeft >= 12 && timeLeft < 25) {
//            timeBonus = 100;
//        } else {
//            timeBonus = 50;
//        }
//
//        player.setMoney(player.getMoney() + roundBonus + rand.nextInt
//                (timeBonus));
        System.out.println("Gamble");
        player.setMoney(player.getMoney() + 100);
        game.endTurn();
    }
}
