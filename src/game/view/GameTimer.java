//package game.view;
//
//import game.Main;
//import game.model.Game;
//import javafx.application.Platform;
//import java.util.Date;
//import java.util.Timer;
//import java.util.TimerTask;
//
///**
// * Created by jlesniak on 9/29/15.
// */
//public class GameTimer extends Controller {
//    Game game;
//
//    Timer timer;
//    int timeLeft;
//
//    public GameTimer() {
//        timer = new Timer();
//        game = main.getGame();
//    }
//
//    public void getTimerTask() {
//        int round = game.getRoundCounter();
//        int food = game.getCurrentPlayer().getFood();
//        if (round < 4) {
//            if (food == 0) {
//                timeLeft = 5;
//                timer.schedule(new TurnEnder(), 5*1000);
//                timer.schedule(new ClockUpdater(), new Date(), 1000);
//            } else if (food >= 3) {
//                timeLeft = 50;
//                timer.schedule(new TurnEnder(), 50*1000);
//                timer.schedule(new ClockUpdater(), new Date(), 1000);
//            } else {
//                timeLeft = 30;
//                timer.schedule(new TurnEnder(), 30*1000);
//                timer.schedule(new ClockUpdater(), new Date(), 1000);
//            }
//        } else if (round > 7) {
//            if (food == 0) {
//                timeLeft = 5;
//                timer.schedule(new TurnEnder(), 5*1000);
//                timer.schedule(new ClockUpdater(), new Date(), 1000);
//            } else if (food >= 5) {
//                timeLeft = 50;
//                timer.schedule(new TurnEnder(), 50*1000);
//                timer.schedule(new ClockUpdater(), new Date(), 1000);
//            } else {
//                timeLeft = 30;
//                timer.schedule(new TurnEnder(), 30*1000);
//                timer.schedule(new ClockUpdater(), new Date(), 1000);
//            }
//        } else {
//            if (food == 0) {
//                timeLeft = 5;
//                timer.schedule(new TurnEnder(), 5*1000);
//                timer.schedule(new ClockUpdater(), new Date(), 1000);
//            } else if (food >= 4) {
//                timeLeft = 50;
//                timer.schedule(new TurnEnder(), 50*1000);
//                timer.schedule(new ClockUpdater(), new Date(), 1000);
//            } else {
//                timeLeft = 30;
//                timer.schedule(new TurnEnder(), 30*1000);
//                timer.schedule(new ClockUpdater(), new Date(), 1000);
//            }
//        }
//    }
//
//    private class TurnEnder extends TimerTask {
//        public void run() {
//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
//                     = true;
//                    game.endTurn();
//                    handleNext();
//                    timer.cancel();
//                    timer = new Timer();
//                    getTimerTask();
//                }
//            });
//        }
//    }
//
//    private class ClockUpdater extends TimerTask {
//        public void run() {
//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
//                    clock.setText("Time Remaining in Turn: " + timeLeft);
//                    timeLeft--;
//                }
//            });
//        }
//    }
//}
