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
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    TextView textView;
    ImageAdapter adapter;
    DisplayMetrics displayMetrics;
    int height, width;
    int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textView = findViewById(R.id.tvTest);

        Intent intent= getIntent();
        Bundle b = intent.getExtras();

        if(b!=null)
        {
            String game_type =(String) b.get("GAME_TYPE");
            textView.setText(game_type);
        }

        displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;


        final GridView gridView = findViewById(R.id.playBoard);
        adapter = new ImageAdapter(this, height, width);
        gridView.setAdapter(adapter);

        

        gridView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int currentXPos = (int) event.getX();
                int currentYPos = (int) event.getY();

                position = gridView.pointToPosition(currentXPos, currentYPos); // Converte coordenadas para a posição da Gridview

                Log.e("Position: ", Integer.toString(position) );

                //if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    //vibrate();
                    //if (adapter.getBoard().movePiece(position) == -2)

                      //  Toast.makeText(GameActivity.this, "Movimento inválido!", Toast.LENGTH_SHORT).show(); // TODO Adicionar à biblioteca de strings

                //}

                //adapter.notifyDataSetChanged();
                //changePlayer(adapter.getBoard().getPid());
                return event.getAction() == MotionEvent.ACTION_MOVE;

            }


        });



    }

    public void vibrate() {

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(20);

    }

}
