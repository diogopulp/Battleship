package diogomarcelo.battleship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import diogomarcelo.battleship.logic.Player;

public class MainActivity extends AppCompatActivity {

    Player p1, p2;
    Button btnSinglePlayer, btnMultiPlayer, btnCredits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSinglePlayer = findViewById(R.id.btn_singleplayer);
        btnMultiPlayer = findViewById(R.id.btn_multiplayer);
        btnCredits = findViewById(R.id.btn_credits);

        btnSinglePlayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(MainActivity.this, GameActivity.class);
                myIntent.putExtra("GAME_TYPE", "SINGLE_PLAYER"); //Optional parameters
                MainActivity.this.startActivity(myIntent);
            }
        });

        btnMultiPlayer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent myIntent = new Intent(MainActivity.this, GameActivity.class);
                myIntent.putExtra("GAME_TYPE", "MULTI_PLAYER"); //Optional parameters
                MainActivity.this.startActivity(myIntent);
            }
        });

        btnCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, CreditsActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });




    }

}
