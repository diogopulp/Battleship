package diogomarcelo.battleship.logic;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class Player extends BaseAdapter
{
    // These are the lengths of all of the ships.
    private static final int[] SHIP_LENGTHS = {1, 1, 2, 2, 3, 3, 3};
    private static final int[] SHIP_WIDTHS = {0, 0, 0, 0, 0, 0, 2};
    private static final int NUM_OF_SHIPS = 7;

    public Ship[] ships;
    public Board playerBoard;

    private Context mContext;
    private Integer[] mThumbIds;
    private Board board;
    private int screenHeight, screenWidth;

    public Player(Context c, int height, int width)
    {

        this.screenHeight = height;
        this.screenWidth = width;

        System.out.println("Height: " + screenHeight);
        System.out.println("Width: " + screenWidth);

        board = new Board();
        mThumbIds = new Integer[64];

        for (int i=0;i<64;i++)
            mThumbIds[i] = 0;


        mContext = c;
        updateImages(this.board);

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


    public void updateImages(Board board){

        int k=0;
        for (int i=0; i< 64; i++){

            //mThumbIds[k] = R.drawable.block;
            //mThumbIds[k] = board.getPiece(i).getImgPath();
            k++;

        }

    }

    public Board getBoard(){
        return this.board;
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
        updateImages(this.board);

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
        }
        catch (Exception e) {

            Log.e("Erro ", e.getLocalizedMessage());

        }
        return imageView;
    }
}