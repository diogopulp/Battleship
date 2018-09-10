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

    TextView textView;
    DisplayMetrics displayMetrics;
    GridView gridViewP1, gridViewP2;
    Button nextButton;
    int height, width;
    int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textView = findViewById(R.id.tvTest);
        nextButton = findViewById(R.id.btn_next);

        nextButton.setVisibility(View.GONE);

        Intent intent= getIntent();
        Bundle b = intent.getExtras();
        String game_type;

        // Get Game Type
        game_type =(String) b.get("GAME_TYPE");
        textView.setText(game_type);


        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;

        gridViewP1 = findViewById(R.id.playBoard);

        p1 = new Player(this,height,width);
        //p2 = new Player();

        gridViewP1.setAdapter(p1);

        gridViewP1.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int currentXPos = (int) event.getX();
                int currentYPos = (int) event.getY();

                position = gridViewP1.pointToPosition(currentXPos, currentYPos); // Converte coordenadas para a posição da Gridview

                Toast.makeText(GameActivity.this, "Position: " + position, Toast.LENGTH_SHORT).show(); // TODO Adicionar à biblioteca de strings

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    if(p1.numOfShipsLeft()>0){
                        p1.setup(position);
                        p1.notifyDataSetChanged();
                    }

                if(p1.numOfShipsLeft()==0){
                    nextButton.setVisibility(View.VISIBLE);
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
            }
        });




    }

    public void vibrate() {

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(20);

    }

}
