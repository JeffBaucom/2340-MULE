package game.model;


public class Turn {
    private Player player;
    protected int time;
    Game game;

    public Turn() {

    }

    public Turn(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

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
