package diogomarcelo.battleship.logic;

public class Player
{
    // These are the lengths of all of the ships.
    private static final int[] SHIP_LENGTHS = {1, 1, 2, 2, 3, 3, 3};
    private static final int[] SHIP_WIDTHS = {0, 0, 0, 0, 0, 0, 2};
    private static final int NUM_OF_SHIPS = 7;

    public Ship[] ships;
    public Board playerGrid;
    public Board oppGrid;

    public Player()
    {
        if (NUM_OF_SHIPS != 7)
        {
            throw new IllegalArgumentException("ERROR! Num of ships must be 7");
        }

        ships = new Ship[NUM_OF_SHIPS];
        for (int i = 0; i < NUM_OF_SHIPS; i++)
        {
            Ship tempShip = new Ship(SHIP_LENGTHS[i], SHIP_WIDTHS[i]);
            ships[i] = tempShip;
        }

        playerGrid = new Board();
        oppGrid = new Board();
    }

    public void addShips()
    {
        for (Ship s: ships)
        {
            playerGrid.addShip(s);
        }
    }

    public int numOfShipsLeft()
    {
        int counter = 7;
        for (Ship s: ships)
        {
            if (s.isLocationSet() && s.isDirectionSet())
                counter--;
        }

        return counter;

    }

    public void chooseShipLocation(Ship s, int row, int col, int direction)
    {
        s.setLocation(row, col);
        s.setDirection(direction);
        playerGrid.addShip(s);
    }
}