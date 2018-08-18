package diogomarcelo.battleship.logic;

public class Location
{
    public static final int UNGUESSED = 0;
    public static final int HIT = 1;
    public static final int MISSED = 2;

    private boolean hasShip;
    private int status;
    private int lengthOfShip;
    private int widthOfShip;
    private int directionOfShip;

    public Location()
    {
        status = 0;
        hasShip = false;
        lengthOfShip = -1;
        widthOfShip = -1;
        directionOfShip = -1;
    }

    public boolean checkHit()
    {
        if (status == HIT)
            return true;
        else
            return false;
    }

    public boolean checkMiss()
    {
        if (status == MISSED)
            return true;
        else
            return false;
    }

    public boolean isUnguessed()
    {
        if (status == UNGUESSED)
            return true;
        else
            return false;
    }

    public void markHit()
    {
        setStatus(HIT);
    }

    public void markMiss()
    {
        setStatus(MISSED);
    }

    public boolean hasShip()
    {
        return hasShip;
    }

    public void setShip(boolean val)
    {
        this.hasShip = val;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public int getStatus()
    {
        return status;
    }

    public int getLengthOfShip()
    {
        return lengthOfShip;
    }

    public int getWidthOfShip()
    {
        return widthOfShip;
    }

    public void setLengthOfShip(int val)
    {
        lengthOfShip = val;
    }

    public void setWidthOfShip(int val)
    {
        widthOfShip = val;
    }

    public int getDirectionOfShip()
    {
        return directionOfShip;
    }

    public void setDirectionOfShip(int val)
    {
        directionOfShip = val;
    }
}