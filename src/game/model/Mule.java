package game.model;

/**
 * Represents a MULE a player has bought
 *
 */
public class Mule {
	private String muleType;
	private Tile tile;
	private Player owner;


	public Mule() {
	}

	public Mule(Player player) {
		this.owner = player;
	}

	public Mule(String type, Player player) {
		this.muleType = type;
		this.owner = player;
	}

	/**
     * sets the type of the mule
     * @param the type of the mule
     */
    public void setType(String type) {
        this.muleType = type;
    }

	/**
	* @return MULE type
	*/
	public String getType() {
		return muleType;
	}

	/**
	* sets the location of the mule
	* @param loc tile to place the mule
	*/
	public void setLocation(Tile loc) {
		this.tile = loc;
	}

	/**
	* @return tile the location of the mule
	*/
	public Tile getLocation() {
		return tile;
	}

	/**
     * sets the owner of the mule
     * @param the owner of the mule
     */
    public void setOwner(Player player) {
	    this.owner = player;
    }


}
