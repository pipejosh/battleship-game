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

        private final int size;

        ShipType(int size) 
        {
            this.size = size;
        }

        public int getSize() 
        {
            return size;
        }
    }

    public Ship(ShipType type) 
    {
        this.shipType = type;
        this.shipSize = type.getSize();
        this.shipHealth = shipSize; 
        this.isDestroyed = false;
    }

    public int getShipSize() 
    {
        return shipSize;
    }

    public int getShipHealth() 
    {
        return shipHealth;
    }

    public boolean getIsDestroyed() 
    {
        if (shipHealth == 0)
        {
            isDestroyed = true;
        }

        return isDestroyed;
    }

    public void hit() 
    {
        if (shipHealth > 0) 
        {
            shipHealth--;
            if (shipHealth == 0) 
            {
                isDestroyed = true; 
            }
        } 
        else 
        {
            System.out.println("SHIP IS ALREADY DISTROYED");
        }
    }

    public ShipType getShipType() 
    {
        return shipType;
    }

    public String getShipName()
    {
        return shipType.toString();
    }
}
