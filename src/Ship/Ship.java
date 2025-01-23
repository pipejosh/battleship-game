package Ship;


public class Ship {

    private final int shipSize;
    private int shipHealth;
    private boolean isDestroyed;
    private final ShipType shipType;

    public enum ShipType 
    {
        CARRIER(5), 
        BATTLESHIP(4), 
        CRUISER(3), 
        SUBMARINE(3), 
        DESTROYER(2),
        BOAT(1);
//the five types of ships to be placed
        private final int size;

        ShipType(int size) 
        {
            this.size = size;
        }
//gets the ship size 
        public int getSize() 
        {
            return size;
        }
    }
//ship constructor
    public Ship(ShipType type) 
    {
        this.shipType = type;
        this.shipSize = type.getSize();
        this.shipHealth = shipSize; 
        this.isDestroyed = false;
    }
//Gets the ship size 
    public int getShipSize() 
    {
        return shipSize;
    }
//Gets the ship health
    public int getShipHealth() 
    {
        return shipHealth;
    }
//Checks if the ship is destroyed and changes it to true
    public boolean getIsDestroyed() 
    {
        if (shipHealth == 0)
        {
            isDestroyed = true;
        }

        return isDestroyed;
    }
//gets the ship type
    public ShipType getShipType() 
    {
        return shipType;
    }
//Gets the ship name from the ship type
    public String getShipName()
    {
        return shipType.toString();
    }
}