package diogomarcelo.battleship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    TextView textView;

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



    }

}
