package diogomarcelo.battleship;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import diogomarcelo.battleship.logic.Player;

public class GameActivity extends AppCompatActivity {

    Player p1, p2;

    TextView textView, firstShipTV, secondShipTV, thirdShipTV, fourthShipTV, fivethShipTV, sixethShipTV, seventhShipTV, arrow, p1TV, p2TV;
    DisplayMetrics displayMetrics;
    GridView gridViewP1, gridViewP2;
    Button nextButton;
    int height, width;
    int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

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

        p1TV = findViewById(R.id.p1_tv);
        p2TV = findViewById(R.id.p2_tv);

        arrow = findViewById(R.id.arrow);

        nextButton.setVisibility(View.GONE);
        p2TV.setVisibility(View.GONE);

        Intent intent= getIntent();
        Bundle b = intent.getExtras();
        String game_type;

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

        //Toast.makeText(GameActivity.this, "Place the " + p1.getCurrentShipSize() + " piece size ship."  , Toast.LENGTH_LONG).show();

        gridViewP1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int currentXPos = (int) event.getX();
                int currentYPos = (int) event.getY();

                position = gridViewP1.pointToPosition(currentXPos, currentYPos); // Converte coordenadas para a posição da Gridview

                //Toast.makeText(GameActivity.this, "Position: " + position, Toast.LENGTH_SHORT).show(); // TODO Adicionar à biblioteca de strings

                //int shipsize;

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    if(p1.numOfShipsLeft()>0){
                        p1.setup(position);
                        p1.notifyDataSetChanged();
                    }

                if(p1.numOfShipsLeft()==0){
                    nextButton.setVisibility(View.VISIBLE);
                }

                if(p1.numOfShipsLeft()!=-1){

                    switch (p1.numOfShipsLeft()){
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

                    //Toast.makeText(GameActivity.this, "Place the " + p1.getCurrentShipSize() + " pieces ship."  , Toast.LENGTH_SHORT).show();
                }


                return event.getAction() == MotionEvent.ACTION_MOVE;

            }

        });

        gridViewP2.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int currentXPos = (int) event.getX();
                int currentYPos = (int) event.getY();

                position = gridViewP2.pointToPosition(currentXPos, currentYPos); // Converte coordenadas para a posição da Gridview

                //Toast.makeText(GameActivity.this, "Position: " + position, Toast.LENGTH_SHORT).show(); // TODO Adicionar à biblioteca de strings

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    if(p2.numOfShipsLeft()>0){
                        p2.setup(position);
                        p2.notifyDataSetChanged();
                    }

                if(p2.numOfShipsLeft()==0){
                    //nextButton.setVisibility(View.VISIBLE);
                    gridViewP2.setVisibility(View.GONE);
                    gridViewP1.setVisibility(View.VISIBLE);

                }

                if(p2.numOfShipsLeft()!=-1){

                    switch (p2.numOfShipsLeft()){
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

                    //Toast.makeText(GameActivity.this, "Place the " + p1.getCurrentShipSize() + " pieces ship."  , Toast.LENGTH_SHORT).show();
                }

                return event.getAction() == MotionEvent.ACTION_MOVE;

            }

        });


        // Human VS CPU
        if (game_type.equalsIgnoreCase("SINGLE_PLAYER")){


        }
        // Human VS Human
        else{

        }


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gridViewP1.setVisibility(View.GONE);
                nextButton.setVisibility(View.GONE);
                changePlayer();
                gridViewP2.setVisibility(View.VISIBLE);

                if(p2.numOfShipsLeft()!=-1) {
                    firstShipTV.setVisibility(View.VISIBLE);
                    secondShipTV.setVisibility(View.VISIBLE);
                    thirdShipTV.setVisibility(View.VISIBLE);
                    fourthShipTV.setVisibility(View.VISIBLE);
                    fivethShipTV.setVisibility(View.VISIBLE);
                    sixethShipTV.setVisibility(View.VISIBLE);
                    seventhShipTV.setVisibility(View.VISIBLE);
                    arrow.setVisibility(View.VISIBLE);
                }

            }
        });




    }

    public void changePlayer(){
        if (p1TV.getVisibility() == View.VISIBLE) {
            p1TV.setVisibility(View.GONE);
            p2TV.setVisibility(View.VISIBLE);
        }else if(p2TV.getVisibility() == View.VISIBLE){
            p2TV.setVisibility(View.GONE);
            p1TV.setVisibility(View.VISIBLE);
        }
    }

    public void vibrate() {

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(20);

    }

}
