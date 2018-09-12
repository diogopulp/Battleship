package diogomarcelo.battleship.logic;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import diogomarcelo.battleship.R;

public class Player extends BaseAdapter
{
    // These are the lengths of all of the ships.
    private static final int[] SHIP_LENGTHS = {1, 1, 2, 2, 3, 3, 3};
    private static final int[] SHIP_WIDTHS = {0, 0, 0, 0, 0, 0, 2};
    private static final int NUM_OF_SHIPS = 7;

    int setupNormCounter = 0;

    public Ship[] ships;
    public Board playerBoard;

    private String lastErrorMessage;

    private Context mContext;
    private Integer[] mThumbIds;
    //private Board board;
    private int screenHeight, screenWidth;

    public Player(Context c, int height, int width)
    {

        this.screenHeight = height;
        this.screenWidth = width;

        this.setLastErrorMessage(" ");

        System.out.println("Height: " + screenHeight);
        System.out.println("Width: " + screenWidth);

        playerBoard = new Board();
        mThumbIds = new Integer[64];

        for (int i=0;i<64;i++)
            mThumbIds[i] = 0;

        mContext = c;
        updateImages(this.playerBoard);

        ships = new Ship[NUM_OF_SHIPS];
        for (int i = 0; i < NUM_OF_SHIPS; i++)
        {
            Ship tempShip = new Ship(SHIP_LENGTHS[i], SHIP_WIDTHS[i]);
            ships[i] = tempShip;
        }

        playerBoard = new Board();
    }

    public void addShips()
    {
        for (Ship s: ships)
        {
            playerBoard.addShip(s);
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
        playerBoard.addShip(s);
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }

    public void setLastErrorMessage(String lastErrorMessage) {
        this.lastErrorMessage = lastErrorMessage;
    }


    public void updateImages(Board board){

        int k=0;
        for (int i=0; i< 8; i++){
            for(int j=0; j<8; j++) {

                //mThumbIds[k] = R.drawable.block;
                //mThumbIds[k] = board.getPiece(i).getImgPath();
                mThumbIds[k] = playerBoard.get(i,j).getImg();
                k++;
            }

        }

    }

    public Board getBoard(){
        return this.playerBoard;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    private float racio(){
        return this.screenHeight/this.screenWidth;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;
        updateImages(this.playerBoard);

        double width, height;

        if(screenHeight <= 900) { // Low DPI
            width = screenWidth / 9;
            height = screenHeight / 16.5;
        }else if(screenHeight > 900 && screenHeight < 1300){ // Medium DPI
            width = screenWidth / 9;
            height = screenHeight / 14.5;
        }else if(screenHeight >= 1300 && screenHeight < 1800){ // High DPI
            width = screenWidth / 9;
            height = screenHeight / 14.3;
        }else if(screenHeight >= 1800 && screenHeight < 2000){
            width = screenWidth / 9;
            height = screenHeight / 14.8;
        }else{
            width = screenWidth / 9;
            height = screenHeight / 13.5;
        }

        if (convertView == null) {


            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams((int)width, (int)height));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 5, 0, 0);

        } else {
            imageView = (ImageView) convertView;
        }

        //System.out.println(mThumbIds);
        try {
            imageView.setImageResource(mThumbIds[position]);
            //imageView.setImageResource(playerBoard.get(positionToRow(position),positionToRow(position)).getImg());
        }
        catch (Exception e) {

            Log.e("Erro ", e.getLocalizedMessage());

        }
        return imageView;
    }

    public int positionToRow(int position){

        int row = 0;

        if(position >= 0 && position <= 7)
            row = 0;
        if(position >= 8 && position <= 15)
            row = 1;
        if(position >= 16 && position <= 23)
            row = 2;
        if(position >= 24 && position <= 31)
            row = 3;
        if(position >= 32 && position <= 39)
            row = 4;
        if(position >= 40 && position <= 47)
            row = 5;
        if(position >= 48 && position <= 55)
            row = 6;
        if(position >= 56 && position <= 63)
            row = 7;

        return row;
    }

    public int positionToCol(int position){

        int col = 0;

        if(position == 0 || position == 8 || position == 16 || position == 24 || position == 32 || position == 40 || position == 48 || position == 56)
            col = 0;
        if(position == 1 || position == 9 || position == 17 || position == 25 || position == 33 || position == 41 || position == 49 || position == 57)
            col = 1;
        if(position == 2 || position == 10 || position == 18 || position == 26 || position == 34 || position == 42 || position == 50 || position == 58)
            col = 2;
        if(position == 3 || position == 11 || position == 19 || position == 27 || position == 35 || position == 43 || position == 51 || position == 59)
            col = 3;
        if(position == 4 || position == 12 || position == 20 || position == 28 || position == 36 || position == 44 || position == 52 || position == 60)
            col = 4;
        if(position == 5 || position == 13 || position == 21 || position == 29 || position == 37 || position == 45 || position == 53 || position == 61)
            col = 5;
        if(position == 6 || position == 14 || position == 22 || position == 30 || position == 38 || position == 46 || position == 54 || position == 62)
            col = 6;
        if(position == 7 || position == 15 || position == 23 || position == 31 || position == 39 || position == 47 || position == 55 || position == 63)
            col = 7;

        return col;

    }

    public boolean setup(int position, int dir) {

        // Boats Position

        int counter = 1;

        int row = -1;
        int col = -1;

        row = this.positionToRow(position);
        col = this.positionToCol(position);


        System.out.println("DEBUG: " + row + col + dir);

        if (col >= 0 && col <= 8 && row != -1 && dir != -1) // Check valid input
        {
            if (hasErrors(row, col, dir, this, setupNormCounter)) // Check if errors will produce (out of bounds)
            {
                return false;
            }
        }


        //System.out.println("FURTHER DEBUG: row = " + row + "; col = " + col);
        this.ships[setupNormCounter].setLocation(row, col);
        this.ships[setupNormCounter].setDirection(dir);
        this.playerBoard.addShip(this.ships[setupNormCounter]);
        System.out.println();
        System.out.println("You have " + this.numOfShipsLeft() + " remaining ships to place.");

        setupNormCounter++;
        counter++;
        return true;
    }

    public int getCurrentShipSize(){
        return ships[setupNormCounter].getLength()+ships[setupNormCounter].getWidth();
    }

    private boolean hasErrors(int row, int col, int dir, Player p, int count)
    {
        //System.out.println("DEBUG: count arg is " + count);

        int length = p.ships[count].getLength();

        // Check if off grid - Horizontal
        if (dir == 0)
        {
            int checker = length + col;
            //System.out.println("DEBUG: checker is " + checker);
            if (checker > 8)
            {
                setLastErrorMessage(mContext.getResources().getString(R.string.notfit_msg));
                return true;
            }
        }

        // Check if off grid - Vertical
        if (dir == 1) // VERTICAL
        {
            int checker = length + row;
            //System.out.println("DEBUG: checker is " + checker);
            if (checker > 8)
            {
                setLastErrorMessage(mContext.getResources().getString(R.string.notfit_msg));
                return true;
            }
        }

        // Check if overlapping with another ship
        if (dir == 0) // Hortizontal
        {
            // For each location a ship occupies, check if ship is already there
            for (int i = col; i < col+length; i++)
            {
                //System.out.println("DEBUG: row = " + row + "; col = " + i);
                if(p.playerBoard.hasShip(row, i))
                {
                    setLastErrorMessage(mContext.getResources().getString(R.string.alreadyshipatlocation_msg));
                    return true;
                }

                // -------- Near Ships --------

                // ACIMA
                if(row != 0){
                    if(p.playerBoard.hasShip(row-1, i))
                    {
                        setLastErrorMessage(mContext.getResources().getString(R.string.alreadyshipnear_msg));
                        return true;
                    }
                }

                // ABAIXO
                if(row != 7){
                    if(p.playerBoard.hasShip(row+1, i))
                    {
                        setLastErrorMessage(mContext.getResources().getString(R.string.alreadyshipnear_msg));
                        return true;
                    }
                }

                // ESQUERDA
                if(i != 0){
                    if(p.playerBoard.hasShip(row, i-1))
                    {
                        setLastErrorMessage(mContext.getResources().getString(R.string.alreadyshipnear_msg));
                        return true;
                    }
                }

                // DIREITA
                if(i != 7){
                    if(p.playerBoard.hasShip(row, i+1))
                    {
                        setLastErrorMessage(mContext.getResources().getString(R.string.alreadyshipnear_msg));
                        return true;
                    }
                }

               /* // ACIMA ESQUERDA
                if(row != 0 && i !=0){
                    if(p.playerBoard.hasShip(row-1, i-1))
                    {
                        setLastErrorMessage(mContext.getResources().getString(R.string.alreadyshipnear_msg));
                        return true;
                    }
                }

                // ACIMA DIREITA
                if(row != 0 && i !=7){
                    if(p.playerBoard.hasShip(row-1, i+1))
                    {
                        setLastErrorMessage(mContext.getResources().getString(R.string.alreadyshipnear_msg));
                        return true;
                    }
                }

                // ABAIXO ESQUERDA
                if(row != 7 && i !=0){
                    if(p.playerBoard.hasShip(row+1, i-1))
                    {
                        setLastErrorMessage(mContext.getResources().getString(R.string.alreadyshipnear_msg));
                        return true;
                    }
                }

                // ABAIXO DIREITA
                if(row != 7 && i !=7){
                    if(p.playerBoard.hasShip(row+1, i+1))
                    {
                        setLastErrorMessage(mContext.getResources().getString(R.string.alreadyshipnear_msg));
                        return true;
                    }
                }*/
            }
        }
        else if (dir == 1) // Vertical
        {
            // For each location a ship occupies, check if ship is already there
            for (int i = row; i < row+length; i++)
            {
                //System.out.println("DEBUG: row = " + row + "; col = " + i);
                if(p.playerBoard.hasShip(i, col))
                {
                    setLastErrorMessage(mContext.getResources().getString(R.string.alreadyshipatlocation_msg));
                    return true;
                }


                // DIREITA
                if(col != 0) {
                    if (p.playerBoard.hasShip(i, col - 1)) {
                        setLastErrorMessage(mContext.getResources().getString(R.string.alreadyshipnear_msg));
                        return true;
                    }
                }

                // ESQUERDA
                if(col != 7) {
                    if (p.playerBoard.hasShip(i, col + 1)) {
                        setLastErrorMessage(mContext.getResources().getString(R.string.alreadyshipnear_msg));
                        return true;
                    }
                }

                // ABAIXO
                if(i != 0) {
                    if (p.playerBoard.hasShip(i-1, col)) {
                        setLastErrorMessage(mContext.getResources().getString(R.string.alreadyshipnear_msg));
                        return true;
                    }
                }

                // ACIMA
                if(i != 7) {
                    if (p.playerBoard.hasShip(i+1, col)) {
                        setLastErrorMessage(mContext.getResources().getString(R.string.alreadyshipnear_msg));
                        return true;
                    }
                }

                // ABAIXO DIREITA
                /*if(col != 0 && i !=0){
                    if(p.playerBoard.hasShip(col-1, i-1))
                    {
                        setLastErrorMessage(mContext.getResources().getString(R.string.alreadyshipnear_msg));
                        return true;
                    }
                }

                // ACIMA DIREITA
                if(col != 0 && i !=7){
                    if(p.playerBoard.hasShip(col-1, i+1))
                    {
                        setLastErrorMessage(mContext.getResources().getString(R.string.alreadyshipnear_msg));
                        return true;
                    }
                }

                // ABAIXO ESQUERDA
                if(col != 7 && i !=0){
                    if(p.playerBoard.hasShip(col+1, i-1))
                    {
                        setLastErrorMessage(mContext.getResources().getString(R.string.alreadyshipnear_msg));
                        return true;
                    }
                }

                // ACIMA ESQUERDA
                if(col != 7 && i !=7){
                    if(p.playerBoard.hasShip(col+1, i+1))
                    {
                        setLastErrorMessage(mContext.getResources().getString(R.string.alreadyshipnear_msg));
                        return true;
                    }
                }*/
            }
        }

        return false;
    }

}