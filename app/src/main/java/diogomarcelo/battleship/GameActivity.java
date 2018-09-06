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
    ImageAdapter adapter;
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
        adapter = new ImageAdapter(this, height, width);
        gridView.setAdapter(adapter);

        p1 = new Player();
        p2 = new Player();

        gridView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int currentXPos = (int) event.getX();
                int currentYPos = (int) event.getY();

                position = gridView.pointToPosition(currentXPos, currentYPos); // Converte coordenadas para a posição da Gridview

                Toast.makeText(GameActivity.this, "Position: " + position, Toast.LENGTH_SHORT).show(); // TODO Adicionar à biblioteca de strings

                return event.getAction() == MotionEvent.ACTION_MOVE;

            }


        });

        // Human VS CPU
        if (game_type.equalsIgnoreCase("SINGLE_PLAYER")){

            setup(p1);

        }
        // Human VS Human
        else{

        }




    }

    public void setup(Player p){

        // Boats Position

        System.out.println();
        int counter = 1;
        int normCounter = 0;
        while (p.numOfShipsLeft() > 0)
        {
            for (Ship s: p.ships)
            {
                System.out.println("\nShip #" + counter + ": Length-" + s.getLength());
                int row = -1;
                int col = -1;
                int dir = -1;
                while(true)
                {
                    System.out.print("Type in row (A-J): ");
                    //String userInputRow = reader.next();
                    //userInputRow = userInputRow.toUpperCase();
                    //row = convertLetterToInt(userInputRow);

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
                    if(position >= 56 && position <= 7)
                        row = 7;


                    //System.out.print("Type in column (1-10): ");
                    //col = reader.nextInt();
                    //col = convertUserColToProCol(col);

                    if(position == 0 || position%8 ==1)
                        col = 0;


                    System.out.print("Type in direction (0-H, 1-V): ");
                    dir = reader.nextInt();

                    //System.out.println("DEBUG: " + row + col + dir);

                    if (col >= 0 && col <= 9 && row != -1 && dir != -1) // Check valid input
                    {
                        if (!hasErrors(row, col, dir, p, normCounter)) // Check if errors will produce (out of bounds)
                        {
                            break;
                        }
                    }

                    System.out.println("Invalid location!");
                }

                //System.out.println("FURTHER DEBUG: row = " + row + "; col = " + col);
                p.ships[normCounter].setLocation(row, col);
                p.ships[normCounter].setDirection(dir);
                p.playerBoard.addShip(p.ships[normCounter]);
                System.out.println();
                System.out.println("You have " + p.numOfShipsLeft() + " remaining ships to place.");

                normCounter++;
                counter++;
            }
        }

    }

    public void vibrate() {

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(20);

    }

}
