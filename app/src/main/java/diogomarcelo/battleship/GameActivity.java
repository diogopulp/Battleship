package diogomarcelo.battleship;

import android.animation.TimeAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import diogomarcelo.battleship.logic.Player;
import diogomarcelo.battleship.logic.Ship;

public class GameActivity extends AppCompatActivity {

    Player p1, p2;

    TextView textView;
    DisplayMetrics displayMetrics;
    GridView gridView;
    int height, width;
    int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textView = findViewById(R.id.tvTest);

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

        gridView = findViewById(R.id.playBoard);

        p1 = new Player(this,height,width);
        //p2 = new Player();

        gridView.setAdapter(p1);

        gridView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int currentXPos = (int) event.getX();
                int currentYPos = (int) event.getY();

                position = gridView.pointToPosition(currentXPos, currentYPos); // Converte coordenadas para a posição da Gridview

                Toast.makeText(GameActivity.this, "Position: " + position, Toast.LENGTH_SHORT).show(); // TODO Adicionar à biblioteca de strings

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    if(p1.numOfShipsLeft()>0){
                        p1.setup(position);
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




    }

    public void vibrate() {

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(20);

    }

}
