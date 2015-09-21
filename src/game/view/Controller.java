package game.view;

import game.Main;

public class Controller {
    public Main main;

    public Controller() {
        main = Main.getInstance();
        System.out.println(main);
    }
}
