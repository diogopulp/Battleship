package diogomarcelo.battleship;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import diogomarcelo.battleship.logic.Player;

public class GameActivity extends AppCompatActivity {

    Player p1, p2;

    TextView textView, firstShipTV, secondShipTV, thirdShipTV, fourthShipTV, fivethShipTV, sixethShipTV, seventhShipTV, arrow, p1TV, p2TV, phaseTitle;
    DisplayMetrics displayMetrics;
    GridView gridViewP1, gridViewP2;
    Button nextButton;
    ImageButton rotateShipBtn;
    int height, width;
    int position = -1;

    int orientation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        orientation = 0;

        //FOR DEBUG ONLY
        //textView = findViewById(R.id.tvTest);
        nextButton = findViewById(R.id.btn_next);

        firstShipTV = findViewById(R.id.firstship);
        secondShipTV = findViewById(R.id.secondship);
        thirdShipTV = findViewById(R.id.thirdship);
        fourthShipTV = findViewById(R.id.fourthship);
        fivethShipTV = findViewById(R.id.fivethship);
        sixethShipTV = findViewById(R.id.sixethship);
        seventhShipTV = findViewById(R.id.sevethship);

        rotateShipBtn = findViewById(R.id.rotate_btn);

        p1TV = findViewById(R.id.p1_tv);
        p2TV = findViewById(R.id.p2_tv);

        arrow = findViewById(R.id.arrow);

        phaseTitle = findViewById(R.id.phaseTitle_tv);

        nextButton.setVisibility(View.GONE);
        p2TV.setVisibility(View.GONE);

        Intent intent= getIntent();
        Bundle b = intent.getExtras();
        final String game_type;

        // Get Game Type
        game_type =(String) b.get("GAME_TYPE");
        //textView.setText(game_type);


        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        gridViewP1 = findViewById(R.id.playBoard1);
        gridViewP2 = findViewById(R.id.playBoard2);
        gridViewP2.setVisibility(View.GONE);

        p1 = new Player(this,height,width);
        p2 = new Player(this,height,width);

        gridViewP1.setAdapter(p1);
        gridViewP2.setAdapter(p2);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gridViewP1.setVisibility(View.GONE);
                nextButton.setVisibility(View.GONE);
                gridViewP2.setVisibility(View.VISIBLE);
                gameStart(game_type);

            }
        });

        // Game Setup Phase

        rotateShipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOrientation();
            }
        });

            gridViewP1.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    int currentXPos = (int) event.getX();
                    int currentYPos = (int) event.getY();

                    position = gridViewP1.pointToPosition(currentXPos, currentYPos);

                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        if (p1.numOfShipsLeft() > 0) {
                            if (p1.setup(position, orientation)) {
                            } else {
                                Toast.makeText(GameActivity.this, p1.getLastErrorMessage(), Toast.LENGTH_SHORT).show();
                            }
                            p1.notifyDataSetChanged();
                        }
                    }

                    if (p1.numOfShipsLeft() == 0) {
                        rotateShipBtn.setVisibility(View.GONE);
                        nextButton.setVisibility(View.VISIBLE);
                    }

                    if (p1.numOfShipsLeft() != -1) {

                        switch (p1.numOfShipsLeft()) {
                            case 6:
                                firstShipTV.setVisibility(View.GONE);
                                break;
                            case 5:
                                secondShipTV.setVisibility(View.GONE);
                                break;
                            case 4:
                                thirdShipTV.setVisibility(View.GONE);
                                break;
                            case 3:
                                fourthShipTV.setVisibility(View.GONE);
                                break;
                            case 2:
                                fivethShipTV.setVisibility(View.GONE);
                                break;
                            case 1:
                                sixethShipTV.setVisibility(View.GONE);
                                break;
                            case 0:
                                seventhShipTV.setVisibility(View.GONE);
                                arrow.setVisibility(View.GONE);
                                break;
                            default:
                                break;
                        }

                    }

                    return event.getAction() == MotionEvent.ACTION_MOVE;

                }

            });
    }



    public void gameStart(String gameType){

        if(gameType.equalsIgnoreCase("SINGLE_PLAYER")) {

            // CPU Setup
            while (p2.numOfShipsLeft() != 0) {
                int numofships = p2.numOfShipsLeft();
                position = new Random().nextInt(64);
                int dir = new Random().nextInt(2);
                p2.setup(position,dir);
            }


            // Play Phase
            phaseTitle.setText(R.string.gamePhase_tv);
            p2.getBoard().hideShips();

            // Human
            gridViewP2.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    int pos;
                    int currentXPos = (int) event.getX();
                    int currentYPos = (int) event.getY();

                    pos = gridViewP2.pointToPosition(currentXPos, currentYPos);

                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        int row = p2.positionToRow(pos);
                        int col = p2.positionToCol(pos);

                        if (p2.getBoard().hasShip(row, col)) {
                            p2.getBoard().markHit(row, col);
                            p2.notifyDataSetChanged();
                            if(p2.getBoard().hasLost()){
                                Toast.makeText(GameActivity.this, R.string.p1wins_msg, Toast.LENGTH_SHORT).show();
                                finish();
                                return event.getAction() == MotionEvent.ACTION_MOVE;
                            }
                            Toast.makeText(GameActivity.this, R.string.hit_msg, Toast.LENGTH_SHORT).show();
                        } else {
                            p2.getBoard().markMiss(row,col);
                            Toast.makeText(GameActivity.this, R.string.sea_msg, Toast.LENGTH_SHORT).show();
                            // CPU
                            changePlayer();
                                boolean hit = true;
                                while (true){

                                    pos = new Random().nextInt(64);
                                    row = p1.positionToRow(pos);
                                    col = p1.positionToCol(pos);
                                    Log.i("CPU TRY","ROW: " + Integer.toString(row) + " COL: " +Integer.toString(col));
                                    if(p1.getBoard().hasShip(row,col)){
                                        p1.getBoard().markHit(row,col);
                                        if(p1.getBoard().hasLost()){
                                            Toast.makeText(GameActivity.this, R.string.cpu_msg, Toast.LENGTH_SHORT).show();
                                            finish();
                                            return event.getAction() == MotionEvent.ACTION_MOVE;
                                        }
                                        Log.i("CPU HIT","ROW: " + Integer.toString(row) + " COL: " +Integer.toString(col));
                                    }else{
                                        p1.getBoard().markMiss(row,col);
                                        changePlayer();
                                        return event.getAction() == MotionEvent.ACTION_MOVE;
                                        //hit = false;
                                    }
                                }

                        }
                    }

                            /*
                            if(p2.numOfShipsLeft()>0){
                                p2.setup(pos);
                                p2.notifyDataSetChanged();
                            }*/

                    return event.getAction() == MotionEvent.ACTION_MOVE;

                }

            });
        }else if(gameType.equalsIgnoreCase("MULTI_PLAYER")){

        }


    }

    public void changePlayer(){
        if (p1TV.getVisibility() == View.VISIBLE) {
            p1TV.setVisibility(View.GONE);
            p2TV.setVisibility(View.VISIBLE);
            gridViewP2.setVisibility(View.GONE);
            gridViewP1.setVisibility(View.VISIBLE);
        }else if(p2TV.getVisibility() == View.VISIBLE){
            p2TV.setVisibility(View.GONE);
            p1TV.setVisibility(View.VISIBLE);
            gridViewP1.setVisibility(View.GONE);
            gridViewP2.setVisibility(View.VISIBLE);

        }
    }

    public void setOrientation() {
        if(orientation == 0){
            orientation = 1;
        }else if(orientation == 1){
            orientation = 0;
        }
    }


    public void vibrate() {

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(20);

    }

}
