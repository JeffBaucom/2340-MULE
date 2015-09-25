package game.model;

//import java.util.Timer;
//import java.util.TimerTask;

public class Turn {

    private Player player;
    Game game;
//    private Timer timer;

    public Turn() {

    }

    public Turn(Player player, Game game) {
        this.player = player;
        this.game = game;
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

    public void enterTown() {
    }

    public void placeMule(int row, int col) {
    }

    public void buyTile(int row, int col) {
        game.getMap().getTile(row, col).setOwner(player.getId());
        if (game.getPhase() == 1) {
            game.getCurrentPlayer().setMoney(game.getCurrentPlayer().getMoney() - 300);
        }
        player.addProperty();
        game.getMap().removeTile();
    }

}
