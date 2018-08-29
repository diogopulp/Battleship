package diogomarcelo.battleship;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import diogomarcelo.battleship.logic.Board;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private Integer[] mThumbIds;
    private Board board;
    private int screenHeight, screenWidth;



    public ImageAdapter(Context c, int height, int width) {

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

    }

    public void updateImages(Board board){

        int k=0;
        for (int i=0; i< 64; i++){

            mThumbIds[k] = k;
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
