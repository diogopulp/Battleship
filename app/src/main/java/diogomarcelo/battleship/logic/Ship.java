package diogomarcelo.battleship.logic;

public class Ship {

    private int row;
    private int col;
    private int length;
    private int width;
    private int direction;

    public static final int UNSET = -1;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    public Ship(int length, int width)
    {
        this.length = length;
        this.width = width;
        this.row = -1;
        this.col = -1;
        this.direction = UNSET;
    }

    public boolean isLocationSet()
    {
        if (row == -1 || col == -1)
            return false;
        else
            return true;
    }

    public boolean isDirectionSet()
    {
        if (direction == UNSET)
            return false;
        else
            return true;
    }

    public void setLocation(int row, int col)
    {
        this.row = row;
        this.col = col;
    }

    public void setDirection(int direction)
    {
        if (direction != UNSET && direction != HORIZONTAL && direction != VERTICAL)
            throw new IllegalArgumentException("Invalid direction. It must be -1, 0, or 1");
        this.direction = direction;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public int getLength()
    {
        return length;
    }

    public int getWidth()
    {
        return width;
    }

    public int getDirection()
    {
        return direction;
    }

}
